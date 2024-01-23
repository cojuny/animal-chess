package hk.edu.polyu.comp.comp2021.jungle.model;


import org.junit.Test;


public class FileHandlerTest {
    Jungle j = new Jungle();

    @Test
    public void saveGameTest(){
        FileHandler fileHandler = new FileHandler();
        fileHandler.saveGame(j, 1);
    }

    @Test
    public void loadGameTest(){
        FileHandler fileHandler = new FileHandler();
        fileHandler.loadGame(1);
    }


}

