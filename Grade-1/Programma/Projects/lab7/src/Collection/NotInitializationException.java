package Collection;

/**
 * Exception which will be throwed when static linkedhashset people in collections of person isn't initialized
 */
public class NotInitializationException extends RuntimeException {
    public NotInitializationException(String message) {
        super(message);
    }
}
