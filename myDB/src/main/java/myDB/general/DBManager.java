package myDB.general;

import java.io.File;
import java.io.FileNotFoundException;

public class DBManager {
    public void createDB(String path, String name){
        new File(path + name).mkdirs();
    }

    public File getDB(String path, String name) throws FileNotFoundException {
        File file = new File(path);
        for (File f:
                file.listFiles()) {
            if (f.getName().equals(name)){
                return f;
            }
        }
        throw new FileNotFoundException();
    }
}
