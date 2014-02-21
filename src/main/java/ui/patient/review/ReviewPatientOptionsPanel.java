package ui.patient.review;

import employees.Doctor;
import ui.components.FillTitleBorder;
import ui.show_schedule.HeaderPanel;
import util.Const;
import util.Worker;
import util.finder.WordsFinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReviewPatientOptionsPanel extends JPanel {
    public static final int ID_BUTTON_EDIT_VISITS = 1;
    public static final int ID_BUTTON_EDIT_PATIENT = 2;
    public static final int ID_BUTTON_REMOVE_PATIENT = 4;
    public static final int ID_BUTTON_ADD_PATIENT = 5;
    public static final int ID_BUTTON_SHOW_TEMP = 6;

    private JButton btnEditVisits;
    private JButton btnEditPatient;
    private JButton btnAddPatient;
    private JButton btnRemovePatient;
    private JButton btnShowTemp;
    private WordsFinder wordsFinder;

    public ReviewPatientOptionsPanel(WordsFinder wordsFinder, ActionListener buttonListener, final Worker worker) {
        super(new GridBagLayout());
        this.wordsFinder = wordsFinder;
        initComponents(buttonListener);
        createContent();
        setBackground(Const.Colors.FILL_BORDER);
        setBorder(new FillTitleBorder(""));
        switchViewByWorker(worker);
    }

    private void switchViewByWorker(Worker worker) {
        if (worker instanceof Doctor)
            showViewByWorker();
    }

    private void showViewByWorker() {
        btnEditVisits.setEnabled(false);
        btnEditPatient.setEnabled(false);
        btnAddPatient.setEnabled(false);
        btnRemovePatient.setEnabled(false);
    }

    private void initComponents(ActionListener buttonListener) {
        btnEditVisits = new JButton("Zarządzaj wizytami");
        btnEditVisits.addActionListener(buttonListener);
        btnEditVisits.setMnemonic(ID_BUTTON_EDIT_VISITS);

        btnEditPatient = new JButton("Edytuj dane");
        btnEditPatient.setMnemonic(ID_BUTTON_EDIT_PATIENT);
        btnEditPatient.addActionListener(buttonListener);

        btnAddPatient = new JButton("Rejestruj pacjenta");
        btnAddPatient.setMnemonic(ID_BUTTON_ADD_PATIENT);
        btnAddPatient.addActionListener(buttonListener);

        btnRemovePatient = new JButton("Usuń pacjenta");
        btnRemovePatient.setMnemonic(ID_BUTTON_REMOVE_PATIENT);
        btnRemovePatient.addActionListener(buttonListener);

        btnShowTemp = new JButton("Wykres temperatur");
        btnShowTemp.setMnemonic(ID_BUTTON_SHOW_TEMP);
        btnShowTemp.addActionListener(buttonListener);
    }

    private void createContent() {
        HeaderPanel headerVisitActions = new HeaderPanel("Akcje wizyt", "");
        HeaderPanel headerPatientActions = new HeaderPanel("Akcje pacjent", "");
        HeaderPanel headerSearch = new HeaderPanel("Wyszukaj pacjenta", "");

        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        Insets insetsBetweenBtn = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 0, 1.0, 0.0, new Insets(5, 5, -5, 5));
        add(headerSearch, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 1, 0.0, 0.0, new Insets(5, 0, 5, 0));
        add(createSearchPanel(), gridConstraints);


        settleGridConstrains(gridConstraints, 0, 2, 1.0, 1.0, new Insets(0, 0, 0, 0));
        add(new JLabel(), gridConstraints);


        settleGridConstrains(gridConstraints, 0, 3, 1.0, 0.0, new Insets(0, 5, -5, 5));
        add(headerPatientActions, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 4, 0.0, 0.0, insetsBetweenBtn);
        add(btnAddPatient, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 5, 0.0, 0.0, insetsBetweenBtn);
        add(btnEditPatient, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 6, 0.0, 0.0, insetsBetweenBtn);
        add(btnRemovePatient, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 7, 0.0, 0.0, insetsBetweenBtn);
        add(btnShowTemp, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 8, 1.0, 0.0, new Insets(10, 5, -5, 5));
        add(headerVisitActions, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 9, 0.0, 0.0, insetsBetweenBtn);
        add(btnEditVisits, gridConstraints);


        settleGridConstrains(gridConstraints, 0, 10, 1.0, 1.0, new Insets(0, 0, 70, 0));
        add(new JLabel(), gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY, Insets insets) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
        gridConstraints.insets = insets;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;

        settleGridConstrains(gridConstraints, 0, 0, 1.0, 0.0, new Insets(0, 0, 0, 0));
        panel.add(wordsFinder, gridConstraints);

        return panel;
    }
}