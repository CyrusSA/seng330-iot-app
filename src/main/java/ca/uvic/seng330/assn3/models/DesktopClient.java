package ca.uvic.seng330.assn3.models;

import org.json.JSONObject;

import ca.uvic.seng330.assn3.HubInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DesktopClient implements Client {
  public ArrayList<User> users;
  private final UUID uuid;
  private User current;

  public DesktopClient() {
    uuid = UUID.randomUUID();
    users = new ArrayList<User>();
  }

  /*
   * Notifies the client of an alert from a device via a Mediator
   * @param JSONObject json - JSON data from the Mediator
   */
  public void notify(JSONObject json) {
    //System.out.println(json.toString());
  }

  public UUID getIdentifier() {
    return uuid;
  }

  public boolean registerUser(User u) {
    if (users.contains(u)) {
      return false;
    } else {
      users.add(u);
      Hub h = HubInstance.getHubInstance();
      h.log(String.format("User %s(username: %s) has been registered", u.getName(), u.getUsername()));
      HubInstance.setHubInstance(h);
      return true;
    }
  }

  public void unregisterUser(String username) {
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        users.remove(u);
        Hub h = HubInstance.getHubInstance();
        h.log(String.format("User %s has been unregistered", u.getName()));
        HubInstance.setHubInstance(h);
        break;
      }
    }
  }

  public User validateUser(String username, String password) {
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        if (u.getPassword().equals(password)) {
          current = u;
          Hub h = HubInstance.getHubInstance();
          h.log(String.format("User %s logged in", u.getName()));
          HubInstance.setHubInstance(h);
          return u;
        }
      }
    }
    return null;
  }

  public ArrayList<User> getUsers() {
    return users;
  }
  
  public User getCurrent() {
	  return current;
  }
}
