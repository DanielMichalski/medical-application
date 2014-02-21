package ui.worker;

import ui.worker.review.ReviewWorkerOptionsPanel;
import ui.worker.review.ReviewWorkerTablePanel;
import ui.worker.update.RegistrationAndEditWorkerDialog;
import ui.worker.update.form.show.ShowWorkerInfoDialog;
import util.Const;
import util.MyProgramLogger;
import util.Utils;
import util.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * @author Daniel Michalski
 */

public class RegisterWorkerFrame extends JFrame implements Runnable {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private static final int DEFAULT_WIDTH = 950;
    private static final int DEFAULT_HEIGHT = 600;

    private ReviewWorkerOptionsPanel optionsPanel;
    private ReviewWorkerTablePanel tableWithPatientsPanel;

    private ButtonsActionListener buttonsActionListener;
    private RegisterWorkerPresenter registerPresenter;

    public RegisterWorkerFrame() {
        super("Przegląd i rejestracja pracowników");
        buttonsActionListener = new ButtonsActionListener();

        LOGGER.info("Utworzenie okienka rejestracji pracowników");

        setupFrame();

        Thread thread2 = new Thread(this);
        thread2.start();

        try {
            thread2.join();
        } catch (InterruptedException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void run() {
        registerPresenter = new RegisterWorkerPresenter();
        initComponents();
        createContent();
    }

    private void setupFrame() {
        setLayout(new GridBagLayout());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Utils.setApplicationIcon(this);
        getContentPane().setBackground(Const.Colors.BACKGROUND_PANEL);
        setLocationRelativeTo(null); //centrowanie
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e)
        {
            JOptionPane.showMessageDialog(this,
                    "Wystąpił błąd podczas ładowania wyglądu okna: " + e,
                    "Uwaga",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        optionsPanel = new ReviewWorkerOptionsPanel(buttonsActionListener);
        tableWithPatientsPanel = new ReviewWorkerTablePanel(registerPresenter.getWorkersFromDB());

        registerPresenter.setTableWithWorkerPanel(tableWithPatientsPanel);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;

        settleGridConstrains(gridConstraints, 0, 0, 1.0, 1.0, new Insets(5, 5, 10, 5));
        add(tableWithPatientsPanel, gridConstraints);
        settleGridConstrains(gridConstraints, 1, 0, 0.0, 1.0, new Insets(12, 5, 12, 5));
        add(optionsPanel, gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY, Insets insets) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
        gridConstraints.insets = insets;
    }

    class ButtonsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton btn = (JButton) event.getSource();
            Worker selectedWorker = tableWithPatientsPanel.getSelectedWorker();
            if (btn.getMnemonic() == ReviewWorkerOptionsPanel.ID_BUTTON_ADD_WORKER) {
                new RegistrationAndEditWorkerDialog(Worker.nullObject(), tableWithPatientsPanel);
            } else if (selectedWorker != null) {
                switch (btn.getMnemonic()) {
                    case ReviewWorkerOptionsPanel.ID_BUTTON_EDIT_WORKER:
                        new RegistrationAndEditWorkerDialog(selectedWorker, tableWithPatientsPanel);
                        break;
                    case ReviewWorkerOptionsPanel.ID_BUTTON_SHOW_WORKER:
                        new ShowWorkerInfoDialog(selectedWorker);
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nie można wykonać kolejnej akcji, ponieważ żaden pracownik nie został jeszcze wybrany.", "Brak zaznacznia", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}