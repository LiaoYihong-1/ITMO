package Command;

import Collection.CollectionsofPerson;
import Collection.NullException;
import Collection.Person;
import Lab.CommandPackage;

import java.util.LinkedHashSet;

/**
 * command removebyid
 */
public class RemoveById extends AbstractCommand {
    public RemoveById() {
        this.name = "RemoveByid";
        this.help = "remove one element from the collection whose eyeColor field value is equivalent to the specified one";
    }

    /**
     * remove a element that contains specified id of collections.Only accept one int parameter
     * {@link CommandManager#executeRemoveById(Integer)}
     *
     * @throws ParaInapproException when para incorrect
     */
    public void execute(CommandManager manager, CommandPackage commandPackage) throws NullException {
        if (new CollectionsofPerson().getPeople().size() != 0) {
            LinkedHashSet<Person> newone = new LinkedHashSet<>();
            new CollectionsofPerson().getPeople().stream().filter(People -> !People.getId().equals(commandPackage.getPerson().getId())).forEach(newone::add);
            CollectionsofPerson.setPeople(newone);
            manager.setOut("You removed:\n" + commandPackage.getPerson().toString(), false);
        } else {
            throw new NullException("Collection is empty\n");
        }
    }
}
