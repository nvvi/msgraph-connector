package msgraph;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.microsoft.graph.MicrosoftGraphMessage;

import msgraph.wrappers.MsGraphRest;

public class MailDataModel extends LazyDataModel<MicrosoftGraphMessage> {

  @Override
  public Object getRowKey(MicrosoftGraphMessage mail) {
    return mail.getId();
  }

  @Override
  public List<MicrosoftGraphMessage> load(int first, int pageSize, String sortField, SortOrder sortOrder,
          Map<String, Object> filters) {
    var restClient = new MsGraphRest().first(first).pageSize(pageSize);
    var messages = restClient.getMessages();

    if (messages.get_count() == null) {
      setRowCount(messages.getValue().size());
    } else {
      setRowCount(messages.get_count());
    }
    return messages.getValue();
  }

}
