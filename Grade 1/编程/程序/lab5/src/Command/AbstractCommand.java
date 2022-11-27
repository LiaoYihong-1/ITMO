package Command;

import Collection.NullException;
import Collection.ValueTooSmallException;
import java.io.IOException;

/**
 * the basic module of a command
 */
public abstract class AbstractCommand {
    protected String name;
    protected String help;

    /**
     * return name of the command
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * return help of the command
     * @return String
     */
    public String getHelp() {
        return help;
    }

    /**
     * how command execute. But here i set all the details in CommandManager, so that we can unit the amount and type of parameters
     * @param commandManager
     * @param args
     * @throws IOException
     * @throws ParaInapproException
     * @throws NullException
     * @throws ValueTooSmallException
     * @throws ValueTooSmallException
     */
    abstract public void execute(CommandManager commandManager,String args[],String Saver) throws IOException,ParaInapproException, NullException, ValueTooSmallException,ValueTooSmallException;
}
