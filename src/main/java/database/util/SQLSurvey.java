package database.util;

import patient.PatientCard;
import patient.Survey;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Autor: Daniel Michalski
 * Date: 28.04.13
 */

public class SQLSurvey {

    public static final String TABLE_NAME = "dbo.Badanie";

    public static final String W_ID_SURVEY = "surveyId";
    public static final String W_DATE = "data";
    public static final String W_TEMPERATURE = "TEMPERATURA";
    public static final String W_PATIENT_ID = "id_pacjenta";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String selectAllQueeryByPatientId(int patientID) {
        return "SELECT * FROM " + TABLE_NAME + " WHERE " + W_PATIENT_ID + "=" + patientID;
    }

    public static String getSaveSurveyQuery(Survey survey, int patientID) {
        String query = "INSERT INTO " + TABLE_NAME + "(" +
                W_DATE + "," +
                W_TEMPERATURE + "," +
                W_PATIENT_ID + ") VALUES ('" +
                dateFormat.format(survey.getDate()) + "'," +
                survey.getTemperature() + "," +
                patientID + ")";
        return query;
    }

    public static String getDeleteSurveyQuery(PatientCard patientCard) {
        return "DELETE FROM " + TABLE_NAME + " WHERE " + W_PATIENT_ID + "=" + patientCard.getId();
    }

    public static String getSelectSurveryByDate(Date date, int patientID) {
        return "SELECT " + W_DATE + " FROM " + TABLE_NAME + " WHERE " + W_DATE + "='" + dateFormat.format(date) + "' AND " + W_PATIENT_ID + "=" + patientID;
    }

    public static String getSelectSurveryFromLastMonth(int patientId) {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.DATE, -30);
        Date monthBefore = cal.getTime();

        return "SELECT * FROM " + TABLE_NAME + " WHERE " + W_DATE + " BETWEEN '" + dateFormat.format(monthBefore) + "' AND '" + dateFormat.format(today) + "' AND " + W_PATIENT_ID + "=" + patientId;
    }
}
