package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.main.DataBaseManager;
import ru.itmo.main.User;

import java.sql.Connection;
import java.util.TreeMap;

public class InsertCommand extends Command {

    public static String syntaxDescription =
            "\nCommand: insert <key> {element}" +
                    "\nDescription: Adds new element with the following key (if the key doesn't exist)." +
                    "\nNumber of arguments: 2" +
                    "\n   First argument:  key     (Integer)" +
                    "\n   Second argument: element (MusicBand)\n";


    /**
     * @param collection
     */
    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, MusicBand musicBand) {

//        if (collection.containsKey(key))
//            throw new InvalidCommandException("Error: Collection already contains musicBand with key: " + key + ". Command is impossible.");
        int key;

        key = DataBaseManager.addMusicBand(connection, musicBand);
        DataBaseManager.addOwnedMusicBandIDByUserID(connection, DataBaseManager.getUserIDByUserName(connection, user.getLogin()), key);

        musicBand.setId(key);
        collection.put(key, musicBand);

        return String.format("Reply: MusicBand was successfully added with ID : '%s' .", key);
    }
}