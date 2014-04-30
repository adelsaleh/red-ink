package com.redink;

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
public class GeolocationTest extends TestCase {

    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {

    }

    private boolean locationSucceed(double lat, double lng, String address) {
        /**
         * EFFECTS: Checks whether the location at lat,lng matches the
         *          address provided
         * RETURNS: True if they match, false otherwise
         */
    	Location l = null;
		try {
			l = Geolocation.getLocation(lat, lng);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (l == null) return false;
        return (l.getLocationName().equals(address));
    }

    
    @Ignore("Not implemented yet")
    public void testGetLocationWithProperCoords() {
        /**
         * Considering the fact that the api is probably well tested
         * (because if we can't trust google who can we trust right?)
         * branch coverage will be sufficient.
         */
        assertTrue(locationSucceed(40.714224,-73.961452,
            "277 Bedford Avenue, Brooklyn, NY 11211, USA"));
        assertTrue(locationSucceed(90,-73.961452,"407 9th St, Craig, AK 99921, USA"));
        assertTrue(locationSucceed(-90,180,"Antarctica"));
        assertTrue(locationSucceed(-90,-180,"Antarctica"));
        assertTrue(locationSucceed(-90,40,"Antarctica"));
        
        assertFalse(locationSucceed(40, -180, ""));
        assertFalse(locationSucceed(40, 180, ""));

    }
    
    @Ignore("Not implemented yet")
    public void testGetLocationWithInvalidCoords() {
        assertTrue(locationFail(-90.01, 30.4322));
        assertTrue(locationFail(-60.00, 180.4322));
        assertTrue(locationFail(-70.00, -180.4322));
        assertTrue(locationFail(90.01, 4.2134));
        assertTrue(locationFail(90.01, 180.01));
    }
    
    private boolean locationFail(double lat, double lng) {
        /**
         * EFFECTS: Checks whether an exception is thrown when trying
         * to get the specified position
         * RETURNS: True if exception is thrown, false otherwise
         */
        boolean ret = true;
        Location l;
        try {
            l = Geolocation.getLocation(lat, lng);
            ret = false;
        }catch(IllegalArgumentException e) { }
        return ret;
    }

    private boolean locationByAddress(String address) {
        Location l = Geolocation.getLocation(address);
        return l==null;
    }

    @Ignore("Not implemented yet")
    public void testGetLocationWithValidAddress() {
        assertTrue(locationByAddress("London"));
        assertFalse(locationByAddress("adfsfjafj"));
    }
}
