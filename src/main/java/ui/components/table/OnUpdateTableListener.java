package ui.components.table;

import java.util.List;

public interface OnUpdateTableListener<T> {
    public void insertItem(T item);

    public void editItem(T item);

    public void editItem(T oldItem, T newItem);

    public void changeAllItems(List<T> items);

    public void deleteItem(T item);
}
