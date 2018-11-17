package ca.uvic.seng330.assn3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.DeviceInstance;
import ca.uvic.seng330.assn3.models.devices.Lightbulb;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class LightbulbController implements Initializable {
	@FXML Text toggleInfo;
	@FXML ToggleButton toggleButton;
	
	private Lightbulb l;
	
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
	  l = (Lightbulb) DeviceInstance.getDeviceInstance();
	  if(l.getState()) {
		  toggleInfo.setText("ON");
	  } else {
		  toggleInfo.setText("OFF");
	  }
  }
  
  @FXML public void toggle() {
	  l.toggle();
	  if(l.getState()) {
		  toggleInfo.setText("ON");
	  } else {
		  toggleInfo.setText("OFF");
	  }
  }
}
