package Command;

import Collection.CollectionsofPerson;
import Collection.NullException;
import Lab.CommandPackage;
import Lab.MainRequest;

public class Show extends AbstractCommand {
    public Show() {
        this.name = "show";
        this.help = "display all elements of the collection in string representation to standard output";
    }

    /**
     * show all the elements in the collection.Don't accept any parameter
     *
     * @param commandManager CommandPackage
     * @throws ParaInapproException,NullException by executeShow()
     */
    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws ParaInapproException, NullException {
        commandManager.executeShow(collection);
    }
}
