package cart.exception.customexceptions;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(final String message) {
        super(message);
    }
}
