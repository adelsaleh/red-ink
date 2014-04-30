package com.redink;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.redink.Location;
import com.redink.Geolocation;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class GeolocationTest extends TestCase{

    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {

    }

    private boolean locationSucceed(double lat, double lng, Word[] address) {
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
        Word[] locAddress = l.getLocationName();
        for(int i = 0; i < address.length; i++) {
        	if (!address[i].getWord().equals(locAddress[i].getWord())) {
        		return false;
        	}
        		
        }
        return true;
    }

    
    @Test
    public void testGetLocationWithProperCoords() {
        /**
         * Considering the fact that the api is probably well tested
         * (because if we can't trust google who can we trust right?)
         * branch coverage will be sufficient.
         */
        assertTrue(locationSucceed(40.714224,-73.961452,
            new Word[]{new Word("277"),  new Word("Bedford"), new Word("Avenue"), new Word("Brooklyn"), 
        						new Word("NY"), new Word("11211"), new Word("USA")}));
         
        Word[] w = new Word[]{new Word("407"), new Word("9th"), new Word("St"), new Word("Craig"), 
        		new Word("AK"), new Word("99921"), new Word("USA")};
        assertTrue(locationSucceed(90.0,-73.961452, w));
        assertTrue(locationSucceed(-90,180,new Word[]{new Word("Antarctica")}));
        assertTrue(locationSucceed(-90,-180,new Word[]{new Word("Antarctica")}));
        assertTrue(locationSucceed(-90,40,new Word[]{new Word("South"), new Word("Pole"), new Word("Station"), 
        		new Word("Antarctica")}));
        
        assertFalse(locationSucceed(40, -180, new Word[]{new Word("")}));
        assertFalse(locationSucceed(40, 180, new Word[]{new Word("")}));

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
        return l!=null;
    }

    @Ignore("Not implemented yet")
    public void testGetLocationWithAddress() {
        assertTrue(locationByAddress("London"));
        assertFalse(locationByAddress("adfsfjafj"));
    }
}
