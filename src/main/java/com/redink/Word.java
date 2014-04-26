package com.redink;
import java.util.*;

public class Word {
	/**
	 * OVERVIEW: A word is a string of characters in a novel.
	 */

	 /*
	  * AF(word, offset, tag)=w | w is the string word of type tag at a
	  * 	distance offset from the start of an unknown novel.
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
}
