package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.main.DataBaseManager;
import ru.itmo.main.User;

import java.sql.Connection;
import java.util.TreeMap;

public class ReplaceIfLowerCommand extends Command {

    public static String syntaxDescription =
            "\nCommand: replace_if_lower <key> {element}" +
                    "\nDescription: Replaces the element of specified key with the specified element if the new one is less." +
                    "\nNumber of arguments 2: " +
                    "\n   First argument:  key     (Integer)" +
                    "\n   Second argument: element (MusicBand)\n";


    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, Integer key, MusicBand musicBand) {

        checkCollectionForEmptiness(collection);

        if (!collection.containsKey(key))
            return "Error: No such key in the collection. Command is impossible.";

        if (!DataBaseManager.userOwnsMusicBand(connection, user, musicBand)) {
            return "Reply: MusicBand wasn't replaced as you don't own it and can't modify.";

        } else if (musicBand.isLessThan(collection.get(key))) {
            DataBaseManager.updateMusicBandByID(connection, musicBand, key);
            collection.put(key, musicBand);

            return "Reply: MusicBand was successfully replaced.";

        } else {
            return "Reply: MusicBand wasn't replaced as the new one is now lower than MusicBand with the specified key.";
        }
    }

    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, String key, MusicBand musicBand) {
        return execute(collection, connection, user, Integer.parseInt(key), musicBand);
    }

}
