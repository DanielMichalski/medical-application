package ui.components.comboBox;

import work.Branch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboBoxBranchAdapter extends AbstractComboBoxModel<Branch> {

    public ComboBoxBranchAdapter(List<Branch> items) {
        super(items);
    }

    public Branch getSelectedBranch() {
        return selectedItem;
    }

    public void setSelectedItem(Branch selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    protected Map<String, Branch> initObjectByString() {
        Map<String, Branch> branchMap = new HashMap<String, Branch>();
        for (Branch branch : items) {
            branchMap.put(branch.getName(), branch);
        }
        return branchMap;
    }

    @Override
    protected String getItemForList(Branch item) {
        return item.getName();
    }


}
