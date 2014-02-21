package ui.patient.update;

import patient.PatientCard;

public interface OnUpdatePatientListener {
    public void insertedNewPatient(PatientCard patientCard);

    public void editedPatient(PatientCard patientCard);

    public void deletedPatient(PatientCard patientCard);
}
