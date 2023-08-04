package ru.itmo.commands;

import ru.itmo.classes.MusicBand;

import java.util.TreeMap;

public class HelpCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: help [command_name]" +
                    "\nDescription: Prints the information about all of the command or the specified one." +
                    "\nNumber of arguments: 0 or 1" +
                    "\n   Optional argument:  command_name (String)\n";



    /**
     * @return
     */
    private static String execute() {
        return HelpCommand.syntaxDescription +
                InfoCommand.syntaxDescription +
                ShowCommand.syntaxDescription +
                InsertCommand.syntaxDescription +
                UpdateCommand.syntaxDescription +
                RemoveByKeyCommand.syntaxDescription +
                ClearCommand.syntaxDescription +
                SaveCommand.syntaxDescription +
                ExecuteScriptCommand.syntaxDescription +
                ExitCommand.syntaxDescription +
                RemoveGreaterCommand.syntaxDescription +
                ReplaceIfLowerCommand.syntaxDescription +
                RemoveLowerKeyCommand.syntaxDescription +
                RemoveAllByGenreCommand.syntaxDescription +
                MaxByFrontManCommand.syntaxDescription +
                FilterGreaterThanSinglesCountCommand.syntaxDescription;
    }


    /**
     * @param command
     * @return
     */
    private static String executeConcreteCommand(String command) {
        switch (command) {
            case ("help"): return HelpCommand.syntaxDescription;

            case ("info"): return InfoCommand.syntaxDescription;

            case ("show"): return ShowCommand.syntaxDescription;

            case ("insert"): return InsertCommand.syntaxDescription;

            case ("update"): return UpdateCommand.syntaxDescription;

            case ("remove_key"): return RemoveByKeyCommand.syntaxDescription;

            case ("clear"): return ClearCommand.syntaxDescription;

            case ("save"): return SaveCommand.syntaxDescription;

            case ("execute_script"): return ExecuteScriptCommand.syntaxDescription;

            case ("exit"): return ExitCommand.syntaxDescription;

            case ("remove_greater"): return RemoveGreaterCommand.syntaxDescription;

            case ("replace_if_lower"): return ReplaceIfLowerCommand.syntaxDescription;

            case ("remove_lower_key"): return RemoveLowerKeyCommand.syntaxDescription;

            case ("remove_all_by_genre"): return RemoveAllByGenreCommand.syntaxDescription;

            case ("max_by_front_man"): return MaxByFrontManCommand.syntaxDescription;

            case ("filter_greater_than_singles_count"): return FilterGreaterThanSinglesCountCommand.syntaxDescription;

            default: throw new UnsupportedOperationException(String.format("Error: Command %s isn't supported.", command));
        }
    }


    /**
     * @param arg
     * @return
     */
    public static String execute(TreeMap<Integer, MusicBand> collection, String arg) {

        if (arg == null) {
           return execute();
        } else {
            return executeConcreteCommand(arg);
        }
    }
}
