package database.util;

public enum JoinType {
    /**
     * Wybieranie rodzaju klauzul SQL join
     * <p/>
     * rozpatrujemy przypadek laczenia table1 z tabel2 po table1.kolumn1 z table2.kolumn2
     * INNER - SELECT pobierze rekord tylko wtedy kiedy table1.kolumn1 i table2.kolumn2 jest rozna od null
     * FULL - zawsze pobiera rekord uzupelniajac null'ami brakujace pola
     * RIGHT -
     * LEFT -
     */
    INNER {
        @Override
        public String getJoinClause() {
            return " INNER JOIN ";
        }
    }, FULL {
        @Override
        public String getJoinClause() {
            return " FULL JOIN ";
        }
    }, RIGHT {
        @Override
        public String getJoinClause() {
            return " RIGHT JOIN ";
        }
    }, LEFT {
        @Override
        public String getJoinClause() {
            return " LEFT JOIN ";
        }
    };

    abstract public String getJoinClause();

}
