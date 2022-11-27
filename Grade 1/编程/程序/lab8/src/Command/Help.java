package Command;

import Collection.CollectionsofPerson;
import Lab.CommandPackage;
import Lab.MainRequest;

import java.io.Serializable;

/**
 * command Help
 */
public class Help extends AbstractCommand implements Serializable {
    public Help() {
        this.name = "Help";
        this.help = "display help for available commands";
    }

    /**
     * print helps of all commands.Don't accept any parameter
     * {@link CommandManager#executeHelp()}
     *
     * @param cm CommandManager
     * @throws ParaInapproException thrown by executeHelp
     */
    public void execute(CommandManager cm, MainRequest request, CollectionsofPerson collection) throws ParaInapproException {
        cm.executeHelp();
    }
}
