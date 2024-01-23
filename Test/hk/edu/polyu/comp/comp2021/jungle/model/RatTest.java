package hk.edu.polyu.comp.comp2021.jungle.model;

import com.sun.source.tree.CompoundAssignmentTree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RatTest {
    Jungle j = new Jungle();

    @Test
    public void getMovablesTest() {
        Rat rat = new Rat(Location.fromString("A4"), Team.Home);
        rat.getMovables(j);
        assert rat.movables[1] == null;
        assert rat.movables[2].equals(Location.fromString("B4"));
        assert rat.movables[3] == null;
        assert rat.movables[0].equals(Location.fromString("A5"));

        rat = new Rat(Location.fromString("A6"), Team.Home);
        rat.getMovables(j);
        assert rat.movables[0].equals(Location.fromString("A7"));

        j = new Jungle();
        Piece[][] thisJungle = j.getBoard();
        Location l = Location.fromString("B6");
        thisJungle[l.getX()][l.getY()] = new Rat(l, Team.Home);
        j.fromBoard(thisJungle);

        Location n = Location.fromString("B7");
        thisJungle[n.getX()][n.getY()] = new Rat(n, Team.Away);
        j.fromBoard(thisJungle);

        thisJungle[1][5].getMovables(j);

        assert thisJungle[1][5].movables[0] == null;

    }
    @Test
    public void toStringTest() {
        Rat rat1 = new Rat(Location.fromString("A1"), Team.Home);
        assert rat1.toString().equals("   ↑rat↑   ");
        Rat rat2 = new Rat(Location.fromString("A2"), Team.Away);
        assert rat2.toString().equals("   ↓rat↓   ");
    }
}