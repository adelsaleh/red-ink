package com.redink;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

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
    public void setUp(){
        try {
			n = new Novel("test_novels/");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    @After
    public void tearDown() {

    }

    @Ignore("Not implemented yet")
    public void testgenerateWordCloud() {
                
    }
}
