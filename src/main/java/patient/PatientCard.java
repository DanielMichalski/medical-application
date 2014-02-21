package patient;

import util.Person;

import java.util.List;

public class PatientCard extends Person implements Comparable<PatientCard> {

    private List<Reservation> reservations;
    private String PESEL;

    public PatientCard(String PESEL, String firstName, String lastName, String phoneNo, String address, String eMail) {
        super(firstName, lastName, address, phoneNo, eMail);
        this.PESEL = PESEL;
    }

    public PatientCard(Integer id, String PESEL, String firstName, String lastName, String address, String phoneNo, String eMail) {
        super(id, firstName, lastName, address, phoneNo, eMail);
        this.PESEL = PESEL;
    }

    public static PatientCard nullObject() {
        return new PatientCard(null, "", "", "", "", "", "");
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    @Override
    public String toString() {
        return "PatientCard{" +
                "id=" + getId() +
                ", PESEL=" + getPESEL() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNo='" + getPhoneNo() + '\'' +
                ", eMail='" + geteMail() + '\'' +
                ", reservation=" + reservations +
                '}';
    }

    @Override
    public int compareTo(PatientCard o) {
        return getLastName().compareTo(o.getLastName());
    }
}
