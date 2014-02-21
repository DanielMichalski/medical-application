package ui.branches_and_specializations.Specializations;

import database.dao.DaoFactory;
import database.dao.SpecializationDao;
import ui.components.comboBox.ComboBoxSpecializationAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * Date: 27.05.12
 * Time: 17:30
 *
 * @author Daniel Michalski
 */
public class SpecializationPresenter {
    private SpecializationGlownyPanel glownyPanel;
    private SpecializationDolnyPanel dolnyPanel;
    private JComboBox specializationJComboBox;
    private JButton dodajButton, edytujButton;
    private SpecializationDao specializationDao;
    private ComboBoxSpecializationAdapter comboBoxSpecializationAdapter;

    public SpecializationPresenter(SpecializationGlownyPanel _glownyPanel, SpecializationDolnyPanel _dolnyPanel) {
        DaoFactory factory = new DaoFactory();
        specializationDao = factory.getSpecializationDAO();
        comboBoxSpecializationAdapter = createCbSpecializationAdapter(factory);

        glownyPanel = _glownyPanel;
        dolnyPanel = _dolnyPanel;

        specializationJComboBox = glownyPanel.getOddzialyComboBox();
        specializationJComboBox.setModel(comboBoxSpecializationAdapter);
        specializationJComboBox.addActionListener(new specializationComboBoxListener());

        dodajButton = dolnyPanel.getDodajButton();
        dodajButton.addActionListener(new dodajButtonListener());
        edytujButton = dolnyPanel.getEdytujButton();
        edytujButton.addActionListener(new edytujButtonListener());
    }

    class specializationComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class dodajButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddAndEditSpecializationFrame frame = new AddAndEditSpecializationFrame(null, SpecializationPresenter.this);
            frame.setVisible(true);
        }
    }

    class edytujButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddAndEditSpecializationFrame frame = new AddAndEditSpecializationFrame(comboBoxSpecializationAdapter.getSelectedSpecialization(), SpecializationPresenter.this);
            frame.setVisible(true);
        }
    }


    public void refreshComboBox() {
        DaoFactory factory = new DaoFactory();
        specializationDao = factory.getSpecializationDAO();
        comboBoxSpecializationAdapter = createCbSpecializationAdapter(factory);
        specializationJComboBox.setModel(comboBoxSpecializationAdapter);
    }

    public ComboBoxSpecializationAdapter createCbSpecializationAdapter(DaoFactory factory) {
        SpecializationDao specializationDao = factory.getSpecializationDAO();
        return new ComboBoxSpecializationAdapter(specializationDao.getAllSpecializations());
    }

    public ComboBoxSpecializationAdapter getComboBoxSpecializationAdapter() {
        return comboBoxSpecializationAdapter;
    }

}
