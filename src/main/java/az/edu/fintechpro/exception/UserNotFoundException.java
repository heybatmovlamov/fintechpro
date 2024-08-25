package az.edu.fintechpro.exception;

public class UserNotFoundException extends RuntimeException {// Thrown when a user is not found in the system, for example, during login or fetching details.
    public UserNotFoundException(String message) {
        super(message);
    }
}
