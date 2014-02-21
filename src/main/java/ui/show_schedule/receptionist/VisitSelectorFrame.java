package ui.show_schedule.receptionist;

import patient.PatientCard;
import ui.reservation.update.UpdateReservationsPresenter;
import ui.show_schedule.ScheduleTopMenuBar;
import util.MyProgramLogger;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class VisitSelectorFrame extends JDialog {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    public static final int DEFAULT_WIDTH = 700;
    public static final int DEFAULT_HEIGHT = 588;

    private ScheduleTopMenuBar scheduleTopMenuBar;
    private SelectorFreeTimePanel selectorFreeTimePanel;
    private VisitConfirmationPanel visitConfirmationPanel;

    private VisitSelectorPresenter visitSelectorPresenter;

    public VisitSelectorFrame(UpdateReservationsPresenter updateReservationsPresenter) {
        setupFrame();

        visitSelectorPresenter = new VisitSelectorPresenter(this, updateReservationsPresenter);
        initPanels(updateReservationsPresenter.getPatientCard());
        visitSelectorPresenter.constructValidator();
        createContent();
        setVisible(true);

        LOGGER.info("Utworzenie okienka do zarządzania wizytami");
    }

    private void setupFrame() {
        Utils.setApplicationIcon(this);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setLocationRelativeTo(null); //centrowanie
        setTitle("Zapis pacjenta na wizytę");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
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
        setModal(true);
    }

    private void initPanels(PatientCard patientCard) {
        scheduleTopMenuBar = new ScheduleTopMenuBar(this, visitSelectorPresenter);
        visitConfirmationPanel = new VisitConfirmationPanel(patientCard, visitSelectorPresenter);
        selectorFreeTimePanel = new SelectorFreeTimePanel(visitSelectorPresenter);

        visitSelectorPresenter.setVisitConfirmationPanel(visitConfirmationPanel);
        visitSelectorPresenter.setDoctorChooserPanel(selectorFreeTimePanel.getDoctorChooserPanel());
        visitSelectorPresenter.setCalendarPanel(selectorFreeTimePanel.getCalendarPanel());
        visitSelectorPresenter.setReservationsTablePanel(selectorFreeTimePanel.getReservationsTablePanel());
    }

    private void createContent() {
        visitSelectorPresenter.fillViews();
        setJMenuBar(scheduleTopMenuBar);

        add(new JLabel(), BorderLayout.WEST);
        add(selectorFreeTimePanel, BorderLayout.WEST);
        add(visitConfirmationPanel, BorderLayout.CENTER);
    }

}