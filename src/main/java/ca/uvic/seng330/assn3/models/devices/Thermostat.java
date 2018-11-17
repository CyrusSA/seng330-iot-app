package ca.uvic.seng330.assn3.models.devices;

import java.util.UUID;

import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.Status;

public class Thermostat extends Device{
  private Temperature temperature;

  public Thermostat(Hub hub, String name) throws HubRegistrationException{
    temperature = new Temperature(37, Temperature.Unit.CELSIUS);
    setIdentifier(UUID.randomUUID());
    setStatus(Status.OFFLINE);
    setHub(hub);
    setName(name);
    setType("Thermostat");
    hub.alert(this, String.format("Thermostat %s registered", this.getIdentifier().toString()));
  }

  /*
   * Changes the temperature of thermostat
   * Throws Exception if Temperature is greater than 100
   * @param Temperature newTemp - New Temperature object
   */
  public void setTemp(Temperature newTemp) throws Temperature.TemperatureOutofBoundsException {
    if (newTemp.getTemperature() > 100) {
      throw new Temperature().new TemperatureOutofBoundsException("Temperature out of bounds.");
    }
    temperature = newTemp;
    getHub().alert(this, String.format("New temperature is %f", temperature.getTemperature()));
  }
}
