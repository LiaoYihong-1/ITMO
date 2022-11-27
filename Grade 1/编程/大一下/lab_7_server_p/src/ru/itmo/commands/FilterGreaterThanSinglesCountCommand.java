package ru.itmo.commands;

import ru.itmo.classes.MusicBand;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class FilterGreaterThanSinglesCountCommand extends Command {

    public static String syntaxDescription =
            "\nCommand: filter_greater_than_singles_count <singlesCount>" +
                    "\nDescription: Prints the elements, which 'singlesCount' value is greater than the specified." +
                    "\nNumber of arguments: 1" +
                    "\n   Argument:  singlesCount (int)\n";


    /**
     * @param collection
     * @param singlesCount
     * @return
     */
    public static String execute(TreeMap<Integer, MusicBand> collection, int singlesCount) {

        checkCollectionForEmptiness(collection);

        return collection.values().stream().filter(x -> x.getSinglesCount() > singlesCount).map(MusicBand::toString).collect(Collectors.joining());
    }


    public static String execute(TreeMap<Integer, MusicBand> collection, String singlesCount) {
        return execute(collection, Integer.parseInt(singlesCount));
    }


}
