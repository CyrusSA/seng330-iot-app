package ca.uvic.seng330.assn3.models;

public class User {
	private final String username;
	private String password;
	private String name;
	private final boolean admin;
	
	public User(String username, String password, String name, boolean admin) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.admin = admin;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}