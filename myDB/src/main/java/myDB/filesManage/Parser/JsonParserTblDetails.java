package myDB.filesManage.Parser;

import com.fasterxml.jackson.databind.JsonNode;
import myDB.general.Table;
import myDB.general.column.ColumnType;
import myDB.general.column.DBDate;
import myDB.general.column.DBInteger;
import myDB.general.column.DBString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class JsonParserTblDetails implements Parser{
    private Table table;
    JsonNode node;

    public JsonParserTblDetails(JsonNode node) {
        this.node = node;
    }

    @Override
    public void parse() {
        ArrayList<LinkedHashMap<String, ColumnType>> columns = new ArrayList<>();
        Iterator<JsonNode> iterator = this.node.iterator();
        while (iterator.hasNext()){
            JsonNode jsonNode = iterator.next();
            LinkedHashMap<String, ColumnType> cols = getPart(jsonNode);
            if (!cols.isEmpty()) {
                columns.add(cols);
            }
        }
        this.table = new Table(this.node.get("name").textValue(), Integer.parseInt(this.node.get("blockSize").toString()), columns);
    }

    public Table getTable() {
        return table;
    }

    public LinkedHashMap<String, ColumnType> getPart(JsonNode jsonNode){
        LinkedHashMap<String, ColumnType> part = new LinkedHashMap<>();
        Iterator<JsonNode> iterator = jsonNode.iterator();
        while (iterator.hasNext()){
            JsonNode jsonNode1 = iterator.next();
            ArrayList<String> keys = getKeys(jsonNode1);
            int counter = 0;
            Iterator<JsonNode> iterator1 = jsonNode1.iterator();
            while (iterator1.hasNext()) {
                JsonNode jn= iterator1.next();
                ColumnType type = null;
                if (jn.get("type").textValue().equals("String.class")) {
                    type = new DBString();
                } else if (jn.get("type").textValue().equals("Integer.class")) {
                    type = new DBInteger();
                } else if (jn.get("type").textValue().equals("LocalDate.class")) {
                    type = new DBDate();
                }
                part.put(keys.get(counter), type);
                counter++;
            }
        }
        return part;
    }

    public ArrayList<String> getKeys(JsonNode jsonNode){
        ArrayList<String> keys = new ArrayList<>();
        Iterator<String> iterator = jsonNode.fieldNames();
        iterator.forEachRemaining(e -> keys.add(e));
        return keys;
    }
}
