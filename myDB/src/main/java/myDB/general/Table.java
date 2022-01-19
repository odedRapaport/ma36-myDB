package myDB.general;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Table {
    private String name;
    private List<Map<String, Type>> columns;

    public Table(String name, List<Map<String, Type>> columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public List<Map<String, Type>> getColumns() {
        return columns;
    }
}
