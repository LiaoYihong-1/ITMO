package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.main.DataBaseManager;
import ru.itmo.main.User;

import java.sql.Connection;
import java.util.TreeMap;

public class UpdateCommand extends Command {

    public static String syntaxDescription =
            "\nCommand: update <id> {element}" +
                    "\nDescription: Updates value of the element with following 'ID'." +
                    "\nNumber of arguments: 2" +
                    "\n   First argument:  id      (Integer)" +
                    "\n   Second argument: element (MusicBand)\n";


    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, Integer key, MusicBand musicBand) {

        checkCollectionForEmptiness(collection);

        if (!collection.containsKey(key))
            return "Error: No element with such 'ID' in the collection.";

        if (DataBaseManager.userOwnsMusicBand(connection, user, key)) {
            DataBaseManager.updateMusicBandByID(connection, musicBand, key);
            collection.put(key, musicBand);

            return String.format(
                    "Reply: MusicBand with key (ID) : '%s' was successfully updated.",
                    key);
        }


        return String.format(
                "Error: You can't update musicBand with key (ID) : '%s' as you don't own it",
                key);

    }


    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, String key, MusicBand musicBand) {
        return execute(collection, connection, user, Integer.parseInt(key), musicBand);
    }
}
