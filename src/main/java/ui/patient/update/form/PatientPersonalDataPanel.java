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

public class PatientPersonalDataPanel extends JPanel {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private static final Dimension DIMENSION_TEXT_FIELD = new Dimension(200, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(80, 22);

    private JTextField tfFirstName, tfLastName;
    private JFormattedTextField tfPesel;

    public PatientPersonalDataPanel() {
        super(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder("Dane osobowe"));

        initTextFields();
        createContent();
    }

    private void initTextFields() {
        tfFirstName = new JTextField();
        tfFirstName.setPreferredSize(DIMENSION_TEXT_FIELD);

        tfLastName = new JTextField();
        tfLastName.setPreferredSize(DIMENSION_TEXT_FIELD);

        tfPesel = createPeselField();
        tfPesel.setPreferredSize(DIMENSION_TEXT_FIELD);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        add(getLabel("Imię:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        add(tfFirstName, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        add(getLabel("Nazwisko:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        add(tfLastName, gridConstraints);

        gridConstraints.insets = new Insets(5, 5, 15, 5);

        settleGridConstrains(gridConstraints, 0, 2);
        add(getLabel("PESEL:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 2);
        add(tfPesel, gridConstraints);
    }

    public void fillPersonalDataForm(PatientCard patientCard) {
        tfFirstName.setText(patientCard.getFirstName());
        tfLastName.setText(patientCard.getLastName());
        tfPesel.setText(patientCard.getPESEL());
    }

    public String getPatientFirstName() {
        return tfFirstName.getText();
    }

    public String getPatientLastName() {
        return tfLastName.getText();
    }

    public String getPatientPesel() {
        return tfPesel.getText();
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

    private JFormattedTextField createPeselField() {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("###########");
            mask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return new JFormattedTextField(mask);
    }

    public JTextField getTfFirstName() {
        return tfFirstName;
    }

    public JTextField getTfLastName() {
        return tfLastName;
    }

    public JFormattedTextField getTfPesel() {
        return tfPesel;
    }
}

