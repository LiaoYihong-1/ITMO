package Command;

import Lab.CommandPackage;

/***
 * Command UpdateID
 */
public class UpdateID extends AbstractCommand {
    public UpdateID() {
        this.name = "UpdateID";
        this.help = "update the value of the collection item whose id is equal to the given";
    }

    /**
     * reset elements with specified id
     * {@link CommandManager#executeUpdateID(String)}
     *
     * @param commandManager CommandManager
     * @throws ParaInapproException  created when parameter incorrect
     * @throws NumberFormatException thrown by executeUpdateID
     */
    public void execute(CommandManager commandManager, CommandPackage commandPackage) throws ParaInapproException, NumberFormatException {
        commandManager.executeUpdateID(commandPackage.getArg()[0], commandPackage.getPerson());
    }
}
