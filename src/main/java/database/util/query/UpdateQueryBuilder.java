package database.util.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateQueryBuilder {

    private String tableName;

    private List<String> updatingColumns;

    private Map<String, Object> updatingValues;


    public UpdateQueryBuilder(String tableName) {
        this.tableName = tableName;
        updatingColumns = new ArrayList<String>();
        updatingValues = new HashMap<String, Object>();
    }

    public void addObjectForUpdate(String columnName, Object value) {
        if (value == null) {
            return;
        }
        updatingColumns.add(columnName);
        updatingValues.put(columnName, value);
    }

    public String construct(String where) {
        if (updatingColumns.isEmpty() || where == null || where.equals("")) {
            return "";
        }

        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append(createUpdateClause());
        updateQuery.append(createSetValuesClause());
        updateQuery.append(createWhereClause(where));

        return updateQuery.toString();
    }

    /**
     * Metoda tworzy sekwencje "UPDATA dbo.Pracownik"
     */
    private String createUpdateClause() {
        StringBuilder insertClause = new StringBuilder();
        insertClause.append("UPDATE ");
        insertClause.append(tableName);

        return insertClause.toString();
    }

    /**
     * Metoda tworzy sekwencje " SET (columnName = 'value', columnName2 = 'value2' ...)"
     */
    private String createSetValuesClause() {
        StringBuilder setValuesClause = new StringBuilder();
        setValuesClause.append(" SET ");

        int lastIndex = updatingColumns.size() - 1;
        for (int i = 0; i < updatingColumns.size(); i++) {
            String columnName = updatingColumns.get(i);
            setValuesClause.append(columnName);
            setValuesClause.append(" = ");
            setValuesClause.append("'");
            setValuesClause.append(getValueByColumnName(columnName));
            setValuesClause.append("'");
            boolean notLastColumn = i != (lastIndex);
            if (notLastColumn) {
                setValuesClause.append(", ");
            }
        }

        return setValuesClause.toString();
    }

    private String createWhereClause(String where) {
        StringBuilder whereClause = new StringBuilder();
        if (where != null) {
            whereClause.append(" WHERE ");
            whereClause.append(where);
        } else {
            whereClause.append("");
        }

        return whereClause.toString();
    }

    private Object getValueByColumnName(String columnName) {
        return updatingValues.get(columnName);
    }
}