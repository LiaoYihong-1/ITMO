package Command;

import Collection.CollectionsofPerson;
import Collection.Person;
import Lab.CommandPackage;
import Lab.MainRequest;

import java.sql.SQLException;
import java.util.LinkedHashSet;

/**
 * command Addifmin
 */
public class Addifmin extends AbstractCommand {
    public Addifmin() {
        this.name = "Addifmin";
        this.help = "add a new element to the collection if its value is less than the smallest element in this collection";
    }

    /**
     * add if hashcode of new element smaller than others
     *
     * @param commandManager CommandManage
     * @throws ParaInapproException by executeAddifmin
     */
    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws ParaInapproException, SQLException {
        Person p = request.getCommandPackage().getPerson();
        LinkedHashSet<Person> judge = new LinkedHashSet<>();
        collection.getPeople().stream().filter(P -> p.compareTo(P) > 0).forEach(judge::add);
        if (judge.size() == 0) {
            new Add().execute(commandManager,request,collection);
            collection.add(p);
            commandManager.setOut("You add\n" + p.toString() + "to collection\n", false);
        } else {
            commandManager.setOut("Failed to add\n", false);
            Person.balaceicode();
        }
    }
}
