package hk.edu.polyu.comp.comp2021.jungle.model;

@SuppressWarnings("serial")
public class Den extends Piece {

    Den (Location location, Team team) {
        super(location, team, 0);
    }

    void getMovables(Jungle j) { // Den does not move
        movables = new Location[4];
    }

    public String toString() {
        if (team == Team.Home) return "   ↑den↑   ";
        return "   ↓den↓   ";
    }

}
