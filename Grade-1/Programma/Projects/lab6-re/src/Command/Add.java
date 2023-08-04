package Command;

import Collection.*;
import Lab.CommandPackage;

/**
 * Add command allows user set and add a new element to collection
 */
public class Add extends AbstractCommand {
    public Add(){
        this.name="add";
        this.help="add a new element to collection";
    }

    /**
     * when length of command line argument bigger than 1,throw ParaInapproException
     * {@link Command.CommandManager#executeAdd(Person)}
     * @param manager CommandManager
     * @param commandPackage CommandPackage
     */
    public void execute(CommandManager manager, CommandPackage commandPackage){
        new CollectionsofPerson().getPeople().add(commandPackage.getPerson());
        manager.setOut("You add person:\n"+commandPackage.getPerson().toString()+"to collection\n",false);
    }
}
