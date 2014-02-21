package util.validate.validators;

import util.validate.AbstractValidator;
import util.validate.Validable;
import util.validate.ValidationException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Date: 04.06.12
 * Time: 18:28
 *
 * @author Daniel Michalski
 */
public class BranchValidator extends AbstractValidator implements Validable {

    private JTextField branchField;

    public BranchValidator(JDialog parent, JTextField branchField) {
        super(parent, branchField, "");
        this.branchField = branchField;
    }

    protected boolean validationCriteria(JComponent component) {
        JTextField branchField = (JTextField) component;
        String textFromField = branchField.getText();
        if (textFromField == null || textFromField.equals("")) {
            setMessage("Uzupełnij wymagane pole");
            return false;
        } else if (s(textFromField)) {
            setMessage("Niewłaściwy sposób zapisu (dopuszczalne są tylko małe i duże litery)");
            return false;
        } else if (textFromField.length() < 5) {
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
        boolean invalidate = !validationCriteria(branchField);
        if (invalidate) {
            branchField.setBackground(Color.PINK);
            throw new ValidationException();
        }
    }
}



