package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.main.DataBaseManager;
import ru.itmo.main.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 */
public class ClearCommand extends Command {

    public static String syntaxDescription =
            "\nCommand: clear" +
            "\nDescription: Deletes all the keys and values (clears he collection)." +
            "\nNumber of arguments: 0" +
            "\n";


    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user) {

        ArrayList<Integer> ownedMusicBandsByUser = DataBaseManager.getOwnedMusicBandsByUser(connection, user);
        ArrayList<Integer> musicBandsIDsToRemove = new ArrayList<>();

        collection.values().forEach(mb -> {
            if (ownedMusicBandsByUser.contains(mb.getId())) {
                DataBaseManager.removeMusicBand(connection, mb);
                DataBaseManager.removeOwnedMusicBand(connection, user, mb.getId());
                musicBandsIDsToRemove.add(mb.getId());

            }
        });

        musicBandsIDsToRemove.forEach(collection::remove);

        return "Reply: All your MusicBands were removed successfully if presented.";
    }

}
