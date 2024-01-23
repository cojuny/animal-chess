package hk.edu.polyu.comp.comp2021.jungle.model;

import org.junit.*;
import org.junit.AfterClass;

public class JungleTest {
    Jungle jungle;

    @Before
    public void init() {
        jungle = new Jungle();
    }

    @Test
    public void jungleInitialTest(){
        Location l;
        Piece[][] thisJungle = jungle.getBoard();
        Piece[][] otherJungle = new Piece[Location.XMAX][Location.YMAX];

        l = Location.fromString("D1");
        otherJungle[l.getX()][l.getY()] = new Den(l, Team.Home);
        l = Location.fromString("C1");
        otherJungle[l.getX()][l.getY()] = new Trap(l, Team.Home);
        l = Location.fromString("E1");
        otherJungle[l.getX()][l.getY()] = new Trap(l, Team.Home);
        l = Location.fromString("D2");
        otherJungle[l.getX()][l.getY()] = new Trap(l, Team.Home);
        l = Location.fromString("D9");
        otherJungle[l.getX()][l.getY()] = new Den(l, Team.Away);
        l = Location.fromString("C9");
        otherJungle[l.getX()][l.getY()] = new Trap(l, Team.Away);
        l = Location.fromString("E9");
        otherJungle[l.getX()][l.getY()] = new Trap(l, Team.Away);
        l = Location.fromString("D8");
        otherJungle[l.getX()][l.getY()] = new Trap(l, Team.Away);

        // animals
        l = Location.fromString("A1");
        otherJungle[l.getX()][l.getY()] = new Lion(l, Team.Home);
        l = Location.fromString("G1");
        otherJungle[l.getX()][l.getY()] = new Tiger(l, Team.Home);
        l = Location.fromString("B2");
        otherJungle[l.getX()][l.getY()] = new Cat(l, Team.Home);
        l = Location.fromString("F2");
        otherJungle[l.getX()][l.getY()] = new Dog(l, Team.Home);
        l = Location.fromString("A3");
        otherJungle[l.getX()][l.getY()] = new Elephant(l, Team.Home);
        l = Location.fromString("C3");
        otherJungle[l.getX()][l.getY()] = new Wolf(l, Team.Home);
        l = Location.fromString("E3");
        otherJungle[l.getX()][l.getY()] = new Leopard(l, Team.Home);
        l = Location.fromString("G3");
        otherJungle[l.getX()][l.getY()] = new Rat(l, Team.Home);

        l = Location.fromString("G9");
        otherJungle[l.getX()][l.getY()] = new Lion(l, Team.Away);
        l = Location.fromString("A9");
        otherJungle[l.getX()][l.getY()] = new Tiger(l, Team.Away);
        l = Location.fromString("F8");
        otherJungle[l.getX()][l.getY()] = new Cat(l, Team.Away);
        l = Location.fromString("B8");
        otherJungle[l.getX()][l.getY()] = new Dog(l, Team.Away);
        l = Location.fromString("G7");
        otherJungle[l.getX()][l.getY()] = new Elephant(l, Team.Away);
        l = Location.fromString("E7");
        otherJungle[l.getX()][l.getY()] = new Wolf(l, Team.Away);
        l = Location.fromString("C7");
        otherJungle[l.getX()][l.getY()] = new Leopard(l, Team.Away);
        l = Location.fromString("A7");
        otherJungle[l.getX()][l.getY()] = new Rat(l, Team.Away);

        for (int x=0; x<Location.XMAX; x++) {
            for (int y=0; y<Location.YMAX; y++) {
                if (thisJungle[x][y] == null) {
                    assert thisJungle[x][y] == otherJungle[x][y];
                }
                else {
                    assert thisJungle[x][y].equals(otherJungle[x][y]);
                }
            }
        }
    }

    @Test
    public void isPlayersPiece() {
        assert jungle.isPlayersPiece(null) == false;
        assert jungle.isPlayersPiece(jungle.getPiece(Location.fromString("A1")));
        assert !jungle.isPlayersPiece(jungle.getPiece(Location.fromString("A7")));
    }

    @Test
    public void setNames() {
        String home = "Daniel", away = "Wang";
        jungle.setNames(home, away);
        assert jungle.getHomePlayerName().equals(home);
        assert jungle.getAwayPlayerName().equals(away);
    }

    @Test
    public void checkWinner() {
        String home = "Daniel", away = "Wang";
        jungle.setNames(home, away);
        Location l;
        Piece[][] newJungle = new Piece[Location.XMAX][Location.YMAX];
        l = Location.fromString("D2");
        newJungle[l.getX()][l.getY()] = new Trap(l, Team.Home);
        l = Location.fromString("D1");
        newJungle[l.getX()][l.getY()] = new Den(l, Team.Home);
        l = Location.fromString("D9");
        newJungle[l.getX()][l.getY()] = new Den(l, Team.Away);
        l = Location.fromString("G3");
        newJungle[l.getX()][l.getY()] = new Rat(l, Team.Home);
        l = Location.fromString("G9");
        newJungle[l.getX()][l.getY()] = new Lion(l, Team.Away);

        jungle.fromBoard(newJungle);
        jungle.checkWinner();

        l = Location.fromString("G3");
        newJungle[l.getX()][l.getY()] = null;
        jungle.fromBoard(newJungle);
        jungle.checkWinner();
        assert jungle.getWinnerName().equals(away);
        newJungle[l.getX()][l.getY()] = new Rat(l, Team.Home);

        l = Location.fromString("G9");
        newJungle[l.getX()][l.getY()] = null;
        jungle.fromBoard(newJungle);
        jungle.checkWinner();
        assert jungle.getWinnerName().equals(home);
        newJungle[l.getX()][l.getY()] = new Lion(l, Team.Away);

        l = Location.fromString("D1");
        newJungle[l.getX()][l.getY()] = null;
        jungle.fromBoard(newJungle);
        jungle.checkWinner();
        assert jungle.getWinnerName().equals(away);
        newJungle[l.getX()][l.getY()] = new Den(l, Team.Home);

        l = Location.fromString("D9");
        newJungle[l.getX()][l.getY()] = null;
        jungle.fromBoard(newJungle);
        jungle.checkWinner();
        assert jungle.getWinnerName().equals(home);
        newJungle[l.getX()][l.getY()] = new Den(l, Team.Away);

    }

    @Test
    public void getHomePlayerName() {
        String home = "Daniel", away = "Wang";
        jungle.setNames(home, away);
        assert jungle.getHomePlayerName().equals(home);
        assert jungle.getAwayPlayerName().equals(away);
        assert jungle.getCurrentPlayerName().equals(home+"(↑)");
        jungle.runCommand("move A1 A2");
        assert jungle.getCurrentPlayerName().equals(away+"(↓)");
        Piece[][] newJungle = new Piece[Location.XMAX][Location.YMAX];
        newJungle[0][0] = new Den(Location.fromString("A1"), Team.Home);
        newJungle[0][1] = new Rat(Location.fromString("A2"), Team.Home);
        jungle.fromBoard(newJungle);
        jungle.checkWinner();
        assert jungle.getWinnerName().equals(home);
    }


    @Test
    public void isWater() {
        Location l;
        l = new Location(1, 3);
        assert jungle.isWater(l);
        l = new Location(0,0);
        assert !jungle.isWater(l);
    }

    @Test
    public void waterToString() {
        assert jungle.waterToString().equals(" ~~~~~~~~~ ");
    }

    @Test
    public void grassToString() {
        assert jungle.grassToString().equals(" wwwwwwwww ");
    }

    @Test
    public void getCurrentTurn() {
        assert jungle.getCurrentTurn() == true;
    }

    @Test
    public void getPiece() {
        assert jungle.getBoard()[0][0].equals(new Lion(Location.fromString("A1"),Team.Home));
    }

    @Test
    public void open() {
        Jungle different = new Jungle();
        different.runCommand("move A1 A2");
        jungle.open(different);
        Piece[][] newJungle = jungle.getBoard();
        System.out.println(newJungle[0][1]);
        assert newJungle[0][1].toString().equals(new Lion(new Location(0,1),Team.Home).toString());
    }

    @Test
    public void commands() {
        jungle.runCommand("view A1");
        jungle.runCommand("move A1 A2");
        jungle.runCommand("save 1");
        jungle.runCommand("open 1");
        //jungle.runCommand("quit");
    }

}

