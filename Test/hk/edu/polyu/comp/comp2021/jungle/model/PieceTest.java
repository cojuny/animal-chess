package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.Test;

public class PieceTest {
    Jungle j = new Jungle();

    @Test
    public void isMovable() {
        Cat cat = new Cat(Location.fromString("G4"), Team.Home);
        cat.getMovables(j);
        assert cat.isMovable(Location.fromString("G5"));
        assert !cat.isMovable(Location.fromString("G3"));
    }

    @Test
    public void move() {
        Cat cat = new Cat(Location.fromString("G4"), Team.Home);
        cat.move(Location.fromString("G5"));
        assert cat.location.equals(Location.fromString("G5"));
    }

    @Test
    public void viewMovables() {
        Cat cat = new Cat(Location.fromString("G4"), Team.Home);
        cat.getMovables(j);
        cat.viewMovables();
    }


    @Test
    public void captureable() {
        assert Piece.isCapturable(8,5);
        assert !Piece.isCapturable(6,8);
        assert Piece.isCapturable(1,8);
    }

    @Test
    public void equals() {
        Cat cat1 = new Cat(Location.fromString("G4"), Team.Home);
        Cat cat2 = new Cat(Location.fromString("G4"), Team.Home);
        Cat cat3 = new Cat(Location.fromString("G4"), Team.Away);
        assert cat1.equals(cat2);
        assert !cat1.equals(cat3);
    }

}