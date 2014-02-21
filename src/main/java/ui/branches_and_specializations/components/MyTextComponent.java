package ui.branches_and_specializations.components;

import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;

/**
 * @author Daniel Michalski
 */

public class MyTextComponent extends JTextField implements Validable {
    private int minCharValue;
    private JLabel nameLabel, errorLabel;

    public MyTextComponent(String name, int minCharValue) {
        this.setToolTipText(name);
        this.minCharValue = minCharValue;
        nameLabel = createNameLbl(name);
        errorLabel = createErrorLbl(name, minCharValue);
    }

    private JLabel createNameLbl(String name) {
        JLabel nameLbl = new JLabel(name);
        return nameLbl;
    }

    private JLabel createErrorLbl(String name, int minCharactersNo) {
        String errorMessage = "* " + name + " musi mieć co najmniej " + minCharactersNo + " znaków";
        JLabel errorLbl = new JLabel(errorMessage);
        errorLbl.setForeground(Color.red);
        errorLbl.setVisible(false);
        return errorLbl;
    }

    public void clearBorderAndErrorLabel() {
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        errorLabel.setVisible(false);
        this.setText("");
    }

    @Override
    public void check() throws ValidationException {
        if (this.getText().length() < minCharValue) {
            this.setBorder(BorderFactory.createLineBorder(Color.red));
            errorLabel.setVisible(true);
            throw new ValidationException();
        } else {
            this.setBorder(BorderFactory.createLineBorder(Color.gray));
            errorLabel.setVisible(false);
        }
    }

    public void setHorizontalAlignmentForNameLbl(int horizontalAlignment) {
        nameLabel.setHorizontalAlignment(horizontalAlignment);
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }
}