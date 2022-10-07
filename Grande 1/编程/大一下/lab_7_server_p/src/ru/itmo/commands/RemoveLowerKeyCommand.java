package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.main.DataBaseManager;
import ru.itmo.main.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;

public class RemoveLowerKeyCommand extends Command {

    public static String syntaxDescription =
            "Command: remove_lower_key <key>" +
                    "\nDescription: Removes all the elements, which key is less than the specified one." +
                    "\nNumber of arguments: 1" +
                    "\n   Argument: key (Integer)\n";




    /**
     * @param collection
     * @param key
     */
    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, Integer key) {

        checkCollectionForEmptiness(collection);

        ArrayList<Integer> ownedMusicBandsByUser = DataBaseManager.getOwnedMusicBandsByUser(connection, user);
        ArrayList<Integer> musicBandsIDsToRemove = new ArrayList<>();

        collection.values().forEach(mb -> {
            if (mb.getId() < key
                    & ownedMusicBandsByUser.contains(mb.getId())) {
                DataBaseManager.removeMusicBand(connection, mb);
                DataBaseManager.removeOwnedMusicBand(connection, user, mb.getId());
                musicBandsIDsToRemove.add(mb.getId());
            }

        });

        musicBandsIDsToRemove.forEach(collection::remove);

        return "Reply: All the lower MusicBands were removed from the collection if presented.";
    }

    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, String key) {
        return execute(collection, connection, user, Integer.parseInt(key));
    }
}
