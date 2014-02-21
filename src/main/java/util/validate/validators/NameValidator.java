package util.validate.validators;


import util.validate.AbstractValidator;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator extends AbstractValidator implements Validable {

    private JTextField nameField;

    public NameValidator(JDialog parent, JTextField nameField) {
        super(parent, nameField, "");
        this.nameField = nameField;
    }

    protected boolean validationCriteria(JComponent component) {
        JTextField nameField = (JTextField) component;
        String textFromField = nameField.getText();
        if (textFromField == null || textFromField.equals("")) {
            setMessage("Uzupełnij wymagane pole");
            return false;
        } else if (s(textFromField)) {
            setMessage("Niewłaściwy sposób zapisu (dopuszczalne są tylko małe i duże litery)");
            return false;
        } else if (textFromField.length() < 2) {
            setMessage("Zbyt krótki wpis");
            return false;
        } else if (textFromField.length() > 30) {
            setMessage("Zbyt długi wpis");
            return false;
        }
        return true;
    }

    private boolean s(String string) {
        Pattern pattern = Pattern.compile("[^a-z ąĄćĆęĘłŁńŃóÓśŚżŻźŹ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    @Override
    public void check() throws ValidationException {
        boolean invalidate = !validationCriteria(nameField);
        if (invalidate) {
            nameField.setBackground(Color.PINK);
            throw new ValidationException();
        }
    }
}
