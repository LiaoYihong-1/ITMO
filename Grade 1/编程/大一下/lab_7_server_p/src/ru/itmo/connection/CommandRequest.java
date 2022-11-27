package ru.itmo.connection;

import ru.itmo.classes.MusicBand;
import ru.itmo.main.User;

import java.io.Serializable;

public class CommandRequest implements Serializable {

    String commandName;
    String argumentKey = null;
    MusicBand argumentMusicBand = null;
    User user;


    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getArgumentKey() {
        return argumentKey;
    }

    public void setArgumentKey(String argumentKey) {
        this.argumentKey = argumentKey;
    }

    public MusicBand getArgumentMusicBand() {
        return argumentMusicBand;
    }

    public void setArgumentMusicBand(MusicBand argumentElement) {
        this.argumentMusicBand = argumentElement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
