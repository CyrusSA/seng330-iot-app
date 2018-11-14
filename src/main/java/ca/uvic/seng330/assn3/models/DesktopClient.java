package ca.uvic.seng330.assn3.models;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DesktopClient implements Client {
  public ArrayList<User> users;
  private final UUID uuid;

  public DesktopClient(){
    uuid = UUID.randomUUID();
    users = new ArrayList<User>();
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

  public boolean registerUser(User u) {
    if (users.contains(u)) {
      return false;
    } else {
      users.add(u);
      return true;
    }
  }

  public void unregisterUser(String username) {
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        users.remove(u);
        break;
      }
    }
  }

  public User validateUser(String username, String password) {
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        if (u.getPassword().equals(password)) {
          return u;
        }
      }
    }
    return null;
  }
}
