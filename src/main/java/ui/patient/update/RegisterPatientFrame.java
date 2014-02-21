package ui.patient.update;

import ui.patient.RegisterPatientPresenter;
import ui.patient.review.ReviewPatientOptionsPanel;
import ui.patient.review.ReviewPatientTablePanel;
import util.Const;
import util.MyProgramLogger;
import util.Utils;
import util.Worker;
import util.finder.WordsFinder;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * @author Daniel Michalski
 */

public class RegisterPatientFrame extends JFrame {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private static final int DEFAULT_WIDTH = 780;
    private static final int DEFAULT_HEIGHT = 600;

    private ReviewPatientOptionsPanel optionsPanel;
    private ReviewPatientTablePanel tableWithPatientsPanel;

    private RegisterPatientPresenter registerPresenter;

    public RegisterPatientFrame(final Worker worker) {
        super("Przegląd i rejestracja pacjentów");

        setupFrame();
        initComponents(worker);
        createContent();
        LOGGER.info("Utworzenie okienka rejestracji pacjentów");
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
                UnsupportedLookAndFeelException e) {
            LOGGER.warning(e.getStackTrace().toString());
            JOptionPane.showMessageDialog(this,
                    "Wystąpił błąd podczas ładowania wyglądu okna: " + e,
                    "Uwaga",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents(final Worker worker) {
        registerPresenter = new RegisterPatientPresenter();

        WordsFinder wordsFinder = new WordsFinder(registerPresenter);
        optionsPanel = new ReviewPatientOptionsPanel(wordsFinder, registerPresenter, worker);
        tableWithPatientsPanel = new ReviewPatientTablePanel(wordsFinder, registerPresenter.getPatientCardFromDB());

        registerPresenter.setTableWithPatientsPanel(tableWithPatientsPanel);
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
}