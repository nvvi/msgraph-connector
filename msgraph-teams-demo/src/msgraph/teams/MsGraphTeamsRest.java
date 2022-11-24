package msgraph.teams;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.microsoft.graph.MsGraph;

public class MsGraphTeamsRest {

  private final WebTarget restClient;
  private final String chatId;

  public MsGraphTeamsRest(String chatId) {
    this.restClient = new MsGraph().client();
    this.chatId = chatId;
  }

  public CollectionOfMessageWithCount getMessages(int first, int pageSize) {
    return restClient
      .path("me/chats/{chat-id}/messages")
      .resolveTemplate("chat-id", chatId)
      .queryParam("$count", true)
      .queryParam("$skip", first)
      .queryParam("$top", pageSize)
      .request(MediaType.APPLICATION_JSON).get()
      .readEntity(CollectionOfMessageWithCount.class);
  }

}
