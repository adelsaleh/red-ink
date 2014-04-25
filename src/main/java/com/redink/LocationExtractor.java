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

	public LocationExtractor(Novel n) {  } 

	public Iterator<Location> locationIterator() { return null; }

	public boolean isLocation(Word w) { return false; }
}
