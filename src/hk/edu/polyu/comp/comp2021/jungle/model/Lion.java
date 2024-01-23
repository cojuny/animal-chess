package hk.edu.polyu.comp.comp2021.jungle.model;

@SuppressWarnings("serial")
public class Lion extends Piece  {

    Lion (Location location, Team team) {
        super(location, team, 7);
    }

    void getMovables(Jungle jungle) {
        Location[] newMovables = new Location[4];

        // The four directions
        newMovables[0] = new Location(this.location.getX(),this.location.getY()+1); 
        newMovables[1] = new Location(this.location.getX()-1,this.location.getY()); 
        newMovables[2] = new Location(this.location.getX()+1,this.location.getY()); 
        newMovables[3] = new Location(this.location.getX(),this.location.getY()-1); 

        // Remove the location those are: 1) not in the valid range 2) the tile has a same team 3) not capturable 4) test jump if water is found.
        for(int i=0; i<4; i++) { 
                        
            if (    !Location.isValid(newMovables[i]) )  // 1)
                    { newMovables[i] = null; continue;}

            if (Jungle.isWater(newMovables[i])) {
                newMovables[i] = jumpRecursive(jungle,newMovables[i],i); //4)
                if (newMovables[i] == null) continue; // if jump is not possible, continue
            }

            // Eliminate null case: this process is needed because 2) and 3) does not accept null.
            if (jungle.getPiece(newMovables[i]) == null) continue; // the null value is always moveable;
            

            if (    jungle.getPiece(newMovables[i]).team == this.team || // 2)
                    !Piece.isCapturable(this.rank, jungle.getPiece(newMovables[i]).rank) ) // 3)
                    { newMovables[i] = null; }
        }


        // Update
        movables = newMovables;
    }
    public String toString() {
        if (team == Team.Home) return "  ↑lion↑   ";
        return "  ↓lion↓  ";
    }
    
    /**
     * A recursive function checks whether there is a rat or others blocking the way.
     * @param j jungle
     * @param l current recursive's location
     * @param direction the integer value, 0 = north, 1 = west, 2 = east, 3 = south
     * @return the location of the jump destination, or null if object found
     */
    public Location jumpRecursive(Jungle j, Location l, int direction) {
        if (!Jungle.isWater(l)) return l; // jump destination reached
        if (j.getPiece(l) != null) return null; // an object is found, jump is not possible

        switch (direction) { // call recursive by next tile
            case 0: // north direction
                l = new Location(l.getX(),l.getY()+1);
                break;
            case 1: // west direction
                l = new Location(l.getX()-1,l.getY());
                break;
            case 2: // east direction
                l = new Location(l.getX()+1,l.getY());
                break;
            case 3: // south direction
                l = new Location(l.getX(),l.getY()-1);
                break;
        }
        return jumpRecursive(j, l, direction);

    }


}
