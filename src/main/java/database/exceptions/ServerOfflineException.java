package database.exceptions;

public class ServerOfflineException extends RuntimeException {

    public ServerOfflineException(String message) {
        super(message);
    }
}
