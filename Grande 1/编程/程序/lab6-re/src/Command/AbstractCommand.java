package Command;

import Collection.NullException;
import Collection.Person;
import Collection.ValueTooSmallException;
import Lab.CommandPackage;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * the basic module of a command
 */
public abstract class AbstractCommand implements Serializable {
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
     * @param commandPackage
     * @throws IOException
     * @throws ParaInapproException
     * @throws NullException
     * @throws ValueTooSmallException
     * @throws ValueTooSmallException
     */
    abstract public void execute(CommandManager commandManager,CommandPackage commandPackage) throws IOException,ParaInapproException, NullException, ValueTooSmallException,ValueTooSmallException;
}
