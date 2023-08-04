package ru.itmo.connection;

import ru.itmo.main.User;

import java.io.Serializable;

public class ServiceResponse implements Serializable {

    User user;
    boolean authorised = false;
    boolean registered = false;

    String errorMessage = "";

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
        if (authorised) {
            this.registered = false;
        }
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
        if (registered) {
            this.authorised = false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
