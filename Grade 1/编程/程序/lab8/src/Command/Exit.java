package Command;

import Collection.CollectionsofPerson;
import Lab.CommandPackage;
import Lab.MainRequest;

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
    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws ParaInapproException {
        commandManager.executeExit();
    }
}
