package com.redink;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
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
	
	public Word[] toWords(String sentence) {
		ArrayList<Word> words = new ArrayList<Word>();
		for(String s : sentence.split(" ")) {
			words.add(new Word(s));
		}
		return (Word[])words.toArray();
	}

	@Test
	public void testIsLocation() {
		String location = "Beirut hamra";
		LocationExtractor ex = new LocationExtractor();
		assertTrue(ex.isLocation(toWords(location)));
		location = "Amsterdam 5th avenue";
		assertTrue(ex.isLocation(toWords(location)));
		location = "Zuchini cake";
		assertTrue(ex.isLocation(toWords(location)));
		location = "Lil John is the most overpaid person in the world to say two words";
		assertTrue(ex.isLocation(toWords(location)));
	}

}
