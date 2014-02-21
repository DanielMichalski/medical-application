package ui.patient.update;

import patient.PatientCard;
import ui.components.ConfirmPanel;
import ui.patient.RegisterPatientPresenter;
import ui.patient.update.form.FormPatientPanel;
import util.Const;
import util.MyProgramLogger;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Logger;

public class RegistrationAndEditPatientDialog extends JDialog {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private static final int DEFAULT_WIDTH = 380;
    private static final int DEFAULT_HEIGHT = 470;

    private FormPatientPanel formPatientPanel;
    private ConfirmPanel confirmButtonsPanel;

    private ButtonsActionListener buttonsActionListener;
    private RegisterPatientPresenter registerPresenter;
    private PatientCard patientCardForEdit;
    private OnUpdatePatientListener updatePatientListener;
    private boolean isRegistration;

    public RegistrationAndEditPatientDialog(PatientCard patientCardForEdit, OnUpdatePatientListener updatePatientListener) {
        this.patientCardForEdit = patientCardForEdit;
        this.updatePatientListener = updatePatientListener;
        buttonsActionListener = new ButtonsActionListener();

        setupDialog();
        initComponents(patientCardForEdit);
        createContent();
        setVisible(true);
    }

    private void setupDialog() {
        setLayout(new GridBagLayout());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        getContentPane().setBackground(Const.Colors.BACKGROUND_PANEL);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setModal(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
            LOGGER.warning(Arrays.toString(e.getStackTrace()));
            JOptionPane.showMessageDialog(this,
                    "Wystąpił błąd podczas ładowania wyglądu okna: " + e,
                    "Uwaga",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents(PatientCard patientCardForEdit) {
        formPatientPanel = new FormPatientPanel(this, patientCardForEdit);
        if (patientCardForEdit.isPersisted()) {
            setTitle("Edycja pacjenta");
            confirmButtonsPanel = new ConfirmPanel(buttonsActionListener, "Zapisz zmiany", "Anuluj", Const.Colors.BACKGROUND_PANEL);
            isRegistration = false;
        } else {
            setTitle("Rejestracja nowego pacjenta");
            confirmButtonsPanel = new ConfirmPanel(buttonsActionListener, "Rejestruj", "Anuluj", Const.Colors.BACKGROUND_PANEL);
            isRegistration = true;
        }

        registerPresenter = new RegisterPatientPresenter(formPatientPanel);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;

        settleGridConstrains(gridConstraints, 0, 0, 1.0, 1.0, new Insets(5, 5, 5, 5));
        add(formPatientPanel, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 1, 1.0, 0.0, new Insets(5, 5, 10, 10));
        add(confirmButtonsPanel, gridConstraints);

    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY, Insets insets) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
        gridConstraints.insets = insets;
    }

    private class ButtonsActionListener implements ActionListener {
        private String infoDialogMsg;

        private ButtonsActionListener() {
            if (patientCardForEdit.isPersisted()) {
                infoDialogMsg = "Edycja pacjenta zakończyła się pomyślnie.";
            } else {
                infoDialogMsg = "Rejestracja nowego pacjenta zakończyła się pomyślnie.";
            }
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            JButton btn = (JButton) event.getSource();
            switch (btn.getMnemonic()) {
                case ConfirmPanel.ID_POSITIVE_BUTTON:
                    try {
                        patientCardForEdit = registerPresenter.trySavePatientInDB(patientCardForEdit);
                        updateTable(patientCardForEdit);
                        JOptionPane.showMessageDialog(null, infoDialogMsg, "Pacjent zapisany", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (ValidationException e) {
                        JOptionPane.showMessageDialog(null, "Problem podczas próby zapisu pacjenta.\nProszę wypełnić wszystkie wymagane pola zaznaczone na czerwono", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case ConfirmPanel.ID_NEGATIVE_BUTTON:
                    dispose();
                    break;
            }
        }

        private void updateTable(PatientCard patientCard) {
            if (isRegistration) {
                updatePatientListener.insertedNewPatient(patientCard);
            } else {
                updatePatientListener.editedPatient(patientCard);
            }
        }
    }


}
