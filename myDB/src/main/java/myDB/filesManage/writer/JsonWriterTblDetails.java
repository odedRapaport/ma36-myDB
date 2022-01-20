package myDB.filesManage.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import myDB.general.Table;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriterTblDetails implements Writer{
    private Table table;

    public JsonWriterTblDetails(Table table) {
        this.table = table;
    }

    @Override
    public void write(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new FileWriter(path + "details.json", true), table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
