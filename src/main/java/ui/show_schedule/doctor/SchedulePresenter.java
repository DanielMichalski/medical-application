package ui.show_schedule.doctor;


import database.dao.DaoFactory;
import database.dao.ReservationDao;
import employees.Doctor;
import patient.Reservation;
import ui.components.calendar.CalendarPanel;
import ui.components.calendar.ChangeCalendarColorListener;
import ui.components.calendar.cell.CalendarCell;
import ui.components.calendar.cell.CalendarCellFactory;
import ui.components.calendar.cell.ClickCalendarCellListener;
import ui.components.calendar.cell.SetterReservationsInCells;
import ui.show_schedule.ReservationsTablePanel;
import ui.show_schedule.receptionist.SelectReservationListener;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class SchedulePresenter implements
        ClickCalendarCellListener, SelectReservationListener, SetterReservationsInCells, ChangeCalendarColorListener {
    private ReservationDao reservationDao;

    private CalendarPanel calendarPanel;
    private ReservationsTablePanel reservationsTablePanel;
    private PatientDetailsPanel patientDetailsPanel;
    private Doctor doctor;

    public SchedulePresenter(Doctor doctor) {
        this.doctor = doctor;

        DaoFactory factory = new DaoFactory();
        reservationDao = factory.getReservationDAO();
    }

    @Override
    public List<CalendarCell> setReservationsIn(List<CalendarCell> nullObjectCalendarCells, Date firstDayInPage, Date lastDayInPage) {
        List<Reservation> reservationsForDisplayPage = reservationDao.getReservationsByDoctor(doctor, firstDayInPage, lastDayInPage);

        return CalendarCellFactory.settleReservationsIn(nullObjectCalendarCells, reservationsForDisplayPage);
    }

    @Override
    public void onClickCell(CalendarCell cell) {
        reservationsTablePanel.refreshTableWith(cell.getReservations());
        reservationsTablePanel.clearRowSelection();
    }

    @Override
    public void onSelectReservation(Reservation reservation) {
        if (reservation == null || !reservation.isPersisted()) {
            patientDetailsPanel.resetFields();
        } else {
            patientDetailsPanel.fillFields(reservation.getPatientCard());
        }
    }

    @Override
    public void changeColor(Color color) {
        calendarPanel.changeComponentsColor(color);
    }

    public void fillViews() {
        calendarPanel.refreshCalendar();
    }

    public void setCalendarPanel(CalendarPanel calendarPanel) {
        this.calendarPanel = calendarPanel;
    }

    public void setPatientDetailsPanel(PatientDetailsPanel patientDetailsPanel) {
        this.patientDetailsPanel = patientDetailsPanel;
    }

    public void setReservationsTablePanel(ReservationsTablePanel reservationsTablePanel) {
        this.reservationsTablePanel = reservationsTablePanel;
    }
}