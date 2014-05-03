package com.redink;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class Geolocation {
    /**
     * OVERVIEW: Wrapper class for the geolocation api
     */
	private static String apiKey;
	private final static String latIdentifier = "{latitude}";
	private final static String lngIdentifier = "{longitude}";
	private final static String addressIdentifier = "{address}";
	private final static String keyIdentifier = "{API_KEY}";
	private static String normalUrl;
	private static String reverseUrl;
	
	private Geolocation(){}
	
	static {
        apiKey = Config.get("com.google.geolocation").toString();
        String url = Config.get("com.google.geolocation.reverse.url").toString();
        url = url.replace(keyIdentifier, apiKey);
        reverseUrl = url;
        url = Config.get("com.google.geolocation.normal.url");
        url = url.replace(keyIdentifier, apiKey);
        normalUrl = url;
	}
	
	private static String getResponse(URLConnection c) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();
        return sb.toString();
	}

	public static Location getLocation(double latitude, double longitude){ 
        /**
         * EFFECTS: Retrieves the location using coordinates (latitude, longitude)
         * RETURNS: A fully populated location object describing (latitude, longitude)
         *          or null if location is not found
         * THROWS: IllegalArgumentException if invalid latitude and
         *          longitude values are given.
         */
		if(latitude < -90.0  || latitude > 90.0) throw new IllegalArgumentException("Latitude must be between -90 and 90");
		if(longitude < -180.0  || longitude > 180.0) throw new IllegalArgumentException("Longitude must be between -180 and 180");
		String newUrl = reverseUrl.replace(latIdentifier, Double.toString(latitude));
		newUrl = newUrl.replace(lngIdentifier, Double.toString(longitude));
		URL url;
		Location result = null;
		try {
			url = new URL(newUrl);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.connect();
			if(conn.getResponseCode() != 200) {
				throw new IOException("Invalid response code");
			}
			System.out.println(latitude + " " + longitude);
			System.out.println(newUrl);
			JSONObject obj = new JSONObject(getResponse(conn));
			conn.disconnect();
			if(obj.getString("status").equals("OK")) {
				String formattedAddress = obj.getJSONArray("results").getJSONObject(0).getString("formatted_address");
				result = new Location(filter(formattedAddress), latitude, longitude);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result; 
    }
	
	private static IWord[] filter(String formattedAddress){
		String[] swords = formattedAddress.split("[^a-zA-Z0-9\']+");
		IWord[] words = new IWord[swords.length];
		for(int i=0; i<words.length; i++){
			words[i] = new StanfordWord(swords[i]);
		}
		
		return words;
	}
	
	public static Location getLocation(String locationName) { 
        /**
         * EFFECTS: Retrieves the location described by locationName
         * RETURNS: A fully populated location object describing 
         *          locationName
         */
		String newUrl = normalUrl.replace(addressIdentifier, locationName);
		URL url;
		Location result = null;
		try {
			url = new URL(newUrl);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.connect();
			if(conn.getResponseCode() != 200) {
				throw new IOException("Invalid response code");
			}
			System.out.println(newUrl);
			JSONObject obj = new JSONObject(getResponse(conn));
			conn.disconnect();
			if(obj.getString("status").equals("OK")) {
				JSONObject latlng = obj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
				result = new Location(filter(locationName), latlng.getDouble("lat"), latlng.getDouble("lng"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result; 
    }
}
