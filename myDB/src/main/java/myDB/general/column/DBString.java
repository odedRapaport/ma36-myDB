package myDB.general.column;

public class DBString extends ColumnType{
    public DBString(){
        super("String.class");
    }

    @Override
    public String getDescription() {
        return String.class.toString();
    }
}
