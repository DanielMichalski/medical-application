package ui.components.comboBox;

import employees.Doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Daniel Michalski
 */

public class ComboBoxDoctorAdapter extends AbstractComboBoxModel<Doctor> {
    public ComboBoxDoctorAdapter(List<Doctor> items) {
        super(items);
    }

    public Doctor getSelectedDoctor() {
        return selectedItem;
    }

    @Override
    protected Map<String, Doctor> initObjectByString() {
        Map<String, Doctor> doctorMap = new HashMap<String, Doctor>();
        for (Doctor doctor : items) {
            doctorMap.put(getComboItemFrom(doctor), doctor);
        }
        return doctorMap;
    }

    @Override
    protected String getItemForList(Doctor doctor) {
        return getComboItemFrom(doctor);
    }

    private String getComboItemFrom(Doctor doctor) {
        return "" + doctor.getFirstName() + " " + doctor.getLastName() + " - " + doctor.getSpecialization().getName();
    }
}
