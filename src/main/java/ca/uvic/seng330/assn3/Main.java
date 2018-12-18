package ca.uvic.seng330.assn3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import com.google.gson.Gson;

import ca.uvic.seng330.assn3.models.Client;
import ca.uvic.seng330.assn3.models.DesktopClient;
import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.devices.Camera;
import ca.uvic.seng330.assn3.models.devices.Device;
import ca.uvic.seng330.assn3.models.devices.Lightbulb;
import ca.uvic.seng330.assn3.models.devices.SmartPlug;
import ca.uvic.seng330.assn3.models.devices.Thermostat;
import javafx.application.Application;
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
    String path = String.format(".%1$sviews%1$slogin.fxml", File.separator);
    System.out.println(path);
    Parent root = FXMLLoader.load(getClass().getResource(path));
    Scene scene = new Scene(root, 600, 400);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override // Saves data when application closes
  public void stop() throws HubRegistrationException, IOException {
    writeDevices(HubInstance.getHubInstance());
    writeClient(ClientInstance.getClientInstance());
  }

  public static void writeDevices(Hub h) throws HubRegistrationException, IOException {
    clearFile("devices.json");
    Gson gson = new Gson();
    ArrayList<String> objects = new ArrayList<String>();
    for (Device d : h.getDevices().values()) {
      // System.out.println(d.getType());
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
      JSONObject json = new JSONObject(s);
      Device d = null;
      if (json.get("type").equals("Camera")) {
        d = gson.fromJson(s, Camera.class);
      } else if (json.get("type").equals("Lightbulb")) {
        d = gson.fromJson(s, Lightbulb.class);
      } else if (json.get("type").equals("SmartPlug")) {
        d = gson.fromJson(s, SmartPlug.class);
      } else {
        d = gson.fromJson(s, Thermostat.class);
      }
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
