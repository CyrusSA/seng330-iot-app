package ca.uvic.seng330.assn3.models.devices;

import java.util.UUID;


import com.google.gson.annotations.Expose;

import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.Status;

public class Camera extends Device{
  private boolean isRecording;
  private int diskSize;

  public Camera(Hub hub) throws HubRegistrationException{
    setIdentifier(UUID.randomUUID());
    setStatus(Status.INACTIVE);
    setHub(hub);
    hub.alert(this, String.format("Camera %s registered", this.getIdentifier().toString()));
  }

  /*
   * Starts or stops recording.
   * Throws Exception if diskSize is greater than 100
   */
  public void record() throws CameraFullException {
    if (diskSize >= 100) {
      throw new CameraFullException("Camera full");
    }
    if (isRecording) {
      isRecording = false;
      getHub().alert(this, "Recording");
    } else {
      isRecording = true;
      getHub().alert(this, "Not Recording");
    }
  }
}
