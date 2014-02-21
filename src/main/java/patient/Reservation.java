package patient;

import employees.Doctor;

import java.util.Date;

public class Reservation implements Comparable<Reservation> {

    private Integer id;

    private Doctor doctor;

    private Date dateReservation;

    private Date dateVisit;

    private PatientCard patientCard;

    public Reservation(Integer id, Doctor doctor, PatientCard patientCard, Date dateReservation, Date dateVisit) {
        this.id = id;
        this.doctor = doctor;
        this.patientCard = patientCard;
        this.dateReservation = dateReservation;
        this.dateVisit = dateVisit;
    }

    public Reservation(Doctor doctor, PatientCard patientCard, Date dateReservation, Date dateVisit) {
        this.doctor = doctor;
        this.patientCard = patientCard;
        this.dateReservation = dateReservation;
        this.dateVisit = dateVisit;
    }

    public static Reservation nullObject(Date dateVisit) {
        return new Reservation(null, PatientCard.nullObject(), null, dateVisit);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    public PatientCard getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(PatientCard patientCard) {
        this.patientCard = patientCard;
    }

    public boolean isPersisted() {
        return id != null;
    }

    //w equals objekty typu Reservation porównuje tylko po dacie wizyty
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (dateVisit != null ? !dateVisit.equals(that.dateVisit) : that.dateVisit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return dateVisit != null ? dateVisit.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", dateReservation=" + dateReservation +
                ", dateVisit=" + dateVisit +
                ", patientCard=" + patientCard +
                '}';
    }

    @Override
    public int compareTo(Reservation o) {
        return getDateVisit().compareTo(o.getDateVisit());
    }
}
