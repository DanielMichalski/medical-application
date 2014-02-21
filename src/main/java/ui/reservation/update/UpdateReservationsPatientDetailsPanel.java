package ui.reservation.update;


import patient.ConverterPatientAddress;
import patient.PatientCard;
import ui.components.FillTitleBorder;
import util.Const;

import javax.swing.*;
import java.awt.*;

public class UpdateReservationsPatientDetailsPanel extends JPanel {
    private static final String IMAGE_PATH = "src/main/java/pl/sggw/przychodnia/util/images/lekslu.jpg";
    private static final Dimension DIMENSION_CONTACT_DATA_TEXT_FIELD = new Dimension(270, 22);
    private static final Dimension DIMENSION_PERSONAL_DATA_TEXT_FIELD = new Dimension(170, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(50, 22);

    private JLabel lblPatientPESEL, lblPatientFirstName, lblPatientLastName;
    private JLabel lblPatientAddress, lblPatientPhoneNo, lblPatientEmail;


    public UpdateReservationsPatientDetailsPanel(PatientCard patientCard) {
        super(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder("Szczegóły pacjenta"));

        initComponents(patientCard);
        createContent();
    }


    private void initComponents(PatientCard patientCard) {
        Font font = new Font("Verdana", Font.BOLD, 12);

        lblPatientFirstName = createLblForPatientData(patientCard.getFirstName(), DIMENSION_PERSONAL_DATA_TEXT_FIELD, font);
        lblPatientLastName = createLblForPatientData(patientCard.getLastName(), DIMENSION_PERSONAL_DATA_TEXT_FIELD, font);
        lblPatientPESEL = createLblForPatientData(patientCard.getPESEL().toString(), DIMENSION_PERSONAL_DATA_TEXT_FIELD, font);

        lblPatientAddress = createLblForPatientData(ConverterPatientAddress.getFullAddress(patientCard), DIMENSION_CONTACT_DATA_TEXT_FIELD, font);
        lblPatientPhoneNo = createLblForPatientData(patientCard.getPhoneNo(), DIMENSION_CONTACT_DATA_TEXT_FIELD, font);
        lblPatientEmail = createLblForPatientData(patientCard.geteMail(), DIMENSION_CONTACT_DATA_TEXT_FIELD, font);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;

        settleGridConstrains(gridConstraints, 0, 0);
        add(createLeftPanel(), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        add(createRightPanel(), gridConstraints);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Const.Colors.FILL_BORDER);

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        panel.add(getLabel("PESEL:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        panel.add(lblPatientPESEL, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        panel.add(getLabel("Imię:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        panel.add(lblPatientFirstName, gridConstraints);

        gridConstraints.insets = new Insets(5, 5, 15, 5);
        settleGridConstrains(gridConstraints, 0, 2);
        panel.add(getLabel("Nazwisko:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 2);
        panel.add(lblPatientLastName, gridConstraints);


        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Const.Colors.FILL_BORDER);

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        panel.add(getLabel("Adres:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        panel.add(lblPatientAddress, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        panel.add(getLabel("nr tel:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        panel.add(lblPatientPhoneNo, gridConstraints);

        gridConstraints.insets = new Insets(5, 5, 15, 5);
        settleGridConstrains(gridConstraints, 0, 2);
        panel.add(getLabel("e-mail:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 2);
        panel.add(lblPatientEmail, gridConstraints);
        return panel;
    }


    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
    }

    private JLabel createLblForPatientData(String defaultName, Dimension dimension, Font font) {
        JLabel label = new JLabel(defaultName);
        label.setBackground(Color.RED);
        label.setPreferredSize(dimension);
        label.setFont(font);
        return label;
    }

    private JLabel getLabel(String lblName) {
        JLabel label = new JLabel(lblName, JLabel.RIGHT);
        label.setPreferredSize(DIMENSION_LABEL);
        return label;
    }

}
