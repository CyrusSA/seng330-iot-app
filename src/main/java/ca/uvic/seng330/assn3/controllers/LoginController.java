package ca.uvic.seng330.assn3.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

public class LoginController {
	@FXML
	public Button loginButton;
	public TextField usernameField;
	public PasswordField passwordField;
	
	public void login() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		if(username.isEmpty() || password.isEmpty()) {
			errorAlert("Please enter both username and password");
		} else if(username.equals("yo") && password.equals("yoyo")) { //user authenticated
			errorAlert("sah");
		} else {
			errorAlert("Please enter both username and password");
		}
	}
	
	private static void errorAlert(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
