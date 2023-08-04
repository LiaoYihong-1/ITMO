package Command;

import Lab.CommandPackage;

/**
 * command clear
 */
public class Clear extends AbstractCommand {
    public Clear() {
        this.name = "Clear";
        this.help = "clear collections";
    }

    /**
     * clear the collections
     * {@link CommandManager#executeClear()}
     *
     * @param commandManager
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager, CommandPackage commandPackage) throws ParaInapproException {
        commandManager.executeClear();
    }
}
