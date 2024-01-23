package hk.edu.polyu.comp.comp2021.jungle.model;

@SuppressWarnings("serial")
public class Wolf extends Piece {

    Wolf (Location location, Team team) {
        super(location, team, 4);
    }

    void getMovables(Jungle jungle) {
        Location[] newMovables = new Location[4];

        // The four directions
        newMovables[0] = new Location(this.location.getX(),this.location.getY()+1); 
        newMovables[1] = new Location(this.location.getX()-1,this.location.getY()); 
        newMovables[2] = new Location(this.location.getX()+1,this.location.getY()); 
        newMovables[3] = new Location(this.location.getX(),this.location.getY()-1); 
        
        // Remove the location those are: 1) not in the valid range 2) the tile has a same team 3) not capturable 4) the tile is water
        for(int i=0; i<4; i++) { 
                        
            if (    !Location.isValid(newMovables[i]) || // 1)
                    Jungle.isWater(newMovables[i]) )  // 4)
                    { newMovables[i] = null; continue;}

            // Eliminate null case: this process is needed because 3) and 4) does not accept null.
            if (jungle.getPiece(newMovables[i]) == null) continue; // the null value which are not 1) and 4) are always moveable;


            if (    jungle.getPiece(newMovables[i]).team == this.team || // 2)
                    !Piece.isCapturable(this.rank, jungle.getPiece(newMovables[i]).rank) ) // 3)
                    { newMovables[i] = null; }
                
        }


        // Update
        movables = newMovables;
    }

    public String toString() {
        if (team == Team.Home) return "  ↑wolf↑   ";
        return "  ↓wolf↓   ";
    }

}
