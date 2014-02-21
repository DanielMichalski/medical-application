package database.util;

import util.MyProgramLogger;

import java.sql.*;
import java.util.logging.Logger;


public class DaoUtil {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    /**
     * Zwraca PreparedStatement z danego connection oraz ustawia SQL query
     *
     * @param connection          - polaczenie potrzebne do tworzenia PreparedStatement
     * @param sqlQuery            - zapytanie SQL dla PreparedStatement
     * @param returnGeneratedKeys - zdecyduj, czy wrocic wygenerowany klucz, czy nie
     * @throws SQLException - jesli cos sie nie powiedzie podczas tworzenia PreparedStatement
     */
    public static PreparedStatement prepareStatement
    (Connection connection, String sqlQuery, boolean returnGeneratedKeys) throws SQLException {
        // kilka slow wyjasnienia skladni
        // boolen ? obiect1 : obiect2 //jezeli wartosc boolen jest true to pobiera obiect1 jesli false to pobiera obiect2
        int idReturnGeneratedKeys = returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, idReturnGeneratedKeys);
        return preparedStatement;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.warning("Operacja zamykania connection zakonczona niepowodzeniem: " + e.getMessage());
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.warning("Operacja zamykania statement zakonczona niepowodzeniem: " + e.getMessage());
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warning("Operacja zamykania resultSet zakonczona niepowodzeniem: " + e.getMessage());
            }
        }
    }

    public static void close(Connection connection, Statement statement) {
        close(statement);
        close(connection);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }

}