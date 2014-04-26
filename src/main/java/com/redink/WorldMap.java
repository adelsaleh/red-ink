package com.redink;

import java.util.ArrayList;

public class WorldMap {
    /**
     * OVERVIEW: A map of the world to be displayed.
     */

     /*
      * AF(pin): m | m is a map with a pin at each location in
      *                 pins
      */
	private ArrayList<Location> pins;
	public WorldMap(){}

	public void addPin(Location l) { 
        /**
         * EFFECTS: Adds a pin location to be placed on the map.
         */
    }

	public String toHTML() {
		return null;
        /**
         * EFFECTS: Generates the html to be embedded in the page after
         * all the locations have been retrieved.
         */
    }
}
