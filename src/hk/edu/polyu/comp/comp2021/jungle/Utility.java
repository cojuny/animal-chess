package hk.edu.polyu.comp.comp2021.jungle;

public class Utility {
    /*This class is designed to handle the error situation only.*/
    public static void error(String errorMsg) {
        System.out.println(errorMsg);
        System.exit(1);
    }
    public static void inputError(String errorMsg) {
        System.out.println(errorMsg);
    }
    public static void quit() {
        System.out.println("The program exited.");
        System.exit(1);
    }

}
