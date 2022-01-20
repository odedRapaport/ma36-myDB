package myDB.general;

import myDB.filesManage.reader.CSVReaderBlock;
import myDB.filesManage.writer.CSVWriterBlock;
import myDB.filesManage.writer.CSVWriterPart;
import myDB.general.column.ColumnType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
            if (part.size()!=colsTypes.size()||!part.keySet().equals(colsTypes.keySet())){
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

    public void update(int id, LinkedHashMap<String, Object> values){
        LinkedHashMap<String, Object> relaventVals = new LinkedHashMap<>();
        int counter = 0;
        for (LinkedHashMap<String, ColumnType> cols:
             this.table.getColumns()) {
            for (String key:
                 cols.keySet()) {
                if(values.containsKey(key)){
                    relaventVals.put(key, values.get(key));
                }
            }
            if (!relaventVals.isEmpty()){
                CSVReaderBlock reader = new CSVReaderBlock();
                reader.read(db.getPath()+"\\"+table.getName()+"\\data\\0_"+counter+".csv");
                List<String[]> records = reader.getRecords();
                for (String[] record:
                     records) {
                    if (record[0].equals(Integer.toString(id))){
                        int count = 0;
                        for (String key:
                             cols.keySet()) {
                            if (relaventVals.containsKey(key)){
                                record[count] = relaventVals.get(key).toString();
                            }
                            count++;
                        }
                    }
                }
                CSVWriterBlock writer = new CSVWriterBlock("0_"+counter+".csv", records);
                writer.write(db.getPath()+"\\"+table.getName()+"\\data\\");
            }
            relaventVals.clear();
            counter++;
        }
    }
}
