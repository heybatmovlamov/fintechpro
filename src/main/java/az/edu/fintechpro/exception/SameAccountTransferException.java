package az.edu.fintechpro.exception;

public class SameAccountTransferException extends RuntimeException {
    public SameAccountTransferException(String message) {
        super(message);
    }
}// Thrown when a user tries to transfer funds to the same account.
