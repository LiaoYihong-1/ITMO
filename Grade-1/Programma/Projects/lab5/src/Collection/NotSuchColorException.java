package Collection;

/**
 * Exception which will be thrown when user input a color that don't exist
 */
public class NotSuchColorException extends RuntimeException{
    public NotSuchColorException(String message){
        super(message);
    }
}
