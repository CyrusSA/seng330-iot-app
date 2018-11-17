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

import ca.uvic.seng330.assn3.models.devices.*;
import ca.uvic.seng330.assn3.ClientInstance;
import ca.uvic.seng330.assn3.HubInstance;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.User;
import ca.uvic.seng330.assn3.models.devices.Device;
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

public class AdminController implements Initializable{
	
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
		
		//Title
		title.setText(c.getCurrent().getName()+"'s Dashboard");
		
		//logs
		activitiesColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
		
		try {
			List<String> list = FileUtils.readLines(new File("log.txt"));
			Collections.reverse(list);
			logTable.setItems(FXCollections.observableArrayList(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Devices
		nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		adminColumn.setCellValueFactory(new PropertyValueFactory<User, String>("admin"));
		
		userTable.setItems(getObservableUsers(c.getUsers()));
		
		//Users
		deviceColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("type"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
		
		deviceTable.setItems(getObservableDevices(new ArrayList<Device>(h.getDevices().values())));
		
	}
	
	@FXML
	public void addUser() throws IOException {
		
		Stage stage = new Stage();
		stage.setTitle("IoT DesktopClient - Add User");
	    Parent root = FXMLLoader.load(getClass().getResource("..\\views\\add_user.fxml"));
	    Scene scene = new Scene(root, 530, 330);
	    stage.setScene(scene);
	    stage.show();
	    
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() { //refresh stage
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
	              Scene scene1 = new Scene(root, 800, 850);
	              stage.setScene(scene1);
	              stage.show();
	          }
		});
	}
	
	@FXML
	public void deleteUser() throws IOException {
		Stage stage = new Stage();
		stage.setTitle("IoT DesktopClient - Delete User");
	    Parent root = FXMLLoader.load(getClass().getResource("..\\views\\delete_user.fxml"));
	    Scene scene = new Scene(root, 530, 330);
	    stage.setScene(scene);
	    stage.show();
	    
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() { //refresh stage
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
	              Scene scene1 = new Scene(root, 800, 850);
	              stage.setScene(scene1);
	              stage.show();
	          }
		});
	}
	
	@FXML
	public void addDevice() throws IOException {
		
		Stage stage = new Stage();
		stage.setTitle("IoT DesktopClient - Add Device");
	    Parent root = FXMLLoader.load(getClass().getResource("..\\views\\add_device.fxml"));
	    Scene scene = new Scene(root, 530, 330);
	    stage.setScene(scene);
	    stage.show();
	    
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() { //refresh stage
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
	              Scene scene1 = new Scene(root, 800, 850);
	              stage.setScene(scene1);
	              stage.show();
	          }
		});
	}
	
	@FXML
	public void deleteDevice() throws IOException {
		Stage stage = new Stage();
		stage.setTitle("IoT DesktopClient - Delete Device");
	    Parent root = FXMLLoader.load(getClass().getResource("..\\views\\delete_device.fxml"));
	    Scene scene = new Scene(root, 530, 330);
	    stage.setScene(scene);
	    stage.show();
	    
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() { //refresh stage
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
	              Scene scene1 = new Scene(root, 800, 850);
	              stage.setScene(scene1);
	              stage.show();
	          }
		});
	}
	
	public ObservableList<User> getObservableUsers(List<User> users){
		return FXCollections.observableArrayList(users);
	}
	

	public ObservableList<Device> getObservableDevices(List<Device> devices){
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

	@FXML
	public void cam() throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Device - Camera");
		Parent root = FXMLLoader.load(getClass().getResource("..\\views\\camera.fxml"));
		Scene scene = new Scene(root, 530, 330);
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() { //refresh stage
			public void handle(WindowEvent we) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\user.fxml"));
				Parent root = null;
				try {
			          root = loader.load();
			        } catch (IOException e) {
			          // TODO Auto-generated catch block
			        e.printStackTrace();
			        }
	              //Stage stage = (Stage) title.getScene().getWindow();
	              stage.setTitle("IoT DesktopClient");
	              Scene scene1 = new Scene(root, 800, 850);
	              stage.setScene(scene1);
	              stage.show();
	          }
		});
	}
	
	@FXML
	public void light() throws IOException{
		Stage stage = new Stage();
		stage.setTitle("Device - LightBulb");
		Parent root = FXMLLoader.load(getClass().getResource("..\\views\\lightbulb.fxml"));
		Scene scene = new Scene(root, 530, 330);
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() { //refresh stage
			public void handle(WindowEvent we) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\user.fxml"));
				Parent root = null;
				try {
			          root = loader.load();
			        } catch (IOException e) {
			          // TODO Auto-generated catch block
			        e.printStackTrace();
			        }
	              //Stage stage = (Stage) title.getScene().getWindow();
	              stage.setTitle("IoT DesktopClient");
	              Scene scene1 = new Scene(root, 800, 850);
	              stage.setScene(scene1);
	              stage.show();
	          }
		});
	}
	
	@FXML
	public void smart()throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Device - SmartPlug");
		Parent root = FXMLLoader.load(getClass().getResource("..\\views\\smartplug.fxml"));
		Scene scene = new Scene(root, 530, 330);
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() { //refresh stage
			public void handle(WindowEvent we) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\user.fxml"));
				Parent root = null;
				try {
			          root = loader.load();
			        } catch (IOException e) {
			          // TODO Auto-generated catch block
			        e.printStackTrace();
			        }
	              //Stage stage = (Stage) title.getScene().getWindow();
	              stage.setTitle("IoT DesktopClient");
	              Scene scene1 = new Scene(root, 800, 850);
	              stage.setScene(scene1);
	              stage.show();
	          }
		});
	}
	
	@FXML
	public void ther() throws IOException{
		Stage stage = new Stage();
		stage.setTitle("Device - Thermostat");
		Parent root = FXMLLoader.load(getClass().getResource("..\\views\\thermostat.fxml"));
		Scene scene = new Scene(root, 530, 330);
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() { //refresh stage
			public void handle(WindowEvent we) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\user.fxml"));
				Parent root = null;
				try {
			          root = loader.load();
			        } catch (IOException e) {
			          // TODO Auto-generated catch block
			        e.printStackTrace();
			        }
	             // Stage stage = (Stage) title.getScene().getWindow();
	              stage.setTitle("IoT DesktopClient");
	              Scene scene1 = new Scene(root, 800, 850);
	              stage.setScene(scene1);
	              stage.show();
	          }
		});
	}
}
