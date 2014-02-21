package ui.patient.chart.view;

import com.toedter.calendar.JDateChooser;
import ui.patient.chart.controller.PatientCardController;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ButtonsPanel extends JPanel {

    private PatientCardController presenter;

    public ButtonsPanel() {
        presenter = PatientCardController.getInstance();
        setUp();
        initComponents();
    }

    private void setUp() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        setBackground(Const.Colors.BACKGROUND_PANEL);
    }

    private void initComponents() {
        JLabel dateLbl = new JLabel("Data:");
        add(dateLbl);

        JDateChooser dateChooser = new JDateChooser(new Date());
        add(dateChooser);

        JLabel tempLbl = new JLabel("Temperatura:");
        add(tempLbl);

        SpinnerNumberModel model = new SpinnerNumberModel(36.6, 32, 45, 0.1);
        JSpinner tempSpinner = new JSpinner(model);
        tempSpinner.setPreferredSize(new Dimension(60, 20));
        add(tempSpinner);

        JButton buttonAdd = new JButton("Dodaj");
        add(buttonAdd);

        presenter.setDateChooser(dateChooser);
        presenter.setTempSpinner(tempSpinner);

        buttonAdd.addActionListener(presenter.getBtnAddListener());
    }
}

