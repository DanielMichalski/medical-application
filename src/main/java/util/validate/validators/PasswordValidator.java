
package util.validate.validators;

import util.validate.AbstractValidator;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordValidator extends AbstractValidator implements Validable {

    private JTextField passwordField;

    public PasswordValidator(JDialog parent, JTextField passwordField) {
        super(parent, passwordField, "");
        this.passwordField = passwordField;
    }

    protected boolean validationCriteria(JComponent component) {
        JTextField peselField = (JTextField) component;
        String textFromField = peselField.getText();
        if (textFromField == null || textFromField.equals("")) {
            setMessage("Uzupełnij wymagane pole");
            return false;
        } else if (textIsNotCorrect(textFromField)) {
            setMessage("Dopuszczalne są tylko znaki alfanumeryczne");
            return false;
        } else if (textFromField.length() < 6) {
            setMessage("Zbyt krótki wpis");
            return false;
        } else if (textFromField.length() > 30) {
            setMessage("Zbyt długi wpis");
            return false;
        }
        return true;
    }

    private boolean textIsNotCorrect(String string) {
        Pattern pattern = Pattern.compile("[^0-9a-z ąĄćĆęĘłŁńŃóÓśŚżŻźŹ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    @Override
    public void check() throws ValidationException {
        boolean invalidate = !validationCriteria(passwordField);
        if (invalidate) {
            passwordField.setBackground(Color.PINK);
            throw new ValidationException();
        }
    }
}
