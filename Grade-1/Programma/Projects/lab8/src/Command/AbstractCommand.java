package Command;

import Collection.CollectionsofPerson;
import Collection.NullException;
import Collection.ValueTooSmallException;
import Lab.CommandPackage;
import Lab.MainRequest;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * the basic module of a command
 */
public abstract class AbstractCommand implements Serializable {
    protected String name;
    protected String help;

    /**
     * return name of the command
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * return help of the command
     *
     * @return String
     */
    public String getHelp() {
        return help;
    }

    /**
     * how command execute. But here i set all the details in CommandManager, so that we can unit the amount and type of parameters
     *
     * @param commandManager CommandManager
     * @param commandPackage CommandPackage
     * @throws IOException,ParaInapproException,NullException,ValueTooSmallException,ValueTooSmallException depends on commands
     */
    abstract public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws IOException, ParaInapproException, NullException, ValueTooSmallException, ClassNotFoundException, SQLException;
}
