package hk.edu.polyu.comp.comp2021.jungle;
import hk.edu.polyu.comp.comp2021.jungle.model.Jungle;
import hk.edu.polyu.comp.comp2021.jungle.model.Piece;
import hk.edu.polyu.comp.comp2021.jungle.model.Location;


public class View {
    /*This class is designed to print some message by static only.*/
    
    public static void saved() {
        System.out.println("File saved.");
    }

    public static void saveCurrent() {
        System.out.println("Would you like to save the current game? (Y/N)");
    }

    public static void whichSlot() {
        System.out.println("Which slot do you wish to open/save? (0-9)");
    }

    public static void setHomePlayer() {
        System.out.println("Please input home player's name: ");
    }

    public static void setAwayPlayer() {
        System.out.println("Please input away player's name: ");
    }
    public static void openNotFound() {
        System.out.println("There is no file on that slot.");
    }
    public static void newOrLoad() {
        System.out.println("Do you want to continue previously saved game? (Y/N) ");
    }

    public static void whosTurn(String player) {
        System.out.printf(String.format("%s's turn!\n", player));
    }

    public static void waitingInput() { System.out.printf("Waiting for next input: "); }


    /**
     * Prints the board in the command prompt.
     * @param j Jungle
     */
    public static void printBoard(Jungle j) {
        Piece[][] board = j.getBoard().clone();
        String X = "       A          B          C          D          E          F          G     ";
        String line = "  -----------------------------------------------------------------------------";
        String[] Y = {"1|", "2|", "3|", "4|", "5|", "6|", "7|", "8|", "9|"};
        StringBuilder s;
        for (int y = 8; y >= 0; y--){
            s = new StringBuilder();
            s.append(Y[y]);
            for (int x = 0; x < 7; x++)  {
                if (board[x][y] == null) {
                    if (Jungle.isWater(new Location(x,y))) s.append(Jungle.waterToString());
                    else s.append(Jungle.grassToString());
                }
                else s.append(board[x][y].toString());
            }
            System.out.println(s.toString());
        }
        System.out.println(line);
        System.out.println(X);
    }

    /**
     * Prints the winner of the game.
     * @param winner the winner
     */
    public static void win(String winner) {
        System.out.printf("%s wins the game!\n", winner);
    }
}
