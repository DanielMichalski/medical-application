package ui.worker.review;

import ui.components.FillTitleBorder;
import ui.components.table.TableWorkersModel;
import ui.worker.update.OnUpdateWorkerListener;
import util.Const;
import util.Worker;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ReviewWorkerTablePanel extends JScrollPane implements OnUpdateWorkerListener {

    private JTable tableWithWorkers;
    private TableWorkersModel tableModel;

    private DefaultTableCellRenderer centerRenderer;

    public ReviewWorkerTablePanel(List<Worker> workers) {
        setBorder(new FillTitleBorder("Lista pracowników"));
        setBackground(Const.Colors.BACKGROUND_PANEL);
        getViewport().setBackground(Const.Colors.FILL_BORDER);

        init(workers);
        createContent();
    }

    private void init(List<Worker> workers) {
        tableModel = new TableWorkersModel(workers);
        tableWithWorkers = createWorkerTable(tableModel);

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    }

    private void createContent() {
        getViewport().removeAll();
        getViewport().add(tableWithWorkers);
    }

    public Worker getSelectedWorker() {
        Worker seletedWorker = null;
        int selectedRowIndex = tableWithWorkers.getSelectedRow();
        if (selectedRowIndex >= 0) {
            TableWorkersModel tableModel = (TableWorkersModel) tableWithWorkers.getModel();
            seletedWorker = tableModel.getWorkerByRowIndex(selectedRowIndex);
        }
        return seletedWorker;
    }

    @Override
    public void insertedNewWorker(Worker worker) {
        tableModel.insertItem(worker);
        tableModel.refreshModel();
        tableModel.selectItem(worker, tableWithWorkers.getSelectionModel());
    }

    @Override
    public void editedWorker(Worker oldWorker, Worker newWorker) {
        tableModel.editItem(oldWorker, newWorker);
        tableModel.refreshModel();
        //tableModel.selectItem(worker, tableWithWorkers.getSelectionModel());
    }

    @Override
    public void deletedWorker(Worker worker) {
        tableModel.deleteItem(worker);
        tableModel.refreshModel();
    }

    private JTable createWorkerTable(TableModel tableModel) {
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

                    component.setBackground(Color.WHITE);

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
        lastNameColumn.setPreferredWidth(70);
        TableColumn firstNameColumn = table.getColumnModel().getColumn(1);
        firstNameColumn.setPreferredWidth(70);
        TableColumn peselColumn = table.getColumnModel().getColumn(2);
        peselColumn.setPreferredWidth(80);
        TableColumn addressColumn = table.getColumnModel().getColumn(3);
        addressColumn.setPreferredWidth(160);
    }

}