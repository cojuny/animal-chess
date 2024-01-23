package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import hk.edu.polyu.comp.comp2021.jungle.Utility;

public class FileHandler {


    /**
     * Static method, saves the file into the given slot.
     * @param j the Jungle value which will be saved
     * @param slot the integer specifiying which slot to be saved
     */
    public static void saveGame(Jungle j, int slot) {
        try {
            File outputFile = new File("src/hk/edu/polyu/comp/comp2021/jungle/model/resource/file/" + Integer.toString(slot) + ".txt");
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outputFile));
            os.writeObject(j);

        } catch (FileNotFoundException e) { System.out.println("File not found");
		} catch (IOException e) { Utility.error("Fatal: IOException error.");
        }
        
    }

    /**
     * Static method, load the file from the given slot.
     * @param slot the integer specifiying which slot to be loaded
     * @return null if no file is found on the slot, not null Jungle object if object is found
     */
    public static Jungle loadGame(int slot) {
        Jungle output = null;
        try {
            FileInputStream fi = new FileInputStream(new File(
                    "src/hk/edu/polyu/comp/comp2021/jungle/model/resource/file/" + Integer.toString(slot) + ".txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read file to object;
            output = (Jungle) oi.readObject();

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) { return null;
        } catch (IOException e) { Utility.error("Fatal: IOException error.");
        } catch (ClassNotFoundException e) { Utility.error("Fatal: Jungle class cannot be read."); }
        return output;
    }


}
