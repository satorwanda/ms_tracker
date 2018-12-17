package Models;

import java.util.Date;

public class LocationData {
	private int recordId;
	private String deviceDateTime;
	private String deviceIMEI;
	private double latitude;
	private double longitude;
	private int speed;
	private int course;
	private String adresses;
	private String plateNo;
	
	

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getAdresses() {
		return adresses;
	}

	public void setAdresses(String adresses) {
		this.adresses = adresses;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getDeviceDateTime() {
		return  deviceDateTime;
	}

	public void setDeviceDateTime(String deviceDateTime) {
		this.deviceDateTime = deviceDateTime;
	}

	public String getDeviceIMEI() {
		return deviceIMEI;
	}

	public void setDeviceIMEI(String deviceIMEI) {
		this.deviceIMEI = deviceIMEI;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "LocationData [recordId=" + recordId + ", deviceDateTime=" + deviceDateTime + ", deviceIMEI="
				+ deviceIMEI + ", latitude=" + latitude + ", longitude=" + longitude + ", speed=" + speed + ", course="
				+ course + "]";
	}

}
