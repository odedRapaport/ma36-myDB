package myDB.general.column;

public class DBInteger extends ColumnType{
    public DBInteger() {
        super("Integer.class");
    }

    @Override
    public String getDescription() {
        return Integer.class.toString();
    }
}
