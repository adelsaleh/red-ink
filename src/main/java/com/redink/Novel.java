package com.redink;
import java.util.*;

public class Novel {
    /**
     * OVERVIEW: A list of words gotten from a file without
     *           punctuation.
     */

     /*
      * AF(words, filename)=A list of all the tagged words contained in
      * the novel provided at filename.
      *
      * Representation Invariant: words contains no punctuation and contains
      *                           uninteresting words
      */
	private Word[] words;
	private String filename;

	private Novel(){}

	public Novel(String path) {  } 

	public Word[] getSurroundingWords(Word w, int radius) { 
        /**
         * EFFECTS: Gets all the words surrounding the first instance
         *          of w
         * RETURNS: A list of words containing all the words
         * THROWS: WordNotFoundException in case the word is not found
         */
        return null; 
    }

	public Word[] getSurroundingWords(int offset, int radius) { 
        /**
         * EFFECTS: Gets the words from offset-radius to offset+radius
         *          inclusive
         * RETURNS: A list containing all the words
         * THROWS: OutOfBoundsException if offset is less than 0
         *          or if offset is bigger than the size of the novel.
         */
        return null; 
    }
}
