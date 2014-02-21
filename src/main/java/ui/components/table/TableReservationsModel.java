package ui.components.table;

import patient.PatientCard;
import patient.Reservation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TableReservationsModel extends AbstractTableAdapter<Reservation> {

    private static String[] columnNames = {"Godzina", "Pacjent"};
    private Format formatter;

    public TableReservationsModel(List<Reservation> items) {
        super(items);
        formatter = new SimpleDateFormat("HH:mm");
    }

    public Reservation getReservationByRowIndex(int rowIndex) {
        return items.get(rowIndex);
    }

    @Override
    protected List<String> getRowByItem(Reservation reservation) {
        PatientCard patientCard = reservation.getPatientCard();
        String timeVisit = getTimeFrom(reservation.getDateVisit());
        String patientName = patientCard.getFirstName() + " " + patientCard.getLastName();

        String[] rowItems = {timeVisit, patientName};
        return Arrays.asList(rowItems);
    }

    private String getTimeFrom(Date date) {
        return formatter.format(date);
    }

    @Override
    protected List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

}