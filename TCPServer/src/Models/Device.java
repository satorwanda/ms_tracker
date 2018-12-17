package Models;

public class Device {
private String deviceIMEI;
private String vehiclePlateNo;
private boolean isOnline;
private boolean isInstalled;


public Device(String deviceIMEI, String vehiclePlateNo, boolean isOnline, boolean isInstalled) {
	super();
	this.deviceIMEI = deviceIMEI;
	this.vehiclePlateNo = vehiclePlateNo;
	this.isOnline = isOnline;
	this.isInstalled = isInstalled;
}


public Device() {
	super();
}


public String getDeviceIMEI() {
	return deviceIMEI;
}
public void setDeviceIMEI(String deviceIMEI) {
	this.deviceIMEI = deviceIMEI;
}
public String getVehiclePlateNo() {
	return vehiclePlateNo;
}
public void setVehiclePlateNo(String vehiclePlateNo) {
	this.vehiclePlateNo = vehiclePlateNo;
}
public boolean isOnline() {
	return isOnline;
}
public void setOnline(boolean isOnline) {
	this.isOnline = isOnline;
}
public boolean isInstalled() {
	return isInstalled;
}
public void setInstalled(boolean isInstalled) {
	this.isInstalled = isInstalled;
}



}
