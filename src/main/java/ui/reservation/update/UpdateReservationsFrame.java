package ui.reservation.update;


import patient.PatientCard;
import patient.Reservation;
import util.Const;
import util.MyProgramLogger;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class UpdateReservationsFrame extends JDialog {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private static final int DEFAULT_WIDTH = 680;
    private static final int DEFAULT_HEIGHT = 600;

    private ButtonsActionListener buttonsActionListener;

    private UpdateReservationsOptionsPanel optionsPanel;
    private UpdateReservationsTablePanel reservationsTablePanel;
    private UpdateReservationsPatientDetailsPanel patientDetailsPanel;

    private UpdateReservationsPresenter presenter;

    public UpdateReservationsFrame(PatientCard patientCard) {
        setTitle("Zarządzanie wizytami");
        buttonsActionListener = new ButtonsActionListener();

        setupFrame();
        initComponents(patientCard);
        createContent();

        setVisible(true);

        LOGGER.info("Utorzenie okienka zarządzania wizytami");
    }

    private void setupFrame() {
        setLayout(new GridBagLayout());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Utils.setApplicationIcon(this);
        getContentPane().setBackground(Const.Colors.BACKGROUND_PANEL);
        setLocationRelativeTo(null); //centrowanie
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);

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

    private void initComponents(PatientCard patientCard) {
        presenter = new UpdateReservationsPresenter(patientCard);
        optionsPanel = new UpdateReservationsOptionsPanel(buttonsActionListener);
        patientDetailsPanel = new UpdateReservationsPatientDetailsPanel(patientCard);
        reservationsTablePanel = new UpdateReservationsTablePanel(presenter.getReservationsFromDB());
        presenter.setReservationsTablePanel(reservationsTablePanel);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;

        settleGridConstrains(gridConstraints, 0, 0, 1.0, 0.0, new Insets(5, 5, 5, 5));
        add(patientDetailsPanel, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 1, 1.0, 1.0, new Insets(5, 5, 5, 5));
        add(reservationsTablePanel, gridConstraints);

        gridConstraints.gridheight = 2;
        settleGridConstrains(gridConstraints, 1, 0, 0.0, 1.0, new Insets(12, 5, 7, 5));
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
            Reservation selectedReservation = reservationsTablePanel.getSelectedReservation();
            if (btn.getMnemonic() == UpdateReservationsOptionsPanel.ID_BUTTON_ADD_VISIT) {
                presenter.addVisitAction();
            } else if (selectedReservation != null) {
                switch (btn.getMnemonic()) {
                    case UpdateReservationsOptionsPanel.ID_BUTTON_EDIT_VISIT:
                        presenter.editVisitAction(selectedReservation);
                        break;
                    case UpdateReservationsOptionsPanel.ID_BUTTON_REMOVE_VISIT:
                        presenter.removeVisitAction(selectedReservation);
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nie można wykonać kolejnej akcji, ponieważ żadna wizyta nie została jeszcze wybrana.", "Brak zaznacznia", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}