package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.models.DesktopClient;

public class ClientInstance {
	private static DesktopClient instance;
	
	public static DesktopClient getClientInstance() {
		return instance;
	}
	
	public static void setClientInstance(DesktopClient client) {
		instance = client;
	}
}
