package ui.worker.review;

import ui.components.FillTitleBorder;
import ui.show_schedule.HeaderPanel;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReviewWorkerOptionsPanel extends JPanel {
    public static final int ID_BUTTON_EDIT_WORKER = 1;
    public static final int ID_BUTTON_ADD_WORKER = 2;
    public static final int ID_BUTTON_SHOW_WORKER = 3;

    private JButton btnEditWorker;
    private JButton btnAddWorker;
    private JButton btnShowWorker;

    public ReviewWorkerOptionsPanel(ActionListener buttonListener) {
        super(new GridBagLayout());
        initComponents(buttonListener);
        createContent();
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder(""));
    }

    private void initComponents(ActionListener buttonListener) {
        btnEditWorker = new JButton("Edytuj dane");
        btnEditWorker.setMnemonic(ID_BUTTON_EDIT_WORKER);
        btnEditWorker.addActionListener(buttonListener);

        btnAddWorker = new JButton("Rejestruj pracownika");
        btnAddWorker.setMnemonic(ID_BUTTON_ADD_WORKER);
        btnAddWorker.addActionListener(buttonListener);

        btnShowWorker = new JButton("Pokaż informacje");
        btnShowWorker.setMnemonic(ID_BUTTON_SHOW_WORKER);
        btnShowWorker.addActionListener(buttonListener);
    }

    private void createContent() {
        HeaderPanel headerPatientActions = new HeaderPanel("Akcje pracownik", "");

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        Insets insetsBetweenBtn = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 3, 1.0, 0.0, new Insets(0, 5, -5, 5));
        add(headerPatientActions, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 4, 0.0, 0.0, insetsBetweenBtn);
        add(btnShowWorker, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 5, 0.0, 0.0, insetsBetweenBtn);
        add(btnAddWorker, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 6, 0.0, 0.0, insetsBetweenBtn);
        add(btnEditWorker, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 7, 0.0, 0.0, insetsBetweenBtn);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY, Insets insets) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
        gridConstraints.insets = insets;
    }
}