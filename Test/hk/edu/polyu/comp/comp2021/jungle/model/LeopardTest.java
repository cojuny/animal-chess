package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeopardTest {


    Jungle j = new Jungle();
    @Test
    public void getMovables() {
        Leopard leopard = new Leopard(Location.fromString("A4"), Team.Home);
        leopard.getMovables(j);
        assert leopard.movables[1] == null;
        assert leopard.movables[2] == null;
        assert leopard.movables[3] == null;
        assert leopard.movables[0].equals(Location.fromString("A5"));

        leopard = new Leopard(Location.fromString("A6"), Team.Home);
        leopard.getMovables(j);
        assert leopard.movables[0].equals(Location.fromString("A7"));

        leopard = new Leopard(Location.fromString("G8"), Team.Home);
        leopard.getMovables(j);
        assert leopard.movables[0] == null;

    }

    @Test
    public void testToString() {
        Leopard leopard1 = new Leopard(Location.fromString("A1"), Team.Home);
        assert leopard1.toString().equals(" ↑leopard↑ ");

        Leopard leopard2 = new Leopard(Location.fromString("A1"), Team.Away);
        assert leopard2.toString().equals(" ↓leopard↓ ");
    }
}