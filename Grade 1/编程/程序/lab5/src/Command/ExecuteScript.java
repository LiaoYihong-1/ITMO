package Command;

import java.io.IOException;

/**
 * command ExecuteScript
 */
public class ExecuteScript extends AbstractCommand {
    private int time=0;
    public ExecuteScript(){
        this.name="ExecuteScript";
        this.help=" read and execute a script from the specified file. The script contains commands in the same form in which the user enters them interactively.";
    }

    /**
     * when length of parameter bigger than 2 or smaller than 1,throw ParaInapproException
     * {@link CommandManager#executeExecuteScript(String, CommandManager,String )}
     * @param commandManager
     * @param args
     * @throws IOException
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager,String args[],String Saver) throws IOException,ParaInapproException {
        if (time == 0) {
            if (args.length > 2 || args.length == 1) {
                throw new ParaInapproException("this command only and must accepts one parameter\n");
            } else {
                commandManager.executeExecuteScript(args[1], commandManager,Saver);
            }
        }
    }
}
