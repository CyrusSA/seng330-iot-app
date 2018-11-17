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

public class AdminTest extends ApplicationTest{
	private AddUserController adduser=new AddUserController();
	private DeleteUserController deleteuser=new DeleteUserController();
	
	@Override
	public void start (Stage stage) throws Exception {
	  Parent mainNode = FXMLLoader.load(Main.class.getResource("add_user.fxml"));
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
		clickOn("#passwordField").write("passward");
		clickOn("#usernameField").write("passward");
		clickOn("#nameField").write("passward");
		clickOn("#addUserButton");
		assertThat(adduser.addUser(), is("User Added!"));
		
	}
	  

}
