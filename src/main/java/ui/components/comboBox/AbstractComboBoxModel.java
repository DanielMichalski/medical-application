package ui.components.comboBox;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Klasa ktora tworzy wzorzec tudziez model ktory bedzie wykorzysytywany w kilku klasach
 * a wiec innymi slowy jest to klasa z czescia wspolna innych klas w generykach czyli <T> jest symbol
 * T-obiektu pisze się T chyba od Type ale pewnosci nie mam
 * pozniej gdy bedziemy rozszerzali bądź implementowali ta klase abstrakcynja w generykach bedziemy
 * musieli podac nasz konkretny obiekt
 */
public abstract class AbstractComboBoxModel<T> extends AbstractListModel implements javax.swing.ComboBoxModel {

    protected T selectedItem;

    protected List<T> items;

    private Map<String, T> itemsTByString;

    public AbstractComboBoxModel(List<T> items) {
        this.items = items;
        itemsTByString = initObjectByString();
        trySettleDefaultObject(items);
    }

    private void trySettleDefaultObject(List<T> items) throws NoSuchElementException{
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            selectedItem = items.get(0);
        }
    }

    /**
     * @return mape ktora ma strukture
     *         Key - String dla listy JComboBox
     *         Value - Obiekt T
     */
    protected abstract Map<String, T> initObjectByString();

    /**
     * @return metoda zwraca String ktory symbolizuje obiekt T i bedzie wyswietlany w liscie JComboBox
     */
    protected abstract String getItemForList(T item);


    /**
     * @return zwraca wybrany w JComboBox obiekt
     */
    public Object getSelectedItem() {
        return getItemForList(selectedItem);
    }

    /**
     * @param item - jest to obiekt z listy JComboBox czyli string, dlatego aby uzyskac pelny obiekt
     *             pobieramy go z wczesniej utworzonej mapy obiectTByString
     */
    public void setSelectedItem(Object item) {
        selectedItem = itemsTByString.get(item);
    }

    /**
     * @param position - pozycja itemu w liscie JComboBox
     * @return tak jak w dokumentacji metody getItemForList()
     */
    public Object getElementAt(int position) {
        return getItemForList(getItemByPosition(position));
    }

    private T getItemByPosition(int position) {
        return items.get(position);
    }

    public int getSize() {
        return items.size();
    }
}
