package msgraph;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MailsBean {
  private MailDataModel mailDataModel;

  public MailsBean() {
    mailDataModel = new MailDataModel();
  }

  public MailDataModel getMailDataModel() {
    return mailDataModel;
  }
}
