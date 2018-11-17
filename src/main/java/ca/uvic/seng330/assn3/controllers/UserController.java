package ca.uvic.seng330.assn3.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.models.devices.*;

import ca.uvic.seng330.assn3.ClientInstance;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.User;
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

public class UserController implements Initializable{
	@FXML private Button camera_button;
	@FXML private Button lightbulb_button;
	@FXML private Button smartplug_button;
	@FXML private Button thermostat_button;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
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
