package ui.login.view;

import ui.login.presenter.LoginPresenter;
import util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginFrame extends JDialog {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();

    public static final int DEFAULT_WIDTH = 250;
    public static final int DEFAULT_HEIGHT = 180;

    private LoginClickListener loginClickListener;

    private ConnectionStatusPanel connectionStatusPanel;
    private LoginPanel loginPanel;
    private LoginPresenter loginPresenter;

    public LoginFrame() {
        loginClickListener = new LoginClickListener();
        loginPresenter = new LoginPresenter(this);

        setupFrame();
        init();
        createContent();

        setVisible(true);

        LOGGER.info("Utorzenie okienka logowania");
    }

    private void init() {
        connectionStatusPanel = new ConnectionStatusPanel();
        loginPanel = new LoginPanel(this, loginPresenter, loginClickListener);
        loginPresenter.setLoginPanel(loginPanel);
        loginPresenter.createValidator();
    }

    private void setupFrame() {
        getContentPane().setBackground(Const.Colors.BACKGROUND_PANEL);
        Utils.setApplicationIcon(this);
        addWindowListener(new potwierdzenieZamknieciaOkna());

        setLayout(new GridBagLayout());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setLocationRelativeTo(null); //centrowanie
        setResizable(false);
        setTitle(Const.TitleFrames.LOGIN_EMPLOYEES);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e)
        {
            LOGGER.log(Level.WARNING, "Wystąpił błąd podczas ładowania wyglądu okna");
            JOptionPane.showMessageDialog(this,
                    "Wystąpił błąd podczas ładowania wyglądu okna: " + e,
                    "Uwaga",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createContent() {
        GridConstrainsBuilder constrains = new GridConstrainsBuilder();

        constrains.setMargin(8, 8, 5, 0);
        constrains.setWeights(1.0, 0.0);
        constrains.setPosition(0, 0);
        add(loginPanel, constrains);

        constrains.setMargin(10, 8, 0, 0);
        constrains.setWeights(1.0, 0.0);
        constrains.setPosition(0, 1);
        add(connectionStatusPanel, constrains);
    }

    private class LoginClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            LOGGER.info("Próba logowania użytkownika");
            loginPresenter.loginWorkerAction();
        }
    }
}
