package myDB.general.column;

import java.time.LocalDate;

public class DBDate extends ColumnType{
    LocalDate value;

    public DBDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    public void setValue(LocalDate value) {
        this.value = value;
    }
}
