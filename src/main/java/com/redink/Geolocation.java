package com.redink;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.cert.Certificate;
import java.util.*;

import javax.imageio.stream.FileImageInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.json.JSONObject;

public class Geolocation {
    /**
     * OVERVIEW: Wrapper class for the geolocation api
     */
	private static String apiKey;
	private final static String latIdentifier = "{latitude}";
	private final static String lngIdentifier = "{longitude}";
	private final static String keyIdentifier = "{API_KEY}";
	private static String normalUrl;
	private static String reverseUrl;
	
	private Geolocation(){}
	
	static {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File("configuration.properties")));
			apiKey = (String)p.get("com.google.geolocation");
			String url = (String)p.get("com.google.geolocation.reverse.url");
			url.replace(keyIdentifier, apiKey);
			reverseUrl = url;
			url = (String)p.get("com.google.geolocation.normal.url");
			url.replace(keyIdentifier, apiKey);
			normalUrl = url;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if(latitude < -180.0  || latitude > 180.0) throw new IllegalArgumentException("Longitude must be between -180 and 180");
		String newUrl = normalUrl.replace(latIdentifier, Double.toString(latitude));
		newUrl = newUrl.replace(lngIdentifier, Double.toString(latitude));
		URL url;
		String result;
		try {
			url = new URL(newUrl);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.connect();
			String response = (String)conn.getContent();
			conn.disconnect();
			JSONObject obj = new JSONObject(response);
			obj.getJSONArray("results").getJSONObject(0).getString("formatted_address");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return null; 
    }

	public static Location getLocation(String locationName) { 
        /**
         * EFFECTS: Retrieves the location described by locationName
         * RETURNS: A fully populated location object describing 
         *          locationName
         */
		
        return null; 
    }
}
