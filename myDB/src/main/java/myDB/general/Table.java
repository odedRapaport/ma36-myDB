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
        this.columns = columns;
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
