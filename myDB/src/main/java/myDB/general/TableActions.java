package myDB.general;

import myDB.filesManage.writer.CSVWriterPart;
import myDB.general.column.ColumnType;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TableActions {
    private DB db;
    private Table table;

    public TableActions(DB db, Table table) {
        this.db = db;
        this.table = table;
    }
    
    public void insert(ArrayList<LinkedHashMap<String, Object>> values) throws UnmatchColsException {
        if (values.size()!=this.table.getColumns().size()){
            throw new UnmatchColsException();
        }
        int counter = 0;
        for (LinkedHashMap<String, Object> part:
             values) {
            LinkedHashMap<String, ColumnType> colsTypes = this.table.getColumns().get(counter);
            if (part.size()!=colsTypes.size()){
                throw new UnmatchColsException();
            }
            for (String key:
                 part.keySet()) {
                if (!part.get(key).getClass().toString().equals(colsTypes.get(key).getDescription())){
                    throw new UnmatchColsException();
                }
            }
            CSVWriterPart writerPart = new CSVWriterPart("0_"+counter+".csv", part);
            writerPart.write(db.getPath()+"\\"+table.getName()+"\\data\\");
            counter++;
        }
    }
}
