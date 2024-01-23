package hk.edu.polyu.comp.comp2021;
import hk.edu.polyu.comp.comp2021.jungle.Controller;
import hk.edu.polyu.comp.comp2021.jungle.Display;

// EVERYTHING STARTS HERE.

import java.util.Scanner;
class Application {

    public static void main(String[] args) {

        if (isGui()) {
            new Display();
        }
        else new Controller();

    }


    private static boolean isGui() {
        System.out.println("Do you wish to execute on GUI? (Y/N)");

        String input;
        Scanner s = new Scanner(System.in);
        input = s.nextLine();

        if (input.charAt(0) == 'y' || input.charAt(0) == 'Y') return true;
        return false;
    }
}
