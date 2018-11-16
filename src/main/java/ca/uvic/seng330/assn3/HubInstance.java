package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.models.Hub;

public class HubInstance {
	private static Hub instance;
	
	public static Hub getHubInstance() {
		return instance;
	}
	
	public static void setHubInstance(Hub hub) {
		instance = hub;
	}
}
