package ca.uvic.seng330.assn3;

import javafx.application.Application;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import ca.uvic.seng330.assn3.models.*;
import ca.uvic.seng330.assn3.models.devices.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) throws IOException, HubRegistrationException {
    // Code to write Client to file
    //	DesktopClient c = new DesktopClient();
    //	User u = new User("admin", "password", "Admin", true);
    //	User u1 = new User("user1", "password", "User1", false);
    //	c.registerUser(u);
    //	c.registerUser(u1);
    //	FileUtils.writeStringToFile(new File("client.json"), gson.toJson(c));

    Hub h = new Hub();
    readDevices(h);
    DesktopClient c = readClient(h);
    ClientInstance.setClientInstance(c);

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

  // Code to write devices to file
  public static void writeDevices(Hub h) throws HubRegistrationException, IOException {
    Device c = new Camera(h);
    Device c1 = new Camera(h);
    Gson gson = new Gson();
    ArrayList<String> objects = new ArrayList<String>();
    objects.add(gson.toJson(c) + "\n");
    objects.add(gson.toJson(c1) + "\n");
    FileUtils.writeLines(new File("devices.json"), objects);
    ArrayList<String> objects2 = (ArrayList<String>) FileUtils.readLines(new File("devices.json"));
    // System.out.println(objects2.get(0).toString());
    for (String s : objects2) {
      if (s.isEmpty()) continue;
      Device c2 = gson.fromJson(s, Camera.class);
      System.out.println(c2.getIdentifier().toString());
    }
  }

  public static void readDevices(Hub h) throws IOException {

    Gson gson = new Gson();
    ArrayList<String> objects2 = (ArrayList<String>) FileUtils.readLines(new File("devices.json"));
    HashMap<UUID, Device> devices = new HashMap<UUID, Device>();

    for (String s : objects2) {
      if (s.isEmpty()) continue;
      Device d = gson.fromJson(s, Camera.class);
      devices.put(d.getIdentifier(), d);
    }

    h.setDevices(devices);
  }

  public static DesktopClient readClient(Hub h) throws IOException {
    Gson gson = new Gson();
    DesktopClient c =
        (DesktopClient)
            gson.fromJson(FileUtils.readFileToString(new File("client.json")), DesktopClient.class);
    HashMap<UUID, Client> clients = new HashMap<UUID, Client>();
    clients.put(c.getIdentifier(), c);
    h.setClients(clients);

    return c;
  }
}
