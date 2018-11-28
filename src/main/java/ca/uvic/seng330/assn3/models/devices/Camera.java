package ca.uvic.seng330.assn3.models.devices;

import java.util.UUID;

import com.google.gson.annotations.Expose;

import ca.uvic.seng330.assn3.models.Hub;
import ca.uvic.seng330.assn3.models.HubRegistrationException;
import ca.uvic.seng330.assn3.models.Status;

public class Camera extends Device {
  private boolean isRecording;
  private int freeMemory;
  private final int diskSize = 100;

  public Camera(Hub hub, String name) throws HubRegistrationException {
    setIdentifier(UUID.randomUUID());
    setStatus(Status.OFFLINE);
    setHub(hub);
    setName(name);
    setType("Camera");
    this.freeMemory = diskSize;
  }

  /*
   * Starts or stops recording.
   * Throws Exception if diskSize is greater than 100
   */
  public void record() throws CameraFullException {
    if (freeMemory == 0) {
      throw new CameraFullException("Camera full");
    }
    if (isRecording) {
      isRecording = false;
      //getHub().alert(this, "Recording");
    } else {
      isRecording = true;
      //getHub().alert(this, "Not Recording");
    }
  }
  
  public void clearDisk() {
	  freeMemory = diskSize;
  }

  public int getFreeMemory() {
    return freeMemory;
  }

  public void setFreeMemory(int freeMemory) {
    this.freeMemory = freeMemory;
  }

  public boolean getIsRecording() {
    return isRecording;
  }
}
