
package com.redink;
import java.util.*;

public class DescriptorFetcher {
    /**
     * OVERVIEW: Filter for words that are useful to us
     *           based on tags
     */
	
	private String[] allowedTags;

	public DescriptorFetcher(){
        // NOTE: Please compile with oracle JDK since values is a compiler
        //       specific function.
        allowedTags = null;
    }

	public DescriptorFetcher(String[] allowedTags) { 
        this.allowedTags = allowedTags;
    } 

	public boolean isUseful(IWord word) {
        /**
         * EFFECTS: Checks whether a word is useful according the tags
         *          we specified
         * RETURNS: True if useful, false otherwise
         */
        if(allowedTags == null) return true;
		for(int i=0; i<allowedTags.length; i++){
			if(word.getTag()==allowedTags[i]) return true;
		}
        return false; 
    }

	public IWord[] getUsefulWords(IWord[] sentence) {
        /**
         * EFFECTS: Filters the undesirable words from the sentence based
         * 			on allowed tag
         * RETURNS: An iterator that iterates over the useful words
         *          in the sentence
         */
		ArrayList<IWord> usefulWords = new ArrayList<IWord>();
		for(IWord word: sentence){
			for(String tag: allowedTags){
				if(word.getTag().equals(tag)) {
					usefulWords.add(word);
                    break;
                }
			}
		}
		return (IWord[]) usefulWords.toArray();
		
    }

	public Map<IWord, Integer> getUsefulWordsAsHistogram(IWord[] sentence) { 
        /**
         * EFFECTS: generates a histogram containing the number of occurrences
         *          of each word in the sentence 
         * RETURNS: A map where the key is the word and the value is 
         * 			the number of characters
         */
		Map<IWord, Integer> histogram = new HashMap<IWord, Integer>();
		for(IWord word: sentence){
			if(histogram.containsKey(word)) {
				int count = histogram.get(word)+1;
				histogram.put(word, count);
			} else {
				histogram.put(word, 1);
			}
		}
        return null; 
    }
}
