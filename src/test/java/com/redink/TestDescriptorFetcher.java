package com.redink;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import java.util.*;

public class TestDescriptorFetcher {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public Word[] toWords(String sentence) {
		ArrayList<Word> words = new ArrayList<Word>();
		for(String s : sentence.split(" ")) {
			words.add(new Word(s));
		}
		return (Word[])words.toArray();
	}
	
	@Ignore
	public void testGetUsefulWordsAsHistogram() {
		String sentence = "they'd be left in the cold and rain with no roof over their heads";
		Word[] w = toWords(sentence);
		DescriptorFetcher df = new DescriptorFetcher();
		Map<Word, Integer> mp = df.getUsefulWordsAsHistogram(w);
		assertTrue(mp.get(new Word("be")) == 1);
		assertTrue(mp.get(new Word("with")) == 1);
		assertTrue(mp.get(new Word("heads")) == 1);
		df = new DescriptorFetcher(new ETag[]{ETag.NNP, ETag.CC});
		mp = df.getUsefulWordsAsHistogram(w);
		try {
			mp.get(new Word("be"));
			fail();
		}catch(Exception e){}
	}
	
	public void testGetUsefulWordsAsArray() {
		String sentence = "they'd be left in the cold and rain with no roof over their heads";
		Word[] w = toWords(sentence);
		DescriptorFetcher df = new DescriptorFetcher();
		ArrayList<Word> words = new ArrayList<Word>();
		for(Word word : df.getUsefulWords(w)) {
			words.add(word);
		}
		assertTrue(words.get(0).getWord().equals("they"));
		assertTrue(words.get(1).getWord().equals("'d"));
		assertTrue(words.get(2).getWord().equals("be"));
		df = new DescriptorFetcher(new ETag[]{ETag.NNP, ETag.CC});
		words = new ArrayList<Word>();
		for(Word word : df.getUsefulWords(w)) {
			words.add(word);
		}
		assertTrue(words.get(0).getWord().equals("and"));
	}
}
