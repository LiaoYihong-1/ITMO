package Command;

import Lab.CommandPackage;

/**
 * command Info
 */
public class Info extends AbstractCommand {
    public Info() {
        this.name = "Info";
        this.help = "print information about the collection (type, date of initialization, number of elements, etc.) to standard output";
    }

    /**
     * print all the information about collection.Don't accept any parameter
     * {@link CommandManager#executeInfo()}
     *
     * @param commandManager CommandManager
     * @throws ParaInapproException by executeInfo
     */
    public void execute(CommandManager commandManager, CommandPackage commandPackage) throws ParaInapproException {
        commandManager.executeInfo();
    }
}
