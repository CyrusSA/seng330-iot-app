package ca.uvic.seng330.assn3.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.User;
import javafx.fxml.FXML;

public class LoginController {
	@FXML
	public Button loginButton;
	public TextField usernameField;
	public PasswordField passwordField;
	
	public void login() {
		DesktopClient c = ControllerMethods.readClient();
		Hub h = ControllerMethods.readHub();
		String username = usernameField.getText();
		String password = passwordField.getText();
		User user = c.validateUser(username, password);
		if(username.isEmpty() || password.isEmpty()) {
			ControllerMethods.errorAlert("Please enter both username and password");
		} else if(user==null) { //user authenticated
			ControllerMethods.errorAlert("dafuq bro");
		} else {
			ControllerMethods.errorAlert("AAYYYOOOOOO " + user.getName() );
		}
	}
	

}
