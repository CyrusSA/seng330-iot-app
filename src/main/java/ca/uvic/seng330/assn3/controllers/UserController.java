package ca.uvic.seng330.assn3.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.models.devices.*;

import ca.uvic.seng330.assn3.ClientInstance;
import ca.uvic.seng330.assn3.DeviceInstance;
import ca.uvic.seng330.assn3.HubInstance;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.Hub;
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
	@FXML private Text title;
	@FXML private TableView<Device> deviceTable;
	@FXML private TableColumn<Device, String> deviceColumn;
	@FXML private TableColumn<Device, String> typeColumn;
	@FXML private TableColumn<Device, String> statusColumn;
	@FXML private Button launchButton;
	
	private Hub h;
	private DesktopClient c;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		c = ClientInstance.getClientInstance();
		h = HubInstance.getHubInstance();
		
		//Title
		title.setText(c.getCurrent().getName()+"'s Dashboard");
		
		//Device table
		deviceColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("type"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
		
		deviceTable.setItems(getObservableDevices(new ArrayList<Device>(h.getDevices().values())));
	}
	
	public ObservableList<Device> getObservableDevices(List<Device> devices){
		return FXCollections.observableArrayList(devices);
	}
	
	//Launch any of the devices
	public void launchDevice() throws IOException{
		Device d = deviceTable.getSelectionModel().getSelectedItem();
		String dType = d.getType();
		DeviceInstance.setDeviceInstance(d);
		
		Stage stage = new Stage();
		stage.setTitle("Device - " + dType);
		Parent root = FXMLLoader.load(getClass().getResource("..\\views\\" + dType.toLowerCase() + ".fxml"));
		Scene scene = null;
	    if (dType.equals("Camera")) {
	      scene = new Scene(root, 600, 400);
	    } else {
	      scene = new Scene(root, 530, 330);
	    }
		stage.setScene(scene);
		stage.show();
		
		//refresh stage
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() { 
			public void handle(WindowEvent we) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\views\\user.fxml"));
				Parent root = null;
				try {
			          root = loader.load();
			        } catch (IOException e) {
			          // TODO Auto-generated catch block
			        e.printStackTrace();
			        }
	              Stage stage = (Stage) title.getScene().getWindow();
	              stage.setTitle("Home Automation System");
	              Scene scene = new Scene(root, 600, 400);
	              stage.setScene(scene);
	              stage.show();
	          }
		});
	}
}
