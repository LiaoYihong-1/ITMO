package Command;

/**
 * command removebyid
 */
public class RemoveById extends AbstractCommand {
    public RemoveById(){
        this.name="RemoveByid";
        this.help="remove one element from the collection whose eyeColor field value is equivalent to the specified one";
    }

    /**
     * remove a element that contains specified id of collections.Only accept one int parameter
     * {@link CommandManager#executeRemoveById(Integer)}
     * @param commandManager
     * @param args
     * @throws ParaInapproException
     * @throws NumberFormatException
     */
    public void execute(CommandManager commandManager,String [] args,String Saver) throws ParaInapproException,NumberFormatException{
        if(args.length>2||args.length==1){
            throw new ParaInapproException("this command only and must accept one parameter\n");
        }else {
            if(Integer.valueOf(args[1])<=0){
                throw new ParaInapproException("parameter should bigger than 0\n");
            }else {
                commandManager.executeRemoveById(Integer.valueOf(args[1]));
            }
        }
    }
}
