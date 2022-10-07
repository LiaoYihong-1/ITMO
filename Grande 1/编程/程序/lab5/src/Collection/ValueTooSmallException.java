package Collection;

/**
 * will be thrown when set a value too small
 */
public class ValueTooSmallException extends RuntimeException{
    /**
     * constructor
     * @param message
     */
    ValueTooSmallException(String message){
        super(message);
    }
}
