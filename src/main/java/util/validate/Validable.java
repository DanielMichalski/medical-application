package util.validate;

/**
 * @author Daniel Michalski
 */
public interface Validable {
    void check() throws ValidationException;
}
