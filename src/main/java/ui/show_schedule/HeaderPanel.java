package ui.show_schedule;

import ui.components.ErrorLabel;
import util.Const;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {

    private JLabel headerLabel;
    private ErrorLabel errorLabel;

    public HeaderPanel(String headerLbl, String errorLbl) {
        super(new FlowLayout(FlowLayout.LEFT));
        setBackground(Const.Colors.FILL_BORDER);
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Const.Colors.BORDER_LINE));
        headerLabel = new JLabel(headerLbl);
        headerLabel.setForeground(Const.Colors.HEADER_TEXT);
        headerLabel.setFont(Const.Fonts.HEADER);
        errorLabel = new ErrorLabel(errorLbl);
        errorLabel.setForeground(Const.Colors.ERROR_TEXT);
        errorLabel.setFont(Const.Fonts.HEADER);


        hideError();
        add(headerLabel);
        add(errorLabel);
    }

    public void showError() {
        headerLabel.setVisible(false);
        errorLabel.setVisible(true);
    }

    public void hideError() {
        headerLabel.setVisible(true);
        errorLabel.setVisible(false);
    }
}