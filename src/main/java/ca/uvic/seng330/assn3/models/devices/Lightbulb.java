package ca.uvic.seng330.assn3.models.devices;

import java.util.UUID;

import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.Status;

public class Lightbulb extends Device{
  private boolean state;
  private Hub hub;

  public Lightbulb(Hub hub) throws HubRegistrationException{
    setIdentifier(UUID.randomUUID());
    setStatus(Status.INACTIVE);
    this.hub = hub;
    hub.register(this);
    hub.alert(this, String.format("Lightbulb %s registered", this.getIdentifier().toString()));
  }

  /*
   * Switches on or switches off the lightbulb
   */
  public void toggle() {
    if (state) {
      state = false;
      hub.alert(this, "Lightbulb off");
    } else {
      state = true;
      hub.alert(this, "Lightbulb on");
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
