package ca.uvic.seng330.assn3.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import ca.uvic.seng330.assn3.DeviceInstance;
import ca.uvic.seng330.assn3.models.Status;
import ca.uvic.seng330.assn3.models.devices.Camera;
import ca.uvic.seng330.assn3.models.devices.CameraFullException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

public class CameraController implements Initializable {

  @FXML private MediaView mv;
  @FXML private MediaPlayer mp;
  @FXML private Media m;
  @FXML private Button recordButton;
  @FXML private Button startButton;
  @FXML private Button offButton;
  @FXML private Button clearButton;
  @FXML private Text capacity;

  private Camera c;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    c = (Camera) DeviceInstance.getDeviceInstance();
    if (c.getStatus() == Status.FUNCTIONING) {
      turnOn();
    }
  }

  @FXML
  public void record() {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> t;
    if (!c.getIsRecording()) {
      recordButton.setText("Stop");
      try {
        c.record();
      } catch (CameraFullException e) {
        c.setStatus(Status.ERROR);
      }

      Runnable memRunnable =
          new Runnable() {
            public void run() {
              c.setFreeMemory(c.getFreeMemory() - 1);
              capacity.setText("" + c.getFreeMemory());
            }
          };

      //t = executor.scheduleAtFixedRate(memRunnable, 0, 1, TimeUnit.SECONDS);

    } else {
      //t.cancel(false);
      recordButton.setText("Record");
      try {
        c.record();
      } catch (CameraFullException e) {
        c.setStatus(Status.ERROR);
      }
    }
  }

  @FXML
  public void clearMemory() {
    c.clearDisk();
    capacity.setText("" + c.getFreeMemory());
  }

  @FXML
  public void turnOn() {
	  
	    m = new Media("https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_10mb.mp4");
	    mp = new MediaPlayer(m);
	    mv.setMediaPlayer(mp);
	    mp.play();

    capacity.setText("" + c.getFreeMemory());

    c.setStatus(Status.FUNCTIONING);
  }

  @FXML
  public void turnOff() {
    capacity.setText("");
    mp.stop();
    c.setStatus(Status.OFFLINE);
  }
}
