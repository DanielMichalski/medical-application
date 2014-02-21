package ui.components.comboBox;

import util.Worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Daniel Michalski
 */

public class ComboBoxWorkerAdapter extends AbstractComboBoxModel<Worker> {
    public ComboBoxWorkerAdapter(List<Worker> items) {
        super(items);
    }

    public Worker getSelectedWorker() {
        return selectedItem;
    }


    @Override
    protected Map<String, Worker> initObjectByString() {
        Map<String, Worker> workerMap = new HashMap<String, Worker>();
        for (Worker worker : items) {
            workerMap.put(worker.getLogin(), worker);
        }
        return workerMap;
    }

    @Override
    protected String getItemForList(Worker item) {
        return item.getLogin();
    }
}
