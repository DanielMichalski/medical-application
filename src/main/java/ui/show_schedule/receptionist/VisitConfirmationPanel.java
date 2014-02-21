package ui.show_schedule.receptionist;

import employees.Doctor;
import patient.ConverterPatientAddress;
import patient.PatientCard;
import ui.components.ConfirmPanel;
import ui.components.FillTitleBorder;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitConfirmationPanel extends JPanel implements ActionListener {
    private static final String IMAGE_PATH = "src/main/java/pl/sggw/przychodnia/util/images/lekslu.jpg";
    private static final Dimension DIMENSION_TEXT_FIELD = new Dimension(270, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(80, 22);

    private ConfirmPanel confirmPanel;
    private JLabel lblPatientPESEL, lblPatientFirstName, lblPatientLastName, lblPatientAddress;
    private JLabel lblDoctorFirstName, lblDoctorLastName, lblDoctorSpecialization, lblDoctorBranch, lblVisitDate, lblVisitTime;
    private GridBagConstraints gridConstraints;
    private Format dateFormatter;
    private Format timeFormatter;
    private VisitSelectorPresenter visitPresenter;

    public VisitConfirmationPanel(PatientCard patientCard, VisitSelectorPresenter visitPresenter) {
        setupPanel();
        this.visitPresenter = visitPresenter;
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        timeFormatter = new SimpleDateFormat("HH:mm");
        initComponents(patientCard);
        createContent();
    }

    private void setupPanel() {
        setLayout(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
    }

    private JLabel createLblForPatientData(String defaultName, Font font) {
        JLabel label = new JLabel(defaultName);
        label.setBackground(Color.RED);
        label.setPreferredSize(DIMENSION_TEXT_FIELD);
        label.setFont(font);
        return label;
    }

    private void initComponents(PatientCard patientCard) {
        Font font = new Font("Verdana", Font.BOLD, 12);

        lblPatientPESEL = createLblForPatientData(patientCard.getPESEL().toString(), font);
        lblPatientFirstName = createLblForPatientData(patientCard.getFirstName(), font);
        lblPatientLastName = createLblForPatientData(patientCard.getLastName(), font);
        lblPatientAddress = createLblForPatientData(ConverterPatientAddress.getFullAddress(patientCard), font);

        lblDoctorFirstName = createLblForPatientData("", font);
        lblDoctorLastName = createLblForPatientData("", font);
        lblDoctorSpecialization = createLblForPatientData("", font);
        lblDoctorBranch = createLblForPatientData("", font);

        lblVisitDate = createLblForPatientData("", font);
        lblVisitTime = createLblForPatientData("", font);

        confirmPanel = new ConfirmPanel(this, "Zapisz wizytę", Const.Colors.FILL_BORDER);
    }

    private void createContent() {
        gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        gridConstraints.weightx = 1.0;

        settleGridConstrains(gridConstraints, 0, 0);
        add(createPatientDataPanel(), gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        add(createDoctorInformationPanel(), gridConstraints);

        settleGridConstrains(gridConstraints, 0, 2);
        add(createVisitInformationPanel(), gridConstraints);

        settleGridConstrains(gridConstraints, 0, 3);
        gridConstraints.weighty = 1.0;
        add(new JLabel(), gridConstraints);
    }

    public void resetTimeVisitLbl() {
        lblVisitTime.setText("");
    }

    public void actualizeDateVisitLbl(Date dateVisit) {
        lblVisitDate.setText(dateFormatter.format(dateVisit));
    }

    public void actualizeTimeVisitLbl(Date timeVisit) {
        lblVisitTime.setText(timeFormatter.format(timeVisit));
    }

    public void actualizeDoctorLbls(Doctor doctor) {
        lblDoctorFirstName.setText(doctor.getFirstName());
        lblDoctorLastName.setText(doctor.getLastName());
        lblDoctorSpecialization.setText(doctor.getSpecialization().getName());
        lblDoctorBranch.setText(doctor.getBranch().getName());
    }


    private JPanel createPatientDataPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Const.Colors.BACKGROUND_PANEL);
        panel.setBorder(new FillTitleBorder("Dane pacjenta"));

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        panel.add(getLabel("Imię:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        panel.add(lblPatientFirstName, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        panel.add(getLabel("Nazwisko:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        panel.add(lblPatientLastName, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 2);
        panel.add(getLabel("PESEL:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 2);
        panel.add(lblPatientPESEL, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 3);
        panel.add(getLabel("Adres:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 3);
        panel.add(lblPatientAddress, gridConstraints);

        return panel;
    }

    private JPanel createDoctorInformationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Const.Colors.BACKGROUND_PANEL);
        panel.setBorder(new FillTitleBorder("Informacje o lekarzu"));

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        panel.add(getLabel("Imię:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        panel.add(lblDoctorFirstName, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        panel.add(getLabel("Nazwisko:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        panel.add(lblDoctorLastName, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 2);
        panel.add(getLabel("Specjalizacja:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 2);
        panel.add(lblDoctorSpecialization, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 3);
        panel.add(getLabel("Oddział:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 3);
        panel.add(lblDoctorBranch, gridConstraints);


        return panel;
    }

    private JPanel createVisitInformationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Const.Colors.BACKGROUND_PANEL);
        panel.setBorder(new FillTitleBorder("Informacje o terminie"));

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        panel.add(getLabel("Data:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        panel.add(lblVisitDate, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        panel.add(getLabel("Godzina:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        panel.add(lblVisitTime, gridConstraints);

        gridConstraints.weightx = 1.0;
        gridConstraints.insets = new Insets(5, 5, 10, 10);
        settleGridConstrains(gridConstraints, 1, 3);
        panel.add(confirmPanel, gridConstraints);

        return panel;
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
    }

    private JLabel getLabel(String lblName) {
        JLabel label = new JLabel(lblName, JLabel.RIGHT);
        label.setPreferredSize(DIMENSION_LABEL);
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton btn = (JButton) event.getSource();
        switch (btn.getMnemonic()) {
            case ConfirmPanel.ID_NEUTRAL_BUTTON:
                visitPresenter.saveReservation();
                break;
        }
    }
}