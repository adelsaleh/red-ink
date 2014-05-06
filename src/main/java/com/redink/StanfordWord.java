package com.redink;
import edu.stanford.nlp.ling.TaggedWord;
import java.util.*;
import java.util.regex.Pattern;

public class StanfordWord implements IWord{
	/**
	 * OVERVIEW: A word is a string of characters at a certain position 
     *              in a novel.
	 */

	 /*
	  * AF(word, offset, tag)=w | w is the string word of type tag at a
	  * 	distance offset from the start of an unknown novel.
      *
      * Notes:
      *     AF(w1) == AF(w2) iff (w1.offset==w2.offset AND w1.word == w2.word)
	  */
	private int offset;
    private TaggedWord taggedWord;
    private ETag tag;
	public static final Comparator<IWord> offset_comparator = new Comparator<IWord>() {
		public int compare(IWord w1, IWord w2) {
			return w1.getOffset()-w2.getOffset();
		}
	};
	public static final Comparator<IWord> string_comparator = new Comparator<IWord>() {
		public int compare(IWord w1, IWord w2) {
			return w1.getWord().compareTo(w2.getWord());
		}
	};
	
	public StanfordWord(){}
	
	public StanfordWord(String word) {  
	/**
	 * REQUIRES: word is non empty or null	
	 */	
			if(Pattern.matches(" *", word)) throw new IllegalArgumentException("Word cannot be empty");
			this.taggedWord = new TaggedWord(word.toLowerCase());
		}

	public StanfordWord(String word, int offset) {  
	/**
	 * REQUIRES: word is non empty or null	
	 */
		if(Pattern.matches(" *", word)) throw new IllegalArgumentException("Word cannot be empty");
        this.taggedWord = new TaggedWord(word.toLowerCase());
		this.offset = offset;
	} 
    public StanfordWord(TaggedWord word, int offset) {  
	/**
	 * REQUIRES: word is non empty or null	
	 */
        word.setWord(word.word());
        this.taggedWord = word;
		this.offset = offset;
	}
	public StanfordWord(String word, int offset, String tag) {  
	/**
	 * REQUIRES: word is non empty or null	
	 */
		if(Pattern.matches(" *", word)) throw new IllegalArgumentException("Word cannot be empty");
        this.taggedWord = new TaggedWord(word, tag);
		this.offset = offset;
	} 
	

	public String getWord() { return taggedWord.word(); }
	public int getOffset() { return offset; }
	public String getTag() { return taggedWord.tag(); }
	
	@Override
	public int hashCode() {
		return getWord().hashCode();
	}

	@Override
	public String toString(){
		return getWord() + " ";
	}
	
    public boolean equals(IWord w2) {	
    /**
     * EFFECTS: Checks if AF(this) == AF(w2)
     * RETURNS: True if they are equal, false otherwise.
     */
    	return (w2.getWord().equals(getWord()) && w2.getOffset()==getOffset());
    }

	public int compareTo(IWord anotherWord) {
		return offset_comparator.compare(this, anotherWord);
    }
}
