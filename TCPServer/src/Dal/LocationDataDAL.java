package Dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.LocationData;

public class LocationDataDAL {
	static ResultSet rs = null;
	static java.sql.PreparedStatement pst = null;

	public static void main(String[] args) throws SQLException {
		
		Models.LocationData d=new Models.LocationData();
		d.setDeviceIMEI("022222");
		d.setCourse(1);
		d.setLatitude(2.3);
		d.setLongitude(3.3);
		d.setSpeed(30);
		create(d);
	}

	public static void create(LocationData da) throws SQLException {
		try {
			
				String sql = "insert into devices_locations(device_date_time,device_imei,latitude,longitude,speed,course,location_adress,plate_no)values(?,?,?,?,?,?,?,?)";
				pst = dbConnection.getConnection().prepareStatement(sql);
				pst.setString(1,da.getDeviceDateTime());;
				pst.setString(2, da.getDeviceIMEI());
				pst.setDouble(3, da.getLatitude());
				pst.setDouble(4,da.getLongitude());
				pst.setInt(5,da.getSpeed());
				pst.setInt(6,da.getCourse());
				pst.setString(7,da.getAdresses());
		        pst.setString(8,da.getPlateNo());
				pst.executeUpdate();
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			if(dbConnection.con!=null)dbConnection.con.close();
		}
	}

}
