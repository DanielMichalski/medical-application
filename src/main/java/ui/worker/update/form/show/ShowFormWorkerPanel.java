package ui.worker.update.form.show;

import ui.worker.update.form.WorkerAddressDataPanel;
import ui.worker.update.form.WorkerPersonalDataPanel;
import ui.worker.update.form.WorkerUserDataPanel;
import util.Const;
import util.Worker;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel Michalski
 * Date: 22.03.13
 */

public class ShowFormWorkerPanel extends JPanel {

    private WorkerAddressDataPanel addressPanel;
    private WorkerPersonalDataPanel personalPanel;
    private WorkerUserDataPanel userDataPanel;
    private ShowWorkerUserType workerTypePanel;

    public ShowFormWorkerPanel(JDialog parent, Worker worker) {
        super(new GridBagLayout());
        JDialog parent1 = parent;
        setBackground(Const.Colors.BACKGROUND_PANEL);

        init();
        fillForm(worker);
        createContent();
    }

    private void init() {
        personalPanel = new WorkerPersonalDataPanel();
        personalPanel.disableTFields();

        addressPanel = new WorkerAddressDataPanel();
        addressPanel.disableTFields();

        userDataPanel = new WorkerUserDataPanel();
        userDataPanel.disableAllFields();

        workerTypePanel = new ShowWorkerUserType();
    }

    private void createContent() {
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        gridConstraints.weightx = 1.0;

        settleGridConstrains(gridConstraints, 0, 0);
        add(personalPanel, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 1);
        add(addressPanel, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 2);
        add(userDataPanel, gridConstraints);

        settleGridConstrains(gridConstraints, 0, 3);
        add(workerTypePanel, gridConstraints);
    }

    private void settleGridConstrains(GridBagConstraints gridConstraints, int x, int y) {
        gridConstraints.gridx = x;
        gridConstraints.gridy = y;
    }

    public void fillForm(Worker worker) {
        personalPanel.fillPersonalDataForm(worker);
        addressPanel.fillAddressForm(worker);
        userDataPanel.fillPersonalDataForm(worker);
        workerTypePanel.fillWorkerTypeForm(worker);
    }
}
