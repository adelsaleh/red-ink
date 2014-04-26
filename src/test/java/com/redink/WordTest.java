package com.redink;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.redink.Word;

/**
 * Unit test for simple App.
 */
public class WordTest extends TestCase {

    Word w1;
    Word w2;
    Word w3;
    Word w4;
    Word w5;
    @Before
    public void setUp() {
        w1 = new Word("hello", 542);
        w2 = new Word("hello", 542);
        
        w3 = new Word("hello", 340);
        w4 = new Word("how", 542);
        w5 = new Word("how", 302);
        w6 = new Word("hello");
        w7 = new Word("how");
    }
    
    @After
    public void tearDown() {

    }

    @Ignore("Not implemented yet")
    @Test
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
