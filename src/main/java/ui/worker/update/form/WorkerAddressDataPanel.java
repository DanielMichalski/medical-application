package ui.worker.update.form;

import employees.ConverterWorkerAdress;
import patient.ConverterPatientAddress;
import ui.components.FillTitleBorder;
import util.Const;
import util.MyProgramLogger;
import util.Worker;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Logger;

public class WorkerAddressDataPanel extends JPanel {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private static final Dimension DIMENSION_TEXT_FIELD = new Dimension(200, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(80, 22);

    private JTextField tfAddress, tfLocality;
    private JFormattedTextField tfZipCode;


    public WorkerAddressDataPanel() {
        super(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder("Dane adresowe"));

        initTextFields();
        createContent();
    }

    private void initTextFields() {
        tfAddress = new JTextField();
        tfAddress.setPreferredSize(DIMENSION_TEXT_FIELD);

        tfZipCode = createZipCodeField();
        tfZipCode.setPreferredSize(DIMENSION_TEXT_FIELD);

        tfLocality = new JTextField();
        tfLocality.setPreferredSize(DIMENSION_TEXT_FIELD);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        add(getLabel("Ulica:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        add(tfAddress, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        add(getLabel("Kod pocztowy:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        add(tfZipCode, gridConstraints);

        gridConstraints.insets = new Insets(5, 5, 15, 5);

        settleGridConstrains(gridConstraints, 0, 2);
        add(getLabel("Miejscowość:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 2);
        add(tfLocality, gridConstraints);
    }

    public void fillAddressForm(Worker worker) {
        String[] splitAddress = ConverterWorkerAdress.getSplitAddressInTable(worker);
        tfZipCode.setText(splitAddress[0]);
        tfLocality.setText(splitAddress[1]);
        tfAddress.setText(splitAddress[2]);
    }

    public String getPatientAddress() {
        return ConverterPatientAddress.getAddressForPatientObj(tfZipCode.getText(), tfLocality.getText(), tfAddress.getText());
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

    private JFormattedTextField createZipCodeField() {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##-###");
            mask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return new JFormattedTextField(mask);
    }

    public JTextField getTfAddress() {
        return tfAddress;
    }

    public JTextField getTfLocality() {
        return tfLocality;
    }

    public JFormattedTextField getTfZipCode() {
        return tfZipCode;
    }

    public void disableTFields() {
        tfAddress.setEnabled(false);
        tfLocality.setEnabled(false);
        tfZipCode.setEnabled(false);
    }
}