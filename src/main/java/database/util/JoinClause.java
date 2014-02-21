package database.util;

public class JoinClause {

    private String mainTableName;

    private String joinTableName;

    private String joinColumnName;

    private String mainColumnName;

    private JoinType joinType;

    /**
     * laczenie tabel
     *
     * @param joinTableName  - nazwa tabeli ktora ma byc dolaczona do glownej
     * @param joinColumnName - nazwa kolumny z dolaczonej tabeli ktora ma byc wartoscia laczaca (klauzula ON )
     * @param mainColumnName - nazwa kolumny z tabeli glownej czyli tej do ktorej dolaczamy tabele
     * @param joinType       - typ polaczenia (inner, full, left, right)
     */
    public JoinClause(String mainTableName, String joinTableName, String joinColumnName, String mainColumnName, JoinType joinType) {
        this.mainTableName = mainTableName;
        this.joinTableName = joinTableName;
        this.joinColumnName = joinColumnName;
        this.mainColumnName = mainColumnName;
        this.joinType = joinType;
    }

    public String getJoinTableName() {
        return joinTableName;
    }

    public String getJoinColumnName() {
        return joinColumnName;
    }

    public String getMainColumnName() {
        return mainColumnName;
    }

    public String getMainTableName() {
        return mainTableName;
    }

    public String getJoinTypeClause() {
        return joinType.getJoinClause();
    }
}
