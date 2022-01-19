package myDB.general.column;

public class DBString extends ColumnType{
    private String value;
    private int limit;

    public DBString(String value, int limit) {
        this.value = value;
        this.limit = limit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
