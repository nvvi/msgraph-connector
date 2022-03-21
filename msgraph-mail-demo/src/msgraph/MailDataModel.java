package msgraph;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.microsoft.graph.MicrosoftGraphMessage;

import ch.ivyteam.ivy.jsf.primefaces.legazy.LazyDataModel7;
import msgraph.wrappers.MsGraphRest;

public class MailDataModel extends LazyDataModel7<MicrosoftGraphMessage> {

  @Override
  public String getRowKey(MicrosoftGraphMessage mail) {
    return mail.getId();
  }

  @Override
  public List<MicrosoftGraphMessage> load(int first, int pageSize, String sortField, SortOrder sortOrder,
          Map<String, Object> filters) {
    var restClient = new MsGraphRest().first(first).pageSize(pageSize);
    var messages = restClient.getMessages();

    if (messages.getCount() == null) {
      setRowCount(messages.getValue().size());
    } else {
      setRowCount(messages.getCount());
    }
    return messages.getValue();
  }

}
