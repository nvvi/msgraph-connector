package com.microsoft.graph;

import java.util.UUID;

import javax.ws.rs.client.WebTarget;

import ch.ivyteam.ivy.environment.Ivy;

public class MsGraph {

  private final WebTarget client;

  public MsGraph() {
    client = Ivy.rest().client(UUID.fromString("007036dc-72d1-429f-88a7-ba5d5cf5ae58"));
  }

  public WebTarget client() {
    return client;
  }

}
