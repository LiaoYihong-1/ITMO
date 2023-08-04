package Command;

import Collection.NullException;

/**
 * command clear
 */
public class Clear extends AbstractCommand {
    public Clear(){
        this.name="Clear";
        this.help="clear collections";
    }

    /**
     * clear the collections
     * {@link CommandManager#executeClear()}
     * @param commandManager
     * @param args
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager,String args[],String Saver) throws ParaInapproException {
        if(args.length>1){
            throw new ParaInapproException("this command don't accept any parameter\n");
        }else if(args.length==0){
            throw new NullException("please input the name of command");
        }else {
            commandManager.executeClear();
        }
    }
}
