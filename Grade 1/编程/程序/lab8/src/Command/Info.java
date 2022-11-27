package Command;

import Collection.CollectionsofPerson;
import Lab.CommandPackage;
import Lab.MainRequest;

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
     *
     * @param commandManager CommandManager
     * @throws ParaInapproException by executeInfo
     */
    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws ParaInapproException {
        commandManager.executeInfo(collection);
    }
}
