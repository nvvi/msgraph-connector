package msgraph.teams;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.microsoft.graph.MicrosoftGraphChatMessage;

public class TeamsMessagesDataModel extends LazyDataModel<MicrosoftGraphChatMessage> {

  public String chatId = "mock";

  @Override
  public String getRowKey(MicrosoftGraphChatMessage msg) {
    return msg.getId();
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return 0;
  }

  @Override
  public List<MicrosoftGraphChatMessage> load(int first, int pageSize,
      Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
    var messages = new MsGraphTeamsRest(chatId).getMessages(first, pageSize);
    if (messages.getCount() == null) {
      setRowCount(messages.getValue().size());
    } else {
      setRowCount(messages.getCount());
    }
    return messages.getValue();
  }

}
