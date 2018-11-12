package ca.uvic.seng330.assn3.models;

import java.util.UUID;

import org.json.JSONObject;

public interface Client {
  public void notify(JSONObject json);

  public UUID getIdentifier();
}
