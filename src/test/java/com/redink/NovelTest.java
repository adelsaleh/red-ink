package com.redink;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class NovelTest extends TestCase {
    
    Novel n;
    @Before
    public void setUp() throws FileNotFoundException {
        n = new Novel("test_novels/3lpigs.txt");
    }
    
    @After
    public void tearDown() {

    }
    
    @Test
    public void testConstructor(){
    	
    }
    
    @Test
    public void testGetSurroundingWords() {
        String[] words = new String[] {"the", "three", "little", "pigs", "once", "a", "time", "there", "were", "three", "little"};
        String[] surroundingWords = new String[words.length];
        for(int i=0; i<words.length; i++){
        	surroundingWords[i] = n.getSurroundingWords(5, 6).get(i).getWord();
        }
        assertTrue(Arrays.equals(surroundingWords, words));
        
        
        words = new String []{"the", "three", "little", "pigs", "once","upon", "time", "there", "were", "three", "little" , "pigs"};
        surroundingWords = new String[words.length];
        for(int i=0; i<words.length; i++){
        	surroundingWords[i] = n.getSurroundingWords(6, 6).get(i).getWord();
        }
        assertTrue(Arrays.equals(surroundingWords, words));

        
        words = new String []{"once", "upon", "a", "there", "were", "three"};
        surroundingWords = new String[words.length];
        for(int i=0; i<words.length; i++){
        	surroundingWords[i] = n.getSurroundingWords(7, 3).get(i).getWord();
        }
        assertTrue(Arrays.equals(surroundingWords, words));

        
        words = new String[]{"a"};
        try {
        	n.getSurroundingWords(7, 0);
        	fail();
        }catch(IllegalArgumentException e){}
    	
    }
    
    @Test
    public void testGetSurroundingWordsByWord() {
        String[] words = new String[] {"of", "planks", "of", "seasoned", "wood", "it", "took", "him", "two", "days"};
        String[] surroundingWords = new String[words.length];
        ArrayList<Word> ws = n.getSurroundingWords("clunk", 5);
        for(int i=0; i<words.length; i++){
        	surroundingWords[i] = ws.get(i).getWord();
        }
        System.out.println(Arrays.toString(surroundingWords));
        assertTrue(Arrays.equals(surroundingWords, words));
        
        assertTrue(n.getSurroundingWords("hdffsdgsdgsd", 5).size() == 0);
    }
    
}
