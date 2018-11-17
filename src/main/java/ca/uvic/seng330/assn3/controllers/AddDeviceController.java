package ca.uvic.seng330.assn3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.ClientInstance;
import ca.uvic.seng330.assn3.HubInstance;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.User;
import ca.uvic.seng330.assn3.models.devices.Camera;
import ca.uvic.seng330.assn3.models.devices.Device;
import ca.uvic.seng330.assn3.models.devices.Lightbulb;
import ca.uvic.seng330.assn3.models.devices.SmartPlug;
import ca.uvic.seng330.assn3.models.devices.Thermostat;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddDeviceController implements Initializable{
	@FXML private TextField deviceNameField;
	@FXML private ChoiceBox<String> deviceTypeChoiceBox;
	@FXML private Button addDeviceButton;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		deviceTypeChoiceBox.setItems(FXCollections.observableArrayList("Camera", "Lightbulb", "SmetPlug", "Thermostat"));
		deviceTypeChoiceBox.setValue("Camera");
	}
	
	@FXML
	public void addDevice() throws HubRegistrationException {
		Hub h = HubInstance.getHubInstance();
		Device d = null;
		String name = deviceNameField.getText();
		String choice = deviceTypeChoiceBox.getValue();
		if(choice.equals("Camera")) {
	    	  d = new Camera(h, name);
	      } else if(choice.equals("Lightbulb")) {
	    	  d = new Lightbulb(h, name);
	      } else if(choice.equals("SmartPlug")) {
	    	  d = new SmartPlug(h, name);
	      } else {
	    	  d = new Thermostat(h, name);
	      }
		h.register(d);
		HubInstance.setHubInstance(h);
		addDeviceButton.setText("Device Added!");
	}
}
