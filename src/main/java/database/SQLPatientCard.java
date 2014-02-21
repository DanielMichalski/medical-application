package database;

import database.util.query.DeleteQueryBuilder;
import database.util.query.InsertQueryBuilder;
import database.util.query.SelectQueryBuilder;
import database.util.query.UpdateQueryBuilder;
import patient.PatientCard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPatientCard {

    public static final String TABLE_PATIENT = "dbo.Pacjent";
    public static final String P_ID = "Id_pacjenta";
    public static final String P_PESEL = "PESEL";
    public static final String P_FIRST_NAME = "Imie";
    public static final String P_LAST_NAME = "Nazwisko";
    public static final String P_ADDRESS = "Adres";
    public static final String P_PHONE_NO = "Telefon";
    public static final String P_E_MAIL = "E_mail";

    public static String insertQuery(PatientCard patientCard) {
        InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder(TABLE_PATIENT);
        insertQueryBuilder.addObjectForInsert(P_PESEL, patientCard.getPESEL());
        insertQueryBuilder.addObjectForInsert(P_FIRST_NAME, patientCard.getFirstName());
        insertQueryBuilder.addObjectForInsert(P_LAST_NAME, patientCard.getLastName());
        insertQueryBuilder.addObjectForInsert(P_ADDRESS, patientCard.getAddress());
        insertQueryBuilder.addObjectForInsert(P_PHONE_NO, patientCard.getPhoneNo());
        insertQueryBuilder.addObjectForInsert(P_E_MAIL, patientCard.geteMail());
        return insertQueryBuilder.construct();
    }

    public static String updateQuery(PatientCard patientCard) {
        UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder(TABLE_PATIENT);
        updateQueryBuilder.addObjectForUpdate(P_PESEL, patientCard.getPESEL());
        updateQueryBuilder.addObjectForUpdate(P_FIRST_NAME, patientCard.getFirstName());
        updateQueryBuilder.addObjectForUpdate(P_LAST_NAME, patientCard.getLastName());
        updateQueryBuilder.addObjectForUpdate(P_ADDRESS, patientCard.getAddress());
        updateQueryBuilder.addObjectForUpdate(P_PHONE_NO, patientCard.getPhoneNo());
        updateQueryBuilder.addObjectForUpdate(P_E_MAIL, patientCard.geteMail());
        String where = P_ID + " = " + patientCard.getId();
        return updateQueryBuilder.construct(where);
    }

    public static String deleteQuery(PatientCard patientCard) {
        String where = P_ID + " = " + patientCard.getId();
        return DeleteQueryBuilder.construct(TABLE_PATIENT, where);
    }

    public static String selectAllQuery() {
        String[] columns = {
                P_ID,
                P_PESEL,
                P_FIRST_NAME,
                P_LAST_NAME,
                P_ADDRESS,
                P_PHONE_NO,
                P_E_MAIL
        };
        return SelectQueryBuilder.construct(TABLE_PATIENT, columns, null, null, null);
    }


    //		POBIERANIE OBIEKTOW Z BAZY
    public static PatientCard getPatientByResultSet(ResultSet resultSet) throws SQLException {
        return new PatientCard(
                resultSet.getInt(P_ID),
                resultSet.getString(P_PESEL),
                resultSet.getString(P_FIRST_NAME),
                resultSet.getString(P_LAST_NAME),
                resultSet.getString(P_ADDRESS),
                resultSet.getString(P_PHONE_NO),
                resultSet.getString(P_E_MAIL)
        );
    }
}
