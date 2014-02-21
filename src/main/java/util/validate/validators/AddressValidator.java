
package util.validate.validators;

import util.validate.AbstractValidator;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressValidator extends AbstractValidator implements Validable {

    private JTextField addressField;

    public AddressValidator(JDialog parent, JTextField addressField) {
        super(parent, addressField, "");
        this.addressField = addressField;
    }

    protected boolean validationCriteria(JComponent component) {
        JTextField nameField = (JTextField) component;
        String textFromField = nameField.getText();
        if (textFromField == null || textFromField.equals("")) {
            setMessage("Uzupełnij wymagane pole");
            return false;
        } else if (s(textFromField)) {
            setMessage("Niepoprawny adres. Podaj ulicę z numerem domu lub mieszkania, np. Krakowska 1/1");
            return false;
        } else if (textFromField.length() > 30) {
            setMessage("Zbyt długi wpis");
            return false;
        }
        return true;
    }

    private boolean s(String string) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ąĄćĆęĘłŁńŃóÓśŚżŻźŹ]+ [0-9\\/]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        return !matcher.matches();
    }

    @Override
    public void check() throws ValidationException {
        boolean invalidate = !validationCriteria(addressField);
        if (invalidate) {
            addressField.setBackground(Color.PINK);
            throw new ValidationException();
        }
    }
}
