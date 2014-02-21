package ui.branches_and_specializations.Branches;


import database.dao.BranchDao;
import database.dao.DaoFactory;
import ui.branches_and_specializations.components.MyTextComponent;
import ui.components.FillTitleBorder;
import util.Const;
import util.MyProgramLogger;
import util.validate.ValidationException;
import work.Branch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * @author Daniel Michalski
 */
public class AddAndEditBranchFrame extends JDialog {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    public static final int DEFAULT_WIDTH = 250;
    public static final int DEFAULT_HEIGHT = 130;
    private MyTextComponent oddzialTextField;
    private Branch selectedBranh;
    private BranchPresenter branchPresenter;

    // Tworzenie ramki
    public AddAndEditBranchFrame(Branch aSelectedBranch, BranchPresenter aBranchPresenter) {
        selectedBranh = aSelectedBranch;
        branchPresenter = aBranchPresenter;
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Dodaj oddział");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

        LOGGER.info("Utworzenie okienka Branches And Specializations");
    }

    private void initComponents() {
        JPanel glownyPanel = new JPanel();
        glownyPanel.setBackground(Const.Colors.BACKGROUND_PANEL);
        glownyPanel.setLayout(null);
        glownyPanel.setBorder(new FillTitleBorder("Oddziały"));

        oddzialTextField = new MyTextComponent("Oddział", 6);
        oddzialTextField.setBounds(10, 25, 220, 20);

        JLabel errorLabel = oddzialTextField.getErrorLabel();
        errorLabel.setBounds(10, 45, 220, 20);

        if (selectedBranh != null)
            oddzialTextField.setText(selectedBranh.getName());


        glownyPanel.add(oddzialTextField);
        glownyPanel.add(oddzialTextField.getErrorLabel());

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

    private void ZapiszOddzialDoBazy() {
        DaoFactory factory = new DaoFactory();
        BranchDao branchDao = new BranchDao(factory);
        branchDao.saveBranch(new Branch(null, oddzialTextField.getText()));
        branchPresenter.refreshComboBox();
        JOptionPane.showMessageDialog(this, "Dodałeś oddział \"" + oddzialTextField.getText()
                + "\" do bazy danych", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void EdytujOddzialWBazie() {
        String nazwaOddzialu = selectedBranh.getName();
        DaoFactory factory = new DaoFactory();
        BranchDao branchDao = new BranchDao(factory);
        selectedBranh.setName(oddzialTextField.getText());
        branchDao.saveBranch(selectedBranh);
        branchPresenter.refreshComboBox();
        JOptionPane.showMessageDialog(this, "Edytowałeś oddział \"" + nazwaOddzialu +
                "\" w bazie danych\nTeraz ma nazwę \"" + oddzialTextField.getText() + "\"", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    class zapiszButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                oddzialTextField.check();
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                if (selectedBranh == null)
                    ZapiszOddzialDoBazy();
                else
                    EdytujOddzialWBazie();
            } catch (ValidationException e1) {
                //setSize(new Dimension(DEFAULT_WIDTH, 112));
            }
        }
    }
}