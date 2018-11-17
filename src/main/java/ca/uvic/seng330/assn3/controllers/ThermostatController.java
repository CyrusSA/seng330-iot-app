package ca.uvic.seng330.assn3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.DeviceInstance;
import ca.uvic.seng330.assn3.models.Status;
import ca.uvic.seng330.assn3.models.devices.Temperature;
import ca.uvic.seng330.assn3.models.devices.Thermostat;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ThermostatController implements Initializable {

  @FXML private Text tempField;
  @FXML private TextField newTempField;
  @FXML private Button celsiusButton;
  @FXML private Button fahrenheitButton;
  @FXML private Button startButton;
  @FXML private Button turnOffButton;
  @FXML private Button setTempButton;
  @FXML private ChoiceBox<Temperature.Unit> unitChoiceBox;

  private Thermostat t;
  private Temperature temp;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    unitChoiceBox.setItems(FXCollections.observableArrayList(Temperature.Unit.CELSIUS, Temperature.Unit.FAHRENHEIT));
    unitChoiceBox.setValue(Temperature.Unit.CELSIUS);

    t = (Thermostat) DeviceInstance.getDeviceInstance();
    temp = t.getTemp();
    

    if (t.getStatus() == Status.FUNCTIONING) {
      tempField.setText(
          String.format("%.2f \u00b0%s", temp.getTemperature(), temp.getUnit().toString()));
    }
  }

  @FXML
  public void toCelsius() {
    if (!(temp.getUnit() == Temperature.Unit.CELSIUS)) {
      double newTemp = ((temp.getTemperature() - 32) * 5 )/ 9.0;
      temp.setTemperature(newTemp, Temperature.Unit.CELSIUS);
      tempField.setText(String.format("%.2f \u00b0%s", temp.getTemperature(), temp.getUnit().toString()));
    }
  }

  @FXML
  public void toFahrenheit() {
	  
	  Temperature.Unit un= temp.getUnit() ;
    if (!(un.toString().equals(Temperature.Unit.FAHRENHEIT.toString()))) {
      double newTemp = ((temp.getTemperature() * 9) / 5.0) + 32;
      temp.setTemperature(newTemp, Temperature.Unit.FAHRENHEIT);
      tempField.setText(
          String.format("%.2f \u00b0%s", temp.getTemperature(), temp.getUnit().toString()));
    }
  }

  @FXML
  public void start() {
    t.setStatus(Status.FUNCTIONING);
    tempField.setText(String.format("%.2f \u00b0%s", temp.getTemperature(), temp.getUnit().toString()));
  }

  @FXML
  public void turnOff() {
    t.setStatus(Status.OFFLINE);
    tempField.setText("");
  }

  @FXML
  public void setTemp() {
    temp.setTemperature(Integer.parseInt(newTempField.getText()), unitChoiceBox.getValue());
    tempField.setText(String.format("%.2f \u00b0%s", temp.getTemperature(), temp.getUnit().toString()));
  }
}
