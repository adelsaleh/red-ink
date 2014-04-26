package com.redink;
import java.util.*;

public class Word {
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
      *                         OR ((w1.offset == -1 OR w2.offset == -1) AND
      *                         (w1.word == w2.word))
      *     w1.word == w2.word iff toLower(w1.word) == toLower(w2.word)
      *     where toLower(str) is defined as a function that maps str
      *     to a string ret where all the characters of ret are the lowercase
      *     characters of str
	  */
	private String word;
	private int offset;
	private ETag tag;

	public Word(){}

	public Word(String word, int offset) {  } 

	public Word(String word) {  } 

	public String getWord() { return null; }

	public int getOffset() { return 0; }

	public ETag getTag() { return null; }

    public boolean equals(Word w2) {
        /**
         * EFFECTS: Checks if AF(this) == AF(w2)
         * RETURNS: True if they are equal, false otherwise.
         */
    }
}
