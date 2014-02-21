package internet;

public class NoInternetException extends RuntimeException {

    public NoInternetException(String message) {
        super(message);
    }
}
