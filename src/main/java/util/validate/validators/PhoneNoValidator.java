package util.validate.validators;


import util.validate.AbstractValidator;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNoValidator extends AbstractValidator implements Validable {

    private JTextField phoneNoField;

    public PhoneNoValidator(JDialog parent, JTextField phoneNoField) {
        super(parent, phoneNoField, "");
        this.phoneNoField = phoneNoField;
    }

    protected boolean validationCriteria(JComponent component) {
        JTextField peselField = (JTextField) component;
        String textFromField = peselField.getText();
        if (textFromField == null || textFromField.equals("") || s(textFromField)) {
            setMessage("Uzupełnij wymagane pole");
            return false;
        }
        return true;
    }

    private boolean s(String string) {
        Pattern pattern = Pattern.compile("[^0-9 +()]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    @Override
    public void check() throws ValidationException {
        boolean invalidate = !validationCriteria(phoneNoField);
        if (invalidate) {
            phoneNoField.setBackground(Color.PINK);
            throw new ValidationException();
        }
    }
}
