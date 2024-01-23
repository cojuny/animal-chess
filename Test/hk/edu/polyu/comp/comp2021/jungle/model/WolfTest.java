package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WolfTest {
    Jungle j = new Jungle();

    @Test
    public void getMovables() {
        Wolf wolf = new Wolf(Location.fromString("G4"), Team.Home);
        wolf.getMovables(j);
        assert wolf.movables[1] == null;
        assert wolf.movables[2] == null;
        assert wolf.movables[3] == null;
        assert wolf.movables[0].equals(Location.fromString("G5"));

        wolf = new Wolf(Location.fromString("A6"), Team.Home);
        wolf.getMovables(j);
        assert wolf.movables[0].equals(Location.fromString("A7"));

        wolf = new Wolf(Location.fromString("G8"), Team.Home);
        wolf.getMovables(j);
        assert wolf.movables[0] == null;
    }

    @Test
    public void testToString() {
        Wolf wolf1 = new Wolf(Location.fromString("A1"), Team.Home);
        assert wolf1.toString().equals("  ↑wolf↑   ");
        Wolf wolf2 = new Wolf(Location.fromString("A1"), Team.Away);
        assert wolf2.toString().equals("  ↓wolf↓   ");
    }
}