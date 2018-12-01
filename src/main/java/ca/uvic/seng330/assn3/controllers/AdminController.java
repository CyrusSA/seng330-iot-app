package ca.uvic.seng330.assn3.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import ca.uvic.seng330.assn3.ClientInstance;
import ca.uvic.seng330.assn3.DeviceInstance;
import ca.uvic.seng330.assn3.HubInstance;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.User;
import ca.uvic.seng330.assn3.models.devices.Device;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdminController implements Initializable {

  @FXML private Text title;

  @FXML private TableView<String> logTable;
  @FXML private TableColumn<String, String> activitiesColumn;
  @FXML private Button clearLogButton;

  @FXML private TableView<Device> deviceTable;
  @FXML private TableColumn<Device, String> deviceColumn;
  @FXML private TableColumn<Device, String> typeColumn;
  @FXML private TableColumn<Device, String> statusColumn;

  @FXML private Button addDeviceButton;
  @FXML private Button deleteDeviceButton;
  @FXML private Button launchButton;
  @FXML private Button checkStatusButton;

  @FXML private TableView<User> userTable;
  @FXML private TableColumn<User, String> nameColumn;
  @FXML private TableColumn<User, String> usernameColumn;
  @FXML private TableColumn<User, String> adminColumn;

  @FXML private Button addUserButton;
  @FXML private Button deleteUserButton;
  @FXML private Button camera_button;
  @FXML private Button lightbulb_button;
  @FXML private Button smartplug_button;
  @FXML private Button thermostat_button;

  private DesktopClient c;
  private Hub h;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    c = ClientInstance.getClientInstance();
    h = HubInstance.getHubInstance();

    // Title
    title.setText(c.getCurrent().getName() + "'s Dashboard");

    // logs
    activitiesColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));

    try {
      List<String> list = FileUtils.readLines(new File("log.txt"));
      Collections.reverse(list);
      logTable.setItems(FXCollections.observableArrayList(list));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Devices
    deviceColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("type"));
    statusColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));

    deviceTable.setItems(getObservableDevices(new ArrayList<Device>(h.getDevices().values())));

    // Users
    nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
    usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
    adminColumn.setCellValueFactory(new PropertyValueFactory<User, String>("admin"));

    userTable.setItems(getObservableUsers(c.getUsers()));
  }

  @FXML
  public void addUser() throws IOException {

    Stage stage = new Stage();
    stage.setTitle("IoT DesktopClient - Add User");
    Parent root = FXMLLoader.load(getClass().getResource("..\\views\\add_user.fxml"));
    Scene scene = new Scene(root, 530, 330);
    stage.setScene(scene);
    stage.show();

    stage.setOnCloseRequest(
        new EventHandler<WindowEvent>() { // refresh stage
          public void handle(WindowEvent we) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\admin.fxml"));
            Parent root = null;
            try {
              root = loader.load();
            } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            Stage stage = (Stage) title.getScene().getWindow();
            stage.setTitle("IoT DesktopClient");
            Scene scene1 = new Scene(root, 600, 850);
            stage.setScene(scene1);
            stage.show();
          }
        });
  }

  @FXML
  public void deleteUser() throws IOException {
    User user = userTable.getSelectionModel().getSelectedItem();
    c.unregisterUser(user.getUsername());
    FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\admin.fxml"));
    Parent root = null;
    try {
      root = loader.load();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Stage stage = (Stage) title.getScene().getWindow();
    stage.setTitle("IoT DesktopClient");
    Scene scene1 = new Scene(root, 600, 850);
    stage.setScene(scene1);
    stage.show();
  }

  @FXML
  public void addDevice() throws IOException {

    Stage stage = new Stage();
    stage.setTitle("IoT DesktopClient - Add Device");
    Parent root = FXMLLoader.load(getClass().getResource("..\\views\\add_device.fxml"));
    Scene scene = new Scene(root, 530, 330);
    stage.setScene(scene);
    stage.show();

    stage.setOnCloseRequest(
        new EventHandler<WindowEvent>() { // refresh stage
          public void handle(WindowEvent we) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\admin.fxml"));
            Parent root = null;
            try {
              root = loader.load();
            } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            Stage stage = (Stage) title.getScene().getWindow();
            stage.setTitle("IoT DesktopClient");
            Scene scene1 = new Scene(root, 600, 850);
            stage.setScene(scene1);
            stage.show();
          }
        });
  }

  @FXML
  public void deleteDevice() throws IOException, HubRegistrationException {
    Device device = deviceTable.getSelectionModel().getSelectedItem();
    h.unregister(device);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\admin.fxml"));
    Parent root = null;
    try {
      root = loader.load();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Stage stage = (Stage) title.getScene().getWindow();
    stage.setTitle("IoT DesktopClient");
    Scene scene1 = new Scene(root, 600, 850);
    stage.setScene(scene1);
    stage.show();
  }

  @FXML
  public void launchDevice() throws IOException {

    Device d = deviceTable.getSelectionModel().getSelectedItem();
    String dType = d.getType();
    DeviceInstance.setDeviceInstance(d);

    Stage stage = new Stage();
    stage.setTitle("Device - " + dType);
    Parent root =
        FXMLLoader.load(getClass().getResource("..\\views\\" + dType.toLowerCase() + ".fxml"));
    Scene scene = null;
    if (dType.equals("Camera")) {
      scene = new Scene(root, 600, 400);
    } else {
      scene = new Scene(root, 530, 330);
    }
    stage.setScene(scene);
    stage.show();

    // refresh stage
    stage.setOnCloseRequest(
        new EventHandler<WindowEvent>() {
          public void handle(WindowEvent we) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\admin.fxml"));
            Parent root = null;
            try {
              root = loader.load();
            } catch (IOException e) {
              e.printStackTrace();
            }

            Stage stage = (Stage) title.getScene().getWindow();
            stage.setTitle("Home Automation System");
            Scene scene1 = new Scene(root, 600, 850);
            stage.setScene(scene1);
            stage.show();
          }
        });
  }
  
  @FXML
  public void statusCheck() throws InterruptedException {
	  class StatusCheckThread implements Runnable{
		  private Hub h;

	    @Override
	    public void run() {
	      h = HubInstance.getHubInstance();
	      for(Device d: h.getDevices().values()) {
	    	  h.log(String.format("Status of %s(UUID: %s): %s", d.getName(), d.getIdentifier().toString(), d.getStatus().toString()));
	      }
	    } 
	  }
	  
	  HubInstance.setHubInstance(h);
	  
	  Thread t = new Thread(new StatusCheckThread());
	  
	  t.start();
	  t.join();
	  
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\admin.fxml"));
      Parent root = null;
      try {
        root = loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }

      Stage stage = (Stage) title.getScene().getWindow();
      stage.setTitle("Home Automation System");
      Scene scene1 = new Scene(root, 600, 850);
      stage.setScene(scene1);
      stage.show();
  }

  public ObservableList<User> getObservableUsers(List<User> users) {
    return FXCollections.observableArrayList(users);
  }

  public ObservableList<Device> getObservableDevices(List<Device> devices) {
    return FXCollections.observableArrayList(devices);
  }

  public void clearLog() throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(new File("log.txt"));
    writer.write("");
    writer.close();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\admin.fxml"));
    Parent root = null;
    try {
      root = loader.load();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Stage stage = (Stage) title.getScene().getWindow();
    stage.setTitle("IoT DesktopClient");
    Scene scene1 = new Scene(root, 800, 850);
    stage.setScene(scene1);
    stage.show();
  }
  
  public void shutdown() {
	  for(Device d: h.getDevices().values()) {
		  d.turnOff();
	  }
	  
	  h.log("System Shutdown");
	  
	  HubInstance.setHubInstance(h);
	  ClientInstance.setClientInstance(c);
	  
	  Platform.exit();
  }
}
