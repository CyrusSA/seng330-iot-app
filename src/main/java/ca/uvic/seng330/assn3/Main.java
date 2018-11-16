package ca.uvic.seng330.assn3;

import javafx.application.Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
    Hub h = new Hub();
    readDevices(h);
    DesktopClient c = readClient(h);
    ClientInstance.setClientInstance(c);
    HubInstance.setHubInstance(h);
    
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

  @Override //Saves data when application closes
  public void stop() throws HubRegistrationException, IOException {
    writeDevices(HubInstance.getHubInstance());
    writeClient(ClientInstance.getClientInstance());
  }

  public static void writeDevices(Hub h) throws HubRegistrationException, IOException {
    clearFile("devices.json");
    Gson gson = new Gson();
    ArrayList<String> objects = new ArrayList<String>();
    for (Device d : h.getDevices().values()) {
      objects.add(gson.toJson(d) + "\n");
    }
    FileUtils.writeLines(new File("devices.json"), objects);
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

  public static void writeClient(DesktopClient c) throws IOException {
	 clearFile("client.json");
    Gson gson = new Gson();
    FileUtils.writeStringToFile(new File("client.json"), gson.toJson(c));
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

  public static void clearFile(String filename) {
    try {
      PrintWriter writer = new PrintWriter(new File(filename));
      writer.write("");
      writer.close();
    } catch (FileNotFoundException e) {
    }
  }
}
