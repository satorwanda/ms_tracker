package Dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.VehicleDevice;

public class VehicleDeviceDAL {

	static ResultSet rs=null;
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		VehicleDevice d=new VehicleDevice();
		d.setDeviceIMEI("352544074489775");
		d.setVehiclePlateNo("RAC0 14U");
		
		VehicleDeviceDAL t=new VehicleDeviceDAL();
		//t.linkVehicleToDevice(d);
		
		System.out.println(t.deviceConectedVehicle("352544074489775"));
		
	}
	
	public static void linkVehicleToDevice(VehicleDevice vd) throws ClassNotFoundException, SQLException{
		
		String sql="insert into devices_vehicles(device_imei,vehiclle_plate_no)values(?,?)";
		PreparedStatement pst=dbConnection.getConnection().prepareStatement(sql);
		pst.setString(1,vd.getDeviceIMEI());
		pst.setString(2,vd.getVehiclePlateNo());
		pst.executeUpdate();
	}
	public boolean isDeviceInstalled(String deviceIMEI) throws SQLException, ClassNotFoundException{
		boolean bol_=false;
		String query="select device_imei,vehiclle_plate_no from devices_vehicles where device_imei like ? and uninstallation_date is null";
		PreparedStatement pst=dbConnection.getConnection().prepareStatement(query);
		pst.setString(1,deviceIMEI);
	    rs=pst.executeQuery();
		while(rs.next()){
			bol_=true;
		}
		return bol_;
	}
	
	public static String deviceConectedVehicle(String deviceIMEI) throws SQLException, ClassNotFoundException{
		String bol_=null;
		String query="select vehiclle_plate_no from devices_vehicles where device_imei like ? and uninstallation_date is null";
		PreparedStatement pst=dbConnection.getConnection().prepareStatement(query);
		pst.setString(1,deviceIMEI.trim());
	    rs=pst.executeQuery();
		while(rs.next()){
			bol_=rs.getString("vehiclle_plate_no");
		}
		return bol_;
	}

}
