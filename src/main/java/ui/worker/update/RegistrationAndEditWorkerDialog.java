package ui.worker.update;

import ui.components.ConfirmPanel;
import ui.worker.RegisterWorkerPresenter;
import ui.worker.update.form.FormWorkerPanel;
import util.Const;
import util.Worker;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationAndEditWorkerDialog extends JDialog {

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 700;

    private FormWorkerPanel formWorkerPanel;
    private ConfirmPanel confirmButtonsPanel;

    private ButtonsActionListener buttonsActionListener;
    private RegisterWorkerPresenter registerPresenter;
    private Worker workerForEdit;
    private OnUpdateWorkerListener updatePatientListener;
    private boolean isRegistration;

    public RegistrationAndEditWorkerDialog(Worker workerForEdit, OnUpdateWorkerListener updatePatientListener) {
        this.workerForEdit = workerForEdit;
        this.updatePatientListener = updatePatientListener;
        buttonsActionListener = new ButtonsActionListener();

        setupDialog();
        initComponents(workerForEdit);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents(Worker worker) {
        formWorkerPanel = new FormWorkerPanel(this, worker);
        if (worker.isPersisted()) {
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT - 200);
            formWorkerPanel.setVisibleForEdit();
            setTitle("Edycja pracownika");
            confirmButtonsPanel = new ConfirmPanel(buttonsActionListener, "Zapisz zmiany", "Anuluj", Const.Colors.BACKGROUND_PANEL);
            isRegistration = false;
        } else {
            formWorkerPanel.setVisibleForRegister();
            setTitle("Rejestracja nowego pracownika");
            confirmButtonsPanel = new ConfirmPanel(buttonsActionListener, "Rejestruj", "Anuluj", Const.Colors.BACKGROUND_PANEL);
            isRegistration = true;
        }

        registerPresenter = new RegisterWorkerPresenter(formWorkerPanel);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;

        settleGridConstrains(gridConstraints, 0, 0, 1.0, 1.0, new Insets(5, 5, 5, 5));
        add(formWorkerPanel, gridConstraints);
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
            if (workerForEdit.isPersisted()) {
                infoDialogMsg = "Edycja pracownika zakończyła się pomyślnie.";
            } else {
                infoDialogMsg = "Rejestracja nowego pracownika zakończyła się pomyślnie.";
            }
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            JButton btn = (JButton) event.getSource();
            switch (btn.getMnemonic()) {
                case ConfirmPanel.ID_POSITIVE_BUTTON:
                    try {

                        Worker newWorker = registerPresenter.trySaveWorkerInDB(RegistrationAndEditWorkerDialog.this.workerForEdit);

                        updateTable(RegistrationAndEditWorkerDialog.this.workerForEdit, newWorker);
                        JOptionPane.showMessageDialog(null, infoDialogMsg, "Pracownik zapisany", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (ValidationException e) {
                        JOptionPane.showMessageDialog(null, "Problem podczas próby zapisu pracownika.\nProszę wypełnić wszystkie wymagane pola zaznaczone na czerwono", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case ConfirmPanel.ID_NEGATIVE_BUTTON:
                    dispose();
                    break;
            }
        }

        private void updateTable(Worker oldWorker, Worker newWorker) {
            if (isRegistration) {
                updatePatientListener.insertedNewWorker(newWorker);
            } else {
                updatePatientListener.editedWorker(oldWorker, newWorker);
            }
        }
    }
}
