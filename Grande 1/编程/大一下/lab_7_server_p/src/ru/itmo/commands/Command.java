package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.personalExceptions.InvalidCommandException;

import java.util.TreeMap;

public abstract class Command {


    protected static void checkCollectionForEmptiness(TreeMap<Integer, MusicBand> collection) {
    if (collection.size() == 0)
        throw new InvalidCommandException("Error: Collection is empty. Impossible to run the command.");
    }
}
