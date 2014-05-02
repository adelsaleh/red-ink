package com.redink;
import java.util.*;

public class WordCloud {
    /**
     * OVERVIEW: WordCloud: A set of (word, occurrence) tuple displayed in 
     * a compressed image.
     */

     /*
      * AF(location, histogram): An image containing histogram.keys.
      */
    private String location;
    private Map<IWord, Integer> histogram;

    public WordCloud(){}

    public WordCloud(String location, Map<IWord, Integer> histogram) {  } 

    
    public String getImageURL() {
    /**
     * EFFECTS: Generates the wordcloud image, writes it to
     *             persistent storage and returns the url
     * RETURNS: The URL of the image
     * THROWS: IOException in case disk write fails
     *         InvalidArgumentsException in case
     *         either histogram or location is empty.
     */
        return null; 
    }
}
