package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatTest  {

    Jungle j = new Jungle();
    @Test
    public void getMovablesTest() {
        Cat cat = new Cat(Location.fromString("A4"), Team.Home);
        cat.getMovables(j);
        assert cat.movables[1] == null; // case for not in valid range
        assert cat.movables[2] == null; // case for water tile
        assert cat.movables[3] == null; // case for same team
        assert cat.movables[0].equals(Location.fromString("A5")); // case for movable to a empty grass tile

        cat = new Cat(Location.fromString("A6"), Team.Home);
        cat.getMovables(j);
        assert cat.movables[0].equals(Location.fromString("A7")); // case for capturable

        cat = new Cat(Location.fromString("G8"), Team.Home);
        cat.getMovables(j);
        assert cat.movables[0] == null; // case for not capturable
    }

    @Test
    public void toStringTest() {
        Cat cat1 = new Cat(Location.fromString("A1"), Team.Home);
        assert cat1.toString().equals("   ↑cat↑   ");
        Cat cat2 = new Cat(Location.fromString("A1"), Team.Away);
        assert cat2.toString().equals("   ↓cat↓   ");
    }

}