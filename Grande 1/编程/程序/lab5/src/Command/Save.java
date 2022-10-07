package Command;

import java.io.IOException;

public class Save extends AbstractCommand {
    Save(){
        this.name="Save";
        this.help="save collection to file";
    }

    /**
     * save data to file Person.csv.Don't accept any parameter
     * {@link CommandManager#executeSave(String Saver)}
     * @param commandManager
     * @param args
     * @throws IOException
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager,String args[],String Saver) throws IOException,ParaInapproException{
        if(args.length>1){
            throw new ParaInapproException("this command don't accept parameter\n");
        }else {
            commandManager.executeSave(Saver);
        }
    }
}
