package az.edu.fintechpro.exception;

public class InactiveAccountException extends RuntimeException {
    public InactiveAccountException(String message) {
        super(message);
}
}//Thrown when a user tries to interact with an inactive account (e.g., during a transfer).
