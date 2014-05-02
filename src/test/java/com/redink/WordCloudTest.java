package com.redink;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordCloudTest {
	IWord[] hist1;
	IWord[] hist2;
	IWord[] hist3;
    IWord[] hist4;
	WordCloud w1;
	WordCloud w2;
	WordCloud w3;
	WordCloud w4;
	public IWord[] toWords(String sentence) {
		ArrayList<IWord> words = new ArrayList<IWord>();
		for(String s : sentence.split("-")) {
			words.add(new StanfordWord(s, 0, ETag.FW));
		}
        IWord[] w = new StanfordWord[words.size()];
        words.toArray(w);
		return w;
	}
	@Before
	public void setUp() throws Exception {
		hist1 = new IWord[]{new StanfordWord("valley"), 
           new StanfordWord( "river"),new StanfordWord("road"), new StanfordWord("dry"), 
           new StanfordWord("silicon"), new StanfordWord("house"),
           new StanfordWord("bayan"),new StanfordWord("alaa"), 
           new StanfordWord("moustafa"), new StanfordWord("hakuna"), new StanfordWord("matata")};
		
		hist2 = null;
		
		hist3 = new IWord[0];
		
		hist4 = new IWord[]{new StanfordWord("Sabine")};
		
		w1 = new WordCloud(toWords("ali-jaber-nabatiyeh"), hist1);
		w4 = new WordCloud(toWords("abou baker-el bayrouteh-trablos"), hist1);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testWordCloud(){
		try{
			w2 = new WordCloud(toWords("hussein-tahsin-lessa"), hist2);
			w3 = new WordCloud(toWords("Kanj-Fakher-Niha"), hist3);
			fail("Cannot have empty histogram");
		} catch(Exception e){
		}		
	}
	
	@Test
	public void testGetImageURL() {
		assertTrue(w1.getURL()!=null);
		assertFalse(w1.getURL().isEmpty());
		assertTrue(w4.getURL()!=null);
		assertFalse(w4.getURL().isEmpty());
	}

}
