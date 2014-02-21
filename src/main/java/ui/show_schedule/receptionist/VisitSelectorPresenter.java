package ui.show_schedule.receptionist;

import database.dao.DaoFactory;
import database.dao.ReservationDao;
import database.dao.WorkerDao;
import employees.Doctor;
import patient.Reservation;
import ui.components.calendar.CalendarPanel;
import ui.components.calendar.ChangeCalendarColorListener;
import ui.components.calendar.cell.CalendarCell;
import ui.components.calendar.cell.CalendarCellFactory;
import ui.components.calendar.cell.ClickCalendarCellListener;
import ui.components.calendar.cell.SetterReservationsInCells;
import ui.reservation.update.UpdateReservationsPresenter;
import ui.show_schedule.ReservationsTablePanel;
import util.validate.ValidationException;
import util.validate.Validator;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class VisitSelectorPresenter implements
        ClickCalendarCellListener, SelectDoctorListener, SelectReservationListener, SetterReservationsInCells, ChangeCalendarColorListener {
    private ReservationDao reservationDao;
    private WorkerDao workerDao;

    private DoctorChooserPanel doctorChooserPanel;
    private CalendarPanel calendarPanel;
    private ReservationsTablePanel reservationsTablePanel;
    private VisitConfirmationPanel visitConfirmationPanel;

    private JDialog visitSelectorFrame;
    private UpdateReservationsPresenter updateReservationsPresenter;
    private Validator validator;

    public VisitSelectorPresenter(JDialog visitSelectorFrame, UpdateReservationsPresenter updateReservationsPresenter) {
        this.visitSelectorFrame = visitSelectorFrame;
        this.updateReservationsPresenter = updateReservationsPresenter;

        DaoFactory factory = new DaoFactory();
        reservationDao = factory.getReservationDAO();
        workerDao = factory.getWorkerDAO();
    }

    public void saveReservation() {
        try {
            validator.validate();
            Reservation selectedReservation = reservationsTablePanel.getSelectedReservation();
            selectedReservation = updateReservationsPresenter.trySaveReservationInDB(
                    doctorChooserPanel.getSelectedDoctor(),
                    selectedReservation.getDateVisit()
            );
            JOptionPane.showMessageDialog(null, updateReservationsPresenter.getInfoMsg(), "Pomyślnie zapisano wizytę", JOptionPane.INFORMATION_MESSAGE);
            visitSelectorFrame.dispose();
        } catch (ValidationException e) {
			JOptionPane.showMessageDialog(null, "Wystąpił problem podczas zapisu wizyty.\nProszę sprawdzić czy wszystkie wymagane dane\nzostały wybrane.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public List<CalendarCell> setReservationsIn(List<CalendarCell> nullObjectCalendarCells, Date firstDayInPage, Date lastDayInPage) {
        Doctor doctor = doctorChooserPanel.getSelectedDoctor();
        List<Reservation> reservationsForDisplayPage = reservationDao.getReservationsByDoctor(doctor, firstDayInPage, lastDayInPage);

        return CalendarCellFactory.settleReservationsIn(nullObjectCalendarCells, reservationsForDisplayPage);
    }

    @Override
    public void onSelectDoctor(Doctor doctor) {
        if (calendarPanel != null) {
            calendarPanel.refreshCalendar();
        }
        visitConfirmationPanel.actualizeDoctorLbls(doctor);
    }

    @Override
    public void onClickCell(CalendarCell cell) {
        reservationsTablePanel.refreshTableWith(cell.getReservations());
        visitConfirmationPanel.actualizeDateVisitLbl(cell.getDay());
    }

    @Override
    public void onSelectReservation(Reservation reservation) {
        if (reservation == null || reservation.isPersisted()) {
            visitConfirmationPanel.resetTimeVisitLbl();
            reservationsTablePanel.clearRowSelection();
        } else {
            visitConfirmationPanel.actualizeTimeVisitLbl(reservation.getDateVisit());
        }
    }

    @Override
    public void changeColor(Color color) {
        calendarPanel.changeComponentsColor(color);
    }

    public void fillViews() {
        Doctor doctor = doctorChooserPanel.getSelectedDoctor();
        visitConfirmationPanel.actualizeDoctorLbls(doctor);
        calendarPanel.refreshCalendar();
    }

    public void constructValidator() {
        validator = new Validator(doctorChooserPanel, calendarPanel, reservationsTablePanel);
    }


    public void setReservationsTablePanel(ReservationsTablePanel reservationsTablePanel) {
        this.reservationsTablePanel = reservationsTablePanel;
    }

    public void setVisitConfirmationPanel(VisitConfirmationPanel visitConfirmationPanel) {
        this.visitConfirmationPanel = visitConfirmationPanel;
    }

    public void setCalendarPanel(CalendarPanel calendarPanel) {
        this.calendarPanel = calendarPanel;
    }

    public void setDoctorChooserPanel(DoctorChooserPanel doctorChooserPanel) {
        this.doctorChooserPanel = doctorChooserPanel;
    }

    public List<Doctor> getDoctorsFromDB() {
        return workerDao.getDoctors();
    }

}