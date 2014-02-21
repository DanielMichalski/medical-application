package ui.worker.update.form;

import ui.components.FillTitleBorder;
import util.Const;
import util.Worker;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel Michalski
 * Date: 14.02.13
 */

public class WorkerUserDataPanel extends JPanel {
    private static final Dimension DIMENSION_TEXT_FIELD = new Dimension(200, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(80, 22);

    private JTextField tflogin;
    private JPasswordField pfPassword1, pfPassword2;

    public WorkerUserDataPanel() {
        super(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder("Login oraz hasło"));

        initTextFields();
        createContent();
    }

    private void initTextFields() {
        tflogin = new JTextField();
        tflogin.setPreferredSize(DIMENSION_TEXT_FIELD);

        pfPassword1 = new JPasswordField();
        pfPassword1.setPreferredSize(DIMENSION_TEXT_FIELD);

        pfPassword2 = new JPasswordField();
        pfPassword2.setPreferredSize(DIMENSION_TEXT_FIELD);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        add(getLabel("Login:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0);
        add(tflogin, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        add(getLabel("Hasło:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 1);
        add(pfPassword1, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 2);
        add(getLabel("Powtórz hasło:"), gridConstraints);
        settleGridConstrains(gridConstraints, 1, 2);
        add(pfPassword2, gridConstraints);

        gridConstraints.insets = new Insets(5, 5, 15, 5);
    }

    public void fillPersonalDataForm(Worker worker) {
        tflogin.setText(worker.getLogin());
        if (worker.isPersisted())
            tflogin.setEnabled(false);
    }

    public String getPatientLogin() {
        return tflogin.getText();
    }

    public String getPatientPassword1() {
        return String.valueOf(pfPassword1.getPassword());
    }

    public String getPatientPassword2() {
        return String.valueOf(pfPassword2.getPassword().toString());
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

    public JTextField getTflogin() {
        return tflogin;
    }

    public JPasswordField getPfPassword1() {
        return pfPassword1;
    }

    public JPasswordField getPfPassword2() {
        return pfPassword2;
    }

    public void disableTFLogin() {
        tflogin.setEnabled(false);
    }

    public void disableAllFields() {
        tflogin.setEnabled(false);
        pfPassword1.setText("**********");
        pfPassword1.setEnabled(false);
        pfPassword2.setText("**********");
        pfPassword2.setEnabled(false);
    }
}
