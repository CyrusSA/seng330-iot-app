package ca.uvic.seng330.assn3;

import javafx.application.Application;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


import ca.uvic.seng330.assn3.models.*;
import ca.uvic.seng330.assn3.models.devices.*;
import flexjson.JSONSerializer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) throws IOException, HubRegistrationException {
	Hub h = new Hub();
	DesktopClient c = new DesktopClient();
	c.registerUser(new User("admin", "password", "Admin", true));
	h.register(c);
	JSONSerializer ser = new JSONSerializer();
	FileUtils.writeStringToFile(new File("client.json"), new JSONSerializer().deepSerialize(c));
	FileUtils.writeStringToFile(new File("hub.json"), new JSONSerializer().deepSerialize(h));
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("IoT DesktopClient - Login");
    Parent root = FXMLLoader.load(getClass().getResource("\\views\\login.fxml"));
    Scene scene = new Scene(root, 600, 400);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
