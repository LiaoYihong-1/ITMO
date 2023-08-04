package ru.itmo.connection;

import ru.itmo.main.User;

import java.io.Serializable;

public class ServiceRequest implements Serializable {
    private boolean registration = false;
    private boolean authorisation = true;
    User user;

    public ServiceRequest() {}


    public ServiceRequest(User user, boolean authorisation) {
        setUser(user);
        setAuthorisation(authorisation);
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
        this.authorisation = !registration;
    }

    public boolean isAuthorisation() {
        return authorisation;
    }

    public void setAuthorisation(boolean authorisation) {
        this.authorisation = authorisation;
        this.registration = !authorisation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
