package ca.uvic.seng330.assn3.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uvic.seng330.assn3.HubInstance;
import ca.uvic.seng330.assn3.models.devices.*;

public class Hub {

  private HashMap<UUID, Device> devices;

  private HashMap<UUID, Client> clients;

  public Hub() {
    this.startup();
  }
  /*
   * Alerts Clients
   * @param Device d - Device that calls alert
   * @param String message - Message to be passed on
   * @return JSONObject json - JSON Object containing device info and message
   */
  public void alert(Device d, String message) {
    JSONObject json = new JSONMessaging(d, message).invoke();
    for (Client client : clients.values()) {
      client.notify(json);
    }
    this.log(message);
  }

  /*
   * Initializes Hub
   * @see ca.uvic.seng330.assn2.part1.Mediator#startup()
   */
  public void startup() {
    devices = new HashMap<UUID, Device>();
    clients = new HashMap<UUID, Client>();
  }

  /*
   * Shutsdown Hub
   * @see ca.uvic.seng330.assn2.part1.Mediator#shutdown()
   */
  public void shutdown() {
    devices = null;
    clients = null;
  }

  /*
   * Registers a new device
   * @param Device d - Device to be registered
   * @see ca.uvic.seng330.assn2.part1.Mediator#register(ca.uvic.seng330.assn2.part1.devices.Device)
   */
  public void register(Device d) throws HubRegistrationException {
    if (devices.containsKey(d.getIdentifier())) {
      throw new HubRegistrationException("Device already registered");
    } else {
      devices.put(d.getIdentifier(), d);
    }
    this.alert(d, String.format("%s %s(UUID: %s) registered", d.getName(), d.getType(), d.getIdentifier().toString()));
  }

  /*
   * Registers a new client
   * @param Client c - Client to be registered
   * @see ca.uvic.seng330.assn2.part1.Mediator#register(ca.uvic.seng330.assn2.part1.Client)
   */
  public void register(Client c, boolean logIt) throws HubRegistrationException {
    if (clients.containsKey(c.getIdentifier())) {
      throw new HubRegistrationException("Client already registered");
    } else {
      clients.put(c.getIdentifier(), c);
    }
  }

  /*
   * Unregisters a device, throws an exception if the device is not found
   * @param Device d - Device to be unregistered
   * @see ca.uvic.seng330.assn2.part1.Mediator#unregister(ca.uvic.seng330.assn2.part1.devices.Device)
   */
  public void unregister(Device d) throws HubRegistrationException {
    if (devices.isEmpty() || !devices.containsKey(d.getIdentifier())) {
      throw new HubRegistrationException("Device not found");
    }
    devices.remove(d.getIdentifier());
    this.alert(d, String.format("%s %s(UUID: %s) unregistered", d.getName(), d.getType(), d.getIdentifier().toString()));
  }

  /*
   * Unregisters a client, throws an exception if the client is not found
   * @param Client c - Client to be unregistered
   * @see ca.uvic.seng330.assn2.part1.Mediator#unregister(ca.uvic.seng330.assn2.part1.devices.Device)
   */
  public void unregister(Client c) throws HubRegistrationException {
    if (clients.isEmpty() || !clients.containsKey(c.getIdentifier())) {
      throw new HubRegistrationException("Client not found");
    }
    clients.remove(c.getIdentifier());
  }

  /*
   * Logger
   * @param String message - String to be logged
   * @see ca.uvic.seng330.assn2.part1.Mediator#log(java.lang.String)
   */
  public void log(String message) {
    ArrayList<String> logs = null;
    try {
      logs = (ArrayList<String>) FileUtils.readLines(new File("log.txt"));
      logs.add(message);
      FileUtils.writeLines(new File("log.txt"), logs);
    } catch (IOException e) {
    }
    HubInstance.setHubInstance(this);
  }

  /*
   * Returns Device HashMap
   * @return HashMap<UUID, Device> devices
   * @see ca.uvic.seng330.assn2.part1.Mediator#getDevices()
   */
  public HashMap<UUID, Device> getDevices() {
    return devices;
  }

  public void setDevices(HashMap<UUID, Device> devices) {
    this.devices = devices;
  }

  public void setClients(HashMap<UUID, Client> clients) {
    this.clients = clients;
  }
}
