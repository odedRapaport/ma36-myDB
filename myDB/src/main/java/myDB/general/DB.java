package myDB.general;

import java.io.File;
import java.io.FileNotFoundException;

public class DB {
    private String path;

    public DB(String path) {
        this.path = path;
    }

    public void createTbl(Table tbl){
        new File(path + tbl.getName()).mkdirs();
        File table = new File(path + tbl.getName());
    }

    public File getTbl(String name) throws FileNotFoundException {
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
