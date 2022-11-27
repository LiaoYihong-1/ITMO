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
    ValueTooSmallException(String message) {
        super(message);
    }
}
