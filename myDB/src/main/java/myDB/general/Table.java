package myDB.general;

import myDB.general.column.ColumnType;

import java.util.*;

public class Table {
    private String name;
    private List<LinkedHashMap<String, ColumnType>> columns;

    public Table(String name, ArrayList<LinkedHashMap<String, ColumnType>> columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public List<LinkedHashMap<String, ColumnType>> getColumns() {
        return columns;
    }
}
