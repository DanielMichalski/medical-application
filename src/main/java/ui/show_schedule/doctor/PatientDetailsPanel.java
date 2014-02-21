package ui.show_schedule.doctor;

import patient.ConverterPatientAddress;
import patient.PatientCard;
import ui.components.FillTitleBorder;
import util.Const;

import javax.swing.*;
import java.awt.*;

public class PatientDetailsPanel extends JPanel {
    private static final String IMAGE_PATH = "src/main/java/pl/sggw/przychodnia/util/images/lekslu.jpg";
    private static final Dimension DIMENSION_DATA_TEXT_FIELD = new Dimension(300, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(50, 22);

    private JLabel lblPatientPESEL, lblPatientFirstName, lblPatientLastName;
    private JLabel lblPatientAddress, lblPatientPhoneNo, lblPatientEmail;


    public PatientDetailsPanel() {
        super(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);

        initComponents();
        createContent();
    }

    private void initComponents() {
        Font font = new Font("Verdana", Font.BOLD, 12);

        lblPatientFirstName = createLblForPatientData("", font);
        lblPatientLastName = createLblForPatientData("", font);
        lblPatientPESEL = createLblForPatientData("", font);

        lblPatientAddress = createLblForPatientData("", font);
        lblPatientPhoneNo = createLblForPatientData("", font);
        lblPatientEmail = createLblForPatientData("", font);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.weightx = 1.0;

        settleGridConstrains(gridConstraints, 0, 0);
        add(createPersonalDataPatientPanel(), gridConstraints);
        gridConstraints.insets = new Insets(10, 0, 0, 0);
        settleGridConstrains(gridConstraints, 0, 1);
        add(createContactPatientData(), gridConstraints);

        gridConstraints.insets = new Insets(0, 0, 0, 0);
        settleGridConstrains(gridConstraints, 0, 2);
        gridConstraints.weighty = 1.0;
        add(new JLabel(), gridConstraints);
    }

    public void fillFields(PatientCard patientCard) {
        lblPatientFirstName.setText(patientCard.getFirstName());
        lblPatientLastName.setText(patientCard.getLastName());
        lblPatientPESEL.setText(patientCard.getPESEL());

        lblPatientAddress.setText(ConverterPatientAddress.getFullAddress(patientCard));
        lblPatientPhoneNo.setText(patientCard.getPhoneNo());
        lblPatientEmail.setText(patientCard.geteMail());
    }

    public void resetFields() {
        lblPatientFirstName.setText("");
        lblPatientLastName.setText("");
        lblPatientPESEL.setText("");

        lblPatientAddress.setText("");
        lblPatientPhoneNo.setText("");
        lblPatientEmail.setText("");
    }

    private JPanel createPersonalDataPatientPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Const.Colors.BACKGROUND_PANEL);
        panel.setBorder(new FillTitleBorder("Dane personalne pacjenta"));

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 1);
        panel.add(getLabel("Imię:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        panel.add(lblPatientFirstName, gridConstraints);


        settleGridConstrains(gridConstraints, 0, 2);
        panel.add(getLabel("Nazwisko:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 2);
        panel.add(lblPatientLastName, gridConstraints);

        gridConstraints.insets = new Insets(5, 5, 15, 5);
        settleGridConstrains(gridConstraints, 0, 3);
        panel.add(getLabel("PESEL:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 3);
        panel.add(lblPatientPESEL, gridConstraints);


        return panel;
    }

    private JPanel createContactPatientData() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Const.Colors.BACKGROUND_PANEL);
        panel.setBorder(new FillTitleBorder("Dane kontaktowe pacjenta"));

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

    private JLabel createLblForPatientData(String defaultName, Font font) {
        JLabel label = new JLabel(defaultName);
        label.setBackground(Color.RED);
        label.setPreferredSize(DIMENSION_DATA_TEXT_FIELD);
        label.setFont(font);
        return label;
    }

    private JLabel getLabel(String lblName) {
        JLabel label = new JLabel(lblName, JLabel.RIGHT);
        label.setPreferredSize(DIMENSION_LABEL);
        return label;
    }

}