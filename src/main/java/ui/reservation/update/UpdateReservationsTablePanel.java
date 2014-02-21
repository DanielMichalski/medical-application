package ui.reservation.update;

import patient.Reservation;
import ui.components.FillTitleBorder;
import ui.components.table.TableReservationsWithDoctorModel;
import util.Const;
import util.MyProgramLogger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class UpdateReservationsTablePanel extends JPanel implements OnUpdateReservationListener, ListSelectionListener {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    private JTable reservationsTable;

    private TableReservationsWithDoctorModel tableModel;

    public UpdateReservationsTablePanel(List<Reservation> reservations) {
        super(new GridBagLayout());
        setBorder(new FillTitleBorder("Lista zarezerwowanych wizyt"));
        setBackground(Const.Colors.BACKGROUND_PANEL);

        init(reservations);
        createContent();
    }

    private void init(List<Reservation> reservations) {
        tableModel = new TableReservationsWithDoctorModel(reservations);
        reservationsTable = createReservationsTable(tableModel);
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;

        settleGridConstrains(gridConstraints, 0, 0, 1.0, 1.0);
        add(createScrollTable(reservationsTable), gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y, double weightX, double weightY) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
        gridConstraints.weightx = weightX;
        gridConstraints.weighty = weightY;
    }

    private JScrollPane createScrollTable(JTable table) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setBackground(Const.Colors.FILL_BORDER);
        scrollPane.getViewport().add(table);
        return scrollPane;
    }

    public Reservation getSelectedReservation() {
        Reservation selectedReservation = null;
        int selectedRowIndex = reservationsTable.getSelectedRow();
        if (selectedRowIndex >= 0) {
            selectedReservation = tableModel.getReservationByRowIndex(selectedRowIndex);
        }
        return selectedReservation;
    }

    private JTable createReservationsTable(TableReservationsWithDoctorModel tableModel) {
        JTable table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                component.setEnabled(true);
                if (!isRowSelected(row)) {
                    Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    int modelRow = convertRowIndexToModel(row);

                    String dateVisitColumn = (String) getModel().getValueAt(modelRow, 0);
                    Date dateFromColumn = null;
                    try {
                        dateFromColumn = (Date) formatter.parseObject(dateVisitColumn);
                    } catch (ParseException e) {
                        LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
                    }

                    if (dateFromColumn != null && dateFromColumn.compareTo(new Date()) == -1) {
                        component.setEnabled(false);
                    }
                }
                return component;
            }
        };
        setupTable(table);
        return table;
    }

    private void setupTable(JTable table) {
        table.getSelectionModel().addListSelectionListener(this);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        TableColumn visitDateColumn = table.getColumnModel().getColumn(0);
        visitDateColumn.setPreferredWidth(80);
        TableColumn doctorColumn = table.getColumnModel().getColumn(1);
        doctorColumn.setPreferredWidth(300);
        TableColumn reservationDateColumn = table.getColumnModel().getColumn(2);
        reservationDateColumn.setPreferredWidth(80);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        reservationsTable.setFocusable(false);

        Reservation selectedReservation = getSelectedReservation();
        if (selectedReservation != null) {
            if (selectedReservation.getDateVisit() != null && selectedReservation.getDateVisit().compareTo(new Date()) == -1) {
                reservationsTable.clearSelection();
            }
        }
    }

    public void updateTable(Reservation reservation) {
        if (reservation.isPersisted()) {
            editedReservation(reservation);
        } else {
            insertedNewReservation(reservation);
        }
    }

    @Override
    public void insertedNewReservation(Reservation reservation) {
        tableModel.insertItem(reservation);
        tableModel.refreshModel();

        tableModel.selectItem(reservation, reservationsTable.getSelectionModel());
    }

    @Override
    public void editedReservation(Reservation reservation) {
        tableModel.refreshModel();

        tableModel.selectItem(reservation, reservationsTable.getSelectionModel());
    }

    @Override
    public void deletedReservation(Reservation reservation) {
        tableModel.deleteItem(reservation);
        tableModel.refreshModel();
    }
}
