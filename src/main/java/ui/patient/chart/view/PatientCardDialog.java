package ui.patient.chart.view;

import patient.PatientCard;
import ui.patient.chart.controller.PatientCardController;
import util.Const;
import util.Utils;

import javax.swing.*;
import java.awt.*;

public class PatientCardDialog extends JDialog {
    public static final int DEFAULT_WIDTH = 1190;
    public static final int DEFAULT_HEIGHT = 530;

    PatientCardController presenter = PatientCardController.getInstance();

    public PatientCardDialog(PatientCard patientCard) {
        setUpFrame();
        initComponents(patientCard);
        presenter.setCurrentPatient(patientCard);
    }

    private void setUpFrame() {
        setTitle(Const.TitleFrames.PATIENT_CARD_DIALOG);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        //setResizable(false);
        setModal(true);

        Utils.setWindowsLookAndFeel();
        getContentPane().setBackground(Color.black);
    }

    private void initComponents(PatientCard patientCard) {
        LeftTablePanel leftTablePanel = new LeftTablePanel(patientCard);
        RightChartPanel rightChartPanel = new RightChartPanel(patientCard);
        ButtonsPanel buttonsPanel = new ButtonsPanel();

        add(leftTablePanel, BorderLayout.WEST);
        add(rightChartPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
