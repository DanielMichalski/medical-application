package work;

import java.util.List;

public class SpecializationsInBranch {

    private Integer id;

    private Branch branch;

    private List<Specialization> specializations;

    public SpecializationsInBranch(Integer id, Branch branch, List<Specialization> specializations) {
        this.id = id;
        this.branch = branch;
        this.specializations = specializations;
    }

    public SpecializationsInBranch(Branch branch, List<Specialization> specializations) {
        this.specializations = specializations;
        this.branch = branch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public void addSpecialization(Specialization specialization) {
        if (specializations != null) {
            specializations.add(specialization);
        }
    }

    public void removeSpecialization(Specialization specialization) {
        if (specializations != null) {
            specializations.remove(specialization);
        }
    }

    @Override
    public String toString() {
        return "SpecializationsInBranch{" +
                "id=" + id +
                ", branch=" + branch +
                ", specializations=" + specializations +
                '}';
    }
}