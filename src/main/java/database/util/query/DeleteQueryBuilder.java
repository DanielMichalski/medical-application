package database.util.query;

public class DeleteQueryBuilder {

    public static String construct(String tableName, String where) {
        StringBuilder deleteQuery = new StringBuilder();
        deleteQuery.append("DELETE FROM ");
        deleteQuery.append(tableName);
        deleteQuery.append(" WHERE ");
        deleteQuery.append(where);

        return deleteQuery.toString();
    }

}