package ca.uvic.seng330.assn3.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.ClientInstance;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class DeleteUserController implements Initializable{
	@FXML private TextField usernameField;
	@FXML private Button deleteUserButton;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {}
	
	@FXML
	public void deleteUser() {
		DesktopClient c = ClientInstance.getClientInstance();
		for(User u: c.getUsers()) {
			if(u.getUsername().equals(usernameField.getText())) {
				c.unregisterUser(u.getUsername());
				break;
			}
		}
		ClientInstance.setClientInstance(c);
		deleteUserButton.setText("User Deleted!");
	}
}

