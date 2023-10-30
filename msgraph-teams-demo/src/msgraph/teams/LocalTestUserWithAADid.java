package msgraph.teams;

import org.apache.commons.lang3.reflect.MethodUtils;

import ch.ivyteam.ivy.security.ISecurityContext;

public class LocalTestUserWithAADid {

  public static void assign(String username, String azureID) throws Exception {
    var usr = ISecurityContext.current().users().find(username);
    if (usr != null && usr.getExternalId() == null) {
      MethodUtils.invokeExactMethod(usr, "setExternalId", azureID);
    }
  }

}
