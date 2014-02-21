package database.dao;

import database.DBConnection;

import java.sql.Connection;

public class DaoFactory {
    private DBConnection dbConnection;

    public DaoFactory() {
        this.dbConnection = new DBConnection();
    }

    public Connection getConnection() {
        return dbConnection.getConnection();
    }

    public BranchDao getBranchDAO() {
        return new BranchDao(this);
    }

    public PatientCardDao getPatientDAO() {
        return new PatientCardDao(this);
    }

    public WorkerDao getWorkerDAO() {
        return new WorkerDao(this);
    }

    public SpecializationDao getSpecializationDAO() {
        return new SpecializationDao(this);
    }

    public ReservationDao getReservationDAO() {
        return new ReservationDao(this);
    }
}




























