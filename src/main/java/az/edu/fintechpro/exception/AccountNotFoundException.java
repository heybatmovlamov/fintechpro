package az.edu.fintechpro.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }//Thrown when an account is not found during operations like fetching accounts or transferring funds.
}
