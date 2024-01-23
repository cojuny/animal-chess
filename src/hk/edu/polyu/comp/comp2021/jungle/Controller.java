package hk.edu.polyu.comp.comp2021.jungle;
import hk.edu.polyu.comp.comp2021.jungle.model.FileHandler;
import hk.edu.polyu.comp.comp2021.jungle.model.Jungle;
import hk.edu.polyu.comp.comp2021.jungle.model.Location;


import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Connected to model and view, independent from the model.
 * Functions of this class:
 * 1) Make changes to the data of model, the Jungle object by calling its methods.
 * 2) Give command to the view, telling what to print.
 * 3) Fetch input from Scanner, and check its validity refered form Jungle object.
 * 4) Controls the flow of the game, check whos turn, whether there is a winner, and this is helped from the Jungle object. 
 * 
 */

public class Controller {

    Jungle j;

    public Controller() {
        // check whether to continue preivous game or not
        View.newOrLoad();

        if (getBooleanInput()) { // The requirement on asking whether to start a new or load the preivous game.
            int slot;
            do {
                slot = getIntInput();
                j = FileHandler.loadGame(slot);
                if (j == null) Utility.inputError("Input Error: There is no file on that slot.");
            } while (j == null);
        }
        else this.j = new Jungle();

        // set names of players if not set yet
        if (!j.isNameSet) {
            setNames(j);
        }

        // play game
        View.printBoard(j); // Print board first
        View.whosTurn(j.getCurrentPlayerName()); // Print whos turn
        j.checkWinner(); // Check winner before the game begins
        while (j.onGame) {
            nextMove();
            j.checkWinner();
        }

        View.win(j.getWinnerName());
        // End of game
    }


    /**
     * The general method runs the next command.
     * There are some special effects which is required from the professor:
     * 1) Upon open, prompt to check whether to save the current game or not.
     * 2) Upon move, print board and the next player.
     */
    private void nextMove() {
        String input;
        do {
            input = getStringInput();
        } while (!isInputValid(0,input));

        if (input.charAt(0) == 'o') saveBeforeLoad(input); //1)
        j.runCommand(input);
        if (input.charAt(0) == 'm' || input.charAt(0) == 'o' ) {
            View.printBoard(j);
            View.whosTurn(j.getCurrentPlayerName()); //2)
        }

    }

    /**
     * Set names of the players to the Jungle.
     * @param j the Jungle object
     */
    private void setNames(Jungle j) {
        View.setHomePlayer();
        String home = getStringInput();
        View.setAwayPlayer();
        String away = getStringInput();
        j.setNames(home, away);
    }  

    /**
     * Gets the boolean result from the player, it runs until correct input is settled.
     * @return true if Y or y is fetched
     */
    private boolean getBooleanInput() {
        String s;
        do {
            s = getStringInput();
        } while (!isInputValid(1, s));

        if (s.charAt(0) == 'y' || s.charAt(0) == 'Y') return true;
        else return false;
    }

    /**
     * Gets the int result from the player, it runs until correct input is settled.
     * @return the integer fetched 
     */
    private int getIntInput() {
        View.whichSlot();
        String input;
        while (true) {
            input = getStringInput();
            if (isInputValid(2, input)) break;
        }

        return Integer.parseInt(input);
    }


    /**
     * Read the String input using the scanner
     * @return the String read by scanner
     */
    private String getStringInput() {
        View.waitingInput();
        Scanner scanner= null;
        try {
            scanner= new Scanner(System.in);
            String input = scanner.nextLine();
            return input;
        }
        catch (NoSuchElementException e) { // This exception is reached with input does not anything including '\n', which is not expected
            Utility.error("Error: Unexpected error");
            return null;
        }
    }




    // argument 0: command
    // argument 1: boolean (yes or no) input.
    // argument 2: number from 0-9.
    /**
     * The general validation on input of all types.
     * @param argument Integer that specify what type of input is
     * 0: command 1: boolean (yes or no) input. 2: number from 0-9.
     * @param s The String that is being tested
     * @return true if the s is a valid command, otherwise false. Upon false, the error statement is printed.
     */
    private boolean isInputValid(int argument, String s) {
       
        if (argument == 1) {
            if (s.charAt(0) == 'y' || s.charAt(0) == 'n' || s.charAt(0) == 'Y' || s.charAt(0) == 'N') {
                return true;
            }
            else {
                Utility.inputError("Input Error: Please input a letter of either Y or N.");
            }
        }

        else if (argument == 2) {
            if (s.matches("\\d+")) {
                if (Integer.parseInt(s) < 10 && Integer.parseInt(s) >= 0) {
                        return true;
                }
                else {
                    Utility.inputError("Input Error: Please input number in range of 0 to 9.");
                }
            }
            else {
                Utility.inputError("Input Error: Please input a number.");
            }
        }

        else if (argument == 0) {
            String[] tokens = s.split("\\s+");
            switch (tokens[0]) {
                case "view":
                    if (tokens[1] != null && Location.isValid(Location.fromString(tokens[1]))) { // check on location format
                        Location l = Location.fromString(tokens[1]);
                        if (j.isPlayersPiece(j.getPiece(l))) { // check on piece belong to the current player.
                            return true;
                        }
                        else {
                            Utility.inputError("Input Error: The choosen piece does not belong to the current player.");
                        }
                    }
                    else {
                        Utility.inputError("Input Error: invalid location input.");
                    }
                    break;
                case "move":
                    if (tokens[1] != null && Location.isValid(Location.fromString(tokens[1])) &&
                        tokens[2] != null && Location.isValid(Location.fromString(tokens[2]))) { // check on location format
                        
                        Location from = Location.fromString(tokens[1]);
                        Location to = Location.fromString(tokens[2]);

                        if (j.isPlayersPiece(j.getPiece(from))) { // check on piece belong to the current player.
                            if (j.getPiece(from).isMovable(to)) { // check on target is capturable from piece
                                return true;
                            }
                            else {
                                Utility.inputError("Input Error: The selected target is not movable or capturable.");
                            }
                        }
                        else {
                            Utility.inputError("Input Error: The choosen piece does not belong to the current player.");
                        }
                    }
                    else {
                        Utility.inputError("Input Error: Invalid location input.");
                    }
                    View.printBoard(j);
                    break;

                case "save":
                    View.saved();
                    return isInputValid(2, tokens[1]);

                case "open":
                    if (isInputValid(2, tokens[1])) {
                        if (FileHandler.loadGame(Integer.parseInt(tokens[1])) != null) {
                            return true;
                        }
                        else {
                            Utility.inputError("Input Error: File does not exist on that slot.");
                        }
                    }

                    
                case "quit":
                    return true;

                default:
                    Utility.inputError(String.format("Input Error: %s is not a valid command.", tokens[0]));
                    return false;
            }
        }

        return false;
    }


    /**
     * Implements the effect of asking whether to save before loading
     * @param s String
     */
    private void saveBeforeLoad(String s) {
        View.saveCurrent();
        if (!getBooleanInput()) return;
        int slot = getIntInput();
        j.runCommand("save " + Integer.toString(slot));
    }

}
