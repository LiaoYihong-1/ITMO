package Command;

/**
 * Command Exit
 */
public class Exit extends AbstractCommand {
    public Exit(){
        this.name="Exit";
        this.help="end the program(won't save)";
    }

    /**
     * finish program.When contains any parameter,throws ParaInapproException
     * {@link CommandManager#executeExit()}
     * @param commandManager
     * @param args
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager,String args[],String Saver) throws ParaInapproException{
        if(args.length>1){
            throw new ParaInapproException("this command don't accept any parameter\n");
        }else {
            commandManager.executeExit();
        }
    }
}
