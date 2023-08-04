package Command;

import Collection.*;
import Lab.CommandPackage;
import java.util.LinkedHashSet;

/**
 * command RemovEeyeColor
 */
public class RemoveEyeColor extends AbstractCommand{
    public RemoveEyeColor() {
        this.name = "RemoveEyeColor";
        this.help = "remove one element from the collection whose eyeColor field value is equivalent to the specified one";
    }

    /**
     * remove the element with specified eye color.Only accept one parameter
     *{@link CommandManager#executeRemoveEyeColor(String)}
     * @param manager CommandManger
     */
    public void execute(CommandManager manager,CommandPackage commandPackage){
        String out = "You remove:\n";
        manager.setOut(out,false);
        LinkedHashSet<Person> newone = new LinkedHashSet<>();
        Person P = commandPackage.getLinkedHashSet().iterator().next();
        new CollectionsofPerson().getPeople().stream().filter(People -> People.getEyeColor()==P.getEyeColor()).forEach(P1 -> manager.setOut(P1.toString(),true));
        new CollectionsofPerson().getPeople().stream().filter(People -> People.getEyeColor()!=P.getEyeColor()).forEach(newone::add);
        CollectionsofPerson.setPeople(newone);
    }
}
