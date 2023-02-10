package com.axonivy.connector.office365.test.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.connector.office365.test.integration.helper.SetupHelper;

import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class RestOauthIvyTest{

  @BeforeEach
  void beforeEach(AppFixture fixture) throws IOException {
    SetupHelper.setup();
    fixture.config("RestClients.Microsoft 365 (Partial Graph API).Properties.AUTH.useAppPermissions", "true");
    fixture.config("RestClients.Microsoft 365 (Partial Graph API).Properties.AUTH.scope", "https://graph.microsoft.com/.default");
  }

  @Test
  void callbackOauthRedirect() {
    var restClient = Ivy.rest().client(UUID.fromString("007036dc-72d1-429f-88a7-ba5d5cf5ae58"));
    var response = restClient.path("/applications").request(MediaType.APPLICATION_JSON).get();
    assertThat(response.readEntity(String.class))
            .contains("\"uri\":\"http://localhost:8081/oauth2/callback\"")
            .contains("\"publisherDomain\":\"azureivyteam.onmicrosoft.com\"");
  }

}

