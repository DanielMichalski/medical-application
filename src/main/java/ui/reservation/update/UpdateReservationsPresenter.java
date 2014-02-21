package ui.reservation.update;

import database.dao.DaoFactory;
import database.dao.ReservationDao;
import employees.Doctor;
import patient.PatientCard;
import patient.Reservation;
import ui.show_schedule.receptionist.VisitSelectorFrame;
import util.validate.ValidationException;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpdateReservationsPresenter {

    private ReservationDao reservationDao;
    private UpdateReservationsTablePanel reservationsTablePanel;
    private PatientCard patientCard;

    private Format dateFormatter;
    private Reservation reservationForSaveInDB;
    private String infoMsg;

    public UpdateReservationsPresenter(PatientCard patientCard) {
        this.patientCard = patientCard;
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        DaoFactory factory = new DaoFactory();
        reservationDao = factory.getReservationDAO();
    }

    public Reservation trySaveReservationInDB(Doctor doctor, Date dateVisit) throws ValidationException {
        Date dateReservation = new Date();

        reservationForSaveInDB.setDoctor(doctor);
        reservationForSaveInDB.setDateVisit(dateVisit);
        reservationForSaveInDB.setDateReservation(dateReservation);
        reservationsTablePanel.updateTable(reservationForSaveInDB);

        reservationForSaveInDB = reservationDao.saveReservation(reservationForSaveInDB);
        return reservationForSaveInDB;
    }

    public List<Reservation> getReservationsFromDB() {
        return reservationDao.getReservationsByPatientCard(patientCard);
    }

    public PatientCard getPatientCard() {
        return patientCard;
    }

    public String getInfoMsg() {
        infoMsg += " na " + dateFormatter.format(reservationForSaveInDB.getDateVisit());
        return infoMsg;
    }

    public void setReservationsTablePanel(UpdateReservationsTablePanel reservationsTablePanel) {
        this.reservationsTablePanel = reservationsTablePanel;
    }

    public void addVisitAction() {
        infoMsg = "Dodanie nowej wizyty zakończyło się pomyślnie.\nOstatecznie zarezerwowano termin";
        reservationForSaveInDB = Reservation.nullObject(new Date());
        reservationForSaveInDB.setPatientCard(patientCard);
        new VisitSelectorFrame(this);
    }

    public void editVisitAction(Reservation reservation) {
        infoMsg = "Edycja wizyty zakończyła się pomyślnie.\nOstatecznie zmieniono termin z " +
                dateFormatter.format(reservation.getDateVisit());
        reservationForSaveInDB = reservation;
        reservationForSaveInDB.setPatientCard(patientCard);
        new VisitSelectorFrame(this);
    }

    public void removeVisitAction(Reservation reservationForDelete) {
        reservationDao.deleteReservation(reservationForDelete);
        reservationsTablePanel.deletedReservation(reservationForDelete);
    }

}
