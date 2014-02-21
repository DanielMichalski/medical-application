package database.dao;

import database.SQLSpecialization;
import database.util.DaoUtil;
import util.MyProgramLogger;
import work.Specialization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class SpecializationDao extends SQLSpecialization {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();
    private DaoFactory daoFactory;

    public SpecializationDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Specialization> getAllSpecializations() {
        List<Specialization> specializations = new ArrayList<Specialization>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllQuery());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Specialization specialization = getSpecializationByResultSet(resultSet);
                specializations.add(specialization);
            }
            LOGGER.info("Specjalizacje pobrane z bazy");
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, resultSet);
        }
        return specializations;
    }

    public Specialization saveSpecialization(Specialization specialization) {
        if (specialization.isPersisted()) {
            updateSpecialization(specialization);
        } else {
            specialization = insertSpecialization(specialization);
        }
        return specialization;
    }

    private Specialization insertSpecialization(Specialization specialization) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, insertQuery(specialization), true);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                specialization.setId(generatedKeys.getInt(1));
            }
            LOGGER.info("nowa specjalizacja dodana do bazy: " + specialization.getName());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, generatedKeys);
        }
        return specialization;
    }

    private void updateSpecialization(Specialization specialization) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, updateQuery(specialization), false);
            preparedStatement.executeUpdate();
            LOGGER.info("zmodyfikowana specjalizacja w bazie: " + specialization.getName());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }

    public void deleteSpecialization(Specialization specialization) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, deleteQuery(specialization), false);
            preparedStatement.executeUpdate();
            LOGGER.info("usunieta specjalizacja z bazy: " + specialization.getName());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }

}
