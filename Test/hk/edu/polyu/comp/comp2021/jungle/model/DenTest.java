package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DenTest {
    Jungle j = new Jungle();

    @Test
    public void getMovables() {
        Den den = new Den(Location.fromString("A2"), Team.Home);
        den.getMovables(j);
        for (int i=0; i<4; i++) {
            assert den.movables[i] == null;
        }
    }

    @Test
    public void testToString() {
        Den home = new Den(Location.fromString("A2"), Team.Home);
        assert home.toString().equals("   ↑den↑   ");

        Den away = new Den(Location.fromString("A2"), Team.Away);
        assert away.toString().equals("   ↓den↓   ");

    }
}

