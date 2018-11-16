package ca.uvic.seng330.assn3.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ca.uvic.seng330.assn3.ClientInstance;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class AdminController implements Initializable{
	@FXML Text title;
	@FXML TableView<User> table;
	@FXML TableColumn<User, String> nameColumn;
	@FXML TableColumn<User, String> usernameColumn;
	@FXML TableColumn<User, String> adminColumn;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		DesktopClient c = ClientInstance.getClientInstance();
		
		title.setText(c.getCurrent().getName()+"'s Dashboard");
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		adminColumn.setCellValueFactory(new PropertyValueFactory<>("admin"));
		
		table.setItems(getObservableUsers(c.getUsers()));
		
	}
	
	public ObservableList<User> getObservableUsers(List<User> users){
		return FXCollections.observableArrayList(users);
	}
}
