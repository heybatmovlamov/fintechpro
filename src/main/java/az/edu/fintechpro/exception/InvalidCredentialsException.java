package az.edu.fintechpro.exception;

public class InvalidCredentialsException extends  RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
        //Thrown when the user provides incorrect login credentials.
    }
}