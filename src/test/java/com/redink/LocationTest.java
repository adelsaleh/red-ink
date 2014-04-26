package com.redink;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.redink.Location;
import com.redink.Geolocation;
import com.redink.InvalidCoordinatesException;

/**
 * Unit test for simple App.
 */
public class LocationTest extends TestCase {
    
    Novel n;
    Word location;
    @Before
    public void setUp() {
        n = new Novel("test_novels/");
    }
    
    @After
    public void tearDown() {

    }

    @Ignore("Not implemented yet")
    @Test
    public void testgenerateWordCloud() {
                
    }
}
