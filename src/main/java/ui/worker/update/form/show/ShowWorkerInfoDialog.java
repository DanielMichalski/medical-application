package ui.worker.update.form.show;

import util.Const;
import util.Worker;

import javax.swing.*;
import java.awt.*;

public class ShowWorkerInfoDialog extends JDialog {

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 600;

    private ShowFormWorkerPanel formWorkerPanel;

    public ShowWorkerInfoDialog(Worker workerForEdit) {
        setupDialog();
        initComponents(workerForEdit);
        createContent();
        setVisible(true);
    }

    private void initComponents(Worker workerForEdit) {
        formWorkerPanel = new ShowFormWorkerPanel(this, workerForEdit);
    }

    private void setupDialog() {
        setTitle("Informacje o pracowniku");
        setLayout(new GridBagLayout());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        getContentPane().setBackground(Const.Colors.BACKGROUND_PANEL);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setModal(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;

        settleGridConstrains(gridConstraints, 0, 0, 1.0, 1.0, new Insets(5, 5, 5, 5));
        add(formWorkerPanel, gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY, Insets insets) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
        gridConstraints.insets = insets;
    }
}
