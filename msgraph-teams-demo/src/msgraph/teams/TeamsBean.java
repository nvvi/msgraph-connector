package msgraph.teams;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TeamsBean {

  private final TeamsMessagesDataModel model;

  public TeamsBean() {
    model = new TeamsMessagesDataModel();
  }

  public TeamsMessagesDataModel getTableModel() {
    return model;
  }
}
