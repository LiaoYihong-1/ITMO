package ru.itmo.connection;

import ru.itmo.main.User;

import java.io.Serializable;

public class CommandResponse implements Serializable {


    String message = "";
    User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
