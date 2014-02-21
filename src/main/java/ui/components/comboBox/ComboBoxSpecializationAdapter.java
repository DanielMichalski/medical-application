package ui.components.comboBox;

import work.Specialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboBoxSpecializationAdapter extends AbstractComboBoxModel<Specialization> {


    public ComboBoxSpecializationAdapter(List<Specialization> items) {
        super(items);
    }

    public Specialization getSelectedSpecialization() {
        return selectedItem;
    }

    public void setSelectedItem(Specialization selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    protected Map<String, Specialization> initObjectByString() {
        Map<String, Specialization> specializationMap = new HashMap<String, Specialization>();
        for (Specialization specialization : items) {
            specializationMap.put(specialization.getName(), specialization);
        }
        return specializationMap;
    }

    @Override
    protected String getItemForList(Specialization item) {
        return item.getName();
    }

}
