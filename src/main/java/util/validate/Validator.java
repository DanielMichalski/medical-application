package util.validate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Daniel Michalski
 */
public class Validator {
    private List<Validable> listToValidate;
    boolean allValid;

    public Validator(Validable... validate) {
        listToValidate = Arrays.asList(validate);
    }

    public void validate() throws ValidationException {
        allValid = true;
        for (Validable validate : listToValidate) {
            try {
                validate.check();
            } catch (ValidationException e) {
                allValid = false;
            }
        }

        if (!allValid) {
            throw new ValidationException();
        }
    }

}
