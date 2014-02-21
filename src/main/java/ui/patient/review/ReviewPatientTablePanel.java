package ui.patient.review;

import patient.PatientCard;
import ui.components.FillTitleBorder;
import ui.components.table.TablePatientsModel;
import ui.patient.update.OnUpdatePatientListener;
import util.Const;
import util.finder.WordsFinder;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ReviewPatientTablePanel extends JScrollPane implements OnUpdatePatientListener {

    private JTable tableWithPatients;
    private TablePatientsModel tableModel;

    private DefaultTableCellRenderer centerRenderer;
    private WordsFinder wordsFinder;

    public ReviewPatientTablePanel(WordsFinder wordsFinder, List<PatientCard> patientCards) {
        this.wordsFinder = wordsFinder;
        setBorder(new FillTitleBorder("Lista pacjentów"));
        setBackground(Const.Colors.BACKGROUND_PANEL);
        getViewport().setBackground(Const.Colors.FILL_BORDER);

        init(patientCards);
        createContent();
    }

    private void init(List<PatientCard> patientCards) {
        tableModel = new TablePatientsModel(patientCards);
        tableWithPatients = createPatientTable(tableModel);

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    }

    private void createContent() {
        getViewport().removeAll();
        getViewport().add(tableWithPatients);
    }

    public PatientCard getSelectedPatientCard() {
        PatientCard selectedPatientCard = null;
        int selectedRowIndex = tableWithPatients.getSelectedRow();
        if (selectedRowIndex >= 0) {
            TablePatientsModel tableModel = (TablePatientsModel) tableWithPatients.getModel();
            selectedPatientCard = tableModel.getPatientCardByRowIndex(selectedRowIndex);
        }
        return selectedPatientCard;
    }

    @Override
    public void insertedNewPatient(PatientCard patientCard) {
        tableModel.insertItem(patientCard);
        tableModel.refreshModel();
        tableModel.selectItem(patientCard, tableWithPatients.getSelectionModel());
    }

    @Override
    public void editedPatient(PatientCard patientCard) {
        tableModel.editItem(patientCard);
        tableModel.refreshModel();
        tableModel.selectItem(patientCard, tableWithPatients.getSelectionModel());
    }

    @Override
    public void deletedPatient(PatientCard patientCard) {
        tableModel.deleteItem(patientCard);
        tableModel.refreshModel();
    }

    private JTable createPatientTable(TableModel tableModel) {
        JTable table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    int modelRow = convertRowIndexToModel(row);

                    String lastNameColumn = (String) getModel().getValueAt(modelRow, 0);
                    String firstNameColumn = (String) getModel().getValueAt(modelRow, 1);
                    String peselColumn = (String) getModel().getValueAt(modelRow, 2);
                    String addressColumn = (String) getModel().getValueAt(modelRow, 3);

                    if (wordsFinder.containsAtLeastOneSequence(peselColumn, lastNameColumn, firstNameColumn, addressColumn)) {
                        component.setBackground(Const.Colors.SEARCHING_TEXT);
                    } else {
                        component.setBackground(Color.WHITE);
                    }

                }
                return component;
            }
        };
        setupTable(table);
        return table;
    }

    private void setupTable(JTable table) {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        TableColumn lastNameColumn = table.getColumnModel().getColumn(0);
        lastNameColumn.setPreferredWidth(80);
        TableColumn firstNameColumn = table.getColumnModel().getColumn(1);
        firstNameColumn.setPreferredWidth(80);
        TableColumn peselColumn = table.getColumnModel().getColumn(2);
        peselColumn.setPreferredWidth(80);
        TableColumn addressColumn = table.getColumnModel().getColumn(3);
        addressColumn.setPreferredWidth(180);
    }

}