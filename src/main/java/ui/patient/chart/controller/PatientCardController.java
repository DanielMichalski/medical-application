package ui.patient.chart.controller;

import com.toedter.calendar.JDateChooser;
import database.dao.DaoFactory;
import database.dao.PatientCardDao;
import patient.PatientCard;
import patient.Survey;
import ui.patient.chart.models.SurveyTableModel;
import ui.patient.chart.view.RightChartPanel;
import util.Const;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class PatientCardController {
    private static PatientCardController ourInstance = new PatientCardController();

    private PatientCardDao patientCardDao = new PatientCardDao(new DaoFactory());

    private PatientCard currentPatientCard;

    private JDateChooser dateChooser;
    private JSpinner tempSpinner;
    private SurveyTableModel surveyTableModel;
    private JTable surveyTable;
    private RightChartPanel rightChartPanel;

    public static PatientCardController getInstance() {
        return ourInstance;
    }

    private PatientCardController() {
    }

    private void onAddBtnClick() {
        patientCardDao.getSurveysFromLastMonth(currentPatientCard);

        Date date = dateChooser.getDate();
        Object value = tempSpinner.getValue();
        if (patientCardDao.isThereThatDate(date, currentPatientCard)) {
            JOptionPane.showMessageDialog(
                    null,
                    Const.Strings.DATE_IS_CHOOSEN,
                    Const.Strings.INFORMATION,
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            Survey survey = new Survey(date, new Double(value.toString()));
            patientCardDao.saveSurvey(survey, currentPatientCard);
            surveyTableModel.addSurvey(survey);
            rightChartPanel.refreshChart();
        }
    }

    public void setSurveyTableModel(SurveyTableModel surveyTableModel) {
        this.surveyTableModel = surveyTableModel;
    }

    public SurveyTableModel getSurveyTableModel() {
        return surveyTableModel;
    }

    public void setSurveyTable(JTable surveyTable) {
        this.surveyTable = surveyTable;
    }

    public JTable getSurveyTable() {
        return surveyTable;
    }

    public void setRightChartPanel(RightChartPanel rightChartPanel) {
        this.rightChartPanel = rightChartPanel;
    }

    class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            onAddBtnClick();
        }
    }

    public ActionListener getBtnAddListener() {
        return new AddBtnListener();
    }

    public void setDateChooser(JDateChooser dateChooser) {
        this.dateChooser = dateChooser;
    }

    public void setTempSpinner(JSpinner tempSpinner) {
        this.tempSpinner = tempSpinner;
    }

    public void setCurrentPatient(PatientCard currentPatientCard) {
        this.currentPatientCard = currentPatientCard;
    }
}
