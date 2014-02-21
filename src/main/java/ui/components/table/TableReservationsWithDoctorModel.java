package ui.components.table;


import employees.Doctor;
import patient.Reservation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TableReservationsWithDoctorModel extends AbstractTableAdapter<Reservation> {

    private static String[] columnNames = {"Data wizyty", "Lekarz", "Data rezerwacji"};

    private Format formatter;

    public TableReservationsWithDoctorModel(List<Reservation> items) {
        super(items);
        formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    }

    public Reservation getReservationByRowIndex(int rowIndex) {
        return items.get(rowIndex);
    }

    @Override
    protected List<String> getRowByItem(Reservation reservation) {
        Doctor doctor = reservation.getDoctor();
        String visitDate = getTimeFromDate(reservation.getDateVisit());
        String doctorName = doctor.getFirstName() + " " + doctor.getLastName() + " - " + doctor.getSpecialization().getName();
        String reservationsDate = getTimeFromDate(reservation.getDateReservation());

        String[] rowItems = {visitDate, doctorName, reservationsDate};
        return Arrays.asList(rowItems);
    }

    private String getTimeFromDate(Date date) {
        return formatter.format(date);
    }

    public void refreshModel() {
        Collections.sort(items);
        fireTableDataChanged();
    }

    @Override
    protected List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

}