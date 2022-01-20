package myDB.general.column;

import java.util.Date;

public class DBDate extends ColumnType{
    public DBDate() {
        super("Date.class");
    }

    @Override
    public String getDescription() {
        return Date.class.toString();
    }
}
