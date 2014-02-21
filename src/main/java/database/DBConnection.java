package database;

import database.exceptions.DriverClassNotFoundException;
import database.exceptions.ServerOfflineException;
import internet.InternetConnection;
import internet.NoInternetException;
import util.Const;
import util.MyProgramLogger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Logger;

public class DBConnection {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private String driverClassName = Const.DB.DRIVER_CLASS_NAME;

    private String user = Const.DB.USER_NAME;

    private String password = Const.DB.USER_PASSWORD;

    private String URL = Const.DB.URL;

    private boolean driverIsPrepared = false;

    private Connection connection;

    /**
     * Metoda przygotowywujaca do nawiazania polaczenia z serverem, kontroluje czy zostanie utworzony sterownik
     */
    private void prepareDriver() throws DriverClassNotFoundException {
        try {
            Class.forName(driverClassName).newInstance();
            driverIsPrepared = true;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOGGER.info("Brak lub błąd sterownika JDBC: " + e);
            throw new DriverClassNotFoundException("Problem ze znalezieniem klasy sterownika sprawdz bilioteke albo sciezke: " + driverClassName);
        }
    }

    /**
     * Metoda tworzaca i sprawdzajaca polaczenie z baza danych
     */
    public void createConnectionWithServer() throws ServerOfflineException {
        if (!isConnected()) {
            try {
                if (!driverIsPrepared) {
                    prepareDriver();
                }
                connection = DriverManager.getConnection(URL, user, password);
                LOGGER.info("Utworzono polaczenie z baza danych");
            } catch (DriverClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, Const.JPanel.MSG_SERVER_OFFLINE, Const.JPanel.TITLE_SERVER_OFFLINE, JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                throw new ServerOfflineException("Nie mozna polaczyc sie z baza: " + URL);
            }
        }
    }

    public Connection getConnection() {
        try {
            InternetConnection.isReachable();
            createConnectionWithServer();
        } catch (NoInternetException e) {
            JOptionPane.showMessageDialog(null, Const.JPanel.MSG_NO_INTERNET_CONNECTION, Const.JPanel.TITLE_NO_INTERNET_CONNECTION, JOptionPane.ERROR_MESSAGE);
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } catch (ServerOfflineException e) {
            JOptionPane.showMessageDialog(null, Const.JPanel.MSG_SERVER_OFFLINE, Const.JPanel.TITLE_SERVER_OFFLINE, JOptionPane.ERROR_MESSAGE);
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return connection;
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            LOGGER.warning("problem ze sprawdzeniem otwarcia polaczenia");
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return false;
    }

    /**
     * Metoda statyczna sprawdzająca połączenie z bazą danych
     *
     * @return zwraca wartość boolean czy polaczono z baza czy nie
     */
    public static boolean isConnectedWithDB() {
        try {
            Class.forName(Const.DB.DRIVER_CLASS_NAME);
            Connection con = DriverManager.getConnection(Const.DB.URL, Const.DB.USER_NAME, Const.DB.USER_PASSWORD);
            Statement stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
