import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Main {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();

		try {
			//URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=Beyrouth&sensor=true&key=AIzaSyAjsfXkh2j_nPej1OojKT-4yjZeN5UyGOU");
			String address = "1600+Amphitheatre+Parkway,+Mountain+View,+CA";
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=true&key=AIzaSyAjsfXkh2j_nPej1OojKT-4yjZeN5UyGOU");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			Object obj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray results = (JSONArray) jsonObject.get("results");
			JSONObject geometry = ((JSONObject) ((JSONObject) results.get(0)).get("geometry"));
			JSONObject location = (JSONObject) geometry.get("location");
			System.out.println(location.get("lat") + "  " + location.get("lng"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}