package Collection;

/**
 * will be thrown when set a value too small
 */
public class ValueTooSmallException extends RuntimeException {
    /**
     * constructor
     *
     * @param message Message
     */
    public ValueTooSmallException(String message) {
        super(message);
    }
}
