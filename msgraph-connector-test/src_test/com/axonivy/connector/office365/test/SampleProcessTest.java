package com.axonivy.connector.office365.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.scripting.objects.CompositeObject;
import ch.ivyteam.ivy.workflow.CaseState;

@IvyProcessTest
@Disabled
public class SampleProcessTest{
  
  private static final BpmProcess testee = BpmProcess.path("MyProcess");
  
  @Test
  public void callProcess(BpmClient bpmClient){
    BpmElement startable = testee.elementName("start.ivp");
    ExecutionResult result = bpmClient.start().process(startable).execute();
    CompositeObject data = result.data().last();
    assertThat(data).isNotNull();
  }
  
  @Test
  @Disabled("illustrative code: needs adaption to your environment")
  public void workflow(BpmClient bpmClient)
  {
    BpmElement startable = testee.elementName("start.ivp");
    
    // start as authenticated user
    String myUser = "myUser";
    ExecutionResult result = bpmClient.start().process(startable).as().user(myUser).execute();
    assertThat(result.workflow().activeCase()).isEqualTo(CaseState.RUNNING);
    assertThat(result.workflow().executedTask().activator().name()).isEqualTo(myUser);
    
    // continue after task/switch
    bpmClient.start().anyActiveTask(result).as().role("supervisor").execute();
  }
}
