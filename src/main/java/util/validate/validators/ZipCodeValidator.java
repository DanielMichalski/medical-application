package util.validate.validators;


import util.validate.AbstractValidator;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipCodeValidator extends AbstractValidator implements Validable {

    private JTextField zipCodeField;

    public ZipCodeValidator(JDialog parent, JTextField zipCodeField) {
        super(parent, zipCodeField, "");
        this.zipCodeField = zipCodeField;
    }

    protected boolean validationCriteria(JComponent component) {
        JTextField nameField = (JTextField) component;
        String textFromField = nameField.getText();
        if (textFromField == null || textFromField.equals("") || s(textFromField)) {
            setMessage("Uzupełnij wymagane pole");
            return false;
        }
        return true;
    }

    private boolean s(String string) {
        Pattern pattern = Pattern.compile("[^0-9-]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    @Override
    public void check() throws ValidationException {
        boolean invalidate = !validationCriteria(zipCodeField);
        if (invalidate) {
            zipCodeField.setBackground(Color.PINK);
            throw new ValidationException();
        }
    }
}
