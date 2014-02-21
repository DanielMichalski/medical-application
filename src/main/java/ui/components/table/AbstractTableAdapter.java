package ui.components.table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class AbstractTableAdapter<T> extends AbstractTableModel implements OnUpdateTableListener<T> {

    private List<String> columnNames;

    protected List<T> items;

    protected abstract List<String> getRowByItem(T item);

    protected abstract List<String> getColumnNames();


    public AbstractTableAdapter(List<T> items) {
        this.columnNames = getColumnNames();
        this.items = items;
        validateColumnsForTable(columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T item = items.get(rowIndex);
        List<String> rowItems = getRowByItem(item);
        validateRowForTable(rowItems);
        return rowItems.get(columnIndex);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }

    @Deprecated
    public void editItem(T item) {
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    private void validateColumnsForTable(List<String> columnNames) {
        if (columnNames == null || columnNames.isEmpty()) {
            try {
                throw new DifferentAmountObjectsException("nie zdefiniowano nazw kolumn w " + this.getClass());
            } catch (DifferentAmountObjectsException differentAmountObjectsException) {
                differentAmountObjectsException.printStackTrace();
            }
        }
    }

    private void validateRowForTable(List<String> rowItems) {
        if (columnNames.size() != rowItems.size()) {
            try {
                throw new DifferentAmountObjectsException("rozna ilosc obiektow w adapterze tabeli (ilosc kolumn != ilosc obiektow w wierszu)");
            } catch (DifferentAmountObjectsException differentAmountObjectsException) {
                differentAmountObjectsException.printStackTrace();
            }
        }
    }

    public void selectItem(T item, ListSelectionModel listSelectionModel) {
        int index = items.indexOf(item);
        listSelectionModel.setSelectionInterval(index, index);
    }

    //metody do odswiezania tabeli po modyfikacji jej obiektów
    @Override
    public void insertItem(T item) {
        items.add(item);
        fireTableDataChanged();
    }

    public void editItem(T oldItem, T newItem) {
        int index = items.indexOf(oldItem);
        items.set(index, newItem);
    }

    @Override
    public void deleteItem(T item) {
        items.remove(item);
    }

    @Override
    public void changeAllItems(List<T> items) {
        this.items = items;
        fireTableDataChanged();
    }

}
