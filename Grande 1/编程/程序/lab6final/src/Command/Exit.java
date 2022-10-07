package Command;

import Lab.CommandPackage;

/**
 * Command Exit
 */
public class Exit extends AbstractCommand {
    public Exit() {
        this.name = "Exit";
        this.help = "end the program(won't save)";
    }

    /**
     * finish program.When contains any parameter,throws ParaInapproException
     * {@link CommandManager#executeExit()}
     *
     * @param commandManager CommandManager
     * @throws ParaInapproException by execueExit
     */
    public void execute(CommandManager commandManager, CommandPackage commandPackage) throws ParaInapproException {
        commandManager.executeExit();
    }
}
