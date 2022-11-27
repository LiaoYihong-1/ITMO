package Command;

import Lab.CommandPackage;

import java.util.ArrayList;

/**
 * Command History.
 */
public class History extends AbstractCommand {
    public History() {
        this.name = "History";
        this.help = "print the last 7 commands (without their arguments)";
    }

    public static ArrayList<String> history = new ArrayList<>();

    /**
     * print last 7 commands.Don accept any parameter.
     * {@link CommandManager#executeHistory()}
     *
     * @param commandManager
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager, CommandPackage commandPackage) throws ParaInapproException {
        commandManager.executeHistory();
    }

    /**
     * get static ArrayList history
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getHistory() {
        return history;
    }
}
