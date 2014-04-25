package com.redink;
import java.util.*;

public class Location {
	private double lat;
	private double log;
	private String locationName;
	private WordCloud wordcloud;

	public Location(){}

	public Location(String locationName, double lat, double log) {  } 

	public WordCloud generateWordCloud(Novel n, Word location, int radius) { return null; }

	public double getLatitude() { return 0.0; }

	public double getLongitude() { return 0.0; }

	public String getLocationName() { return null; }

	public WordCloud getWordCloud() { return null; }
}