package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DogTest {
    Jungle j = new Jungle();

    @Test
    public void getMovablesTest() {
        Dog dog = new Dog(Location.fromString("A4"), Team.Home);
        dog.getMovables(j);
        assert dog.movables[1] == null;
        assert dog.movables[2] == null;
        assert dog.movables[3] == null;
        assert dog.movables[0].equals(Location.fromString("A5"));

        dog = new Dog(Location.fromString("A6"), Team.Home);
        dog.getMovables(j);
        assert dog.movables[0].equals(Location.fromString("A7"));

        dog = new Dog(Location.fromString("G8"), Team.Home);
        dog.getMovables(j);
        assert dog.movables[0] == null;
    }
    @Test
    public void toStringTest() {
        Dog dog1 = new Dog(Location.fromString("A1"), Team.Home);
        assert dog1.toString().equals("   ↑dog↑   ");

        Dog dog2 = new Dog(Location.fromString("A1"), Team.Away);
        assert dog2.toString().equals("   ↓dog↓   ");
    }
}