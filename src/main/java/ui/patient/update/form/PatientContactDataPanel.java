package ui.patient.update.form;

import patient.PatientCard;
import ui.components.FillTitleBorder;
import util.Const;
import util.MyProgramLogger;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Logger;

public class PatientContactDataPanel extends JPanel {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private static final Dimension DIMENSION_TEXT_FIELD = new Dimension(200, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(80, 22);

    private JTextField tfEmail;
    private JFormattedTextField tfPhoneNo;

    public PatientContactDataPanel() {
        super(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder("Dane kontaktowe"));

        initTextFields();
        createContent();
    }

    private void initTextFields() {
        tfPhoneNo = createPhoneNoField();
        tfPhoneNo.setPreferredSize(DIMENSION_TEXT_FIELD);

        tfEmail = new JTextField();
        tfEmail.setPreferredSize(DIMENSION_TEXT_FIELD);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        add(getLabel("nr tel:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        add(tfPhoneNo, gridConstraints);

        gridConstraints.insets = new Insets(5, 5, 15, 5);

        settleGridConstrains(gridConstraints, 0, 1);
        add(getLabel("e-mail:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        add(tfEmail, gridConstraints);
    }

    public void fillContactForm(PatientCard patientCard) {
        tfPhoneNo.setText(patientCard.getPhoneNo());
        tfEmail.setText(patientCard.geteMail());
        if (!patientCard.isPersisted()) {
            tfPhoneNo.setText("48");
        }
    }

    public String getPatientEmailAddress() {
        return tfEmail.getText();
    }

    public String getPatientPhoneNo() {
        return tfPhoneNo.getText();
    }

    private JLabel getLabel(String lblName) {
        JLabel label = new JLabel(lblName, JLabel.RIGHT);
        label.setPreferredSize(DIMENSION_LABEL);
        return label;
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public JFormattedTextField getTfPhoneNo() {
        return tfPhoneNo;
    }

    private JFormattedTextField createPhoneNoField() {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("(+##) ### ### ###");
            mask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return new JFormattedTextField(mask);
    }
}

