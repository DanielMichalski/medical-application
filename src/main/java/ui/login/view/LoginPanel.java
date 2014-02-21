package ui.login.view;

import ui.components.ConfirmPanel;
import ui.components.FillTitleBorder;
import util.Const;
import util.GridConstrainsBuilder;
import util.Hash;
import util.finder.PressEnterListener;
import util.validate.validators.LoginValidator;
import util.validate.validators.PasswordValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginPanel extends JPanel implements KeyListener {
    private static final Dimension DIMENSION_TEXT_FIELD = new Dimension(150, 22);
    private static final Dimension DIMENSION_LABEL = new Dimension(40, 22);

    private PressEnterListener pressEnterListener;
    private ConfirmPanel confirmPanel;
    private JTextField tfLogin;
    private JPasswordField pfPassword;

    private LoginValidator loginValidator;
    private PasswordValidator passwordValidator;

    public LoginPanel(JDialog parent, PressEnterListener pressEnterListener, ActionListener actionListener) {
        super(new GridBagLayout());
        this.pressEnterListener = pressEnterListener;
        setBorder(new FillTitleBorder("Logowanie"));
        setBackground(Const.Colors.BACKGROUND_PANEL);

        init(parent, actionListener);
        createContent();
    }

    private void init(JDialog parent, ActionListener buttonListener) {
        confirmPanel = new ConfirmPanel(buttonListener, Const.ButtonLbls.LOGIN, Const.Colors.FILL_BORDER);

        tfLogin = new JTextField();
        tfLogin.setPreferredSize(DIMENSION_TEXT_FIELD);
        tfLogin.setTransferHandler(null);
        tfLogin.addKeyListener(this);
        loginValidator = new LoginValidator(parent, tfLogin);
        tfLogin.setInputVerifier(loginValidator);

        pfPassword = new JPasswordField();
        pfPassword.setPreferredSize(DIMENSION_TEXT_FIELD);
        pfPassword.setTransferHandler(null);
        pfPassword.addKeyListener(this);
        passwordValidator = new PasswordValidator(parent, pfPassword);
        pfPassword.setInputVerifier(passwordValidator);
    }

    private void createContent() {
        GridConstrainsBuilder constraints = new GridConstrainsBuilder();

        constraints.setMargin(5, 5, 5, 5);
        constraints.setPosition(0, 0);
        add(getLabel("Login:"), constraints);
        constraints.setMargin(5, 5, 5, 5);
        constraints.setPosition(1, 0);
        add(tfLogin, constraints);

        constraints.setMargin(5, 5, 5, 5);
        constraints.setPosition(0, 1);
        add(getLabel("Hasło:"), constraints);
        constraints.setMargin(5, 5, 5, 5);
        constraints.setPosition(1, 1);
        add(pfPassword, constraints);

        constraints.setMargin(5, 5, 5, 5);
        constraints.setPosition(1, 2);
        add(confirmPanel, constraints);
    }

    private JLabel getLabel(String lblName) {
        JLabel label = new JLabel(lblName, JLabel.RIGHT);
        label.setPreferredSize(DIMENSION_LABEL);
        return label;
    }

    public String getLogin() {
        String login = tfLogin.getText();
        login = login.trim();
        return login;
    }

    public String getPassword() {
        Hash hash = new Hash();
        String passwordHashed = hash.HashString(new String(pfPassword.getPassword()));   // zaszyfrowanie hasla
        passwordHashed = passwordHashed.trim();

        return passwordHashed;
    }

    public LoginValidator getLoginValidator() {
        return loginValidator;
    }

    public PasswordValidator getPasswordValidator() {
        return passwordValidator;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        textField.setBackground(Color.WHITE);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            pressEnterListener.onClickEnter();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
