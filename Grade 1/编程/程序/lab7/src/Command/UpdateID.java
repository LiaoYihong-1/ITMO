package Command;

import Collection.CollectionsofPerson;
import Lab.MainRequest;

import java.sql.SQLException;

/***
 * Command UpdateID
 */
public class UpdateID extends AbstractCommand {
    public UpdateID() {
        this.name = "UpdateID";
        this.help = "update the value of the collection item whose id is equal to the given";
    }

    /**
     * reset elements with specified id
     *
     * @param commandManager CommandManager
     * @throws ParaInapproException  created when parameter incorrect
     * @throws NumberFormatException thrown by executeUpdateID
     */
    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws ParaInapproException, NumberFormatException, SQLException {
        commandManager.executeUpdateID(request.getCommandPackage().getArg()[0],request.getCommandPackage().getPerson(),collection, request.getCilentInformation(),request);
    }
}
