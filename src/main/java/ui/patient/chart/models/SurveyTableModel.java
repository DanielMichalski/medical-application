package ui.patient.chart.models;

import patient.PatientCard;
import patient.Survey;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SurveyTableModel extends AbstractTableModel {
    private String[] columnNames = new String[]{"Pacjent", "Temperatura"};
    private List<Survey> surveys;
    private PatientCard patientCard;

    @Override
    public String getColumnName(int column) {
        if (column == 0)
            return columnNames[column] + ": " + patientCard.getFirstName() + " " + patientCard.getLastName();
        else
            return columnNames[column];
    }

    public SurveyTableModel(PatientCard patient, List<Survey> surveys) {
        super();
        this.patientCard = patient;
        if (surveys != null) {
            this.surveys = surveys;
        } else {
            this.surveys = new ArrayList<Survey>();
        }
    }

    @Override
    public int getRowCount() {
        return surveys.size();
    }

    public void addSurvey(Survey meaSurement) {
        surveys.add(meaSurement);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Survey meaSurement = surveys.get(rowIndex);

        switch (columnIndex) {
            case 0:
                SimpleDateFormat dateFormatf = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormatf.format(surveys.get(rowIndex).getDate());
            case 1:
                DecimalFormat decimalFormat = new DecimalFormat("0.0");
                return decimalFormat.format(surveys.get(rowIndex).getTemperature());
            default:
                return "";
        }

    }
}
