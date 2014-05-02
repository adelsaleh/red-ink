package com.redink;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Location {
	private double latitude;
	private double longitude;
	private IWord[] locationName;
	private WordCloud wordCloud;

	private Location(){}

	public Location(IWord[] locationName, double lat, double lng) { 
		this.locationName = locationName;
		this.latitude = lat;
		this.longitude = lng;
	} 
	
	public WordCloud generateWordCloud(Novel n, int radius) { 
	/**
	 * EFFECTS: Given a novel and a radius, generates a word cloud containing
	 * 			the interesting words that describe this location and stores it
	 * MODIFIES: wordCloud 
	 * RETURNS:  A WorldCloud containing all the interesting words   	
	 */
        WordCloud wc=null;
        try {
            wc = new WordCloud(locationName, n.getSurroundingSentence(locationName, radius));
        } catch (Exception ex) {
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
        }
		return wc; 
	}

	public double getLatitude() { return latitude; }

	public double getLongitude() { return longitude; }

	public IWord[] getLocationName() { return locationName; }

	public WordCloud getWordCloud() { return null; }
}