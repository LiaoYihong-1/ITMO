package Command;

import Collection.NullException;

/**
 * command RemoveGreater
 */
public class RemoveGreater extends AbstractCommand {
    public RemoveGreater(){
        this.name="RemoveGreater";
        this.help="remove all elements greater than the specified one from the collection";
    }

    /**
     * remove all the elements,whose id bigger than input.
     * {@link CommandManager#executeRemoveGreater(Integer)}
     * @param commandManager
     * @param args
     * @throws ParaInapproException,Collection.NullException
     */
    public void execute(CommandManager commandManager,String [] args,String Saver) throws ParaInapproException, NullException {
        if(args.length>2||args.length==1){
            throw new ParaInapproException("this command only and must accept one number parameter\n");
        }else {
            commandManager.executeRemoveGreater(Integer.valueOf(args[1]));
        }
    }
}
