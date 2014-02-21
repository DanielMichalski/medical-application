package employees;

import util.Worker;
import work.Branch;
import work.Specialization;

/**
 * @author Daniel Michalski
 */
public class Admin extends Worker {

    private AccountType accountTypes;

    public Admin(Integer id, String firstName, String lastName, String address, String phoneNo, String eMail, String login, String password) {
        super(id, firstName, lastName, address, phoneNo, eMail, login, password);
        accountTypes = AccountType.ADMIN;
    }

    public Admin(String firstName, String lastName, String address, String phoneNo, String eMail, String login, String password) {
        super(firstName, lastName, address, phoneNo, eMail, login, password);
        accountTypes = AccountType.ADMIN;
    }

    public static Admin nullObject() {
        return new Admin(null, "brak", "brak", "brak", "brak", "brak", "brak", "brak");
    }

    @Override
    public AccountType getAccountType() {
        return accountTypes;
    }

    public void setAccountType(AccountType accountType) {
        this.accountTypes = accountType;
    }

    @Override
    public Specialization getSpecialization() {
        return Specialization.nullObject();
    }

    @Override
    public Branch getBranch() {
        return Branch.nullObject();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNo='" + getPhoneNo() + '\'' +
                ", eMail='" + geteMail() + '\'' +
                ", login='" + getLogin() + '\'' +
                ", password='" + getPassword() + '\'' +
                "}\n";
    }
}