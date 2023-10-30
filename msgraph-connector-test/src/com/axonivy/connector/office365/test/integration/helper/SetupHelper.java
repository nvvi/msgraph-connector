package com.axonivy.connector.office365.test.integration.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public class SetupHelper {
  private static final String APP_ID_PROP = "test.azure.app.id";
  private static final String SECRET_KEY_PROP = "test.azure.app.secretKey";
  private static final String TENANT_ID_PROP = "test.azure.app.tenantId";

  public static void setup() {
    load().entrySet().forEach(prop -> {
      Ivy.var().set(prop.getKey(), prop.getValue());
    });
  }

  public static Map<String, String> load() {
    Map<String, String> loaded = new HashMap<>();

    var appId = System.getProperty(APP_ID_PROP);
    var secretKey = System.getProperty(SECRET_KEY_PROP);
    var tenantId = System.getProperty(TENANT_ID_PROP);

    if (StringUtils.isEmpty(secretKey)) {
      try (var in = SetupHelper.class.getResourceAsStream("client.properties")) {
        if (in != null) {
          var props = new Properties();
          props.load(in);
          appId = (String) props.get(APP_ID_PROP);
          secretKey = (String) props.get(SECRET_KEY_PROP);
          tenantId = (String) props.get(TENANT_ID_PROP);
        }
      } catch (IOException ex) {
        throw new RuntimeException("Failed to load client.props", ex);
      }
    }

    loaded.put("microsoft-connector.appId", appId);
    loaded.put("microsoft-connector.secretKey", secretKey);
    loaded.put("microsoft-connector.tenantId", tenantId);
    return loaded;
  }
}
