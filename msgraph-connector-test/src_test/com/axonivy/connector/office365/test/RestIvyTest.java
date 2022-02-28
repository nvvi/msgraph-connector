package com.axonivy.connector.office365.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.graph.MicrosoftGraphUser;

import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;


@IvyTest
public class RestIvyTest{

  @BeforeEach
  void beforeEach(AppFixture fixture) {
    //Disable OAuth feature for mock rest service
    fixture.config("RestClients.Microsoft 365 (Partial Graph API).Features", "ch.ivyteam.ivy.rest.client.mapper.JsonFeature");
    fixture.config("RestClients.Microsoft 365 (Partial Graph API).Url", "{ivy.app.baseurl}/api/graphMock");
    //fixture.var("Twitter-connector.Url", "{ivy.app.baseurl}/api/twitterMock");
  }

  @Test
  public void restApi() {
    var response = Ivy.rest().client(UUID.fromString("007036dc-72d1-429f-88a7-ba5d5cf5ae58"))
            .path("/me").request().get().readEntity(MicrosoftGraphUser.class);
    assertThat(response.getMail()).isEqualTo("reguel.wermelinger@mailinator.com");
  }
}
