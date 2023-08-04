package Command;

import Collection.CollectionsofPerson;
import Lab.CommandPackage;
import Lab.MainRequest;

/**
 * command ExecuteScript
 */
public class ExecuteScript extends AbstractCommand {
    public ExecuteScript() {
        this.name = "ExecuteScript";
        this.help = " read and execute a script from the specified file. The script contains commands in the same form in which the user enters them interactively.";
    }

    /**
     * meaningless in lab6
     */
    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) {
        commandManager.executeScript(request.getCommandPackage().getArg());
    }
}
