def manualDeploy

pipeline {
  agent any

  options {
    buildDiscarder(logRotator(numToKeepStr: '30'))
  }

  triggers {
    cron 'H 6 * * *'
    bitbucketPush()
  }

  parameters {
    string(name: 'engineSource', defaultValue: 'https://jenkins.ivyteam.io/job/ivy-core_product/job/master/lastSuccessfulBuild/', description: 'Engine page url')
    string(name: 'deployTo', defaultValue: 'https://nightly.demo.ivyteam.io/', description: 'Deploy to host')
  }

  environment {
    imgSimilarity = 98
    dockerfileParams = '--shm-size 1g --hostname=ivy'
  }

  stages {
    stage('check') {
      agent {
        docker {
          image 'mstruebing/editorconfig-checker:2.1.0'
          reuseNode true
        }
      }
      steps {
        sh 'ec -no-color'
      }
    }

    stage('build') {
      steps {
        script {
          def deployApplicationName = env.BRANCH_NAME.replaceAll("%2F","_").replaceAll("/","_").replaceAll("\\.","_")

          def random = (new Random()).nextInt(10000000)
          def networkName = "build-" + random
          def seleniumName = "selenium-" + random
          def ivyName = "ivy-" + random
          sh "docker network create ${networkName}"
          try {
            docker.image("selenium/standalone-firefox:3").withRun("-e START_XVFB=false --shm-size=2g --name ${seleniumName} --network ${networkName}") {
              docker.build('maven').inside("--name ${ivyName} --network ${networkName}") {
                maven cmd: 'clean verify ' +
                      '-Dmaven.test.failure.ignore=true ' +
                      "-DdeployApplicationName=msgraph-connector-${deployApplicationName} " +
                      "-Dengine.page.url=${params.engineSource} " +
                      "-Dtest.engine.url=http://${ivyName}:8080 " +
                      "-Dselenide.remote=http://${seleniumName}:4444/wd/hub "

                checkVersions recordIssue: false
              }
            }
          } finally {
            sh "docker network rm ${networkName}"
          }
        }
      }
    }

    stage('deploy') {
      agent {
        dockerfile {
          reuseNode true
        }
      }
      when {
        allOf {
          branch 'master'
          expression { return currentBuild.currentResult == 'SUCCESS' }
        }
      }
      steps {
        script {
          maven cmd: 'deploy -Dmaven.test.skip=true' +
                "-Divy.deploy.engine.url=${params.deployTo} "
        }
      }
    }
  }
}
