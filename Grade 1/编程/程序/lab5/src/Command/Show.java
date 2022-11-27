package Command;

import Collection.NullException;

public class Show extends AbstractCommand {
    public Show(){
        this.name="show";
        this.help="display all elements of the collection in string representation to standard output";
    }

    /**
     * show all the elements in the collection.Don't accept any parameter
     * {@link CommandManager#executeShow()}
     * @param commandManager
     * @param args
     * @throws ParaInapproException
     * @throws NullException
     */
    public void execute(CommandManager commandManager,String args[],String Saver) throws ParaInapproException,NullException{
        if(args.length>1){
            throw new ParaInapproException("this command don't accept any parameter\n");
        }else {
            commandManager.executeShow();
        }
    }
}
