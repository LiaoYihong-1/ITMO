package Command;

import Collection.Person;

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
     * {@link CommandManager#executeAddifmin(Person)}
     * @param commandManager
     * @param args
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager,String args[],String Saver) throws ParaInapproException{
        if(args.length>1){
            throw new ParaInapproException("this command don't accept any parameter\n");
        }else {
            commandManager.executeAddifmin(Person.PeopleCreate());
        }
    }
}
