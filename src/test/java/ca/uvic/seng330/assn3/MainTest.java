package ca.uvic.seng330.assn3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.Assert.*;

import ca.uvic.seng330.assn3.controllers.*;


import static org.junit.Assert.*;

public class MainTest extends ApplicationTest{
	private  LoginController login=new LoginController();
	
	@Override
	public void start (Stage stage) throws Exception {
	  Parent mainNode = FXMLLoader.load(Main.class.getResource("login.fxml"));
	  stage.setScene(new Scene(mainNode));
	  stage.show();
	  stage.toFront();
	}
	
	@Before
	  public void setUp () throws Exception {
	 }
	
	@After
    public void tearDown() throws Exception {}
	
	@Test
	public void contain_passward() {
		clickOn("#passwordField").write("passward");
		clickOn("#loginButton");
		verifyThat(login.passwordField.getText(), is("passward"));
	}
	
	@Test
	public void contain_user() {
		clickOn("#usernameField").write("user");
		clickOn("#loginButton");
		assertThat(login.usernameField.getText(), is("user"));
	}
	
	@Test
	public void wrong_passward() {
		clickOn("#passwordField").write("pass");
		clickOn("#loginButton");
		assertThat(login.login(),is("Incorrect username or password"));
	}
	
	@Test
	public void wrong_username() {
		clickOn("#usernameField").write("u");
		clickOn("#loginButton");
		assertThat(login.login(),is("Incorrect username or password"));
	}
}
