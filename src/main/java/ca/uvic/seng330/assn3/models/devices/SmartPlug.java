package ca.uvic.seng330.assn3.models.devices;

import java.util.UUID;

import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.Status;

public class SmartPlug extends Device{

  public SmartPlug(Hub hub, String name) throws HubRegistrationException{
    setIdentifier(UUID.randomUUID());
    setStatus(Status.OFFLINE);
    setHub(hub);
    setName(name);
    setType("SmartPlug");
    hub.alert(this, String.format("SmartPlug %s registered", this.getIdentifier().toString()));
  }
}
