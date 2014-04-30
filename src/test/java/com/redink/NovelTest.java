package com.redink;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class NovelTest extends TestCase {
    
    Novel n;
    @Before
    public void setUp() {
        n = new Novel("test_novel/3lpigs.txt");
    }
    
    @After
    public void tearDown() {

    }

    @Test
    public void testgetSurroundingWords() {
        n.getSurroundingWords(5, 6);
        ArrayList<String> words = new ArrayList<String>();
        for(Word w : n.getSurroundingWords(5, 6)) {
        	words.add(w.getWord());
        }
        String[] words1 = {"the", "three", "little", "pigs", "once", "upon", "a", "time", "there", "were", "three", "little"};
        assertTrue(Arrays.equals(words.toArray(), words1));
        
        words.clear();        
        for(Word w : n.getSurroundingWords(6, 6)) {
        	words.add(w.getWord());
        }
        words1 = new String []{"the", "three", "little", "pigs", "once", "upon", "a", "time", "there", "were", "three", "little" , "pigs"};
        assertTrue(Arrays.equals(words.toArray(), words1));
        
        words.clear();        
        for(Word w : n.getSurroundingWords(7, 3)) {
        	words.add(w.getWord());
        }
        words1 = new String []{"pigs", "once", "upon", "a", "time", "there", "were"};
        assertTrue(Arrays.equals(words.toArray(), words1));
        
        words.clear();        
        for(Word w : n.getSurroundingWords(7, 0)) {
        	words.add(w.getWord());
        }
        words1 = new String[]{"a"};
        assertTrue(Arrays.equals(words.toArray(), words1));
    }
    
    @Test
    public void testGetSurroundingWordsWithArgumentWord(){
    	
    }
}
