package ui.worker.update.form.show;

import ui.components.FillTitleBorder;
import util.Const;
import util.Worker;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel Michalski
 * Date: 22.03.13
 */

public class ShowWorkerUserType extends JPanel {
    private static final Dimension DIMENSION_TEXT_FIELD = new Dimension(200, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(80, 22);

    private JTextField workerTypeTF;
    private JTextField workerBranchTF;
    private JTextField workerSpecializationTF;


    public ShowWorkerUserType() {
        setLayout(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder("Typ konta"));

        initComponents();
        createContent();
    }

    private void initComponents() {
        workerTypeTF = new JTextField();
        workerTypeTF.setPreferredSize(DIMENSION_TEXT_FIELD);
        workerTypeTF.setEnabled(false);

        workerBranchTF = new JTextField();
        workerBranchTF.setPreferredSize(DIMENSION_TEXT_FIELD);
        workerBranchTF.setEnabled(false);

        workerSpecializationTF = new JTextField();
        workerSpecializationTF.setPreferredSize(DIMENSION_TEXT_FIELD);
        workerSpecializationTF.setEnabled(false);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0);
        add(getLabel("Typ konta:"), gridConstraints);

        settleGridConstrains(gridConstraints, 1, 0);
        add(workerTypeTF, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        add(getLabel("Oddział"), gridConstraints);

        settleGridConstrains(gridConstraints, 1, 1);
        add(workerBranchTF, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 2);
        add(getLabel("Specjalizacja"), gridConstraints);

        settleGridConstrains(gridConstraints, 1, 2);
        add(workerSpecializationTF, gridConstraints);

        gridConstraints.insets = new Insets(5, 10, 15, 5);
    }

    public void fillWorkerTypeForm(Worker worker) {
        workerTypeTF.setText(worker.getAccountType().toString());
        workerBranchTF.setText(worker.getBranch().getName());
        workerSpecializationTF.setText(worker.getSpecialization().getName());
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
}
