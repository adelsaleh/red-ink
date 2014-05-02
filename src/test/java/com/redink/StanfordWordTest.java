package com.redink;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.redink.StanfordWord;

/**
 * Unit test for simple App.
 */
public class StanfordWordTest extends TestCase {

    StanfordWord w1;
    StanfordWord w2;
    StanfordWord w3;
    StanfordWord w4;
    StanfordWord w5;
    StanfordWord w6;
    StanfordWord w7;
    
    @Before
    public void setUp() {
        w1 = new StanfordWord("hello", 542);
        w2 = new StanfordWord("hello", 542);
        w3 = new StanfordWord("hello", 340);
        w4 = new StanfordWord("how", 542);
        w5 = new StanfordWord("how", 302);
        w6 = new StanfordWord("hello");
        w7 = new StanfordWord("how");
    }
    
    @After
    public void tearDown() {

    }
    
    @Ignore("Not implemented yet")
    public void testWord() {
    	try{
    		StanfordWord w10 = new StanfordWord(null);
    		fail("Cannot have null string as parameter");
    	}catch(Exception e) {}    	
    }
    
    @Ignore("Not implemented yet")
    public void testEquals() {
        assertTrue(w1.equals(w2));
        assertFalse(w1.equals(w3));
        assertFalse(w1.equals(w4));
        assertTrue(w1.equals(w6));
        assertFalse(w1.equals(w7));
        assertFalse(w4.equals(w5));
        assertFalse(w3.equals(w5));
    }
}
