package ui.components.calendar.cell;

import java.util.Date;
import java.util.List;

public interface SetterReservationsInCells {
    public List<CalendarCell> setReservationsIn(List<CalendarCell> nullObjectsCalendarCells, Date firstDayInPage, Date lastDayInPage);
}
