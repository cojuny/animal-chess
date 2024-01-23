package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {


    @Test
    public void updateLocationTest(){
        Location location = new Location(1, 1); // Original location
        Location newlocation = new Location(3, 3);  // New location
        location.updateLocation(newlocation);
        assert location.getX() == 3; // Check whether X has changed from original to new (1 to 3)
        assert location.getY() == 3; // Check whether Y has changed from original to new (1 to 3)
    }

    @Test

    public void  isVaildTest(){
        Location location_valid = new Location(2, 2); // Valid location
        assert Location.isValid(location_valid);    // expect true
        Location location_invalid = new Location(100, 100); // Invalid Location
        assert !Location.isValid(location_invalid);    // expect false

    }

    @Test

    public void equalsTest(){
        Location location_1 = new Location(5, 5);   // location_1 with x = 5, y = 5
        Location location_2 = new Location(5, 5);   // location_2 which has identical location with location_1
        Location location_3 = new Location(1, 1);   // location_3 has different x and y with both location_1 and location_2
        assert location_1.equals(location_2);   // expecting true
        assert !location_1.equals(location_3);  // expecting false
    }

    @ Test

    public void toStringTest(){
        Location location_1 = new Location(0, 0);
        assert location_1.toString().equals("A1");   // expecting true
        assert !location_1.toString().equals("A3");    // expecting false

    }

    @ Test
    public void fromStringTest(){
        Location location_1 = new Location(0,0);    // (1, 1) = A1
        Location location_2 = new Location(2,2);    // (2, 2) = C3
        assert Location.fromString("a1").equals(location_1);     // expecting true
        assert Location.fromString("A1").equals(location_1);     // expecting true
        assert !(Location.fromString("A1").equals(location_2));  // expecting false
    }
}
