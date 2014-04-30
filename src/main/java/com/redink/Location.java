package com.redink;
import java.util.*;

public class Location {
	private double latitude;
	private double longitude;
	private Word[] locationName;
	private WordCloud wordCloud;

	public Location(){}

	public Location(Word[] locationName, double lat, double log) {  } 
	
	public WordCloud generateWordCloud(Novel n, int radius) { 
	/**
	 * EFFECTS: Given a novel and a radius, generates a word cloud containing
	 * 			the interesting words that describe this location and stores it
	 * MODIFIES: wordCloud 
	 * RETURNS:  A WorldCloud containing all the interesting words   	
	 */
		return null; 
	}

	public double getLatitude() { return latitude; }

	public double getLongitude() { return longitude; }

	public Word[] getLocationName() { return locationName; }

	public WordCloud getWordCloud() { return null; }
}