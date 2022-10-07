package Command;
import java.util.ArrayList;

/**
 * Command History.
 */
public class History extends AbstractCommand {
    public History(){
        this.name="History";
        this.help="print the last 7 commands (without their arguments)";
    }

    public static ArrayList<String> history=new ArrayList<>();

    /**
     * print last 7 commands.Don accept any parameter.
     * {@link CommandManager#executeHistory()}
     * @param commandManager
     * @param args
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager,String args[],String Saver) throws ParaInapproException{
        if(args.length>1){
            throw new ParaInapproException("this command don't accept any parameter\n");
        }else {
            commandManager.executeHistory();
        }
    }

    /**
     * get static ArrayList history
     * @return ArrayList<String>
     */
    public ArrayList<String> getHistory(){
        return history;
    }
}
