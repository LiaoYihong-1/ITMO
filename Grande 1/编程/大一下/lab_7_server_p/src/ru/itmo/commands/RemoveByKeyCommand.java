package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.main.DataBaseManager;
import ru.itmo.main.User;

import java.sql.Connection;
import java.util.TreeMap;

public class RemoveByKeyCommand extends Command {

    public static String syntaxDescription =
            "\nCommand: remove_key <key>" +
                    "\nDescription: Removes an element with the specified key." +
                    "\nNumber of arguments: 1" +
                    "\n   Argument: key (Integer)\n";


    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, Integer mbID) {

        checkCollectionForEmptiness(collection);

        if ( ! collection.containsKey(mbID)) {
            return "Error: No element with such 'key' in the collection.";
        }

        if ( ! DataBaseManager.userOwnsMusicBand(connection, user, mbID)) {
            return String.format(
                    "Error: You can't remove MusicBand with key (ID) : '%s' as you don't own it.",
                    mbID);

        } else {
            DataBaseManager.removeMusicBandByID(connection, mbID);
            DataBaseManager.removeOwnedMusicBand(connection, user, mbID);
            collection.remove(mbID);

            return String.format(
                    "Reply: MusicBand with key (ID) : '%s' was removed successfully.",
                    mbID);
        }
    }

    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, String key) {
        return execute(collection, connection, user, Integer.parseInt(key));

    }
}