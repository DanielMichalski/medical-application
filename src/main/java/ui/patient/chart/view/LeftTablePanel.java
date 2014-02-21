package ui.patient.chart.view;

import database.dao.DaoFactory;
import database.dao.PatientCardDao;
import patient.PatientCard;
import ui.components.FillTitleBorder;
import ui.patient.chart.controller.PatientCardController;
import ui.patient.chart.models.SurveyTableModel;
import util.Const;

import javax.swing.*;
import java.awt.*;

public class LeftTablePanel extends JPanel {
    PatientCardController presenter = PatientCardController.getInstance();

    PatientCardDao patientCardDao = new PatientCardDao(new DaoFactory());

    public LeftTablePanel(PatientCard patientCard) {
        setUp();
        initComponents(patientCard);
    }

    private void setUp() {

        setBorder(new FillTitleBorder("Lista pacjentów"));
        setBackground(Const.Colors.BACKGROUND_PANEL);
    }

    private void initComponents(PatientCard patientCard) {
        SurveyTableModel surveyTableModel = new SurveyTableModel(patientCard, patientCardDao.getAllSurveysByPatientCard(patientCard));

        JTable patientCardTable = new JTable(surveyTableModel);
        patientCardTable.setRowSelectionAllowed(false);
        patientCardTable.setAutoCreateRowSorter(true);
        patientCardTable.setBackground(Color.WHITE);

        JScrollPane paneWithTable = new JScrollPane(patientCardTable);
        paneWithTable.getViewport().setBackground(Color.WHITE);
        add(paneWithTable);

        presenter.setSurveyTableModel(surveyTableModel);
        presenter.setSurveyTable(patientCardTable);
    }
}
