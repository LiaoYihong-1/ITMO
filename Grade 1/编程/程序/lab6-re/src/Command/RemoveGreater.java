package Command;

import Lab.CommandPackage;

/**
 * command RemoveGreater
 */
public class RemoveGreater extends AbstractCommand {
    public RemoveGreater(){
        this.name="RemoveGreater";
        this.help="remove all elements greater than the specified one from the collection";
    }

    /**
     * remove all the elements,who is bigger than input.
     * {@link CommandManager#executeRemoveGreater(Integer)}
     * @param commandManager CommandManager
     * @throws ParaInapproException,Collection.NullException by executeRemoveGreater
     */
    public void execute(CommandManager commandManager,CommandPackage commandPackage){
        commandManager.executeRemoveGreater(commandPackage.getPerson().getId());
    }
}
