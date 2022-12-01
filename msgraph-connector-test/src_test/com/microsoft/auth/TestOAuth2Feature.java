package com.microsoft.auth;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Optional;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.rest.client.FeatureConfig;

@IvyTest
public class TestOAuth2Feature {

  @Test
  void payload_userDelegate() {
    Map<String, Object> props = Map.of(
      "AUTH.appId", "1234",
      "AUTH.secretKey", "5678"
    );
    var config = toConfig(props);
    var payload = OAuth2Feature.createTokenPayload(config, Optional.of("theToken"), Optional.empty()).asMap();

    assertThat(payload.getFirst("grant_type"))
      .isEqualTo("authorization_code");
    assertThat(payload.keySet()).containsOnly(
      "client_id", "client_secret", "scope", "code", "redirect_uri", "grant_type");
  }

  @Test
  void payload_appAuth() {
    Map<String, Object> props = Map.of(
      "AUTH.appId", "1234",
      "AUTH.secretKey", "5678",
      "AUTH.useAppPermissions", Boolean.TRUE.toString());
    var payload = toPayload(props);
    assertThat(payload.getFirst("grant_type"))
      .isEqualTo("client_credentials");
    assertThat(payload.keySet()).containsOnly(
      "client_id", "client_secret", "scope", "grant_type");
    assertThat(payload.getFirst("scope"))
      .startsWith(OAuth2Feature.Default.APP_SCOPE);
  }

  @Test
  void payload_userPass() {
    Map<String, Object> props = Map.of(
      "AUTH.appId", "1234",
      "AUTH.secretKey", "5678",
      "AUTH.userPassFlow", Boolean.TRUE.toString(),
      "AUTH.user", "rew",
      "AUTH.password", "notMySecret");
    var payload = toPayload(props);
    assertThat(payload.getFirst("grant_type"))
      .isEqualTo("password");
    assertThat(payload.keySet()).containsOnly(
      "client_id", "client_secret", "scope", "username", "password", "grant_type");
  }

  private static MultivaluedMap<String, String> toPayload(Map<String, Object> props) {
    var config = toConfig(props);
    return toPayload(config);
  }

  private static FeatureConfig toConfig(Map<String, Object> props) {
    return new FeatureConfig(p -> props.get(p), TestOAuth2Feature.class);
  }

  private static MultivaluedMap<String, String> toPayload(FeatureConfig config) {
    var form = OAuth2Feature.createTokenPayload(config, Optional.empty(), Optional.empty());
    return form.asMap();
  }

}
