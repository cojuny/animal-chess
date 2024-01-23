package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LionTest {
    Jungle j = new Jungle();


    @Test
    public void getMovables() {
        Lion lion = new Lion(Location.fromString("A4"), Team.Home);
        lion.getMovables(j);
        assert lion.movables[1] == null;
        assert lion.movables[2].equals(Location.fromString("D4"));
        assert lion.movables[3] == null;
        assert lion.movables[0].equals(Location.fromString("A5"));

        lion = new Lion(Location.fromString("A6"), Team.Home);
        lion.getMovables(j);
        assert lion.movables[0].equals(Location.fromString("A7"));

        lion = new Lion(Location.fromString("B3"), Team.Home);
        lion.getMovables(j);
        assert lion.movables[0].equals(Location.fromString("B7"));

        lion = new Lion(Location.fromString("B7"), Team.Home);
        lion.getMovables(j);
        assert lion.movables[3].equals(Location.fromString("B3"));

        lion = new Lion(Location.fromString("G6"), Team.Home);
        lion.getMovables(j);
        assert lion.movables[0] == null;

        Location l = Location.fromString("B5");
        Rat rat = new Rat(l, Team.Home);
        Piece[][] update = j.getBoard();
        update[l.getX()][l.getY()] = rat;
        j.fromBoard(update);
        lion = new Lion(Location.fromString("B3"), Team.Home);
        lion.getMovables(j);
        assert lion.movables[0] == null;
    }

    @Test
    public void testToString() {
        Lion lion1 = new Lion(Location.fromString("A1"), Team.Home);
        assert lion1.toString().equals("  ↑lion↑   ");

        Lion lion2 = new Lion(Location.fromString("A1"), Team.Away);
        assert lion2.toString().equals("  ↓lion↓  ");
    }

}