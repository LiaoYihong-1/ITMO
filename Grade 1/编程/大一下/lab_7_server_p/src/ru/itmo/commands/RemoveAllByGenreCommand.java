package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.classes.MusicGenre;
import ru.itmo.main.DataBaseManager;
import ru.itmo.main.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;

public class RemoveAllByGenreCommand extends Command {

    public static String syntaxDescription =
            "\nCommand: remove_all_by_genre <genre>" +
                    "\nDescription: Removes all the elements, which field 'genre' value matches the specified." +
                    "\nNumber of arguments: 1" +
                    "\n   Argument: genre (MusicGenre)\n";


    /**
     * @param collection
     * @param genre
     */
    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, MusicGenre genre) {

        checkCollectionForEmptiness(collection);

        ArrayList<Integer> ownedMusicBandsByUser = DataBaseManager.getOwnedMusicBandsByUser(connection, user);
        ArrayList<Integer> musicBandsIDsToRemove = new ArrayList<>();

        collection.values()
                .forEach(mb -> {
                    if (mb.getGenre() == genre
                            & ownedMusicBandsByUser.contains(mb.getId())) {
                        DataBaseManager.removeMusicBandByID(connection, mb.getId());
                        DataBaseManager.removeOwnedMusicBand(connection, user, mb.getId());
                        musicBandsIDsToRemove.add(mb.getId());
                    }
                });

        musicBandsIDsToRemove.forEach(collection::remove);

        return String.format(
                "Reply: MusicBands with music genre : '%s' were successfully removed if presented.",
                genre);
    }


    public static String execute(TreeMap<Integer, MusicBand> collection, Connection connection, User user, String genre) {
        return execute(collection, connection, user, MusicGenre.valueOf(genre.toUpperCase()));
    }

}
