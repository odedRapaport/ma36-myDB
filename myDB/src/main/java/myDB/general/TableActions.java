package myDB.general;

import myDB.filesManage.reader.CSVReaderBlock;
import myDB.filesManage.writer.CSVWriterBlock;
import myDB.filesManage.writer.CSVWriterPart;
import myDB.general.column.ColumnType;

import java.io.File;
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

    public ArrayList<LinkedHashMap<String, Object>> markByID(ArrayList<LinkedHashMap<String, Object>> columns){
        ArrayList<LinkedHashMap<String, Object>> returnCols = new ArrayList<>();
        returnCols.add(columns.get(0));
        for (int i = 1; i<columns.size(); i++){
            LinkedHashMap<String, Object> cols = new LinkedHashMap<>();
            cols.put("id", columns.get(0).get("id"));
            for (String key:
                    columns.get(i).keySet()) {
                cols.put(key, columns.get(i).get(key));
            }
            returnCols.add(cols);
        }
        return returnCols;
    }

    public void insert(ArrayList<LinkedHashMap<String, Object>> values) throws UnmatchColsException {
        values = markByID(values);
        if (values.size() != this.table.getColumns().size()) {
            throw new UnmatchColsException();
        }
        int counter = 0;
        for (LinkedHashMap<String, Object> part :
                values) {
            LinkedHashMap<String, ColumnType> colsTypes = this.table.getColumns().get(counter);
            if (part.size() != colsTypes.size() || !part.keySet().equals(colsTypes.keySet())) {
                throw new UnmatchColsException();
            }
            for (String key :
                    part.keySet()) {
                if (!part.get(key).getClass().toString().equals(colsTypes.get(key).getDescription())) {
                    throw new UnmatchColsException();
                }
            }
            CSVWriterPart writerPart = new CSVWriterPart("0_" + counter + ".csv", part);
            writerPart.write(db.getPath() + "\\" + table.getName() + "\\data\\");
            counter++;
        }
    }

    public void update(Object id, LinkedHashMap<String, Object> values) {
        LinkedHashMap<String, Object> relaventVals = new LinkedHashMap<>();
        int counter = 0;
        for (LinkedHashMap<String, ColumnType> cols :
                this.table.getColumns()) {
            for (String key :
                    cols.keySet()) {
                if (values.containsKey(key)) {
                    relaventVals.put(key, values.get(key));
                }
            }
            if (!relaventVals.isEmpty()) {
                CSVReaderBlock reader = new CSVReaderBlock();
                reader.read(db.getPath() + "\\" + table.getName() + "\\data\\0_" + counter + ".csv");
                List<String[]> records = reader.getRecords();
                for (String[] record :
                        records) {
                    if (record[0].equals(id.toString())) {
                        int count = 0;
                        for (String key :
                                cols.keySet()) {
                            if (relaventVals.containsKey(key)) {
                                record[count] = relaventVals.get(key).toString();
                            }
                            count++;
                        }
                    }
                }
                CSVWriterBlock writer = new CSVWriterBlock("0_" + counter + ".csv", records);
                writer.write(db.getPath() + "\\" + table.getName() + "\\data\\");
            }
            relaventVals.clear();
            counter++;
        }
    }

    public void delete(Object id){
        for (int i = 0; i<table.getColumns().size(); i++) {
            new File(db.getPath() + "\\" + table.getName() + "\\data\\");
        }
    }

    public void deleteFromBlock(File file, Object id){

    }
}
