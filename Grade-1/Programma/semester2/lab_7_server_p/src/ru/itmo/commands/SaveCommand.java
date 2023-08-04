package ru.itmo.commands;

import java.io.IOException;

public class SaveCommand extends Command{

    public static String syntaxDescription =
                    "\nCommand: save [file_path]" +
                    "\nDescription: Saves collection to specified file or to the file it was loaded from (default)." +
                    "\nNumber of arguments: " +
                    "\n   Optional argument: file_path (String)\n";


//    {
//        setNumberOfArguments(1);
//    }


    /**
     * @param collection
     * @param filePath
     * @throws IOException
     */
//    public void execute(TreeMap<Integer, MusicBand> collection, String filePath) throws IOException {
//        FileManager.saveCollectionToFile(collection, filePath);
//    }
//
//
//    /**
//     * @param collection
//     * @param args
//     * @throws IOException
//     */
//    public void execute(TreeMap<Integer, MusicBand> collection, String[] args) throws IOException {
//
////        checkNumberOfArguments(args);
//
//        execute(collection, args[0]);
//    }
}
