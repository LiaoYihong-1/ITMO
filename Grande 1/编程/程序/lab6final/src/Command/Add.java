package Command;

import Collection.CollectionsofPerson;
import Collection.Person;
import Lab.CommandPackage;

/**
 * Add command allows user set and add a new element to collection
 */
public class Add extends AbstractCommand {
    public Add() {
        this.name = "add";
        this.help = "add a new element to collection";
    }

    /**
     * when length of command line argument bigger than 1,throw ParaInapproException
     * {@link Command.CommandManager#executeAdd(Person)}
     *
     * @param manager        CommandManager
     * @param commandPackage CommandPackage
     */
    public void execute(CommandManager manager, CommandPackage commandPackage) {
        int a = commandPackage.getPerson().getId();
        int b = Person.getidcode();
        Person person = commandPackage.getPerson();
        if(a<=b){
            Integer newcode = Person.getidcode()+1;
            person.resetid(newcode);
        }
        new CollectionsofPerson().getPeople().add(person);
        Person.setIdcode(person.getId());
        manager.setOut("You add person:\n" + person.toString() + "to collection\n", false);
    }
}
