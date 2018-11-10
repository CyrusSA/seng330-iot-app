package ca.uvic.seng330.assn3.models.devices;

import java.util.UUID;

import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.Status;

public class SmartPlug extends Device{
  private Hub hub;

  public SmartPlug(Hub hub) throws HubRegistrationException{
    setIdentifier(UUID.randomUUID());
    setStatus(Status.INACTIVE);
    this.hub = hub;
    hub.register(this);
    hub.alert(this, String.format("SmartPlug %s registered", this.getIdentifier().toString()));
  }
}
