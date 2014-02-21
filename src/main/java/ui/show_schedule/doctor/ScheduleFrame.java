package ui.show_schedule.doctor;

import employees.Doctor;
import ui.show_schedule.ScheduleTopMenuBar;
import util.Const;
import util.MyProgramLogger;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class ScheduleFrame extends JDialog {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;

    private ScheduleTopMenuBar scheduleTopMenuBar;
    private PatientDetailsPanel patientDetailsPanel;
    private SelectorSchedulePanel selectorSchedulePanel;

    private SchedulePresenter presenter;

    public ScheduleFrame(Doctor doctor) {
        getContentPane().setBackground(Const.Colors.BACKGROUND_PANEL);
        presenter = new SchedulePresenter(doctor);

        setupFrame();
        initPanels();
        createContent();

        LOGGER.info("Utworzenie okienka z harmonogramem wizyt");
    }

    private void setupFrame() {
        setLayout(new GridBagLayout());
        Utils.setApplicationIcon(this);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setLocationRelativeTo(null); //centrowanie
        setTitle("Harmonogram dla doktorka");
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

    private void initPanels() {
        scheduleTopMenuBar = new ScheduleTopMenuBar(this, presenter);
        selectorSchedulePanel = new SelectorSchedulePanel(presenter);
        patientDetailsPanel = new PatientDetailsPanel();

        presenter.setPatientDetailsPanel(patientDetailsPanel);
        presenter.setCalendarPanel(selectorSchedulePanel.getCalendarPanel());
        presenter.setReservationsTablePanel(selectorSchedulePanel.getReservationsTablePanel());

    }

    private void createContent() {
        presenter.fillViews();
        setJMenuBar(scheduleTopMenuBar);

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0, 0.0, 1.0);
        add(selectorSchedulePanel, gridConstraints);

        settleGridConstrains(gridConstraints, 1, 0, 1.0, 1.0);
        add(patientDetailsPanel, gridConstraints);

    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
    }

}