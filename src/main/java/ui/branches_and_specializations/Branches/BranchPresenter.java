package ui.branches_and_specializations.Branches;

import database.dao.BranchDao;
import database.dao.DaoFactory;
import ui.components.comboBox.ComboBoxBranchAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created with IntelliJ IDEA.
 * Date: 27.05.12
 * Time: 17:12
 *
 * @author Daniel Michalski
 */
public class BranchPresenter implements Runnable {
    private GlownyPanel glownyPanel;
    private DolnyPanel dolnyPanel;
    private JComboBox branchComboBox;
    private JButton dodajButton, edytujButton;
    private BranchDao branchDao;
    private ComboBoxBranchAdapter comboBoxBranchAdapter;

    public BranchPresenter(GlownyPanel _glownyPanel, DolnyPanel _dolnyPanel) {


        Thread threadBranches = new Thread(this);
        threadBranches.start();

        glownyPanel = _glownyPanel;
        dolnyPanel = _dolnyPanel;

        dodajButton = dolnyPanel.getDodajButton();
        dodajButton.addActionListener(new dodajButtonListener());
        edytujButton = dolnyPanel.getEdytujButton();
        edytujButton.addActionListener(new edytujButtonListener());
    }

    @Override
    public void run() {
        DaoFactory factory = new DaoFactory();
        branchDao = factory.getBranchDAO();
        comboBoxBranchAdapter = createCbBranchAdapter(factory);

        branchComboBox = glownyPanel.getOddzialyComboBox();
        branchComboBox.setModel(getComboBoxBranchAdapter());
    }

    class dodajButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddAndEditBranchFrame frame = new AddAndEditBranchFrame(null, BranchPresenter.this);
            frame.setVisible(true);
        }
    }

    class edytujButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddAndEditBranchFrame frame = new AddAndEditBranchFrame(comboBoxBranchAdapter.getSelectedBranch(), BranchPresenter.this);
            frame.setVisible(true);
        }
    }

    public void refreshComboBox() {
        DaoFactory factory = new DaoFactory();
        comboBoxBranchAdapter = createCbBranchAdapter(factory);
        branchComboBox.setModel(comboBoxBranchAdapter);
    }

    public ComboBoxBranchAdapter createCbBranchAdapter(DaoFactory factory) {
        BranchDao branchDao = factory.getBranchDAO();
        return new ComboBoxBranchAdapter(branchDao.getAllBranches());
    }

    public ComboBoxBranchAdapter getComboBoxBranchAdapter() {
        return comboBoxBranchAdapter;
    }
}






