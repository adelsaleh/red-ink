package com.redink;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDescriptorFetcher {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public IWord[] toWords(String sentence) {
		ArrayList<IWord> words = new ArrayList<IWord>();
		for(String s : sentence.split(" ")) {
			words.add(new StanfordWord(s));
		}
		IWord[] words1 = new IWord[words.size()];
		for(int i=0; i<words.size(); i++){
			words1[i] = words.get(i);
		}
		return words1;
	}
	
	@Test
	public void testGetUsefulWordsAsHistogram() {
		String sentence = "they'd be left in the cold and rain with no roof over their heads";
		IWord[] w = toWords(sentence);
		DescriptorFetcher df = new DescriptorFetcher();
		Map<IWord, Integer> mp = df.getUsefulWordsAsHistogram(w);
		assertTrue(mp.get(new StanfordWord("be")) == 1);
		assertTrue(mp.get(new StanfordWord("with")) == 1);
		assertTrue(mp.get(new StanfordWord("heads")) == 1);
		df = new DescriptorFetcher(new ETag[]{ETag.NNP, ETag.CC});
		mp = df.getUsefulWordsAsHistogram(w);
		try {
			mp.get(new StanfordWord("be"));
			fail();
		}catch(Exception e){}
	}
	
	@Test
	public void testGetUsefulWordsAsArray() {
		String sentence = "they'd be left in the cold and rain with no roof over their heads";
		IWord[] w = toWords(sentence);
		DescriptorFetcher df = new DescriptorFetcher();
		ArrayList<IWord> words = new ArrayList<IWord>();
		for(IWord word : df.getUsefulWords(w)) {
			words.add(word);
		}
		assertTrue(words.get(0).getWord().equals("they"));
		assertTrue(words.get(1).getWord().equals("'d"));
		assertTrue(words.get(2).getWord().equals("be"));
		df = new DescriptorFetcher(new ETag[]{ETag.NNP, ETag.CC});
		words = new ArrayList<IWord>();
		for(IWord word : df.getUsefulWords(w)) {
			words.add(word);
		}
		assertTrue(words.get(0).getWord().equals("and"));
	}
}
