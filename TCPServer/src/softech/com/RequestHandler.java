package softech.com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import API_Clients.ReverseGeocodeClient;
import Dal.DeviceDAL;
import Dal.LocationDataDAL;
import Dal.VehicleDeviceDAL;
import Models.LoginData;
import Utils.Util;
import Models.LocationData;
import Utils.Converter;
import Models.Device;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler extends Thread {

	final private Socket s;
	static List<LoginData> logins = new ArrayList<LoginData>();

	public RequestHandler(Socket s) {
		this.s = s;

	}

	@Override
	public void run() {
		while (true) {
			String deviceIMEIPadded=null;
			DataInputStream dis = null;
			try {
				dis = new DataInputStream(s.getInputStream());
			} catch (IOException e) {

				e.printStackTrace();
			}
			DataOutputStream dos = null;
			try {
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {

				e.printStackTrace();
			}

			// read from the specific socket
			byte[] bs_received = new byte[44];
			int bytes_read = -1;
			StringBuilder sb = new StringBuilder();
			String newline = System.lineSeparator();

			try {

				while ((bytes_read = dis.read(bs_received)) != -1) {
					sb.setLength(0);
					for (int i = 0; i < bytes_read; i++) {

						sb.append(String.format("0x%02x", bs_received[i]) + " ");
					}

					String arrays[] = sb.toString().split(" ");
					switch (arrays[2]) {
					case "0x01":
						LoginData ld = new LoginData();
						ld.setDeviceIMEI(Util.retrieveDeviceImeiFromLoginPacket(bs_received));
						ld.setIpPort(s.toString());
						if (!isSocketIDAddedToList(ld.getDeviceIMEI(), ld.getIpPort()))
							logins.add(ld);

						System.out.println("Login Packet----------------------------------------from " + s);

						Device de = new Device();
						deviceIMEIPadded=Converter.removeZero(ld.getDeviceIMEI());
						de.setDeviceIMEI(deviceIMEIPadded);
						de.setOnline(true);
						de.setInstalled(true);

						// save to database if it is not saved
						DeviceDAL dev = new DeviceDAL();
						//dev.create(de);

						System.out.println("Send Response required");
						dos.write(Util.sendLoginResponseBytes(bs_received));
						System.out.println(VehicleDeviceDAL.deviceConectedVehicle(deviceIMEIPadded.trim()));
						System.out.println("Login Data Response sent");
						break;

					case "0x02":
						System.out.println("Location Data--------------------------------------from " + s);

					
						LocationData dat = new LocationData();
						dat.setDeviceDateTime(Converter.utcSecondsToDate(Util.gpsDataUtcSeconds(bs_received)).toString());
						
						deviceIMEIPadded=getDeviceIMEIBySocketIpPort(s.toString());						
						dat.setDeviceIMEI(deviceIMEIPadded);
						
						int lat = Converter.byteArrayToInt(Util.gpsLatitude(bs_received));
						dat.setLatitude(lat / 1800000d);
						
						int lon = Converter.byteArrayToInt(Util.gpsLongitude(bs_received));
						dat.setLongitude(lon / 1800000d);
						
						dat.setSpeed(Util.gpsSpeed(bs_received));
						
						String connectedVehiclePlateNo=VehicleDeviceDAL.deviceConectedVehicle(deviceIMEIPadded.trim());
						dat.setPlateNo(connectedVehiclePlateNo);
						
					
						//String adresses=ReverseGeocodeClient.reverseGeocodeResponse(lon, lat);
					    //dat.setAdresses(adresses);
					    
						//Save location to database
					//	LocationDataDAL dal = new LocationDataDAL();
						LocationDataDAL.create(dat);
					
						System.out.println("Location Data is Registered");
						break;

					case "0x03":
						System.out.println("Heartbeat Data--------------------------------------from " + s);
						System.out.println("Send Response  required");
						dos.write(Util.sendHeartBeatResponse(bs_received));
						System.out.println("Heartbeat Response sent----------------------------to " + s);
						break;

					}
					System.out.println(sb.toString());
					System.out.println(newline);

				}
			} catch (IOException e) {

				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				dis.close();
				dos.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

	}

	public static String getDeviceIMEIBySocketIpPort(String socketIpPort) {
		String deviceIMEI = "";
		for (LoginData ld : logins) {
			if (ld.getIpPort().equals(socketIpPort.trim())) {
				deviceIMEI = ld.getDeviceIMEI();
			}
		}
		return Converter.removeZero(deviceIMEI);
	}

	public boolean isSocketIDAddedToList(String deviceImei, String socketId) {
		boolean response = false;
		for (LoginData ld : logins) {
			if (ld.getIpPort().equals(socketId.trim()) && ld.getDeviceIMEI().equals(deviceImei)) {
				response = true;
			}
		}
		return response;
	}

}
