package util;


import employees.AccountType;
import work.Branch;
import work.Specialization;

/**
 * @author Daniel Michalski
 */

public abstract class Worker extends Person implements Comparable<Worker> {

    private String login;
    private String password;

    public abstract AccountType getAccountType();

    public abstract Specialization getSpecialization();

    public abstract Branch getBranch();


    public Worker(Integer id, String firstName, String lastName, String address, String phoneNo, String eMail, String login, String password) {
        super(id, firstName, lastName, address, phoneNo, eMail);
        this.login = login;
        this.password = password;
    }

    public Worker(String firstName, String lastName, String address, String phoneNo, String eMail, String login, String password) {
        super(firstName, lastName, address, phoneNo, eMail);
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPersisted() {
        return super.getId() != null;
    }

    @Override
    public int compareTo(Worker o) {
        return getLastName().compareTo(o.getLastName());
    }

    public static Worker nullObject() {
        return new Worker("", "", "", "", "", "", "") {
            @Override
            public AccountType getAccountType() {
                return null;
            }

            @Override
            public Specialization getSpecialization() {
                return null;
            }

            @Override
            public Branch getBranch() {
                return null;
            }
        };
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNo='" + getPhoneNo() + '\'' +
                ", eMail='" + geteMail() + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                "}\n";
    }


}