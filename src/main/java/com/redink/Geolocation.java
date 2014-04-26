package com.redink;
import java.util.*;

public class Geolocation {
    /**
     * OVERVIEW: Wrapper class for the geolocation api
     */
	public Geolocation(){}

	public static Location getLocation(double lat, double log) throws InvalidCoordinatesException{ 
        /**
         * EFFECTS: Returns the location at (latitude, longitude)
         * RETURNS: A fully populated location object describing (lat, long)
         *          or null if location is not found
         * THROWS: InvalidCoordinatesException if invalid latitude and
         *          longitude values are given.
         */
        return null; 
    }

	public static Location getLocation(String locationName) { 
        /**
         * EFFECTS: Returns the location described by locationName
         * RETURNS: A fully populated location object describing 
         *          locationName
         */
        return null; 
    }
}
