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
import java.net.URL;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.ClientInstance;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class LoginController implements Initializable{
  @FXML public Button loginButton;
  @FXML public TextField usernameField;
  @FXML public PasswordField passwordField;

  @Override
  public void initialize(URL url, ResourceBundle rb) {}

  public void login() throws IOException {

    DesktopClient c = ClientInstance.getClientInstance();
    String username = usernameField.getText();
    String password = passwordField.getText();
    User user = c.validateUser(username, password);
    FXMLLoader loader = null;
    int width = 0;
    int height = 0;

    if (username.isEmpty() || password.isEmpty()) {
      ControllerMethods.errorAlert("Please enter both username and password");
    } else if (user == null) { // Incorrect user or pass
      ControllerMethods.errorAlert("Incorrect username or password");
    } else { // authenticated
      if (user.isAdmin()) {
        loader = new FXMLLoader(getClass().getResource("..\\views\\admin.fxml"));
        width = 600;
        height = 850;
      } else {
    	  loader = new FXMLLoader(getClass().getResource("..\\views\\user.fxml"));
    	  width = 600;
    	  height = 400;
      }

      Parent root = loader.load();
      Stage stage = (Stage) loginButton.getScene().getWindow();
      stage.setTitle("IoT DesktopClient");
      Scene scene1 = new Scene(root, width, height);
      stage.setScene(scene1);
      stage.show();
    }
  }
}
