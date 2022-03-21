package msgraph.wrappers;

import java.util.UUID;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import ch.ivyteam.ivy.environment.Ivy;
import msgraph.models.CollectionOfMessageWithCount;

public class MsGraphRest {
  private final WebTarget restClient;
  private int skipParam;
  private int topParam;

  public MsGraphRest() {
    restClient = Ivy.rest().client(UUID.fromString("007036dc-72d1-429f-88a7-ba5d5cf5ae58"));
  }

  public MsGraphRest pageSize(int size) {
    this.topParam = size;
    return this;
  }

  public MsGraphRest first(int first) {
    this.skipParam = first;
    return this;
  }

  public CollectionOfMessageWithCount getMessages() {
    CollectionOfMessageWithCount collection = restClient
            .path("/me/messages")
            .queryParam("$count", true)
            .queryParam("$skip", skipParam)
            .queryParam("$top", topParam)
            .request(MediaType.APPLICATION_JSON).get()
            .readEntity(CollectionOfMessageWithCount.class);

    return collection;
  }

}
