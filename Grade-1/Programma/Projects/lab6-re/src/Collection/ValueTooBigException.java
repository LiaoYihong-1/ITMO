package Collection;

/**
 * exception ValueTooBigException,which will be thrown when set a value too big
 */
public class ValueTooBigException extends RuntimeException{
    /**
     * constructor
     * @param message
     */
    public ValueTooBigException(String message){
        super(message);
    }
}
