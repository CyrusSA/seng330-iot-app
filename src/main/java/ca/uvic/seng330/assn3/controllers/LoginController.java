package ca.uvic.seng330.assn3.controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import ca.uvic.seng330.assn3.ClientInstance;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class LoginController{
  @FXML public Button loginButton;
  public TextField usernameField;
  public PasswordField passwordField;
  
  @FXML
  public void initialize() {}

  public void login() throws IOException {

    DesktopClient c = ClientInstance.getClientInstance();
    String username = usernameField.getText();
    String password = passwordField.getText();
    User user = c.validateUser(username, password);
    Parent root = null;

      if (username.isEmpty() || password.isEmpty()) {
        ControllerMethods.errorAlert("Please enter both username and password");
      } else if (user == null) { // Incorrect user or pass
        ControllerMethods.errorAlert("Incorrect username or password");
      } else { // authenticated
        if (user.isAdmin()) {

          root = FXMLLoader.load(getClass().getResource("admin.fxml"));

        } else {

          root = FXMLLoader.load(getClass().getResource("\\views\\user.fxml"));
        }

        Stage stage = new Stage();
        stage.setTitle("IoT DesktopClient - Admin");
        Scene scene1 = new Scene(root, 600, 400);
        stage.setScene(scene1);
        loginButton.setText("wtf");
        stage.show();
      }

  }
}
