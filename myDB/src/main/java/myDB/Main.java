package myDB;

import com.fasterxml.jackson.databind.JsonNode;
import myDB.filesManage.Parser.JsonParserTblDetails;
import myDB.filesManage.reader.JsonReaderTblDetails;
import myDB.general.*;
import myDB.general.column.ColumnType;
import myDB.general.column.DBDate;
import myDB.general.column.DBInteger;
import myDB.general.column.DBString;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        DB myDB = new DB(path+"\\");
        /*Table table = null;
        try {
            table = myDB.getTbl("oded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TableActions tableActions = new TableActions(myDB, table);
        ArrayList<LinkedHashMap<String, Object>> values = new ArrayList<>();
        LinkedHashMap<String, Object> part1 = new LinkedHashMap<>();
        Date date = new Date();
        date.setTime(900000000);
        part1.put("birth_date", date);
        values.add(part1);
        tableActions.update(2, part1);





        manager.createDB("C:\\Users\\עודד\\Desktop\\", "myDataBase");
        DB db = null;
        try {
            db = new DB(manager.getDB("C:\\Users\\עודד\\Desktop\\", "myDataBase").getPath()+"\\");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        ArrayList<LinkedHashMap<String, ColumnType>> columns = new ArrayList<>();
        LinkedHashMap<String, ColumnType> part1 = new LinkedHashMap<>();
        part1.put("id", new DBInteger());
        part1.put("first_name", new DBString());
        part1.put("last_name", new DBString());
        part1.put("birth_date", new DBDate());
        columns.add(part1);
        LinkedHashMap<String, ColumnType> part2 = new LinkedHashMap<>();
        part2.put("city", new DBString());
        part2.put("street", new DBString());
        part2.put("house_number", new DBInteger());
        columns.add(part2);
        myDB.createTbl(new Table("double", 4,columns));
    }
}
