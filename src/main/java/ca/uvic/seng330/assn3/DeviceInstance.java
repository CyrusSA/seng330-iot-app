package ca.uvic.seng330.assn3;

import ca.uvic.seng330.assn3.models.devices.Device;

public class DeviceInstance {
private static Device instance;;
	
	public static Device getDeviceInstance() {
		return instance;
	}
	
	public static void setDeviceInstance(Device device) {
		instance = device;
	}
}
