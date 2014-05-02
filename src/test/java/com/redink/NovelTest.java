package com.redink;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for simple App.
 */
public class NovelTest {
    
    static Novel n;
    @BeforeClass
    public static void oneTimeSetUp() throws FileNotFoundException {
        System.out.println(Runtime.getRuntime().maxMemory());
        n = new Novel("test_novels/3lpigs.txt");
    }
    
    @Test 
    public void testGetSurroundingWordsOfSentence() {
        String[] words = new String[] {"the", "once", "upon", "a", "time", "there", "were",
                                       "who","left","their","happier", "than", 
                                       "the", "and", "they", "easily",
                                        "to", "rain", "the","started", "to", "feel",
                                        "an", "lnch", "the", "watched", "him", "and"
                                       };
        IWord[] sentence = {new StanfordWord("three"), new StanfordWord("little"), new StanfordWord("pigs")};
        IWord[] surroundingWords = n.getSurroundingSentence(sentence, 3);
		String[] cmp = new String[surroundingWords.length];
        System.out.println(Arrays.toString(surroundingWords));
        for(int i=0; i<surroundingWords.length; i++){
        	cmp[i] = surroundingWords[i].getWord();
        }
        assertTrue(Arrays.equals(cmp, words));	
        words = new String[] {"the", "three", "little", "pigs", 
                              "once", "upon", "a", "time", "there", "were",
                              "who","left","their","mummy", "and", "daddy", "to",
                              "having","fun", "none", "were", "happier",
                              "than", "the", "and", "they", "easily", "made",
                              "friends", "with", "everyone", "came", "and", "it",
                              "began", "to", "rain", "the", "started", "to", "feel",
                              "they", "needed", "a", "real", "house", "did", "not",
                              "budge", "an", "lnch", "the", "watched", "him", "and",
                              "their", "fear", "began", "to"
                             };
        sentence = new IWord[]{new StanfordWord("three"), new StanfordWord("little"), new StanfordWord("pigs")};
        surroundingWords = n.getSurroundingSentence(sentence, 7);
		cmp = new String[surroundingWords.length];
        for(int i=0; i<surroundingWords.length; i++){
        	cmp[i] = surroundingWords[i].getWord();
        }
    }
    @Test
    public void testGetSurroundingWords() {
        String[] words = new String[] {"the", "three", "little", "pigs", "once", "a", "time", "there", "were", "three", "little"};
        IWord[] surroundingWords = n.getSurroundingWords(5, 6);
		String[] cmp = new String[surroundingWords.length];
        for(int i=0; i<words.length; i++){
        	cmp[i] = surroundingWords[i].getWord();
        }
        System.out.println(Arrays.toString(surroundingWords));
        assertTrue(Arrays.equals(cmp, words));
        
        
        words = new String []{"the", "three", "little", "pigs", "once","upon", "time", "there", "were", "three", "little" , "pigs"};
        surroundingWords = n.getSurroundingWords(6, 6);
		cmp = new String[surroundingWords.length];
        for(int i=0; i<words.length; i++){
        	cmp[i] = surroundingWords[i].getWord();
        }
        assertTrue(Arrays.equals(cmp, words));

        
        words = new String []{"once", "upon", "a", "there", "were", "three"};
        surroundingWords = n.getSurroundingWords(7, 3);
	    cmp = new String[surroundingWords.length];

        for(int i=0; i<words.length; i++){
        	cmp[i] = surroundingWords[i].getWord();
        }
        assertTrue(Arrays.equals(cmp, words));

        
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
        IWord[] ws = n.getSurroundingWords("clunk", 5);
        for(int i=0; i<ws.length; i++){
        	surroundingWords[i] = ws[i].getWord();
        }
        System.out.println(Arrays.toString(surroundingWords));
        assertTrue(Arrays.equals(surroundingWords, words));
        
        assertTrue(n.getSurroundingWords("hdffsdgsdgsd", 5).length == 0);
    }
    
}
