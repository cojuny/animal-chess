package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;
import hk.edu.polyu.comp.comp2021.jungle.Utility;

@SuppressWarnings("serial")
public class Jungle implements Serializable {
    private Piece[][] jungle = new Piece[Location.XMAX][Location.YMAX]; // The board as in 2d array of Piece objects
    private final static Location[] WATERLOCATION = { // Locations where the water tiles located.
            new Location(1, 3),
            new Location(1, 4),
            new Location(1, 5),
            new Location(2, 3),
            new Location(2, 4),
            new Location(2, 5),
            new Location(4, 3),
            new Location(4, 4),
            new Location(4, 5),
            new Location(5, 3),
            new Location(5, 4),
            new Location(5, 5),
    };
    private String homePlayer, awayPlayer, winner; // Two String variables remembers the name of the player, winner remembers the value who won

    private boolean isHomeTurn = true; 
    public boolean isNameSet = false;
    public boolean onGame = true; // Conition for game continuable, false if there is an winner


    /**
     * Constructor
     */
    public Jungle() {
        initJungle();
    }

    public boolean isPlayersPiece(Piece p) {
        if (p == null) return false;
        if (isHomeTurn && p.team == Team.Home) {
            return true;
        }
        else if (!isHomeTurn && p.team == Team.Away) {
            return true;
        }
        return false;
    }

    /**
     * Get the names of the players
     */
    public void setNames(String home, String away) {
        this.homePlayer = home;
        this.awayPlayer = away;
        this.isNameSet = true;
    }

    /**
     * Update the value of movables for piece that matches the player's turn.  
     */
    private void getMovables() {
        for (Piece[] row: jungle) {
            for (Piece p: row) {
                if (p == null) continue;
                if (isPlayersPiece(p)) p.getMovables(this);
            }
        }
    }

    /**
     * Initialize all the pieces at starting position
     */
    private void initJungle() {
        // dens and traps
        addPiece(new Den(Location.fromString("D1"), Team.Home));
        addPiece(new Trap(Location.fromString("C1"), Team.Home));
        addPiece(new Trap(Location.fromString("E1"), Team.Home));
        addPiece(new Trap(Location.fromString("D2"), Team.Home));
        addPiece(new Den(Location.fromString("D9"), Team.Away));
        addPiece(new Trap(Location.fromString("C9"), Team.Away));
        addPiece(new Trap(Location.fromString("E9"), Team.Away));
        addPiece(new Trap(Location.fromString("D8"), Team.Away));
        // animals
        addPiece(new Lion(Location.fromString("A1"), Team.Home));
        addPiece(new Tiger(Location.fromString("G1"), Team.Home));
        addPiece(new Cat(Location.fromString("B2"), Team.Home));
        addPiece(new Dog(Location.fromString("F2"), Team.Home));
        addPiece(new Elephant(Location.fromString("A3"), Team.Home));
        addPiece(new Wolf(Location.fromString("C3"), Team.Home));
        addPiece(new Leopard(Location.fromString("E3"), Team.Home));
        addPiece(new Rat(Location.fromString("G3"), Team.Home));

        addPiece(new Lion(Location.fromString("G9"), Team.Away));
        addPiece(new Tiger(Location.fromString("A9"), Team.Away));
        addPiece(new Cat(Location.fromString("F8"), Team.Away));
        addPiece(new Dog(Location.fromString("B8"), Team.Away));
        addPiece(new Elephant(Location.fromString("G7"), Team.Away));
        addPiece(new Wolf(Location.fromString("E7"), Team.Away));
        addPiece(new Leopard(Location.fromString("C7"), Team.Away));
        addPiece(new Rat(Location.fromString("A7"), Team.Away));

        getMovables();
    }


    /**
     * Runs the command given by the String, the validity must be checked before running this method.
     * @param command The String input from player.
     */
    public void runCommand(String command) {
        String[] tokens = command.split("\\s+");
        switch (tokens[0]) {
            case "move":
                Location from = Location.fromString(tokens[1]);
                Location to = Location.fromString(tokens[2]);

                movePiece(from, to);                    
                isHomeTurn = !isHomeTurn; // Change turn to the next player
                getMovables();

                break;

            case "view":
                Location l = Location.fromString(tokens[1]);
                getPiece(l).viewMovables();
                break;

            case "save":
                FileHandler.saveGame(this,Integer.parseInt(tokens[1]));
                break;

            case "open":
                open(FileHandler.loadGame(Integer.parseInt(tokens[1])));
                break;

            case "quit":
                Utility.quit();
        }
    }

    /**
     * The Condition check for the winner, if a winner is found, the String value winner will be updated.
     */
    public void checkWinner() {
        boolean homeIsAnimalAlive = false, homeIsDenAlive = false;
        boolean awayIsAllAlive = false, awayIsDenAlive = false;
        for (Piece[] row: jungle) {
            for (Piece p: row) {
                if (p == null) continue;
                if (p instanceof Trap) continue;
                if (homeIsAnimalAlive && homeIsDenAlive && awayIsAllAlive && awayIsDenAlive) return;

                if (p instanceof Den) {
                    if (p.team == Team.Home) homeIsDenAlive = true;
                    else awayIsDenAlive = true;
                    continue;
                }
                if (p.team == Team.Home) homeIsAnimalAlive = true;
                else if (p.team == Team.Away) awayIsAllAlive = true;
            
            }
        }
        if (!(homeIsAnimalAlive && homeIsDenAlive)) {
            winner = awayPlayer;
            onGame = false;
            return;
        }
        else if (!(awayIsAllAlive && awayIsDenAlive)) {
            winner = homePlayer;
            onGame = false;
            return;
        }
    }


    /**
     * 
     * @return the current turn's player
     */
    public String getCurrentPlayerName() {
        return isHomeTurn? this.homePlayer+"(↑)" : this.awayPlayer+"(↓)";
    }

    
    /**
     * 
     * @return the private String homePlayer 
     */
    public String getHomePlayerName() {
        return this.homePlayer;
    }



    /**
     * 
     * @return the private String awayPlayer 
     */
    public String getAwayPlayerName() {
        return this.awayPlayer;
    }

    /**
     * 
     * @return the private String winner 
     */
    public String getWinnerName() {
        return this.winner;
    }



    /**
     * Add the piece p into the jungle
     * @param p
     */
    private void addPiece(Piece p) {
        this.jungle[p.location.getX()][p.location.getY()] = p;
    }


    /**
     * Update the jungle by moving a peice from one to another, it could be capture of one animal, trap,or den,
     * a simple move, or the jump from lion or tiger.
     * The method do not check its validity, the validity is checked from Piece.getMovables
     * @param from the starting location where the object is located
     * @param to the target value that will be overwritten
     */
    private void movePiece(Location from, Location to) {
        getPiece(from).move(to); // Update the location value from the piece
        if (getPiece(to) instanceof Trap) {this.jungle[to.getX()][to.getY()] = null;} 
        else {this.jungle[to.getX()][to.getY()] = this.jungle[from.getX()][from.getY()];}
        this.jungle[from.getX()][from.getY()] = null; // The piece at from location always becomes a null value.
    }


    /**
     * Check whether the target location is a water or not
     * @param target
     * @return true if the target is a water location
     */
    public static boolean isWater(Location target) {
        for (Location water: WATERLOCATION) {
            if (target.equals(water)) return true;
        }
        return false;
    }



    /**
     * String for printing water
     * @return String
     */
    public static String waterToString() {
        return (" ~~~~~~~~~ ");
    }



    /**
     * String for printing water
     * @return String
     */
    public static String grassToString() {
        return (" wwwwwwwww ");
    }

    /**
     * 
     * @return the isHomeTurn
     */
    public boolean getCurrentTurn() {
        return isHomeTurn;
    }

    /**
     * A copy of the jungle value 
     * @return this.jungle
     */
    public Piece[][] getBoard() {
        return this.jungle;
    }


    /**
     * A copy of a piece at location target
     * @param target
     * @return the peice at certain location
     */
    public Piece getPiece(Location target) {return this.jungle[target.getX()][target.getY()];}


    /**
     * Manual overwrite upon open command, I choose to design this way because I wish all the command to be executed in the model directory.
     * @param other the new Jungle object
     */
    public void open (Jungle other) {
        this.jungle = other.jungle.clone();
        this.setNames(other.homePlayer, other.awayPlayer);
        this.isHomeTurn = other.getCurrentTurn();
        this.isNameSet = other.isNameSet;
        this.onGame = other.onGame;
    }

    /**
     * Overwrite the jungle board, from given.
     * This method is not used in the game, I made this for the convenience of Test file.
     */
    public void fromBoard (Piece[][] otherJungle) {
        this.jungle = otherJungle.clone();
    }

}

