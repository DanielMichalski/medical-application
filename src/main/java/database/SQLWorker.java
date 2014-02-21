package database;


import database.util.JoinClause;
import database.util.JoinType;
import database.util.query.DeleteQueryBuilder;
import database.util.query.InsertQueryBuilder;
import database.util.query.SelectQueryBuilder;
import database.util.query.UpdateQueryBuilder;
import employees.*;
import util.Worker;
import work.Branch;
import work.Specialization;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLWorker {

    public static final String TABLE_WORKER = "dbo.Pracownik";
    public static final String W_ID = "Id_pracownika";
    public static final String W_LOGIN = "Login";
    public static final String W_PASSWORD = "Haslo";
    public static final String W_FIRST_NAME = "Imie";
    public static final String W_LAST_NAME = "Nazwisko";
    public static final String W_ADDRESS = "Adres";
    public static final String W_EMAIL = "Email";
    public static final String W_PHONE_NO = "Telefon";
    public static final String W_SPECIALIZATION_ID = "Id_specjalizacji";
    public static final String W_BRANCH_ID = "Nr_oddzialu";
    public static final String W_ACCOUNT_TYPE_ID = "Id_rodzaju_konta";

    public static String insertQuery(Worker worker) {
        InsertQueryBuilder insertQueryBuilder = new InsertQueryBuilder(TABLE_WORKER);
        insertQueryBuilder.addObjectForInsert(W_LOGIN, worker.getLogin());
        insertQueryBuilder.addObjectForInsert(W_PASSWORD, worker.getPassword());
        insertQueryBuilder.addObjectForInsert(W_FIRST_NAME, worker.getFirstName());
        insertQueryBuilder.addObjectForInsert(W_LAST_NAME, worker.getLastName());
        insertQueryBuilder.addObjectForInsert(W_ADDRESS, worker.getAddress());
        insertQueryBuilder.addObjectForInsert(W_EMAIL, worker.geteMail());
        insertQueryBuilder.addObjectForInsert(W_PHONE_NO, worker.getPhoneNo());
        insertQueryBuilder.addObjectForInsert(W_SPECIALIZATION_ID, worker.getSpecialization().getId());
        insertQueryBuilder.addObjectForInsert(W_BRANCH_ID, worker.getBranch().getId());
        insertQueryBuilder.addObjectForInsert(W_ACCOUNT_TYPE_ID, worker.getAccountType().getId());
        return insertQueryBuilder.construct();
    }

    public static String updateQuery(Worker worker) {
        UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder(TABLE_WORKER);
        updateQueryBuilder.addObjectForUpdate(W_LOGIN, worker.getLogin());
        updateQueryBuilder.addObjectForUpdate(W_PASSWORD, worker.getPassword());
        updateQueryBuilder.addObjectForUpdate(W_FIRST_NAME, worker.getFirstName());
        updateQueryBuilder.addObjectForUpdate(W_LAST_NAME, worker.getLastName());
        updateQueryBuilder.addObjectForUpdate(W_ADDRESS, worker.getAddress());
        updateQueryBuilder.addObjectForUpdate(W_EMAIL, worker.geteMail());
        updateQueryBuilder.addObjectForUpdate(W_PHONE_NO, worker.getPhoneNo());
        updateQueryBuilder.addObjectForUpdate(W_SPECIALIZATION_ID, worker.getSpecialization().getId());
        updateQueryBuilder.addObjectForUpdate(W_BRANCH_ID, worker.getBranch().getId());
        updateQueryBuilder.addObjectForUpdate(W_ACCOUNT_TYPE_ID, worker.getAccountType().getId());
        String where = W_ID + " = " + worker.getId();
        return updateQueryBuilder.construct(where);
    }

    public static String deleteQuery(Worker worker) {
        String where = W_ID + " = " + worker.getId();
        return DeleteQueryBuilder.construct(TABLE_WORKER, where);
    }

    public static String selectAllQuery() {
        String[] columns = {"*"};
        JoinClause[] joinClauses = {
                new JoinClause(TABLE_WORKER, SQLBranch.TABLE_BRANCH, SQLBranch.B_ID, W_BRANCH_ID, JoinType.LEFT),
                new JoinClause(TABLE_WORKER, SQLSpecialization.TABLE_SPECIALIZATION, SQLSpecialization.S_ID, W_SPECIALIZATION_ID, JoinType.LEFT)
        };
        return SelectQueryBuilder.construct(TABLE_WORKER, columns, joinClauses, null, null);
    }

    public static String selectDoctorsQuery() {
        String[] columns = {"*"};
        JoinClause[] joinClauses = {
                new JoinClause(TABLE_WORKER, SQLBranch.TABLE_BRANCH, SQLBranch.B_ID, W_BRANCH_ID, JoinType.LEFT),
                new JoinClause(TABLE_WORKER, SQLSpecialization.TABLE_SPECIALIZATION, SQLSpecialization.S_ID, W_SPECIALIZATION_ID, JoinType.LEFT)
        };
        String where = W_ACCOUNT_TYPE_ID + " = " + AccountType.DOCTOR.getId();
        return SelectQueryBuilder.construct(TABLE_WORKER, columns, joinClauses, where, null);
    }

    public static String selectByLoginAndPasswordQuery(String login, String password) {
        String[] columns = {"*"};
        String where = W_LOGIN + " = '" + login + "' AND " + W_PASSWORD + " = '" + password + "'";
        JoinClause[] joinClauses = {
                new JoinClause(TABLE_WORKER, SQLBranch.TABLE_BRANCH, SQLBranch.B_ID, W_BRANCH_ID, JoinType.LEFT),
                new JoinClause(TABLE_WORKER, SQLSpecialization.TABLE_SPECIALIZATION, SQLSpecialization.S_ID, W_SPECIALIZATION_ID, JoinType.LEFT)
        };
        return SelectQueryBuilder.construct(TABLE_WORKER, columns, joinClauses, where, null);
    }


    //		POBIERANIE OBIEKTOW Z BAZY
    public static Worker getWorkerByResultSet(ResultSet resultSet) throws SQLException, NoAccountTypeException {
        int idAccountType = resultSet.getInt(W_ACCOUNT_TYPE_ID);
        boolean adminAccount = idAccountType == AccountType.ADMIN.getId();
        boolean doctorAccount = idAccountType == AccountType.DOCTOR.getId();
        boolean receptionistAccount = idAccountType == AccountType.RECEPTIONIST.getId();

        Worker worker;
        if (adminAccount) {
            worker = getAdminByResultSet(resultSet);
        } else if (doctorAccount) {
            worker = getDoctorByResultSet(resultSet);
        } else if (receptionistAccount) {
            worker = getReceptionistByResultSet(resultSet);
        } else {
            throw new NoAccountTypeException("brak zdefiniowanego typu pracownika dla id rodzaju konta: " + idAccountType);
        }
        return worker;
    }

    private static Admin getAdminByResultSet(ResultSet resultSet) throws SQLException {
        return new Admin(
                resultSet.getInt(W_ID),
                resultSet.getString(W_FIRST_NAME),
                resultSet.getString(W_LAST_NAME),
                resultSet.getString(W_ADDRESS),
                resultSet.getString(W_PHONE_NO),
                resultSet.getString(W_EMAIL),
                resultSet.getString(W_LOGIN),
                resultSet.getString(W_PASSWORD)
        );
    }

    private static Receptionist getReceptionistByResultSet(ResultSet resultSet) throws SQLException {
        Branch branch = SQLBranch.getBranchByResultSet(resultSet);
        return new Receptionist(
                resultSet.getInt(W_ID),
                resultSet.getString(W_FIRST_NAME),
                resultSet.getString(W_LAST_NAME),
                resultSet.getString(W_ADDRESS),
                resultSet.getString(W_PHONE_NO),
                resultSet.getString(W_EMAIL),
                resultSet.getString(W_LOGIN),
                resultSet.getString(W_PASSWORD),
                branch
        );
    }

    private static Doctor getDoctorByResultSet(ResultSet resultSet) throws SQLException {
        Branch branch = SQLBranch.getBranchByResultSet(resultSet);
        Specialization specialization = SQLSpecialization.getSpecializationByResultSet(resultSet);
        return new Doctor(
                resultSet.getInt(W_ID),
                resultSet.getString(W_FIRST_NAME),
                resultSet.getString(W_LAST_NAME),
                resultSet.getString(W_ADDRESS),
                resultSet.getString(W_PHONE_NO),
                resultSet.getString(W_EMAIL),
                resultSet.getString(W_LOGIN),
                resultSet.getString(W_PASSWORD),
                branch,
                specialization
        );
    }

}
