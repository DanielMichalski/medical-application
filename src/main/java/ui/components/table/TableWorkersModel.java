package ui.components.table;

import employees.ConverterWorkerAdress;
import util.Worker;

import java.util.Arrays;
import java.util.List;

public class TableWorkersModel extends AbstractTableAdapter<Worker> {

    private static String[] columnNames =
            {"Imie", "Nazwisko", "Adres", "Specjalizacja", "Oddzial", "Rodzaj konta"};


    public TableWorkersModel(List<Worker> items) {
        super(items);
    }

    public Worker getWorkerByRowIndex(int rowIndex) {
        return items.get(rowIndex);
    }

    @Override
    protected List<String> getRowByItem(Worker worker) {
        String firstName = worker.getFirstName();
        String lastName = worker.getLastName();

        String[] splitAddress = ConverterWorkerAdress.getSplitAddressInTable(worker);
        String address = splitAddress[2];

        String phoneNo = worker.getPhoneNo();
        String specializationName = worker.getSpecialization().getName();
        String branchAddress = worker.getBranch().getName();
        String accountType = worker.getAccountType().toString();

        String[] rowItems = {firstName, lastName, address, specializationName, branchAddress, accountType};
        return Arrays.asList(rowItems);
    }

    @Override
    protected List<String> getColumnNames() {
        return Arrays.asList(columnNames);
    }

    public void refreshModel() {
        fireTableDataChanged();
    }

}
