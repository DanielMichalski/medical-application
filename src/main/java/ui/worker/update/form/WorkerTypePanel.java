package ui.worker.update.form;

import employees.AccountType;
import ui.components.FillTitleBorder;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Daniel Michalski
 * Date: 14.02.13
 */

public class WorkerTypePanel extends JPanel {
    private static final Dimension DIMENSION_COMBO_BOX = new Dimension(200, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(80, 22);

    private static final int ADMIN = 1;
    private static final int RECEPTIONIST = 2;
    private static final int DOCTOR = 3;

    private JComboBox specializationComboBox;

    private JComboBox branchesComboBox;

    private JRadioButton adminRadioBtn;

    private JRadioButton doctorRadioBtn;

    private JRadioButton receptionistRadioBtn;

    private ButtonGroup radioButtonGroup;

    private JLabel branchesLabel;

    private JLabel specializationLabel;

    public WorkerTypePanel() {
        super(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder("Typ konta"));

        initComponents();
        createContent();
    }

    private void initComponents() {
        specializationComboBox = new JComboBox();
        specializationComboBox.setPreferredSize(DIMENSION_COMBO_BOX);

        branchesComboBox = new JComboBox();
        branchesComboBox.setPreferredSize(DIMENSION_COMBO_BOX);

        doctorRadioBtn = createRadioBtn("Doktor", new RadioButtonListener(), String.valueOf(DOCTOR));
        doctorRadioBtn.setSelected(true);
        doctorRadioBtn.setMnemonic(DOCTOR);

        receptionistRadioBtn = createRadioBtn("Recepcjonista", new RadioButtonListener(), String.valueOf(RECEPTIONIST));
        receptionistRadioBtn.setMnemonic(RECEPTIONIST);

        adminRadioBtn = createRadioBtn("Administrator", new RadioButtonListener(), String.valueOf(ADMIN));
        adminRadioBtn.setMnemonic(ADMIN);

        branchesLabel = getLabel("Oddział:");
        specializationLabel = getLabel("Specjalizacja:");

        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(adminRadioBtn);
        radioButtonGroup.add(doctorRadioBtn);
        radioButtonGroup.add(receptionistRadioBtn);
    }

    private JRadioButton createRadioBtn(String title, ActionListener listener,
                                        String actionCommand) {
        JRadioButton radioButton = new JRadioButton(title);
        radioButton.addActionListener(listener);
        radioButton.setActionCommand(actionCommand);
        radioButton.setBackground(Const.Colors.FILL_BORDER);
        return radioButton;
    }

    private void createContent() {

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        add(getLabel("Typ konta:"), gridConstraints);

        settleGridConstrains(gridConstraints, 1, 0);
        add(doctorRadioBtn, gridConstraints);

        settleGridConstrains(gridConstraints, 1, 1);
        add(receptionistRadioBtn, gridConstraints);

        settleGridConstrains(gridConstraints, 1, 2);
        add(adminRadioBtn, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 4);
        add(branchesLabel, gridConstraints);

        settleGridConstrains(gridConstraints, 1, 4);
        add(branchesComboBox, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 5);
        add(specializationLabel, gridConstraints);

        settleGridConstrains(gridConstraints, 1, 5);
        add(specializationComboBox, gridConstraints);

        gridConstraints.insets = new Insets(5, 10, 15, 5);

    }

    private void viewForDoctor() {
        branchesLabel.setVisible(true);
        branchesComboBox.setVisible(true);
        specializationLabel.setVisible(true);
        specializationComboBox.setVisible(true);
    }

    private void viewForReceptionist() {
        branchesLabel.setVisible(true);
        branchesComboBox.setVisible(true);
        specializationLabel.setVisible(false);
        specializationComboBox.setVisible(false);
    }

    private void viewForAdmin() {
        branchesLabel.setVisible(false);
        branchesComboBox.setVisible(false);
        specializationLabel.setVisible(false);
        specializationComboBox.setVisible(false);
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

    public AccountType getSelectedAccountType() {
        switch (radioButtonGroup.getSelection().getMnemonic()) {
            case ADMIN:
                return AccountType.ADMIN;
            case RECEPTIONIST:
                return AccountType.RECEPTIONIST;
            case DOCTOR:
                return AccountType.DOCTOR;
            default:
                return null;
        }
    }

    class RadioButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (Integer.parseInt(e.getActionCommand())) {
                case ADMIN:
                    viewForAdmin();
                    break;
                case DOCTOR:
                    viewForDoctor();
                    break;
                case RECEPTIONIST:
                    viewForReceptionist();
                    break;
            }
        }
    }

    public JComboBox getBranchesComboBox() {
        return branchesComboBox;
    }

    public JComboBox getSpecializationComboBox() {
        return specializationComboBox;
    }
}
