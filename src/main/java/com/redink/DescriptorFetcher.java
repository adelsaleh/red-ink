package com.redink;
import java.util.*;

public class DescriptorFetcher {
	private Etag[] allowedTags;

	public DescriptorFetcher(){}

	public DescriptorFetcher(Etag[] allowedTags) {  } 

	public boolean isUseful(Word word) { return false; }

	public Iterator<Word> getUsefulWords(Word[] sentence) { return null; }

	public Map<Word, Integer> getUsefulWordsAsHistogram(Word[] sentence) { return null; }
}