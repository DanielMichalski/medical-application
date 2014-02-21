package ui.patient;

import database.dao.DaoFactory;
import database.dao.PatientCardDao;
import database.dao.ReservationDao;
import patient.PatientCard;
import ui.patient.chart.view.PatientCardDialog;
import ui.patient.review.ReviewPatientOptionsPanel;
import ui.patient.review.ReviewPatientTablePanel;
import ui.patient.update.RegistrationAndEditPatientDialog;
import ui.patient.update.form.FormPatientPanel;
import ui.reservation.update.UpdateReservationsFrame;
import util.finder.PressEnterListener;
import util.validate.ValidationException;
import util.validate.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class RegisterPatientPresenter implements PressEnterListener, ActionListener {

    private PatientCardDao patientDao;
    private ReservationDao reservationDao;
    private FormPatientPanel formPatientPanel;
    private ReviewPatientTablePanel tableWithPatientsPanel;
    private Validator validator;

    public RegisterPatientPresenter() {
        DaoFactory factory = new DaoFactory();
        patientDao = factory.getPatientDAO();
        reservationDao = factory.getReservationDAO();
    }

    public RegisterPatientPresenter(FormPatientPanel formPatientPanel) {
        this.formPatientPanel = formPatientPanel;
        DaoFactory factory = new DaoFactory();
        patientDao = factory.getPatientDAO();
        reservationDao = factory.getReservationDAO();

        validator = new Validator(
                formPatientPanel.getFirstNameValidator(),
                formPatientPanel.getLastNameValidator(),
                formPatientPanel.getPeselValidator(),
                formPatientPanel.getAddressValidator(),
                formPatientPanel.getZipCodeValidator(),
                formPatientPanel.getLocalityValidator(),
                formPatientPanel.getPhoneNoValidator(),
                formPatientPanel.getEmailValidator()
        );
    }

    public PatientCard trySavePatientInDB(PatientCard patientCardToSave) throws ValidationException {
        validator.validate();
        patientCardToSave = formPatientPanel.getPatientCardFromFields(patientCardToSave);
        patientCardToSave = patientDao.savePatient(patientCardToSave);
        return patientCardToSave;
    }

    public PatientCard tryDeletePatientFromDB(PatientCard patientCardForDelete) {
        reservationDao.deleteReservationsByPatient(patientCardForDelete);
        patientDao.deletePatientCard(patientCardForDelete);
        return patientCardForDelete;
    }

    public List<PatientCard> getPatientCardFromDB() {
        List<PatientCard> allPatientCards = patientDao.getAllPatientCards();
        Collections.sort(allPatientCards);
        return allPatientCards;
    }

    public void setTableWithPatientsPanel(ReviewPatientTablePanel tableWithPatientsPanel) {
        this.tableWithPatientsPanel = tableWithPatientsPanel;
    }

    @Override
    public void onClickEnter() {
        tableWithPatientsPanel.repaint();
    }

        @Override
        public void actionPerformed(ActionEvent event) {
            JButton btn = (JButton) event.getSource();
            PatientCard selectedPatientCard = tableWithPatientsPanel.getSelectedPatientCard();
            if (btn.getMnemonic() == ReviewPatientOptionsPanel.ID_BUTTON_ADD_PATIENT) {
                new RegistrationAndEditPatientDialog(PatientCard.nullObject(), tableWithPatientsPanel);
            } else if (selectedPatientCard != null) {
                switch (btn.getMnemonic()) {
                    case ReviewPatientOptionsPanel.ID_BUTTON_EDIT_VISITS:
                        new UpdateReservationsFrame(selectedPatientCard);
                        break;
                    case ReviewPatientOptionsPanel.ID_BUTTON_EDIT_PATIENT:
                        new RegistrationAndEditPatientDialog(selectedPatientCard, tableWithPatientsPanel);
                        break;
                    case ReviewPatientOptionsPanel.ID_BUTTON_REMOVE_PATIENT:
                        PatientCard removedPatientCard = tryDeletePatientFromDB(selectedPatientCard);
                        tableWithPatientsPanel.deletedPatient(removedPatientCard);
                        break;
                    case ReviewPatientOptionsPanel.ID_BUTTON_SHOW_TEMP:
                        PatientCardDialog patientCardDialog = new PatientCardDialog(selectedPatientCard);
                        patientCardDialog.setVisible(true);
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nie można wykonać kolejnej akcji, ponieważ żaden pacjent nie został jeszcze wybrany.", "Brak zaznacznia", JOptionPane.INFORMATION_MESSAGE);
            }
        }
}