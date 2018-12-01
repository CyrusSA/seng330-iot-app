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

public class AddUserController implements Initializable {

  @FXML private TextField nameField;
  @FXML private TextField usernameField;
  @FXML private TextField passwordField;
  @FXML private CheckBox adminCheck;
  @FXML private Button addUserButton;

  @Override
  public void initialize(URL url, ResourceBundle rb) {}

  @FXML
  public void addUser() {
    DesktopClient c = ClientInstance.getClientInstance();
    User u =
        new User(
            usernameField.getText(),
            passwordField.getText(),
            nameField.getText(),
            adminCheck.isSelected());
    c.registerUser(u);
    ClientInstance.setClientInstance(c);
    addUserButton.setText("User Added!");
  }
}
