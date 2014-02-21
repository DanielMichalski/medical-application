package ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConfirmPanel extends JPanel {

    public static final int ID_POSITIVE_BUTTON = 1;
    public static final int ID_NEGATIVE_BUTTON = 2;
    public static final int ID_NEUTRAL_BUTTON = 3;

    public ConfirmPanel(ActionListener actionListener, String positiveBtnName, String negativeBtnName, Color backgroundColor) {
        super(new BorderLayout());
        setBackground(backgroundColor);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton btnPositive = new JButton(positiveBtnName);
        btnPositive.setBackground(backgroundColor);
        btnPositive.setMnemonic(ID_POSITIVE_BUTTON);
        btnPositive.addActionListener(actionListener);
        panel.add(btnPositive);

        JButton btnNegative = new JButton(negativeBtnName);
        btnNegative.setBackground(backgroundColor);
        btnNegative.setMnemonic(ID_NEGATIVE_BUTTON);
        btnNegative.addActionListener(actionListener);
        panel.add(btnNegative);

        add(panel, BorderLayout.EAST);
    }

    public ConfirmPanel(ActionListener actionListener, String neutralBtnName, Color backgroundColor) {
        super(new BorderLayout());
        setBackground(backgroundColor);

        JButton btnNeutral = new JButton(neutralBtnName);
        btnNeutral.setBackground(backgroundColor);
        btnNeutral.setMnemonic(ID_NEUTRAL_BUTTON);
        btnNeutral.addActionListener(actionListener);
        add(btnNeutral, BorderLayout.EAST);
    }
}
