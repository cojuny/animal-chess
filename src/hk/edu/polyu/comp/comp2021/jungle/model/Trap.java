package hk.edu.polyu.comp.comp2021.jungle.model;

@SuppressWarnings("serial")
public class Trap extends Piece{

    Trap (Location location, Team team) {
        super(location, team,0);
    }


    void getMovables(Jungle j) { // traps does not move
        movables = new Location[4];
    }
    
    public String toString() {
        return "   trap    ";
    }

}
