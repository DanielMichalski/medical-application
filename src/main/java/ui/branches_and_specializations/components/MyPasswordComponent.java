package ui.branches_and_specializations.components;

import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;

/**
 * @author Daniel Michalski
 */

public class MyPasswordComponent extends JPasswordField implements Validable {
    private String name;
    private final int minCharValue;
    private boolean isFieldGood;
    private String minLengthErrorMessage, comparePassErrorMessage;
    private JLabel errorLabel, nameLabel;
    private JPasswordField passToCompare;

    public MyPasswordComponent(String name, int minCharValue) {
        this.name = name;
        this.minCharValue = minCharValue;
        this.setToolTipText(name);
        this.isFieldGood = false;
        this.minLengthErrorMessage = "* Hasła muszą mieć co najmniej " + minCharValue + " znaków.";
        this.comparePassErrorMessage = "* Hasła muszą być takie same";

        nameLabel = new JLabel(name);

        errorLabel = new JLabel(minLengthErrorMessage);
        errorLabel.setVisible(false);
        errorLabel.setForeground(Color.red);
    }

    @Override
    public void check() throws ValidationException {
        if (new String(getPassword()).length() < 6 || new String(passToCompare.getPassword()).length() < 6) {
            setBorder(BorderFactory.createLineBorder(Color.red));
            passToCompare.setBorder(BorderFactory.createLineBorder(Color.red));
            setVisibleErrorLabel(true);
            errorLabel.setText(minLengthErrorMessage);
            throw new ValidationException();
        } else if (new String(getPassword()).compareTo(new String(passToCompare.getPassword())) != 0) {
            setBorder(BorderFactory.createLineBorder(Color.red));
            passToCompare.setBorder(BorderFactory.createLineBorder(Color.red));
            setVisibleErrorLabel(true);
            errorLabel.setText(comparePassErrorMessage);
            throw new ValidationException();
        } else {
            setBorder(BorderFactory.createLineBorder(Color.gray));
            passToCompare.setBorder(BorderFactory.createLineBorder(Color.gray));
            setToolTipText("");
            passToCompare.setToolTipText("");
            setVisibleErrorLabel(false);
        }
    }

    public void clearBorderAndErrorLabel() {
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        errorLabel.setVisible(false);
        this.setText("");
    }

    public void prepareErrorLabels() {
        errorLabel.setVisible(true);
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public boolean isFieldGood() {
        return isFieldGood;
    }

    public String getMinLengthErrorMessage() {
        return minLengthErrorMessage;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public void setVisibleErrorLabel(boolean x) {
        errorLabel.setVisible(x);
    }

    public void setPassToCompare(JPasswordField passToCompare) {
        this.passToCompare = passToCompare;
    }
}

