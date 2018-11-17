package ca.uvic.seng330.assn3.models.devices;

import java.util.UUID;

import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.Status;

public class SmartPlug extends Device{
	private boolean state;

  public SmartPlug(Hub hub, String name) throws HubRegistrationException{
    setIdentifier(UUID.randomUUID());
    setStatus(Status.OFFLINE);
    setHub(hub);
    setName(name);
    setType("SmartPlug");
  }
  
  /*
   * Switches on or switches off the smartplug
   */
  public void toggle() {
	  setStatus(Status.FUNCTIONING);
    if (state) {
      state = false;
    } else {
      state = true;
      //this.getHub().log("Smartplug on");
    }
  }

  /*
   * Return the state of the plug
   * @return boolean state
   */
  public boolean getState() {
    return state;
  }
}
