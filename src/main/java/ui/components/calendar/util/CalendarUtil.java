package ui.components.calendar.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarUtil {

    public static GregorianCalendar getCalendar() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar = resetTime(gregorianCalendar);
        return gregorianCalendar;
    }

    public static GregorianCalendar getCalendar(Date day, int hour) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(day);
        gregorianCalendar = resetTime(gregorianCalendar);
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, hour);
        return gregorianCalendar;
    }

    public static GregorianCalendar resetTime(GregorianCalendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar;
    }

    public static Date getFirstDayInCalendarPage(Date src, GregorianCalendar calculationUtil) {
        calculationUtil.setTime(src);
        calculationUtil.set(GregorianCalendar.DAY_OF_MONTH, 1);

        return calculateFirstDayInWeek(calculationUtil);
    }

    private static Date calculateFirstDayInWeek(GregorianCalendar calculationUtil) {
        int firstDayOfWeek = calculationUtil.getFirstDayOfWeek();
        while (calculationUtil.get(Calendar.DAY_OF_WEEK) != firstDayOfWeek) {
            calculationUtil.add(Calendar.DAY_OF_MONTH, -1);
        }
        return calculationUtil.getTime();
    }

    public static String getTodayWithFormat() {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(new Date());
    }

    public static List<String> getShortWeekdaysName() {
        GregorianCalendar calculationUtil = getCalendar();
        String[] weekdayNames = getWeekdaysShortName();

        Date firstDateInWeek = calculateFirstDayInWeek(calculationUtil);
        calculationUtil.setTime(firstDateInWeek);

        List<String> weekdaysShortNames = new ArrayList<String>();
        int firstDayOfWeek = calculationUtil.getFirstDayOfWeek();
        int weekday = calculationUtil.get(Calendar.DAY_OF_WEEK);
        do {
            weekdaysShortNames.add(weekdayNames[weekday]);
            calculationUtil.add(Calendar.DAY_OF_MONTH, 1);
            weekday = calculationUtil.get(Calendar.DAY_OF_WEEK);
        } while (weekday != firstDayOfWeek);
        System.out.println("short weekday names " + weekdaysShortNames);
        return weekdaysShortNames;
    }


    private static String[] getWeekdaysShortName() {
        return new String[]{"", "N", "Pn", "Wt", "Śr", "Cz", "Pt", "So"};
    }

    public static List<String> getMonthNames() {
        List<String> monthNames = new ArrayList<String>();
        monthNames.add("Styczeń");
        monthNames.add("Luty");
        monthNames.add("Marzec");
        monthNames.add("Kwiecień");
        monthNames.add("Maj");
        monthNames.add("Czerwiec");
        monthNames.add("Lipiec");
        monthNames.add("Sierpień");
        monthNames.add("Wrzesień");
        monthNames.add("Październik");
        monthNames.add("Listopad");
        monthNames.add("Grudzień");
        return monthNames;
    }
}
