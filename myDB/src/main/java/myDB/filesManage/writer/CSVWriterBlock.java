package myDB.filesManage.writer;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriterBlock implements Writer{
    private String name;
    private List<String[]> block;

    public CSVWriterBlock(String name, List<String[]> block) {
        this.name = name;
        this.block = block;
    }

    @Override
    public void write(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path + name);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(block);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
