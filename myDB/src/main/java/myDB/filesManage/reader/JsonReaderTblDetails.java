package myDB.filesManage.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;

public class JsonReaderTblDetails implements Reader {
    private JsonNode node;

    @Override
    public void Read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.node = mapper.readTree(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonNode getNode() {
        return node;
    }
}
