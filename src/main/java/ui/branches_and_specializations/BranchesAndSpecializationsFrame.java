package ui.branches_and_specializations;

import ui.branches_and_specializations.Branches.BranchesPanel;
import ui.branches_and_specializations.Specializations.SpecializationsPanel;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * Date: 27.05.12
 * Time: 16:36
 *
 * @author Daniel Michalski
 */
public class BranchesAndSpecializationsFrame extends JDialog {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 180;

    // Tworzenie ramki
    public BranchesAndSpecializationsFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Oddziały i specjalizacje");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

    // Inicjalizacja komponentów
    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Oddziały", new BranchesPanel());
        tabbedPane.add("Specializacje", new SpecializationsPanel());

        add(tabbedPane);
    }

}
