package ca.uvic.seng330.assn3.models;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;

public class Client {
  private HashMap<String, String> users;
  private final UUID uuid;

  public Client(Hub h) throws HubRegistrationException {
    h.register(this);
    uuid = UUID.randomUUID();
  }

  /*
   * Notifies the client of an alert from a device via a Mediator
   * @param JSONObject json - JSON data from the Mediator
   */
  public void notify(JSONObject json) {
    System.out.println(json.toString());
  }

  public UUID getIdentifier() {
    return uuid;
  }
  
  public void registerUser(String username, String password) {
	  users.put(username, password);
  }
}
