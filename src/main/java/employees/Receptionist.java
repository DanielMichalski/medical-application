package employees;


import util.Worker;
import work.Branch;
import work.Specialization;

/**
 * @author Daniel Michalski
 */
public class Receptionist extends Worker {

    private Branch branch;

    private AccountType accountTypes;

    public Receptionist(Integer id, String firstName, String lastName, String address, String phoneNo, String eMail, String login, String password, Branch branch) {
        super(id, firstName, lastName, address, phoneNo, eMail, login, password);
        this.branch = branch;
        accountTypes = AccountType.RECEPTIONIST;
    }

    public Receptionist(String firstName, String lastName, String address, String phoneNo, String eMail, String login, String password, Branch branch) {
        super(firstName, lastName, address, phoneNo, eMail, login, password);
        this.branch = branch;
        accountTypes = AccountType.RECEPTIONIST;
    }

    public static Receptionist nullObject() {
        return new Receptionist(null, "brak", "brak", "brak", "brak", "brak", "brak", "brak", Branch.nullObject());
    }

    @Override
    public AccountType getAccountType() {
        return accountTypes;
    }

    @Override
    public Specialization getSpecialization() {
        return Specialization.nullObject();
    }

    @Override
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNo='" + getPhoneNo() + '\'' +
                ", eMail='" + geteMail() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                "branch=" + branch +
                "}\n";
    }
}