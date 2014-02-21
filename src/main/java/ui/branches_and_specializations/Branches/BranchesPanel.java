package ui.branches_and_specializations.Branches;

import ui.components.FillTitleBorder;
import util.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * Date: 27.05.12
 * Time: 16:47
 *
 * @author Daniel Michalski
 */

public class BranchesPanel extends JPanel {
    BranchPresenter branchPresenter;
    GlownyPanel glownyPanel;
    DolnyPanel dolnyPanel;

    public BranchesPanel() {
        setBackground(Const.Colors.backgroundColor);
        setLayout(new BorderLayout());
        InitComponents();

        branchPresenter = new BranchPresenter(glownyPanel, dolnyPanel);
    }

    private void InitComponents() {
        glownyPanel = new GlownyPanel();
        dolnyPanel = new DolnyPanel();

        add(glownyPanel, BorderLayout.CENTER);
        add(dolnyPanel, BorderLayout.SOUTH);
    }
}


class GlownyPanel extends JPanel {
    private JComboBox oddzialyComboBox;

    public GlownyPanel() {
        setLayout(null);
        setBorder(new FillTitleBorder("Oddziały"));
        oddzialyComboBox = new JComboBox();
        oddzialyComboBox.setBounds(10, 40, 200, 20);
        setBackground(Const.Colors.BACKGROUND_PANEL);
        add(oddzialyComboBox);
    }

    public JComboBox getOddzialyComboBox() {
        return oddzialyComboBox;
    }
}

class DolnyPanel extends JPanel {
    private JButton dodajButton, edytujButton, usunButton, odswiezButton;

    public DolnyPanel() {
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
