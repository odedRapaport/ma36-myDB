package myDB.filesManage.writer;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

public class CSVWriterPart implements Writer{
    private String name;
    private LinkedHashMap<String, Object> part;

    public CSVWriterPart(String name, LinkedHashMap<String,Object> part) {
        this.name = name;
        this.part = part;
    }

    @Override
    public void write(String path) {
        String[] values = new String[this.part.size()];
        int counter = 0;
        for (String key:
             part.keySet()) {
            values[counter] = part.get(key).toString();
            counter++;
        }
        try {
            FileWriter fileWriter = new FileWriter(path + name, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeNext(values);
            csvWriter.close();
        } catch (IOException e) {
            try {
                FileWriter fileWriter = new FileWriter(path + name+"\\", true);
                CSVWriter csvWriter = new CSVWriter(fileWriter);
                csvWriter.writeNext(values);
                csvWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
