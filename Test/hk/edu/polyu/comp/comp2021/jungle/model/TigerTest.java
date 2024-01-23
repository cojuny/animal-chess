package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TigerTest {
    Jungle j = new Jungle();

    @Test
    public void getMovables() {
        Tiger tiger = new Tiger(Location.fromString("A4"), Team.Home);
        tiger.getMovables(j);
        assert tiger.movables[1] == null;
        assert tiger.movables[2].equals(Location.fromString("D4"));
        assert tiger.movables[3] == null;
        assert tiger.movables[0].equals(Location.fromString("A5"));

        tiger = new Tiger(Location.fromString("A6"), Team.Home);
        tiger.getMovables(j);
        assert tiger.movables[0].equals(Location.fromString("A7"));

        tiger = new Tiger(Location.fromString("B3"), Team.Home);
        tiger.getMovables(j);
        assert tiger.movables[0].equals(Location.fromString("B7"));

        tiger = new Tiger(Location.fromString("B7"), Team.Home);
        tiger.getMovables(j);
        assert tiger.movables[3].equals(Location.fromString("B3"));

        tiger = new Tiger(Location.fromString("G6"), Team.Home);
        tiger.getMovables(j);
        assert tiger.movables[0] == null;
    }

    @Test
    public void testToString() {
        Tiger tiger1 = new Tiger(Location.fromString("A1"), Team.Home);
        assert tiger1.toString().equals("  ↑tiger↑  ");

        Tiger tiger2 = new Tiger(Location.fromString("A1"), Team.Away);
        assert tiger2.toString().equals("  ↓tiger↓  ");
    }
}