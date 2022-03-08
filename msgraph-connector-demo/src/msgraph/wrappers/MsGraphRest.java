package msgraph.wrappers;

import java.util.UUID;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import ch.ivyteam.ivy.environment.Ivy;
import msgraph.models.CollectionOfMessage;

public class MsGraphRest {
  private final WebTarget restClient;
  private int skip;
  private int top;

  public MsGraphRest() {
    restClient = Ivy.rest().client(UUID.fromString("007036dc-72d1-429f-88a7-ba5d5cf5ae58"));
  }

  public MsGraphRest pageSize(int top) {
    this.top = top;
    return this;
  }

  public MsGraphRest first(int skip) {
    this.skip = skip;
    return this;
  }

  public CollectionOfMessage getMessages() {
    CollectionOfMessage collection = restClient
            .path("/me/messages")
            .queryParam("$count", true)
            .queryParam("$skip", skip)
            .queryParam("$top", top)
            .request(MediaType.APPLICATION_JSON).get()
            .readEntity(CollectionOfMessage.class);

    return collection;
  }

}
