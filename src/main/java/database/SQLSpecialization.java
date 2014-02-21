package database;

import database.util.query.DeleteQueryBuilder;
import database.util.query.InsertQueryBuilder;
import database.util.query.SelectQueryBuilder;
import database.util.query.UpdateQueryBuilder;
import work.Specialization;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLSpecialization {

    public static final String TABLE_SPECIALIZATION = "dbo.Specjalizacja";
    public static final String S_ID = "Id_specjalizacji";
    public static final String S_NAME = "Nazwa_specjalizacji";

    public static String insertQuery(Specialization specialization) {
        InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder(TABLE_SPECIALIZATION);
        insertQueryBuilder.addObjectForInsert(S_NAME, specialization.getName());
        return insertQueryBuilder.construct();
    }

    public static String updateQuery(Specialization specialization) {
        UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder(TABLE_SPECIALIZATION);
        updateQueryBuilder.addObjectForUpdate(S_NAME, specialization.getName());
        String where = S_ID + " = " + specialization.getId();
        return updateQueryBuilder.construct(where);
    }

    public static String deleteQuery(Specialization specialization) {
        String where = S_ID + " = " + specialization.getId();
        return DeleteQueryBuilder.construct(TABLE_SPECIALIZATION, where);
    }

    public static String selectAllQuery() {
        String[] columns = {
                S_ID,
                S_NAME
        };
        return SelectQueryBuilder.construct(TABLE_SPECIALIZATION, columns, null, null, null);
    }


    //		POBIERANIE OBIEKTOW Z BAZY
    public static Specialization getSpecializationByResultSet(ResultSet resultSet) throws SQLException {
        return new Specialization(
                resultSet.getInt(S_ID),
                resultSet.getString(S_NAME)
        );
    }

}
