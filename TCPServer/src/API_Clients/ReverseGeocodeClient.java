package API_Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Utils.Util;


public class ReverseGeocodeClient {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		double longitude=30.057963333333333;
		double latitude=-1.9442883333333334;
				String adresses=resolvedAdresses(reverseGeocodeResponse(longitude,latitude));
				System.out.println(adresses);
		
		
	}
	
	public static String reverseGeocodeResponse(double longitude,double latitude){
		String output=null;
		StringBuilder sb=new StringBuilder();
		try {
			String apiKeyValue="AIzaSyDWdmiAMpyw8LmY-9aUduoGhB903nvRDYU";		
			URL ur = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng="+latitude+","+longitude +"&key="+apiKeyValue+"");			
			HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));			
			while((output=buff.readLine())!=null){
				sb.append(output);
			}			
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public static String resolvedAdresses(String str){
		String adress="";
		try {
			JSONObject jsonObj = new JSONObject(str);			
			JSONArray arr = jsonObj.getJSONArray("results");			
			for (int i = 0; i < arr.length(); i++)
			{			
			     adress+= arr.getJSONObject(i).getString("formatted_address")+",";		    			
			}			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return adress;
	}

}
