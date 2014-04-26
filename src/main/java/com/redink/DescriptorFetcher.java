package com.redink;
import java.util.*;

public class DescriptorFetcher {
    /**
     * OVERVIEW: Filter for words that are useful to us
     *           based on tags
     */
	private ETag[] allowedTags;

	public DescriptorFetcher(){}

	public DescriptorFetcher(ETag[] allowedTags) {  } 

	public boolean isUseful(Word word) {
        /**
         * EFFECTS: Returns whether a word is useful according the tags
         *          we specified
         * RETURNS: True if useful, false otherwise
         */
        return false; 
    }

	public Iterator<Word> getUsefulWords(Word[] sentence) {
        /**
         * EFFECTS: Returns an iterator that iterates over the useful words
         *          in the sentence
         */
        return null; 
    }

	public Map<Word, Integer> getUsefulWordsAsHistogram(Word[] sentence) { 
        /**
         * EFFECTS: Returns a histogram containing the number of occurrences
         *          of each word in the sentence
         */
        return null; 
    }
}
