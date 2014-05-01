
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
         * EFFECTS: Checks whether a word is useful according the tags
         *          we specified
         * RETURNS: True if useful, false otherwise
         */
		for(int i=0; i<allowedTags.length; i++){
			if(word.getTag()==allowedTags[i]) return true;
		}
        return false; 
    }

	public Word[] getUsefulWords(Word[] sentence) {
        /**
         * EFFECTS: Filters the undesirable words from the sentence based
         * 			on allowed tag
         * RETURNS: An iterator that iterates over the useful words
         *          in the sentence
         */
		ArrayList<Word> usefulWords = new ArrayList<Word>();
		for(Word word: sentence){
			for(ETag tag: allowedTags){
				if(word.getTag().equals(tag))
					usefulWords.add(word);
			}
		}
		return (Word[]) usefulWords.toArray();
		
    }

	public Map<Word, Integer> getUsefulWordsAsHistogram(Word[] sentence) { 
        /**
         * EFFECTS: generates a histogram containing the number of occurrences
         *          of each word in the sentence 
         * RETURNS: A map where the key is the word and the value is 
         * 			the number of characters
         */
		Map<Word, Integer> histogram = new HashMap<Word, Integer>();
		for(Word word: sentence){
			if(histogram.containsKey(word)) {
				int count = histogram.get(word);
				count++;
				histogram.put(word, count);
			} else {
				histogram.put(word, 1);
			}
		}
        return null; 
    }
}
