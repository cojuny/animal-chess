package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElephantTest {
    Jungle j = new Jungle();

    @Test
    public void getMovables() {
        Elephant elephant = new Elephant(Location.fromString("G4"), Team.Home);
        elephant.getMovables(j);
        assert elephant.movables[1] == null;
        assert elephant.movables[2] == null;
        assert elephant.movables[3] == null;
        assert elephant.movables[0].equals(Location.fromString("G5"));

        elephant = new Elephant(Location.fromString("A6"), Team.Home);
        elephant.getMovables(j);
        assert elephant.movables[0].equals(Location.fromString("A7"));


    }

    @Test
    public void testToString() {
        Elephant elephant1 = new Elephant(Location.fromString("A1"), Team.Home);
        assert elephant1.toString().equals("↑elephant↑ ");
        Elephant elephant2 = new Elephant(Location.fromString("A1"), Team.Away);
        assert elephant2.toString().equals("↓elephant↓ ");
    }
}