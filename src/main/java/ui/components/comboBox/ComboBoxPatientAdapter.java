package ui.components.comboBox;


import patient.PatientCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Daniel Michalski
 */

public class ComboBoxPatientAdapter extends AbstractComboBoxModel<PatientCard> {
    public ComboBoxPatientAdapter(List<PatientCard> items) {
        super(items);
    }

    public PatientCard getSelectedPatientCard() {
        return selectedItem;
    }

    @Override
    protected Map<String, PatientCard> initObjectByString() {
        Map<String, PatientCard> patientCardMap = new HashMap<String, PatientCard>();
        for (PatientCard patientCard : items) {
            patientCardMap.put(patientCard.getFirstName(), patientCard);
        }
        return patientCardMap;
    }

    @Override
    protected String getItemForList(PatientCard item) {
        return item.getFirstName();
    }
}
