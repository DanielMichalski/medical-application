package database.util.query;

import database.util.JoinClause;

public class SelectQueryBuilder {

    /**
     * Metoda tworzaca gotowe zapytania SELECT
     *
     * @param tableName   - nazwa tabel z ktorej pobieramy dane
     * @param columns     - kolekcja nazw kolumn ktore bierzemy pod uwage w klauzuli SELECT
     * @param joinClauses - tablica obiektow przeznaczona do laczenia tabeli glownej z parametru tableName
     * @param where       - gotowy warunek typu "Id_pracownika = 2"
     * @param orderBy     - gotowy order
     */
    public static String construct(String tableName, String[] columns, JoinClause[] joinClauses, String where, String orderBy) {
        if (columns == null || columns.length == 0) {
            System.out.println("Nie podano w kolumn w klausuli SELECT");
            return "";
        }

        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append(createSelectClause(columns));
        selectQuery.append(createFromClause(tableName));
        selectQuery.append(createJoinClause(joinClauses));
        selectQuery.append(createWhereClause(where));
        selectQuery.append(createOrderByClause(orderBy));

        return selectQuery.toString();
    }

    private static String createSelectClause(String[] columnNames) {
        StringBuilder selectClause = new StringBuilder();
        selectClause.append("SELECT ");

        int lastIndex = columnNames.length - 1;
        for (int i = 0; i < columnNames.length; i++) {
            selectClause.append(columnNames[i]);
            boolean notLastColumn = i != (lastIndex);
            if (notLastColumn) {
                selectClause.append(", ");
            }
        }

        return selectClause.toString();
    }

    private static String createFromClause(String tableName) {
        StringBuilder fromClause = new StringBuilder();
        fromClause.append(" FROM ");
        fromClause.append(tableName);

        return fromClause.toString();
    }

    private static String createJoinClause(JoinClause[] joinClauses) {
        StringBuilder joinClause = new StringBuilder();

        if (joinClauses != null && joinClauses.length != 0) {
            for (JoinClause joinClauseObj : joinClauses) {
                joinClause.append(joinClauseObj.getJoinTypeClause());
                joinClause.append(joinClauseObj.getJoinTableName());
                joinClause.append(" ON ");
                joinClause.append(joinClauseObj.getMainTableName());
                joinClause.append(".");
                joinClause.append(joinClauseObj.getMainColumnName());
                joinClause.append(" = ");
                joinClause.append(joinClauseObj.getJoinTableName());
                joinClause.append(".");
                joinClause.append(joinClauseObj.getJoinColumnName());
            }
        } else {
            joinClause.append("");
        }

        return joinClause.toString();
    }

    private static String createWhereClause(String where) {
        StringBuilder whereClause = new StringBuilder();
        if (where != null && !where.equals("")) {
            whereClause.append(" WHERE ");
            whereClause.append(where);
        } else {
            whereClause.append("");
        }

        return whereClause.toString();
    }

    private static String createOrderByClause(String orderBy) {
        StringBuilder orderByClause = new StringBuilder();
        if (orderBy != null && !orderBy.equals("")) {
            orderByClause.append(" ORDER BY ");
            orderByClause.append(orderBy);
        } else {
            orderByClause.append("");
        }

        return orderByClause.toString();
    }

}