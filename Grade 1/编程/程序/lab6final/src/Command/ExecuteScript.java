package Command;

import Lab.CommandPackage;

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
    public void execute(CommandManager commandManager, CommandPackage commandPackage) {
        commandManager.executeScript(commandPackage.getArg());
    }
}
