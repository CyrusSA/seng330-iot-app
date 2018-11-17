package ca.uvic.seng330.assn3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import ca.uvic.seng330.assn3.controllers.*;

public class addDeviceTest extends ApplicationTest{
	private AddDeviceController addDevice=new AddDeviceController();
	@Override
	public void start (Stage stage) throws Exception {
	  Parent mainNode = FXMLLoader.load(Main.class.getResource("add_device.fxml"));
	  stage.setScene(new Scene(mainNode));
	  stage.show();
	}
	@Before
	  public void setUp () throws Exception {
	 }
	
	@After
    public void tearDown() throws Exception {}
	
	@Test
	public void add_user() {
		clickOn("#deviceNameField").write("camera");
		clickOn("#deviceTypeChoiceBox").setValue("Camera");
		clickOn("#addDeviceButton");
		assertThat(addDevice.addDevice(), is("Device Added!"));
		
	}

}
