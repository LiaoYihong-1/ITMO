package ru.itmo.main;

import ru.itmo.classes.MusicBand;
import ru.itmo.commands.*;
import ru.itmo.connection.CommandRequest;
import ru.itmo.connection.CommandResponse;
import ru.itmo.personalExceptions.DBException;
import ru.itmo.personalExceptions.InvalidCommandException;

import java.sql.Connection;
import java.util.TreeMap;

public class CommandManager {

    static String dateOfInitialization;


    public static CommandResponse executeCommand(TreeMap<Integer, MusicBand> collection, CommandRequest commandRequest, Connection connection) {

        CommandResponse commandResponse = new CommandResponse();

        User user = commandRequest.getUser();
        String commandName = commandRequest.getCommandName();
        String argumentKey = commandRequest.getArgumentKey();
        MusicBand argumentMusicBand = commandRequest.getArgumentMusicBand();

        commandResponse.setUser(user);

        try {

            switch (commandName) { // &
                case ("help"):
                    commandResponse.setMessage(HelpCommand.execute(collection, argumentKey));
                    break;


                case ("info"): // &
                    if (dateOfInitialization == null)
                        commandResponse.setMessage(InfoCommand.execute(collection, "unknown"));
                    else
                        commandResponse.setMessage(InfoCommand.execute(collection, dateOfInitialization));
                    break;


                case ("show"): // &
                    commandResponse.setMessage(ShowCommand.execute(collection, argumentKey));
                    break;


                case ("insert"):
                    commandResponse.setMessage(InsertCommand.execute(collection, connection, user, argumentMusicBand));
                    break;


                case ("update"):
                    commandResponse.setMessage(UpdateCommand.execute(collection, connection, user, argumentKey, argumentMusicBand));
                    break;


                case ("remove_key"):
                    commandResponse.setMessage(RemoveByKeyCommand.execute(collection, connection, user, argumentKey));
                    break;


                case ("clear"):
                    commandResponse.setMessage(ClearCommand.execute(collection, connection, user));
                    break;


//                case ("save"): // Client doesn't have any access to this method
//
//                    try {
//                        FileManager.saveCollectionToFile(collection, filePath);
//                    } catch (IOException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    return null;


                case ("remove_greater"):
                    commandResponse.setMessage(RemoveGreaterCommand.execute(collection, connection, user, argumentMusicBand));
                    break;


                case ("replace_if_lower"):
                    commandResponse.setMessage(ReplaceIfLowerCommand.execute(collection, connection, user, argumentKey, argumentMusicBand));
                    break;


                case ("remove_lower_key"):
                    commandResponse.setMessage(RemoveLowerKeyCommand.execute(collection, connection, user, argumentKey));
                    break;


                case ("remove_all_by_genre"):
                    commandResponse.setMessage(RemoveAllByGenreCommand.execute(collection, connection, user, argumentKey));
                    break;


                case ("max_by_front_man"):
                    commandResponse.setMessage(MaxByFrontManCommand.execute(collection));
                    break;


                case ("filter_greater_than_singles_count"):
                    commandResponse.setMessage(FilterGreaterThanSinglesCountCommand.execute(collection, Integer.parseInt(argumentKey)));
                    break;


                default:
                    commandResponse.setMessage(String.format("Error: Command '%s' isn't supported.", commandName));
                    break;
            }

        } catch (IllegalArgumentException | InvalidCommandException | DBException e) {
            commandResponse.setMessage(e.getMessage());
        }

        return commandResponse;
    }








}
