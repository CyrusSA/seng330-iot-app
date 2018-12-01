package ca.uvic.seng330.assn3.models.devices;

import java.util.UUID;

import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.Status;

public abstract class Device {
  private UUID uuid;
  private Status status;
  private String name;
  private String type;
  private transient Hub hub;

  /*
   * Returns the Device identifier
   * @return UUID uuid - Current UUID
   * @see ca.uvic.seng330.assn2.part1.devices.Device#getIdentifier()
   */
  public UUID getIdentifier() {
    return uuid;
  }

  /*
   * Sets new value of Device identifier
   * @param UUID uuid - New UUID
   * @see ca.uvic.seng330.assn2.part1.devices.Device#setIdentifier(java.util.UUID)
   */
  public void setIdentifier(UUID uuid) {
    this.uuid = uuid;
  }

  /*
   * Returns the Device status
   * @return Status status - Current status
   * @see ca.uvic.seng330.assn2.part1.devices.Device#getIdentifier()
   */
  public Status getStatus() {
    return status;
  }

  /*
   * Sets new value of Device status
   * @param Status status - New status
   * @see ca.uvic.seng330.assn2.part1.devices.Device#setIdentifier(java.util.UUID)
   */
  public void setStatus(Status status) {
    this.status = status;
  }

  public void setHub(Hub hub) {
    this.hub = hub;
  }

  public Hub getHub() {
    return hub;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    String[] s = this.getClass().toString().split("\\.");
    return s[s.length - 1];
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void turnOff() {
    setStatus(Status.OFFLINE);
  }
}
