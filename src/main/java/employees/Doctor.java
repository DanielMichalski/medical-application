package employees;

import util.Worker;
import work.Branch;
import work.Specialization;

/**
 * @author Daniel Michalski
 */
public class Doctor extends Worker {

    private Branch branch;

    private Specialization specialization;

    private AccountType accountType;

    public Doctor(Integer id, String firstName, String lastName, String address, String phoneNo, String eMail, String login, String password, Branch branch, Specialization specialization) {
        super(id, firstName, lastName, address, phoneNo, eMail, login, password);
        this.branch = branch;
        this.specialization = specialization;
        accountType = AccountType.DOCTOR;
    }

    public Doctor(String firstName, String lastName, String address, String phoneNo, String eMail, String login, String password, Branch branch, Specialization specialization) {
        super(firstName, lastName, address, phoneNo, eMail, login, password);
        this.branch = branch;
        this.specialization = specialization;
        accountType = AccountType.DOCTOR;
    }

    public static Doctor nullObject() {
        return new Doctor(null, "brak", "brak", "brak", "brak", "brak", "brak", "brak", Branch.nullObject(), Specialization.nullObject());
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public Specialization getSpecialization() {
        return specialization;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public Branch getBranch() {
        return branch;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNo='" + getPhoneNo() + '\'' +
                ", eMail='" + geteMail() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", branch=" + branch +
                ", specialization=" + specialization +
                "}\n";
    }
}