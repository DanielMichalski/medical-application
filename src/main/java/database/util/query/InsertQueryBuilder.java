package database.util.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertQueryBuilder {

    private String tableName;

    private List<String> insertingColumns;

    private Map<String, Object> insertingValues;

    public InsertQueryBuilder(String tableName) {
        this.tableName = tableName;
        insertingColumns = new ArrayList<String>();
        insertingValues = new HashMap<String, Object>();
    }

    public void addObjectForInsert(String columnName, Object value) {
        if (value == null) {
            return;
        }
        insertingColumns.add(columnName);
        insertingValues.put(columnName, value);
    }

    public String construct() {
        if (insertingColumns.isEmpty()) {
            return "";
        }

        StringBuilder insertQuery = new StringBuilder();
        insertQuery.append(createInsertClause());
        insertQuery.append(createValueClause());

        return insertQuery.toString();
    }

    /**
     * Metoda tworzy sekwencje "INSERT INTO dbo.Pracownik (columnName1, columnName2...)"
     */
    private String createInsertClause() {
        StringBuilder insertClause = new StringBuilder();
        insertClause.append("INSERT INTO ");
        insertClause.append(tableName);
        insertClause.append(createInsertingColumns());

        return insertClause.toString();
    }

    private String createInsertingColumns() {
        int lastIndex = insertingColumns.size() - 1;

        StringBuilder columns = new StringBuilder();
        columns.append(" (");
        for (int i = 0; i < insertingColumns.size(); i++) {
            String columnName = insertingColumns.get(i);
            columns.append(columnName);
            boolean notLastColumn = i != (lastIndex);
            if (notLastColumn) {
                columns.append(", ");
            }
        }
        columns.append(")");
        return columns.toString();
    }

    /**
     * Metoda tworzy sekwencje " VALUES ( 'value1', 'value2'...)"
     */
    private String createValueClause() {
        StringBuilder insertClause = new StringBuilder();
        insertClause.append(" VALUES ");
        insertClause.append(createInsertingValues());

        return insertClause.toString();
    }

    private String createInsertingValues() {
        int lastIndex = insertingColumns.size() - 1;

        StringBuilder values = new StringBuilder();
        values.append("(");
        for (int i = 0; i < insertingColumns.size(); i++) {
            String columnName = insertingColumns.get(i);

            values.append("'");
            values.append(getValueByColumnName(columnName));
            values.append("'");
            boolean notLastColumn = i != (lastIndex);
            if (notLastColumn) {
                values.append(", ");
            }
        }
        values.append(")");
        return values.toString();
    }

    private Object getValueByColumnName(String columnName) {
        return insertingValues.get(columnName);
    }

}