package Dal;

import java.sql.ResultSet;


import java.sql.*;

import Models.Device;

public class DeviceDAL {

	static ResultSet rs = null;
	static java.sql.PreparedStatement pst = null;

	public static void main(String[] args) {
		Device de = new Device();
		de.setDeviceIMEI("1234562");
		de.setInstalled(true);
		de.setOnline(true);

		DeviceDAL d = new DeviceDAL();
		d.create(de);
	}

	public void create(Device dev) {
		try {
			if (isDeviceRegistered(dev.getDeviceIMEI()) == false) {
				String sql = "insert into devices(device_imei,is_installed,is_online)values(?,?,?)";
				pst = dbConnection.getConnection().prepareStatement(sql);
				pst.setString(1, dev.getDeviceIMEI());
				pst.setBoolean(2, dev.isInstalled());
				pst.setBoolean(3, dev.isOnline());
				pst.executeUpdate();

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean isDeviceRegistered(String deviceImei) {
		boolean response = false;
		try {

			String query = "select * from devices where device_imei like ?";
			pst = dbConnection.getConnection().prepareStatement(query);
			pst.setString(1, deviceImei);
			rs = pst.executeQuery();
			while (rs.next()) {
				response = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

}
