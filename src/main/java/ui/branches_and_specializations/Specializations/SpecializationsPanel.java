package ui.branches_and_specializations.Specializations;

import ui.components.FillTitleBorder;
import util.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * Date: 27.05.12
 * Time: 16:48
 *
 * @author Daniel Michalski
 */

public class SpecializationsPanel extends JPanel {
    private SpecializationGlownyPanel specializationGlownyPanel;
    private SpecializationDolnyPanel specializationDolnyPanel;

    public SpecializationsPanel() {
        setLayout(new BorderLayout());
        InitComponents();

        SpecializationPresenter specializationPresenter = new SpecializationPresenter(specializationGlownyPanel, specializationDolnyPanel);
        setBackground(Const.Colors.BACKGROUND_PANEL);
    }

    private void InitComponents() {
        specializationGlownyPanel = new SpecializationGlownyPanel();
        specializationDolnyPanel = new SpecializationDolnyPanel();
        add(specializationGlownyPanel, BorderLayout.CENTER);
        add(specializationDolnyPanel, BorderLayout.SOUTH);
    }
}


class SpecializationGlownyPanel extends JPanel {
    private JComboBox specjalizacjaComboBox;

    public SpecializationGlownyPanel() {
        setBackground(Const.Colors.BACKGROUND_PANEL);
        setLayout(null);
        setBorder(new FillTitleBorder("Specjalizacje"));
        specjalizacjaComboBox = new JComboBox();
        specjalizacjaComboBox.setBounds(10, 40, 200, 20);

        add(specjalizacjaComboBox);
    }

    public JComboBox getOddzialyComboBox() {
        return specjalizacjaComboBox;
    }
}


class SpecializationDolnyPanel extends JPanel {
    private JButton dodajButton, edytujButton;

    public SpecializationDolnyPanel() {
        setBackground(Const.Colors.BACKGROUND_PANEL);
        dodajButton = new JButton("Dodaj");
        edytujButton = new JButton("Edytuj");
        add(dodajButton);
        add(edytujButton);
    }

    public JButton getDodajButton() {
        return dodajButton;
    }

    public JButton getEdytujButton() {
        return edytujButton;
    }
}
