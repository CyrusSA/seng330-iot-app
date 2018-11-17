package ca.uvic.seng330.assn3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.DeviceInstance;
import ca.uvic.seng330.assn3.models.devices.SmartPlug;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class SmartPlugController implements Initializable{
	@FXML Text toggleInfo;
	@FXML ToggleButton toggleButton;
	
	private SmartPlug s;
	
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
	  s = (SmartPlug) DeviceInstance.getDeviceInstance();
	  if(s.getState()) {
		  toggleInfo.setText("ON");
	  } else {
		  toggleInfo.setText("OFF");
	  }
  }
  
  @FXML public void toggle() {
	  s.toggle();
	  if(s.getState()) {
		  toggleInfo.setText("ON");
	  } else {
		  toggleInfo.setText("OFF");
	  }
  }
}
