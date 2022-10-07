package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.personalExceptions.InvalidCommandException;

import java.util.TreeMap;

public class ShowCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: show [key]" +
                    "\nDescription: Prints all the elements in collection or the one with specified key." +
                    "\nNumber of arguments: 0 - 1" +
                    "\n   Optional argument: key (Integer)\n";



    private static String  execute(TreeMap<Integer, MusicBand> collection) {
        return collection.toString();
    }


    private static String  executeWithKey(TreeMap<Integer, MusicBand> collection, Integer key) {

        checkCollectionForEmptiness(collection);

        if (!collection.containsKey(key)) throw new InvalidCommandException("Error: No element with such 'key' in the collection.");

        return collection.get(key).toString();
    }


    public static String execute(TreeMap<Integer, MusicBand> collection, String arg) {

        if (arg == null) { return  execute(collection);}
        else {
            return executeWithKey(collection, Integer.parseInt(arg));
        }
    }
}
