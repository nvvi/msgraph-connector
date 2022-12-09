package msgraph.teams;

import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.internal.User;

public class LocalTestUserWithAADid {

  public static void assign(String username, String azureID) {
    var usr = (User) ISecurityContext.current().users().find(username);
    if (usr != null && usr.getExternalId() == null) {
      usr.setExternalId(azureID);
    }
  }

}
