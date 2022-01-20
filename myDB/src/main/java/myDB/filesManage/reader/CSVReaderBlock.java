package myDB.filesManage.reader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReaderBlock implements Reader {
    private List<String[]> records;

    @Override
    public void read(String path) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(path));
            this.records = csvReader.readAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getRecords() {
        return records;
    }
}
