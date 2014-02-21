package ui.show_schedule;

import patient.Reservation;
import ui.components.table.TableReservationsModel;
import ui.show_schedule.receptionist.SelectReservationListener;
import util.Const;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationsTablePanel extends JScrollPane implements ListSelectionListener, Validable {

    private JTable tableWithReservations;
    private TableReservationsModel tableModel;
    private SelectReservationListener selectReservationListener;
    private HeaderPanel header;
    private boolean disableSavedVisits;

    public ReservationsTablePanel(HeaderPanel header, boolean disableSavedVisits, SelectReservationListener selectReservationListener) {
        this.header = header;
        this.disableSavedVisits = disableSavedVisits;
        this.selectReservationListener = selectReservationListener;

        init();
        createContent();
    }

    private void init() {
        tableModel = new TableReservationsModel(new ArrayList<Reservation>());
        tableWithReservations = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setEnabled(true);
                if (!isRowSelected(row)) {
                    int modelRow = convertRowIndexToModel(row);
                    String type = (String) getModel().getValueAt(modelRow, 1);
                    if (!" ".equals(type) && disableSavedVisits) {
                        c.setEnabled(false);
                    }
                }
                return c;
            }
        };
        setupTable(tableWithReservations);
    }

    private void createContent() {
        getViewport().removeAll();
        getViewport().add(tableWithReservations);
        getViewport().setBackground(Const.Colors.FILL_BORDER);
    }

    public void refreshTableWith(List<Reservation> reservations) {
        tableModel.changeAllItems(reservations);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        header.hideError();
        tableWithReservations.setFocusable(false);
        selectReservationListener.onSelectReservation(getSelectedReservation());
    }

    public Reservation getSelectedReservation() {
        Reservation selectedReservation = null;
        int selectedRowIndex = tableWithReservations.getSelectedRow();
        if (selectedRowIndex >= 0) {
            selectedReservation = tableModel.getReservationByRowIndex(selectedRowIndex);
        }
        return selectedReservation;
    }

    public void clearRowSelection() {
        tableWithReservations.clearSelection();
    }

    private void setupTable(JTable table) {
//		table.setPreferredScrollableViewportSize(tableWithReservations.getPreferredSize());
        table.getSelectionModel().addListSelectionListener(this);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        TableColumn dateColumn = table.getColumnModel().getColumn(0);
        dateColumn.setCellRenderer(centerRenderer);
        dateColumn.setPreferredWidth(20);

        TableColumn patientColumn = table.getColumnModel().getColumn(1);
        patientColumn.setPreferredWidth(180);
    }

    @Override
    public void check() throws ValidationException {
        if (tableWithReservations == null || getSelectedReservation() == null) {
            header.showError();
            throw new ValidationException();
        }
    }

}