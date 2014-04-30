package com.redink;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordCloudTest {
	HashMap<Word, Integer> hist1;
	HashMap<Word, Integer> hist2;
	HashMap<Word, Integer> hist3;
	HashMap<Word, Integer> hist4;
	WordCloud w1;
	WordCloud w2;
	WordCloud w3;
	WordCloud w4;
	
	@Before
	public void setUp() throws Exception {
		hist1 = new HashMap<Word, Integer>();
		hist1.put(new Word("valley"), 12);
		hist1.put(new Word("river"), 17);
		hist1.put(new Word("road"), 12);
		hist1.put(new Word("dry"), 17);
		hist1.put(new Word("silicon"), 12);
		hist1.put(new Word("house"), 17);
		hist1.put(new Word("bayan"), 12);
		hist1.put(new Word("alaa"), 17);
		hist1.put(new Word("moustafa"), 12);
		hist1.put(new Word("hakuna(mattata)"), 17);
		
		hist2 = null;
		
		hist3 = new HashMap<Word, Integer>();
		
		hist4 = new HashMap<Word, Integer>();
		hist4.put(new Word("Sabine"), 66);
		
		w1 = new WordCloud("ali-jaber-nabatiyeh", hist1);
		w4 = new WordCloud("abou baker-el bayrouteh-trablos", hist1);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testWordCloud(){
		try{
			w2 = new WordCloud("hussein-tahsin-lessa", hist2);
			w3 = new WordCloud("Kanj-Fakher-Niha", hist3);
			fail("Cannot have empty histogram");
		} catch(Exception e){
		}		
	}
	
	@Test
	public void testGetImageURL() {
		assertTrue(w1.getImageURL()!=null);
		assertFalse(w1.getImageURL().isEmpty());
		assertTrue(w4.getImageURL()!=null);
		assertFalse(w4.getImageURL().isEmpty());
	}

}
