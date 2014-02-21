package util;

import java.awt.*;
import java.io.File;

public class Const {

    public static interface Labels {
        String EMPLOYEE_ID = "Identyfikator Pracownika";
        String LOGOWANIE_LABEL = "Zaloguj się";
        String LOGIN = "Login: ";
        String PASSWORD = "Hasło: ";
        String OFFLINELBL = "Serwer Offine";
        String ONLINELBL = "Serwer Online";
    }

    public static interface Strings {
        String BADDATA = "Wprowadź poprawne dane";
        String EMPTYLOGINANDPASS = "Pole login oraz pole hasło nie mogą być puste";
        String EMPTYLOGIN = "Pole login nie może być puste";
        String EMPTYPASS = "Pole haslo nie może być puste";
        String DBERROR = "Wystapił błąd z połączeniem z bazą danych. Spróbuj ponownie później.";
        String NONFIXDATA = "Podane dane są nieprawidłowe. Spróbuj ponownie.";
        String MINVALFIELDS = "Login i hasło musi mieć co najmniej 6 znaków.";
        String JPANETITLE = "Błąd";
        String DATE_IS_CHOOSEN = "Wybrana data została już wybrana";
        String INFORMATION = "Informacja";
    }

    public static interface JPanel {
        String TITLE_NO_INTERNET_CONNECTION = "Brak połączenia z internetem";
        String MSG_NO_INTERNET_CONNECTION = "Aplikacja potrzebuje stałego dostępu do internetu. Proszę sprawdzić swoje połączenie.";
        String TITLE_SERVER_OFFLINE = "Błąd serwera";
        String MSG_SERVER_OFFLINE = "Nie można połączyć się z serwerem, proszę spróbować później.";
    }

    public static interface ButtonLbls {
        String ADD_CARD = "Dodaj Karte";
        String LOGIN = "Zaloguj";
    }

    public static interface TitleFrames {
        String LOGIN_EMPLOYEES = "Logowanie";
        String PATIENT_CARD_DIALOG = "Temperatury pacjenta z ostatniego miesiąca";
    }

    public static interface DB {
        String DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        String HOST = "148.81.130.59";
        String PORT = "1433";
        String DB_NAME = "db142034";
        String USER_NAME = "st142034";
        String USER_PASSWORD = "p142034";
        String URL = "jdbc:sqlserver://" + HOST + "\\SQLEXPRESS:" + PORT + ";databaseName=" + DB_NAME;
    }

    public static interface Separators {
        String DATA = ";";
    }

    public static interface Colors {
        Color backgroundColor = new Color(225, 255, 255);
        Color backgroundCalendarItems = Color.ORANGE;
        Color BACKGROUND_CALENDAR = Color.WHITE;

        //motyw niebieski
        Color BORDER_LINE = new Color(101, 147, 207);
        Color FILL_BORDER = new Color(240, 246, 255);

        Color BACKGROUND_PANEL = new Color(191, 219, 255);
        Color BACKGROUND_CALENDAR_COMPONENT = new Color(175, 210, 255);
        Color HEADER_TEXT = new Color(21, 66, 139);
        Color ERROR_TEXT = new Color(228, 54, 4);
        Color SEARCHING_TEXT = new Color(255, 150, 50);
    }

    public static interface Fonts {
        Font HEADER = new Font("Tahoma", Font.BOLD, 11);
        Font FOOTER = new Font("Tahoma", Font.PLAIN, 10);
    }

    public static interface ID {
        int ADMIN_RADIO_BUTTON_ID = 1;
        int DOCTOR_RADIO_BUTTON_ID = 2;
        int RECEPTIONIST_RADIO_BUTTON_ID = 3;
    }

    public static interface AccountType {
        String DOCTOR = "Doktor";
        String RECEPTIONIST = "Recepcjonista";
        String ADMIN = "Admin";
    }

    public static interface Logger {
        String LOGGER_NAME = "Logger";
        String LOGER_PATH = "src" + File.separator
                + "main" + File.separator
                + "logs" + File.separator
                + "logi.log";
    }
}
