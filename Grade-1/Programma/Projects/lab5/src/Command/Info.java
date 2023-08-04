package Command;

/**
 * command Info
 */
public class Info extends AbstractCommand {
    public Info(){
        this.name="Info";
        this.help="print information about the collection (type, date of initialization, number of elements, etc.) to standard output";
    }

    /**
     * print all the information about collection.Don't accept any parameter
     * {@link CommandManager#executeInfo()}
     * @param commandManager
     * @param args
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager,String args[],String Saver) throws ParaInapproException{
        if(args.length>1){
            throw new ParaInapproException("this command don't accept any parameter\n");
        }else {
            commandManager.executeInfo();
        }
    }
}
