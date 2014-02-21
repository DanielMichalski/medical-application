package database;

import database.util.JoinClause;
import database.util.JoinType;
import database.util.query.*;
import employees.Doctor;
import employees.NoAccountTypeException;
import patient.PatientCard;
import patient.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLReservation {

    public static final String TABLE_RESERVATION = "dbo.Rezerwacja";
    private static final String R_ID = "Nr_rezerwacji";
    private static final String R_PATIENT_ID = "Id_pacjenta";
    private static final String R_DOCTOR_ID = "Id_pracownika";
    private static final String R_DATE_RESERVATION = "Data_rezerwacji";
    private static final String R_DATE_VISIT = "Data_wizyty";

    public static String getDateInSQLFormat(Date date) {
        Format formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return formatter.format(date);
    }

    public static String insertQuery(Reservation reservation) {
        InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder(TABLE_RESERVATION);
        insertQueryBuilder.addObjectForInsert(R_PATIENT_ID, reservation.getPatientCard().getId());
        insertQueryBuilder.addObjectForInsert(R_DOCTOR_ID, reservation.getDoctor().getId());
        Date reservationDate = reservation.getDateReservation();
        insertQueryBuilder.addObjectForInsert(R_DATE_RESERVATION, getDateInSQLFormat(reservationDate));
        Date visitDate = reservation.getDateVisit();
        insertQueryBuilder.addObjectForInsert(R_DATE_VISIT, getDateInSQLFormat(visitDate));
        return insertQueryBuilder.construct();
    }

    public static String updateQuery(Reservation reservation) {
        UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder(TABLE_RESERVATION);
        updateQueryBuilder.addObjectForUpdate(R_DOCTOR_ID, reservation.getDoctor().getId());
        Date reservationDate = reservation.getDateReservation();
        updateQueryBuilder.addObjectForUpdate(R_DATE_RESERVATION, getDateInSQLFormat(reservationDate));
        Date visitDate = reservation.getDateVisit();
        updateQueryBuilder.addObjectForUpdate(R_DATE_VISIT, getDateInSQLFormat(visitDate));
        String where = R_ID + " = " + reservation.getId();
        return updateQueryBuilder.construct(where);
    }

    public static String deleteQuery(Reservation reservation) {
        String where = R_ID + " = " + reservation.getId();
        return DeleteQueryBuilder.construct(TABLE_RESERVATION, where);
    }

    public static String deleteQueryByPatient(PatientCard patientCard) {
        String where = R_PATIENT_ID + " = " + patientCard.getId();
        return DeleteQueryBuilder.construct(TABLE_RESERVATION, where);
    }

    public static String selectAllQuery() {
        String[] columns = {"*"};
        JoinClause[] joinClauses = {
                new JoinClause(TABLE_RESERVATION, SQLWorker.TABLE_WORKER, SQLWorker.W_ID, R_DOCTOR_ID, JoinType.INNER)
        };
        return SelectQueryBuilder.construct(TABLE_RESERVATION, columns, joinClauses, null, null);
    }

    public static String selectReservationsForDoctorQuery(Doctor doctor, Date first, Date last) {
        String[] columns = {"*"};
        JoinClause[] joinClauses = {
                new JoinClause(TABLE_RESERVATION, SQLPatientCard.TABLE_PATIENT, SQLPatientCard.P_ID, R_PATIENT_ID, JoinType.LEFT)
        };
        String between = CustomQueryBuilder.construct(R_DATE_VISIT, " BETWEEN '", getDateInSQLFormat(first), "' AND '", getDateInSQLFormat(last), "'");
        String where = "(" + R_DOCTOR_ID + " = " + doctor.getId() + ") AND (" + between + ")";
        String orderBy = R_DATE_VISIT;
        return SelectQueryBuilder.construct(TABLE_RESERVATION, columns, joinClauses, where, orderBy);
    }

    public static String selectReservationsForPatientQuery(PatientCard patient) {
        String[] columns = {"*"};
        JoinClause[] joinClauses = {
                new JoinClause(
                        TABLE_RESERVATION,
                        SQLWorker.TABLE_WORKER,
                        SQLWorker.W_ID,
                        R_DOCTOR_ID,
                        JoinType.INNER),
                new JoinClause(
                        SQLWorker.TABLE_WORKER,
                        SQLBranch.TABLE_BRANCH,
                        SQLBranch.B_ID,
                        SQLWorker.W_BRANCH_ID,
                        JoinType.INNER),
                new JoinClause(
                        SQLWorker.TABLE_WORKER,
                        SQLSpecialization.TABLE_SPECIALIZATION,
                        SQLSpecialization.S_ID,
                        SQLWorker.W_SPECIALIZATION_ID,
                        JoinType.INNER)
        };
        String where = R_PATIENT_ID + " = " + patient.getId();
        return SelectQueryBuilder.construct(TABLE_RESERVATION, columns, joinClauses, where, null);
    }

    //		POBIERANIE OBIEKTOW Z BAZY
    public static Reservation getReservationByResultSet(Doctor doctor, ResultSet resultSet) throws SQLException, NoAccountTypeException {
        PatientCard patientCard = SQLPatientCard.getPatientByResultSet(resultSet);
        Timestamp timestampReservation = resultSet.getTimestamp(R_DATE_RESERVATION);
        Timestamp timestampVisit = resultSet.getTimestamp(R_DATE_VISIT);
        return new Reservation(
                resultSet.getInt(R_ID),
                doctor,
                patientCard,
                new Date(timestampReservation.getTime()),
                new Date(timestampVisit.getTime())
        );
    }

    public static Reservation getReservationByResultSet(PatientCard patientCard, ResultSet resultSet) throws SQLException, NoAccountTypeException {
        Doctor doctor = (Doctor) SQLWorker.getWorkerByResultSet(resultSet);
        Timestamp timestampReservation = resultSet.getTimestamp(R_DATE_RESERVATION);
        Timestamp timestampVisit = resultSet.getTimestamp(R_DATE_VISIT);
        return new Reservation(
                resultSet.getInt(R_ID),
                doctor,
                patientCard,
                new Date(timestampReservation.getTime()),
                new Date(timestampVisit.getTime())
        );
    }

}