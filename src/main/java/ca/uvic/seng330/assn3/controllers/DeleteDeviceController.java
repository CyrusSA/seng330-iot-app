package ca.uvic.seng330.assn3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.HubInstance;
import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.devices.Device;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteDeviceController implements Initializable{
	@FXML private TextField deviceNameField;
	@FXML private Button deleteDeviceButton;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {}
	
	@FXML
	public void deleteDevice() throws HubRegistrationException {
		Hub h = HubInstance.getHubInstance();
		for(Device d: h.getDevices().values()) {
			if(d.getName().equals(deviceNameField.getText())) {
				h.unregister(d);
				break;
			}
		}
		HubInstance.setHubInstance(h);
		deleteDeviceButton.setText("Device Deleted!");
	}
}
