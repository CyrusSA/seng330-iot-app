package ca.uvic.seng330.assn3.models.devices;

public class Temperature {

  public enum Unit {
    CELSIUS,
    FAHRENHEIT
  }

  public class TemperatureOutofBoundsException extends Exception {
    public TemperatureOutofBoundsException(String message) {
      super(message);
    }
  }

  private double temperature;
  private Unit unit;

  public Temperature() {
    // TODO Auto-generated constructor stub
  }

  public Temperature(double temp, Unit unit) {
    temperature = temp;
    this.unit = unit;
  }

  public void setTemperature(double temp, Unit unit) {
    temperature = temp;
    this.unit = unit;
  }

  public double getTemperature() {
    return temperature;
  }
}
