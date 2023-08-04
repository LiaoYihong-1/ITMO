package Command;

import Collection.NullException;
import Lab.CommandPackage;

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
     * {@link CommandManager#executePrint()}
     *
     * @param commandManager empty
     * @throws ParaInapproException by executePrint()
     * @throws NullException        by executePrint()
     */
    public void execute(CommandManager commandManager, CommandPackage commandPackage) throws ParaInapproException, NullException {
        commandManager.executePrint();
    }
}
