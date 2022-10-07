package Collection;

/**
 * when set a value,which can't be null, as null,this exception will be thrown
 */
public class NullException extends RuntimeException {
    public NullException() {
        super();
    }

    public NullException(String message) {
        super(message);
    }
}
