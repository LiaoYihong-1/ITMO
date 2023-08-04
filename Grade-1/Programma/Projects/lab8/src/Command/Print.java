package Command;

import Collection.CollectionsofPerson;
import Collection.NullException;
import Lab.CommandPackage;
import Lab.MainRequest;

/**
 * command Print
 */
public class Print extends AbstractCommand {
    public Print() {
        this.name = "print";
        this.help = "print the location field values of all elements in ascending order";
    }


    /**
     * print all the location of collections
     * @param commandManager empty
     * @throws ParaInapproException by executePrint()
     * @throws NullException        by executePrint()
     */
    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws ParaInapproException, NullException {
        commandManager.executePrint(collection);
    }
}
