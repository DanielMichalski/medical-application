package util.validate.validators;

import util.validate.AbstractValidator;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotEmptyValidator extends AbstractValidator implements Validable {

    private JTextField textField;

    public NotEmptyValidator(JDialog parent, JTextField textField) {
        super(parent, textField, "");
        this.textField = textField;
    }

    protected boolean validationCriteria(JComponent component) {

        JTextField nameField = (JTextField) component;
        String textFromField = nameField.getText();
        if (textFromField == null || textFromField.equals("")) {
            setMessage("Uzupełnij wymagane pole");
            return false;
        } else if (s(textFromField)) {
            setMessage("Nieprawidłowy zapis. Dopuszczalne są tylko litery");
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
//		Pattern pattern = Pattern.compile("[^a-z0-9 ./-]", Pattern.CASE_INSENSITIVE);
        Pattern pattern = Pattern.compile("[^a-z ąĄćĆęĘłŁńŃóÓśŚżŻźŹ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    @Override
    public void check() throws ValidationException {
        boolean invalidate = !validationCriteria(textField);
        if (invalidate) {
            textField.setBackground(Color.PINK);
            throw new ValidationException();
        }
    }
}
