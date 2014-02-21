package database.dao;

import database.SQLReservation;
import database.util.DaoUtil;
import employees.Doctor;
import employees.NoAccountTypeException;
import patient.PatientCard;
import patient.Reservation;
import util.MyProgramLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ReservationDao extends SQLReservation {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();
    private DaoFactory daoFactory;

    public ReservationDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Reservation> getReservationsByDoctor(Doctor doctor, Date first, Date last) {
        List<Reservation> reservations = new ArrayList<Reservation>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectReservationsForDoctorQuery(doctor, first, last));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reservation reservation = getReservationByResultSet(doctor, resultSet);
                reservations.add(reservation);
            }
            LOGGER.info("rezerwacje pobrana z bazy.");
        } catch (SQLException | NoAccountTypeException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, resultSet);
        }
        return reservations;
    }

    public List<Reservation> getReservationsByPatientCard(PatientCard patientCard) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectReservationsForPatientQuery(patientCard));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reservation reservation = getReservationByResultSet(patientCard, resultSet);
                reservations.add(reservation);
            }
            LOGGER.info("Rezerwacje pobrana z bazy");
        } catch (SQLException | NoAccountTypeException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, resultSet);
        }
        return reservations;
    }

    public Reservation saveReservation(Reservation reservation) {
        if (reservation.isPersisted()) {
            updateReservation(reservation);
        } else {
            reservation = insertReservation(reservation);
        }
        return reservation;
    }

    private Reservation insertReservation(Reservation reservation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, insertQuery(reservation), true);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.setId(generatedKeys.getInt(1));
            }
            LOGGER.info("nowa rezerwacja dodana do bazy: " + reservation + " dla pacjenta: " + reservation.getPatientCard());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, generatedKeys);
        }
        return reservation;
    }

    private void updateReservation(Reservation reservation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, updateQuery(reservation), false);
            preparedStatement.executeUpdate();
            LOGGER.info("zmodyfikowana rezerwacja w bazie: " + reservation);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }

    public void deleteReservation(Reservation reservation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, deleteQuery(reservation), false);
            preparedStatement.executeUpdate();
            LOGGER.info("usunieta rezerwacja z bazy: " + reservation);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }

    public void deleteReservationsByPatient(PatientCard patientCard) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, deleteQueryByPatient(patientCard), false);
            preparedStatement.executeUpdate();
            LOGGER.info("Rezerwacje Pacjenta " + patientCard + " zostaly usuniete");
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }
}
