package Command;

/**
 * when user use command with incorrect parameters,will be thrown
 */
public class ParaInapproException extends RuntimeException {
    public ParaInapproException(String message) {
        super(message);
    }
}
