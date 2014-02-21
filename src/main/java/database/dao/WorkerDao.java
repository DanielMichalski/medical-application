package database.dao;

import database.SQLWorker;
import database.exceptions.NotFoundWorkerException;
import database.util.DaoUtil;
import employees.Doctor;
import employees.NoAccountTypeException;
import util.MyProgramLogger;
import util.Worker;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class WorkerDao extends SQLWorker {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();
    private DaoFactory daoFactory;

    public WorkerDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Worker> getAllWorkers() {
        List<Worker> workers = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllQuery());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Worker worker = getWorkerByResultSet(resultSet);
                workers.add(worker);
            }
            LOGGER.info("Pracownicy pobrani z bazy");
        } catch (SQLException | NoAccountTypeException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, resultSet);
        }
        return workers;
    }

    public List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<Doctor>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectDoctorsQuery());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Worker worker = getWorkerByResultSet(resultSet);
                doctors.add((Doctor) worker);
            }
        } catch (SQLException | NoAccountTypeException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, resultSet);
        }
        return doctors;
    }

    public Worker getWorkerByLoginAndPassword(
            String loginFromField, String passwordFromField)
            throws NotFoundWorkerException {
        Worker worker = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     selectByLoginAndPasswordQuery(loginFromField, passwordFromField));
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                worker = getWorkerByResultSet(resultSet);
                LOGGER.info("Pracowik pobrany z bazy: " + worker.getLogin());
            } else {
                throw new NotFoundWorkerException("nie znaleziono pracownika o loginie " + loginFromField);
            }
        } catch (SQLException | NoAccountTypeException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }

        return worker;
    }

    public Worker saveWorker(Worker worker) {
        if (worker.isPersisted()) {
            updateWorker(worker);
        } else {
            worker = insertWorker(worker);
        }
        return worker;
    }

    private Worker insertWorker(Worker worker) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, insertQuery(worker), true);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                worker.setId(generatedKeys.getInt(1));
            }
            LOGGER.info("zainsertowany pracownik: " + worker.getLogin());
            JOptionPane.showMessageDialog(null, "Użytkownik " + worker.getLogin() + " został dodany do bazy danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, generatedKeys);
        }
        return worker;
    }

    private void updateWorker(Worker worker) {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement =
                     DaoUtil.prepareStatement(connection, updateQuery(worker), false))
        {
            preparedStatement.executeUpdate();
            LOGGER.info("zmodyfikowany pracownik: " + worker.getLogin());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }

    public void deleteWorker(Worker worker) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, deleteQuery(worker), false);
            preparedStatement.executeUpdate();
            LOGGER.info("usuniety pracownik: " + worker.getLogin());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }
}































