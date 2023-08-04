package Command;

import Collection.*;
import java.util.Iterator;

public class Average extends AbstractCommand {
    public Average(){
        this.name="Average";
        this.help="out put the average of the heights of all collections(People)";
    }

    public void execute(CommandManager commandManager,String args[],String Saver) throws ParaInapproException{
        if(args.length>1){
            throw new ParaInapproException("this command don't accept any parameter\n");
        } else {
            commandManager.executeAverage();
        }
    }
}
