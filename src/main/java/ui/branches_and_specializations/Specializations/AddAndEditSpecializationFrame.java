package ui.branches_and_specializations.Specializations;

import database.dao.DaoFactory;
import database.dao.SpecializationDao;
import ui.components.FillTitleBorder;
import ui.branches_and_specializations.components.MyTextComponent;
import util.Const;
import util.validate.ValidationException;
import work.Specialization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * Date: 27.05.12
 * Time: 20:02
 *
 * @author Daniel Michalski
 */
public class AddAndEditSpecializationFrame extends JDialog {
    public static final int DEFAULT_WIDTH = 270;
    public static final int DEFAULT_HEIGHT = 130;
    private MyTextComponent specializacjaTextField;
    private Specialization selectedSpecialization;
    private SpecializationPresenter specializationPresenter;

    // Tworzenie ramki
    public AddAndEditSpecializationFrame(Specialization aSelectedSpecialization, SpecializationPresenter aSpecializationPresenter) {
        selectedSpecialization = aSelectedSpecialization;
        specializationPresenter = aSpecializationPresenter;
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Dodaj specjalizacje");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Const.Colors.backgroundColor);
        setModal(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e)
        {
            JOptionPane.showMessageDialog(this,
                    "Wystąpił błąd podczas ładowania wyglądu okna: " + e,
                    "Uwaga",
                    JOptionPane.ERROR_MESSAGE);
        }

        initComponents();
    }


    private void initComponents() {
        JPanel glownyPanel = new JPanel();
        glownyPanel.setBackground(Const.Colors.BACKGROUND_PANEL);
        glownyPanel.setLayout(null);
        glownyPanel.setBorder(new FillTitleBorder("Specjalizacje"));

        specializacjaTextField = new MyTextComponent("Specjalizacja", 6);
        specializacjaTextField.setBounds(10, 25, 240, 20);

        JLabel errorLabel = specializacjaTextField.getErrorLabel();
        errorLabel.setBounds(10, 45, 260, 20);

        if (selectedSpecialization != null)
            specializacjaTextField.setText(selectedSpecialization.getName());

        glownyPanel.add(specializacjaTextField);
        glownyPanel.add(specializacjaTextField.getErrorLabel());

        JPanel dolnyPanelZPrzyciskami = new JPanel();
        dolnyPanelZPrzyciskami.setBackground(Const.Colors.BACKGROUND_PANEL);

        JButton zapiszButton = new JButton("Zapisz");
        zapiszButton.addActionListener(new zapiszButtonListener());

        JButton anulujButton = new JButton("Anuluj");
        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        dolnyPanelZPrzyciskami.add(zapiszButton);
        dolnyPanelZPrzyciskami.add(anulujButton);

        add(glownyPanel, BorderLayout.CENTER);
        add(dolnyPanelZPrzyciskami, BorderLayout.SOUTH);
    }

    private void ZapiszSpecjalizacjeDoBazy() {
        DaoFactory factory = new DaoFactory();
        SpecializationDao specializationDao = new SpecializationDao(factory);
        specializationDao.saveSpecialization(new Specialization(null, specializacjaTextField.getText()));
        specializationPresenter.refreshComboBox();
        JOptionPane.showMessageDialog(this, "Dodałeś specjalizacje \"" + specializacjaTextField.getText()
                + "\" do bazy danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void EdytujOddzialWBazie() {
        String nazwaSpecjalizacji = selectedSpecialization.getName();
        DaoFactory factory = new DaoFactory();
        SpecializationDao specializationDao = new SpecializationDao(factory);
        selectedSpecialization.setName(specializacjaTextField.getText());
        specializationDao.saveSpecialization(selectedSpecialization);
        specializationPresenter.refreshComboBox();
        JOptionPane.showMessageDialog(this, "Edytowałeś specializacje \"" + nazwaSpecjalizacji +
                "\" w bazie danych\nTeraz ma nazwę \"" + specializacjaTextField.getText() + "\"", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    class zapiszButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                specializacjaTextField.check();
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                if (selectedSpecialization == null)
                    ZapiszSpecjalizacjeDoBazy();
                else
                    EdytujOddzialWBazie();
            } catch (ValidationException e1) {
            }
        }
    }
}