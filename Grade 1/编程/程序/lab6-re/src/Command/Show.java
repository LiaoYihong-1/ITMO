package Command;

import Collection.NullException;
import Lab.CommandPackage;

public class Show extends AbstractCommand {
    public Show(){
        this.name="show";
        this.help="display all elements of the collection in string representation to standard output";
    }

    /**
     * show all the elements in the collection.Don't accept any parameter
     * {@link CommandManager#executeShow()}
     * @param commandManager CommandPackage
     * @throws ParaInapproException,NullException by executeShow()
     */
    public void execute(CommandManager commandManager,CommandPackage commandPackage) throws ParaInapproException,NullException{
        commandManager.executeShow();
    }
}
