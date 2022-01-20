package myDB;

import com.fasterxml.jackson.databind.JsonNode;
import myDB.filesManage.Parser.JsonParserTblDetails;
import myDB.filesManage.reader.JsonReaderTblDetails;
import myDB.general.DB;
import myDB.general.DBManager;
import myDB.general.Table;
import myDB.general.column.ColumnType;
import myDB.general.column.DBDate;
import myDB.general.column.DBInteger;
import myDB.general.column.DBString;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        DBManager manager = new DBManager();
        String path = "";
        try {
            path = manager.getDB("C:\\Users\\עודד\\Desktop\\", "myDataBase").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DB myDB = new DB(path);
        Table table = null;
        try {
            table = myDB.getTbl("oded");
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }





        /*manager.createDB("C:\\Users\\עודד\\Desktop\\", "myDataBase");
        DB db = null;
        try {
            db = new DB(manager.getDB("C:\\Users\\עודד\\Desktop\\", "myDataBase").getPath()+"\\");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<LinkedHashMap<String, ColumnType>> columns = new ArrayList<>();
        LinkedHashMap<String, ColumnType> part1 = new LinkedHashMap<>();
        part1.put("id", new DBInteger());
        part1.put("first_name", new DBString());
        part1.put("last_name", new DBString());
        part1.put("birth_date", new DBDate());
        columns.add(part1);
        db.createTbl(new Table("oded", 4,columns));*/
    }
}
