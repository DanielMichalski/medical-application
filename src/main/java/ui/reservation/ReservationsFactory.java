
package ui.reservation;

import patient.Reservation;
import ui.components.calendar.util.CalendarUtil;

import java.util.*;


public class ReservationsFactory {

    private static final int START_OF_WORK_HOURS = 8;
    private static final int END_OF_WORK_HOURS = 17;
    private static final int TIME_PER_VISIT_IN_MINUTES = 60;

    public static List<Reservation> getNullReservationsForDay(Date day) {
        GregorianCalendar calculationUtil = CalendarUtil.getCalendar(day, START_OF_WORK_HOURS);

        List<Reservation> reservations = new ArrayList<Reservation>();
        while (calculationUtil.get(Calendar.HOUR_OF_DAY) != END_OF_WORK_HOURS) {
            reservations.add(Reservation.nullObject(calculationUtil.getTime()));
            calculationUtil.add(Calendar.MINUTE, TIME_PER_VISIT_IN_MINUTES);
        }
        return reservations;
    }

}
