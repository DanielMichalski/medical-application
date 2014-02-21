package ui.reservation.update;

import ui.components.FillTitleBorder;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UpdateReservationsOptionsPanel extends JPanel {

    public static final int ID_BUTTON_ADD_VISIT = 1;
    public static final int ID_BUTTON_EDIT_VISIT = 2;
    public static final int ID_BUTTON_REMOVE_VISIT = 4;

    private JButton btnAddVisit;
    private JButton btnEditVisits;
    private JButton btnRemoveVisit;

    public UpdateReservationsOptionsPanel(ActionListener buttonListener) {
        super(new GridBagLayout());
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setBorder(new FillTitleBorder(""));

        initComponents(buttonListener);
        createContent();
    }

    private void initComponents(ActionListener buttonListener) {
        btnAddVisit = new JButton("Dodaj wizytę");
        btnAddVisit.setMnemonic(ID_BUTTON_ADD_VISIT);
        btnAddVisit.addActionListener(buttonListener);

        btnEditVisits = new JButton("Edytuj wizytę");
        btnEditVisits.addActionListener(buttonListener);
        btnEditVisits.setMnemonic(ID_BUTTON_EDIT_VISIT);

        btnRemoveVisit = new JButton("Usuń wizytę");
        btnRemoveVisit.setMnemonic(ID_BUTTON_REMOVE_VISIT);
        btnRemoveVisit.addActionListener(buttonListener);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);

        settleGridConstrains(gridConstraints, 0, 1, 0.0, 0.0);
        add(btnAddVisit, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 2, 0.0, 0.0);
        add(btnEditVisits, gridConstraints);
        settleGridConstrains(gridConstraints, 0, 3, 0.0, 0.0);
        add(btnRemoveVisit, gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
    }
}

