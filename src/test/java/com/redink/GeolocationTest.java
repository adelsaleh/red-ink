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

    private boolean locationSucceed(double lat, double lng, IWord[] address) {
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
        IWord[] locAddress = l.getLocationName();
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
            new StanfordWord[]{new StanfordWord("277"),  new StanfordWord("Bedford"), new StanfordWord("Avenue"), new StanfordWord("Brooklyn"), 
        						new StanfordWord("NY"), new StanfordWord("11211"), new StanfordWord("USA")}));
         
        IWord[] w = new StanfordWord[]{new StanfordWord("407"), new StanfordWord("9th"), new StanfordWord("St"), new StanfordWord("Craig"), 
        		new StanfordWord("AK"), new StanfordWord("99921"), new StanfordWord("USA")};
        assertTrue(locationSucceed(90.0,-73.961452, w));
        assertTrue(locationSucceed(-90,180,new StanfordWord[]{new StanfordWord("Antarctica")}));
        assertTrue(locationSucceed(-90,-180,new StanfordWord[]{new StanfordWord("Antarctica")}));
        assertTrue(locationSucceed(-90,40,new StanfordWord[]{new StanfordWord("South"), new StanfordWord("Pole"), new StanfordWord("Station"), 
        		new StanfordWord("Antarctica")}));
        
        assertFalse(locationSucceed(40, -180, new StanfordWord[]{new StanfordWord("")}));
        assertFalse(locationSucceed(40, 180, new StanfordWord[]{new StanfordWord("")}));

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
