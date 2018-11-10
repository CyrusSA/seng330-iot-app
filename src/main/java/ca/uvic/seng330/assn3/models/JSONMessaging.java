package ca.uvic.seng330.assn3.models;

import org.json.*;

import ca.uvic.seng330.assn3.models.devices.*;

import java.util.Date;

public class JSONMessaging {
  private Device d;
  private String message;
  private static int messageID;

  public JSONMessaging(Device d, String message) {
    this.d = d;
    this.message = message;
  }

  public JSONObject invoke() {
    JSONObject json = new JSONObject();
    json.put("msg_id", messageID++);
    json.put("node_id", d.getIdentifier().toString());
    json.put("status", d.getStatus().toString());
    json.put("payload", message);
    json.put("created_at", new Date().toString());
    return json;
  }
}
