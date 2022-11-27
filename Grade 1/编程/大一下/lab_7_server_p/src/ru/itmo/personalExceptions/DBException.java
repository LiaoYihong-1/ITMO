package ru.itmo.personalExceptions;

public class DBException extends RuntimeException {

    public DBException(String message) {
        super(message);
    }

}
