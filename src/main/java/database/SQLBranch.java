package database;


import database.util.query.DeleteQueryBuilder;
import database.util.query.InsertQueryBuilder;
import database.util.query.SelectQueryBuilder;
import database.util.query.UpdateQueryBuilder;
import work.Branch;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLBranch {

    public static final String TABLE_BRANCH = "dbo.Oddzial";
    public static final String B_ID = "Nr_oddzialu";
    public static final String B_ADDRESS = "Adres_oddzialu";

    public static String insertQuery(Branch branch) {
        InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder(TABLE_BRANCH);
        insertQueryBuilder.addObjectForInsert(B_ADDRESS, branch.getName());
        return insertQueryBuilder.construct();
    }

    public static String updateQuery(Branch branch) {
        UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder(TABLE_BRANCH);
        updateQueryBuilder.addObjectForUpdate(B_ADDRESS, branch.getName());
        String where = B_ID + " = " + branch.getId();
        return updateQueryBuilder.construct(where);
    }

    public static String deleteQuery(Branch branch) {
        String where = B_ID + " = " + branch.getId();
        return DeleteQueryBuilder.construct(TABLE_BRANCH, where);
    }

    public static String selectAllQuery() {
        String[] columns = {
                B_ID,
                B_ADDRESS
        };
        return SelectQueryBuilder.construct(TABLE_BRANCH, columns, null, null, null);
    }

    //		POBIERANIE OBIEKTOW Z BAZY
    public static Branch getBranchByResultSet(ResultSet resultSet) throws SQLException {
        return new Branch(
                resultSet.getInt(B_ID),
                resultSet.getString(B_ADDRESS)
        );
    }

}
