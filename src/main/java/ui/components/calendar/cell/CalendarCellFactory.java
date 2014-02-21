package ui.components.calendar.cell;


import patient.Reservation;
import ui.components.calendar.util.CalendarUtil;
import ui.reservation.ReservationsFactory;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CalendarCellFactory {
    private static final int AMOUNT_DAYS_IN_CALENDAR_PAGE = 42;

    private ClickCalendarCellListener clickCellListener;
    private Color backgroundCell;
    private List<String> monthNames;
    private Date today;

    private GregorianCalendar calculationUtil;
    private GregorianCalendar gregorianCalendar;

    public CalendarCellFactory(Color backgroundCell, ClickCalendarCellListener clickCellListener) {
        this.clickCellListener = clickCellListener;
        this.backgroundCell = backgroundCell;

        calculationUtil = CalendarUtil.getCalendar(); // pomocniczy klendarz na ktorym dokonyjemy obliczen
        gregorianCalendar = CalendarUtil.getCalendar(); // kalendarz z aktualnej strony

        monthNames = CalendarUtil.getMonthNames();
        today = gregorianCalendar.getTime();
    }

    public List<CalendarCell> getCalendarCellsForDay(Date day) {
        gregorianCalendar.setTime(day);
        return getCalendarCells();
    }

    public List<CalendarCell> getCalendarCellsForDisplayMonth() {
        return getCalendarCells();
    }

    public List<CalendarCell> getCalendarCellsForPreviousMoth() {
        gregorianCalendar.add(Calendar.MONTH, -1);
        return getCalendarCells();
    }

    public List<CalendarCell> getCalendarCellsForNextMoth() {
        gregorianCalendar.add(Calendar.MONTH, 1);
        return getCalendarCells();
    }

    private List<CalendarCell> getCalendarCells() {
        List<CalendarCell> calendarCells = new ArrayList<CalendarCell>();
        Date firstDateInCalendarPage = CalendarUtil.getFirstDayInCalendarPage(gregorianCalendar.getTime(), calculationUtil);
        calculationUtil.setTime(firstDateInCalendarPage);

        for (int i = 0; i < AMOUNT_DAYS_IN_CALENDAR_PAGE; i++) {
            CalendarCell cell = new CalendarCell(calculationUtil.getTime(), clickCellListener);
            cell.setColor(backgroundCell, backgroundCell);
            cell.setCellType(today, gregorianCalendar.get(Calendar.MONTH));
            calendarCells.add(cell);
            calculationUtil.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }
        return calendarCells;
    }

    public Date getFirstDateInPage() {
        return CalendarUtil.getFirstDayInCalendarPage(gregorianCalendar.getTime(), calculationUtil);
    }

    public Date getLastDateInPage() {
        Date firstDateInCalendarPage = getFirstDateInPage();
        calculationUtil.setTime(firstDateInCalendarPage);
        calculationUtil.add(Calendar.DAY_OF_MONTH, AMOUNT_DAYS_IN_CALENDAR_PAGE + 1);
        return calculationUtil.getTime();
    }

    public void setBackgroundCell(Color backgroundCell) {
        this.backgroundCell = backgroundCell;
    }

    public String getDisplayMonthAndYear() {
        int displayMonthNo = gregorianCalendar.get(Calendar.MONTH);
        return monthNames.get(displayMonthNo) + " " + gregorianCalendar.get(Calendar.YEAR);
    }

    public static List<CalendarCell> settleReservationsIn(List<CalendarCell> nullObjectCellsForPage, List<Reservation> reservationsForPage) {
        for (CalendarCell cell : nullObjectCellsForPage) {
            Date day = cell.getDay();
            List<Reservation> reservationsForDay = ReservationsFactory.getNullReservationsForDay(day);
            for (int i = 0; i < reservationsForDay.size(); i++) {
                Reservation reservation = reservationsForDay.get(i);
                if (reservationsForPage.contains(reservation)) {
                    reservationsForDay.remove(i);
                    Reservation newreservation = reservationsForPage.get(0);
                    reservationsForDay.add(i, newreservation);
                    reservationsForPage.remove(0);
                }
            }
            cell.setReservations(reservationsForDay);
        }
        List<CalendarCell> cellsWithReservations = new ArrayList<CalendarCell>(nullObjectCellsForPage);
        return cellsWithReservations;
    }
}
