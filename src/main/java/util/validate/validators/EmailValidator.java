package util.validate.validators;


import util.validate.AbstractValidator;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends AbstractValidator implements Validable {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private JTextField emailField;

    public EmailValidator(JDialog parent, JTextField emailField) {
        super(parent, emailField, "");
        this.emailField = emailField;
    }

    protected boolean validationCriteria(JComponent component) {
        JTextField nameField = (JTextField) component;
        String textFromField = nameField.getText();
        if (textFromField == null || textFromField.equals("")) {
            setMessage("Uzupełnij wymagane pole");
            return false;
        } else if (s(textFromField)) {
            setMessage("Niepoprawny adres e-mail");
            return false;
        } else if (textFromField.length() > 30) {
            setMessage("Zbyt długi e-mail");
            return false;
        }
        return true;
    }

    private boolean s(String string) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return !matcher.matches();
    }

    @Override
    public void check() throws ValidationException {
        boolean invalidate = !validationCriteria(emailField);
        if (invalidate) {
            emailField.setBackground(Color.PINK);
            throw new ValidationException();
        }
    }
}
