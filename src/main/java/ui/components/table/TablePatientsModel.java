package ui.components.table;

import patient.ConverterPatientAddress;
import patient.PatientCard;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TablePatientsModel extends AbstractTableAdapter<PatientCard> {

    private static String[] columnNames =
            {"Nazwisko", "Imie", "PESEL", "Adres"};

    public TablePatientsModel(List<PatientCard> items) {
        super(items);
    }

    public PatientCard getPatientCardByRowIndex(int rowIndex) {
        return items.get(rowIndex);
    }

    @Override
    protected List<String> getRowByItem(PatientCard patientCard) {
        String firstName = patientCard.getFirstName();
        String lastName = patientCard.getLastName();
        String address = ConverterPatientAddress.getFullAddress(patientCard);
        String PESEL = "" + patientCard.getPESEL();

        String[] rowItems = {lastName, firstName, PESEL, address};
        return Arrays.asList(rowItems);
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
