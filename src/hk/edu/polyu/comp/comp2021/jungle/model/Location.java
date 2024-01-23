package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Location implements Serializable {
    
    public static final int XMAX = 7;
    public static final int YMAX = 9;

    private int x, y;



    /**
     * Constructor
     * @param x
     * @param y
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }



    /**
     * Update the current location if the newLocation is inside the board.
     * @param newLocation the validity is not checked here
     */
    public void updateLocation(Location newLocation) {
        this.x = newLocation.getX();
        this.y = newLocation.getY();
        return;
    }



    /**
     * Static method, check whether the location is valid or not.
     * @param location
     * @return true if the location is valid
     */
    public static boolean isValid(Location location){
        if (location.getX() >= XMAX || location.getX()<0 || location.getY() >= YMAX || location.getY()<0) {
            return false;
        }
        return true;
    }



    /**
     * 
     * @return the private x integer
     */
    public int getX() {
        return this.x;
    }


    /**
     * 
     * @return the private y integer
     */
    public int getY() {
        return this.y;
    }


    /**
     * check whether this and other has the same location
     * @param other
     * @return true if they are the same
     */
    public boolean equals(Location other) {
        if(this.x == other.x && this.y == other.y) return true;
        return false;
    }


    /**
     * 
     * @return the String representation in professor's format
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(Character.toString('A'+this.x));
        s.append(Integer.toString(this.y+1));
        return s.toString();
    }


    /**
     * Static method, the first letter allows both small letter and capital letter.
     * @param s the String in professor's format
     * @return the location object decoded from s, the value is not checked.
     */
    public static Location fromString(String s) {
        int x;
        if (s.charAt(0) >= 97) {
            x = (int)(s.charAt(0)) - 97;
        }
        else { x = (int)(s.charAt(0)) - 65; }

        return new Location(x, Character.getNumericValue(s.charAt(1))-1);
    }

}
