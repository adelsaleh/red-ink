package com.redink;
import java.util.*;

public class LocationExtractor {
    /**
     * OVERVIEW: Contains the list of locations in a novel
     */

     /*
      * AF(novel)= le | le contains the list of locations
      * in the novel.
      */
	private Novel novel;

	public LocationExtractor(){}

	public LocationExtractor(Novel novel) {  } 

	public Iterator<Location> locationIterator() { 
<<<<<<< Updated upstream
	/**
	 * EFFECTS: Retrieves all locations from novel
	 * RETURNS: An Iterator over the locations found in the novel	
	 */
		return null; 
	}

	public boolean isLocation(Word[] words) { 
	/**
	 * EFFECTS: Checks if words contains a location	
	 * RETURNS: true if words contains a location, false otherwise
	 */
		return false; 
	}
=======
        /**
         * EFFECTS: Loops over all the locations in this.novel.
         * RETURNS: An iterator which loops over all the locations.
         */
        return null; 
    }

	public boolean isLocation(Word[] sentence) { 
        /**
         * EFFECTS: Checks if sentence represents a location.
         * RETURNS: true if sentence is a location, false otherwise
         */
        return false; 
    }
>>>>>>> Stashed changes
}
