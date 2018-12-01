package ca.uvic.seng330.assn3.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
