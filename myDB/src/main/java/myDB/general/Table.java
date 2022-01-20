package myDB.general;

import myDB.general.column.ColumnType;

import java.util.*;

public class Table {
    private String name;
    private int blockSize;
    private List<LinkedHashMap<String, ColumnType>> columns;

    public Table(String name, int blockSize, ArrayList<LinkedHashMap<String, ColumnType>> columns) {
        this.name = name;
        this.blockSize = blockSize;
        this.columns = markByID(columns);
    }

    public ArrayList<LinkedHashMap<String, ColumnType>> markByID(ArrayList<LinkedHashMap<String, ColumnType>> columns){
        ArrayList<LinkedHashMap<String, ColumnType>> returnCols = new ArrayList<>();
        returnCols.add(columns.get(0));
        for (int i = 1; i<columns.size(); i++){
            LinkedHashMap<String, ColumnType> cols = new LinkedHashMap<>();
            cols.put("id", columns.get(0).get("id"));
            for (String key:
                 columns.get(i).keySet()) {
                cols.put(key, columns.get(i).get(key));
            }
            returnCols.add(cols);
        }
        return returnCols;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public String getName() {
        return name;
    }

    public List<LinkedHashMap<String, ColumnType>> getColumns() {
        return columns;
    }
}
