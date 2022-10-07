package ru.itmo.personalExceptions;

public class DBConnectionDisestablishedException extends RuntimeException {

        public DBConnectionDisestablishedException(String message) {
            super(message);
        }
}
