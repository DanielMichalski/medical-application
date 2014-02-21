package work;

public class Branch {

    private Integer id;

    private String name;

    public Branch(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Branch nullObject() {
        return new Branch(0, "brak");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPersisted() {
        return getId() != null;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
