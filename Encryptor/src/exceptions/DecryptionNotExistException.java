package exceptions;

public class DecryptionNotExistException extends Exception {
    public DecryptionNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
