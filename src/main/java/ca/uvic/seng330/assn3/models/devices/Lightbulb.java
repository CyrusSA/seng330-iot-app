package ca.uvic.seng330.assn3.models.devices;

import java.util.UUID;

import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.Status;

public class Lightbulb extends Device{
  private boolean state;

  public Lightbulb(Hub hub, String name) throws HubRegistrationException{
    setIdentifier(UUID.randomUUID());
    setStatus(Status.OFFLINE);
    setHub(hub);
    setName(name);
    setType("Lightbulb");
    hub.alert(this, String.format("Lightbulb %s registered", this.getName()));
  }

  /*
   * Switches on or switches off the lightbulb
   */
  public void toggle() {
    if (state) {
      state = false;
      getHub().alert(this, "Lightbulb off");
    } else {
      state = true;
      getHub().alert(this, "Lightbulb on");
    }
  }

  /*
   * Return the state of the bulb
   * @return boolean state
   */
  public boolean getCondition() {
    return state;
  }
}
