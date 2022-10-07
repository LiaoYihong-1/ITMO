package Command;

/**
 * command Help
 */
public class Help extends AbstractCommand {
    public Help(){
        this.name="Help";
        this.help="display help for available commands";
    }

    /**
     * print helps of all commands.Don't accept any parameter
     * {@link CommandManager#executeHelp()}
     * @param cm
     * @param args
     * @throws ParaInapproException
     */
    public void execute(CommandManager cm,String args[],String Saver) throws ParaInapproException{
        if(args.length>1){
            throw new ParaInapproException("this command don't accept any parameter\n");
        }else {
            cm.executeHelp();
        }
    }
}
