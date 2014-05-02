package com.redink;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocationExtractorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLocationIterator() {
		
	}
	
	public IWord[] toWords(String sentence) {
		ArrayList<IWord> words = new ArrayList<IWord>();
		for(String s : sentence.split(" ")) {
			words.add(new StanfordWord(s, 0, ETag.FW));
		}
        IWord[] w = new StanfordWord[words.size()];
        words.toArray(w);
		return w;
	}

	@Test
	public void testIsLocation() {
		String location = "Beirut Hamra";
		LocationExtractor ex = new LocationExtractor();
        System.out.println(Arrays.toString(toWords(location)));
		assertTrue(ex.isLocation(toWords(location)));
		location = "Amsterdam 5th avenue";
		assertTrue(ex.isLocation(toWords(location)));
		location = "Zuchini cake";
		assertTrue(ex.isLocation(toWords(location)));
		location = "Lil John is the most overpaid person in the world to say two words";
		assertTrue(ex.isLocation(toWords(location)));
	}

}
