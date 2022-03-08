package msgraph;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.microsoft.graph.MicrosoftGraphMessage;

import msgraph.wrappers.MsGraphRest;

public class MailDataModel extends LazyDataModel<MicrosoftGraphMessage> {
  private String filter;

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  @Override
  public Object getRowKey(MicrosoftGraphMessage mail) {
    return mail.getId();
  }

  @Override
  public List<MicrosoftGraphMessage> load(int first, int pageSize, String sortField, SortOrder sortOrder,
          Map<String, Object> filters) {
    var restClient = new MsGraphRest().first(first).pageSize(pageSize);
    var messages = restClient.getMessages();
    setRowCount(messages.size());
    return messages;
  }

}
