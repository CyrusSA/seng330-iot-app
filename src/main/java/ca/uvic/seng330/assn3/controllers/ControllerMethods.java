package ca.uvic.seng330.assn3.controllers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControllerMethods {
//  public static void write(DesktopClient c, Hub h) {
//    try {
//    	FileUtils.writeStringToFile(new File("client.json"), new JSONSerializer().deepSerialize(c));
//    	FileUtils.writeStringToFile(new File("hub.json"), new JSONSerializer().deepSerialize(h));
//    } catch (IOException e) {
//      errorAlert("Failed to save data");
//    }
//  }
//
//  public static DesktopClient readClient() {
//    try {
//      return (DesktopClient)new JSONDeserializer().deserialize(FileUtils.readFileToString(new File("client.json")));
//    } catch (IOException e) {
//      errorAlert("Failed to read data");
//      return new DesktopClient();
//    }
//  }
//  
//  public static Hub readHub() {
//	    try {
//	      return (Hub)new JSONDeserializer().deserialize(FileUtils.readFileToString(new File("hub.json")));
//	    } catch (IOException e) {
//	      errorAlert("Failed to read data");
//	      return new Hub();
//	    }
//	  }

  public static void errorAlert(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
  
}
