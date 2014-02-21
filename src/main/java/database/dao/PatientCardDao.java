package database.dao;

import database.SQLPatientCard;
import database.util.DaoUtil;
import database.util.SQLSurvey;
import patient.PatientCard;
import patient.Survey;
import util.MyProgramLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class PatientCardDao extends SQLPatientCard {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();
    private DaoFactory daoFactory;

    public PatientCardDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<PatientCard> getAllPatientCards() {
        List<PatientCard> patientCards = new ArrayList<PatientCard>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllQuery());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PatientCard patientCard = getPatientByResultSet(resultSet);
                patientCards.add(patientCard);
            }
            LOGGER.info("Pacjenci pobrani z bazy");
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, resultSet);
        }
        return patientCards;
    }

    public PatientCard savePatient(PatientCard patientCard) {
        if (patientCard.isPersisted()) {
            updatePatientCard(patientCard);
        } else {
            patientCard = insertPatientCard(patientCard);
        }
        return patientCard;
    }

    private PatientCard insertPatientCard(PatientCard patientCard) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, insertQuery(patientCard), true);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                patientCard.setId(generatedKeys.getInt(1));
            }
            LOGGER.info("nowy pacjent dodany do bazy: " + patientCard.getFirstName() + " " + patientCard.getLastName());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, generatedKeys);
        }
        return patientCard;
    }

    private void updatePatientCard(PatientCard patientCard) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, updateQuery(patientCard), false);
            preparedStatement.executeUpdate();
            LOGGER.info("zmodyfikowany pacjent w bazie: " + patientCard.getFirstName() + " " + patientCard.getLastName());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }

    public void deletePatientCard(PatientCard patientCard) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, deleteQuery(patientCard), false);
            preparedStatement.executeUpdate();
            LOGGER.info("usuniety pacjent z bazy: " + patientCard.getFirstName() + " " + patientCard.getLastName());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }

    public List<Survey> getAllSurveysByPatientCard(PatientCard patientCard) {
        List<Survey> surveys = new ArrayList<Survey>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQLSurvey.selectAllQueeryByPatientId(patientCard.getId()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                surveys.add(getSurveyByResultSet(resultSet));
            }
            LOGGER.info("Karta pacjenta pobrana z bazy dla pacjenta: " + patientCard.getFirstName() + " " + patientCard.getLastName());
        } catch (SQLException e) {
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement, resultSet);
        }

        return surveys;
    }

    public void saveSurvey(Survey survey, PatientCard patientCard) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, SQLSurvey.getSaveSurveyQuery(survey, patientCard.getId()), false);
            preparedStatement.executeUpdate();
            LOGGER.info("Karta z temperaturami zmodyfikowana dla pacjenta: " + patientCard.getFirstName() + " " + patientCard.getLastName());
        } catch (SQLException e) {
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
    }

    private Survey getSurveyByResultSet(ResultSet resultSet) throws SQLException {
        java.sql.Date date = resultSet.getDate(SQLSurvey.W_DATE);
        Double temperature = resultSet.getDouble(SQLSurvey.W_TEMPERATURE);
        return new Survey(date, temperature);
    }

    public boolean isThereThatDate(java.util.Date date, PatientCard patientCard) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, SQLSurvey.getSelectSurveryByDate(date, patientCard.getId()), false);
            preparedStatement.executeQuery();
            generatedKeys = preparedStatement.getResultSet();
            if (generatedKeys.next()) return true;
            else return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
        return false;
    }

    public List<Survey> getSurveysFromLastMonth(PatientCard patientCard) {
        List<Survey> surveys = new ArrayList<Survey>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = DaoUtil.prepareStatement(connection, SQLSurvey.getSelectSurveryFromLastMonth(patientCard.getId()), false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                surveys.add(getSurveyByResultSet(resultSet));
            }
            LOGGER.info("Karta z temperaturami pobrana na ostatni miesiąc dla pacjenta: " + patientCard.getFirstName() + " " + patientCard.getLastName());
        } catch (SQLException e) {
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            DaoUtil.close(connection, preparedStatement);
        }
        return surveys;
    }
}
