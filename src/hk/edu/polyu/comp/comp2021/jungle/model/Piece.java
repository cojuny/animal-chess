package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Piece implements Serializable {
    protected Location location;
    public Team team;
    protected Location[] movables; // The locations that a piece can move to within two direction, null if certain direction cannot be moved.
    public final int rank; // rank is final because it cannot be changed

    /**
     * Update the Movables, the piece sees what locations among the north, west, east, and south
     * are movable to them according to the rules and identity given.
     * @param j The jungle should be provided, so by a given location, program knows what piece is on that tile.
     */
    abstract void getMovables(Jungle j);

    /**
     * The super constructor that will be called by all kinds of pieces
     * @param location The initial location
     * @param team The team that piece belong to
     * @param rank The fixed raking for capturing identification
     */
    protected Piece(Location location, Team team, int rank) {
        this.location = location;
        this.team = team;
        this.rank = rank;
    }

    /**
     * Checks whether the location is inside the movables array
     * @param l target location
     * @return  true if matching value is found.
     */
    public boolean isMovable(Location l) {
        for (int i=0; i<4; i++) {
            if (movables[i] == null) continue;
            if (movables[i].equals(l)) return true;
        }
        return false;
    } 

    /**
     * Method that updates the location of the piece
     * @param l the new Location
     */
    protected void move(Location l) {
        this.location = l;
    }

    /**
     * Print the movable location choosen by the piece.
     */
    protected void viewMovables() {
        System.out.printf("The possible moves are ");
        for (int i=0; i<4; i++) {
            if (movables[i]==null) continue;
            System.out.printf(movables[i].toString() + " ");
        }
        System.out.printf("\n");
    }

    /**
     * return the private movables, needed for gui.
     */
    public Location[] getMovables() {
        return movables;
    }

    /**
     * The static boolean method having the logical rules of capturing one to another according to rank
     * @param fromRank the rank of attacker
     * @param toRank the rank of the target
     * @return true if capturable
     */
    public static boolean isCapturable(int fromRank, int toRank) {
        if (fromRank == 1 && toRank == 8) return true;
        else if (fromRank >= toRank) return true;
        return false;
    }

    /**
     * The override method for default equals()
     * @param other the other piece
     * @return true if two has the same rank, location, and team
     */
    public boolean equals(Piece other) {
        if (other == null) return false;
        if (this.rank == other.rank
                && this.location.equals(other.location)
                && this.team.equals(other.team)) {
            return true;
            }
        return false;
    }

}   
