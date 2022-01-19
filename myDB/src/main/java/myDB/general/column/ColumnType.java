package myDB.general.column;

public abstract class ColumnType {
    protected String type;

    public ColumnType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
