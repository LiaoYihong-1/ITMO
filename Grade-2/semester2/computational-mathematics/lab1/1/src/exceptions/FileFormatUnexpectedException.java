package exceptions;

public class FileFormatUnexpectedException extends RuntimeException{
    public FileFormatUnexpectedException(String mes){
        super(mes);
    }
}
