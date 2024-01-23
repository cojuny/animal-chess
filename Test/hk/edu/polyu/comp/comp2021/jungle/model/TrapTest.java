package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrapTest {
    Jungle j = new Jungle();
    @Test
    public void getMovables() {
        Trap trap = new Trap(Location.fromString("A2"), Team.Home);
        trap.getMovables(j);
        for (int i=0; i<4; i++) {
            assert trap.movables[i] == null;
        }
    }

    @Test
    public void testToString() {
        Trap trap = new Trap(Location.fromString("A2"), Team.Home);
        assert trap.toString().equals("   trap    ");

    }

}