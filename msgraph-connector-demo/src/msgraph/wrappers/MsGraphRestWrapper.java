package msgraph.wrappers;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.microsoft.graph.CollectionOfMessage;
import com.microsoft.graph.MicrosoftGraphMessage;

import ch.ivyteam.ivy.environment.Ivy;

public class MsGraphRestWrapper {
  private final WebTarget restClient;

  public MsGraphRestWrapper() {
    restClient = Ivy.rest().client(UUID.fromString("007036dc-72d1-429f-88a7-ba5d5cf5ae58"));
  }

  public MsGraphRestWrapper pageSize(int top)
  {
    restClient.queryParam("top", top);
    return this;
  }

  public MsGraphRestWrapper first(int skip)
  {
    restClient.queryParam("skip", skip);
    return this;
  }

  public List<MicrosoftGraphMessage> getMessages() {
    CollectionOfMessage collection = restClient
            .path("/me/messages")
            .request(MediaType.APPLICATION_JSON).get()
            .readEntity(CollectionOfMessage.class);
    return collection.getValue();
  }

}
