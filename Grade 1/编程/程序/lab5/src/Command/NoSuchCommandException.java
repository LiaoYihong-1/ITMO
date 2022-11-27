package Command;

/**
 * When user input a nonexistent command,this exception will be thrown
 */
public class NoSuchCommandException extends RuntimeException{
    public NoSuchCommandException(String message){
        super(message);
    }
}
