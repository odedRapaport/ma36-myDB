package myDB.general;

import com.fasterxml.jackson.databind.JsonNode;
import myDB.filesManage.Parser.JsonParserTblDetails;
import myDB.filesManage.reader.JsonReaderTblDetails;
import myDB.filesManage.writer.JsonWriterTblDetails;

import java.io.File;
import java.io.FileNotFoundException;

public class DB {
    private String path;

    public DB(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void createTbl(Table tbl){
        new File(path + tbl.getName()).mkdirs();
        File table = new File(path + tbl.getName());
        JsonWriterTblDetails writerTblDetails = new JsonWriterTblDetails(tbl);
        writerTblDetails.write(table.getPath()+"//");
        new File(table.getPath()+"//data").mkdirs();
    }

    public Table getTbl(String name) throws FileNotFoundException {
        File file = new File(path);
        for (File f:
             file.listFiles()) {
            if (f.getName().equals(name)){
                JsonReaderTblDetails reader = new JsonReaderTblDetails();
                reader.Read(path + "\\" + name + "\\details.json");
                JsonNode node = reader.getNode();
                JsonParserTblDetails parser = new JsonParserTblDetails(node);
                parser.parse();
                Table table = parser.getTable();
                return table;
            }
        }
        throw new FileNotFoundException();
    }
}
