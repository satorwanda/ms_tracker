package Utils;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Converter {
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	public static void main(String[] args) {
		long a = 1394621770;
		System.out.println(a);
	}

	public static String longToHexString(long l) {

		return String.format(Long.toHexString(l));
	}

	public static String intToHexString(int k) {
		Integer i = new Integer(k);
		String hex = Integer.toHexString(i);
		return hex;
	}

	public static byte[] hexStringToByteArray(String hex) {
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(hex.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		return b;
	}

	public static String byteToHexString(byte b) {
		return String.format("%02x", b);
	}

	public static char[] encodeHex(byte[] data, char[] toDigits) {

		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {

			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];

			out[j++] = toDigits[0x0F & data[i]];

		}
		return out;
	}

	public static String byteArrayToHexString(byte... data) {

		return new String(encodeHex(data, DIGITS_UPPER));
	}

	public static String removeZero(String str) {
		// Count leading zeros
		int i = 0;
		while (str.charAt(i) == '0')
			i++;

		// Convert str into StringBuffer as Strings
		// are immutable.
		StringBuffer sb = new StringBuffer(str);

		// The StringBuffer replace function removes
		// i characters from given index (0 here)
		sb.replace(0, i, "");

		return sb.toString(); // return in String
	}

	public static int byteArrayToInt(byte[] intBytes) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(intBytes);
		return byteBuffer.getInt();
	}

	public static int hexToInt(String hex) {
		Integer i = Integer.parseInt(hex, 16);
		return i;
	}

	public static Date utcSecondsToDate(long unix_seconds) {

		// convert seconds to milliseconds
		Date date = new Date(unix_seconds * 1000L);
		//Date rwandaDate=addHoursToJavaUtilDate(date,2);
		
		// format of the date
		//SimpleDateFormat jdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
		//jdf.setTimeZone(TimeZone.getTimeZone("UTC+2"));
		//String java_date = jdf.format(rwandaDate);
		return date;
	}
	
	public static Date addHoursToJavaUtilDate(Date date, int hours) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, hours);
	    return calendar.getTime();
	}
}
