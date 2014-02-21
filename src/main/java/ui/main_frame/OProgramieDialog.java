package ui.main_frame;

import util.Const;
import util.MyProgramLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * Author: Daniel Michalski
 * Date: 24.03.13
 */

public class OProgramieDialog extends JDialog {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    public static final int WIDTH = 250;
    public static final int HEIGHT = 100;

    private JButton zamknijBtn;

    public OProgramieDialog() {
        initialize();
        createContent();
    }

    private void initialize() {
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(Const.Colors.BACKGROUND_PANEL);

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
    }

    private void createContent() {
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Const.Colors.BACKGROUND_PANEL);
        zamknijBtn = new JButton("Zamnij");
        zamknijBtn.addActionListener(new zamknijBtnListener());
        btnPanel.add(zamknijBtn);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Const.Colors.BACKGROUND_PANEL);
        infoPanel.add(new JLabel("Tu będzie informacja o programie."));

        add(infoPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private class zamknijBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
