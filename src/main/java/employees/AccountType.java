package employees;

public enum AccountType {
    DOCTOR(1, "Doktor"),
    RECEPTIONIST(2, "Recepcjonista"),
    ADMIN(3, "Admin");

    private int id;
    private String description;

    AccountType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return description;
    }
}
