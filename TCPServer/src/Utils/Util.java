package Utils;

import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Util {
	
	
	// ******* GPS DATA RESOLVE  START************************************************************

		public static int gpsDataUtcSeconds(byte[] bs) {
			byte[] bs_utc = new byte[4];
			bs_utc[0] = bs[7];
			bs_utc[1] = bs[8];
			bs_utc[2] = bs[9];
			bs_utc[3] = bs[10];
			return Converter.byteArrayToInt(bs_utc);
		}

		public static byte[] gpsLatitude(byte[] bs) {
			byte[] bs_latitude = new byte[4];
			bs_latitude[0] = bs[11];
			bs_latitude[1] = bs[12];
			bs_latitude[2] = bs[13];
			bs_latitude[3] = bs[14];
			return bs_latitude;
		}

		public static byte[] gpsLongitude(byte[] bs) {
			byte[] bs_latitude = new byte[4];
			bs_latitude[0] = bs[15];
			bs_latitude[1] = bs[16];
			bs_latitude[2] = bs[17];
			bs_latitude[3] = bs[18];
			return bs_latitude;
		}

		public static int gpsSpeed(byte[] bs) {
			return bs[19];
		}

		public static byte[] gpsCourse(byte[] bs) {
			byte[] bs_course = new byte[2];
			bs_course[0] = bs[20];
			bs_course[1] = bs[21];

			return bs_course;
		}

		// ****** GPS DATA RESOLVE END************************************

		//*********START OF HEARTBEAT RESPONSE*****************************
		public static byte[] sendHeartBeatResponse(byte[]bs_received){
			byte[] bs=new byte[7];
			bs[0]=0x67;//header
			bs[1]=0x67;//header
			bs[2]=0x03;//protocol number
			bs[3]=0x00;//packet lenght
			bs[4]=0x02;//packet lenght
			bs[5]=bs_received[5];//serial number
			bs[6]=bs_received[6];//serial number
			return bs;
		}
		//*********END OF HEARTBEAT RESPONSE*****************************
		public static String retrieveDeviceImeiFromLoginPacket(byte[] bs) {
			byte[] bs_device = new byte[8];
			bs_device[0] = bs[7];
			bs_device[1] = bs[8];
			bs_device[2] = bs[9];
			bs_device[3] = bs[10];
			bs_device[4] = bs[11];
			bs_device[5] = bs[12];
			bs_device[6] = bs[13];
			bs_device[7] = bs[14];

			return Converter.byteArrayToHexString(bs_device);
		}

		public static byte[] sendLoginResponseBytes(byte[] bs) {
			byte[] srvResp = new byte[11];
			srvResp[0] = 0x67;// header
			srvResp[1] = 0x67;// header
			srvResp[2] = bs[2];// protocol number
			srvResp[3] = 0x00;// packet lenght
			srvResp[4] = 0x02;// packet lenght
			srvResp[5] = bs[5];// serial number
			srvResp[6] = bs[6];// serial number
			srvResp[7] = Converter.hexStringToByteArray(buildServerGmtTimeHex())[0];// server gmt
			srvResp[8] = Converter.hexStringToByteArray(buildServerGmtTimeHex())[1];// server gmt																					
			srvResp[9] = Converter.hexStringToByteArray(buildServerGmtTimeHex())[2];// server gmt
			srvResp[10] = Converter.hexStringToByteArray(buildServerGmtTimeHex())[3];// server gmt
			return srvResp;

		}
		public static long buildServerGmtTimeLong() {
			byte[] bs = new byte[4];
			Instant instant = Instant.now();
			long timeStampSeconds = instant.getEpochSecond();
			return timeStampSeconds;
		}
		public static String buildServerGmtTimeHex() {

			return Converter.longToHexString(buildServerGmtTimeLong());
		}
}
