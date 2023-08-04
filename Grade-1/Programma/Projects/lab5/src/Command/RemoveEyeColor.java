package Command;

import Collection.*;

/**
 * command RemovEeyeColor
 */
public class RemoveEyeColor extends AbstractCommand{
    public RemoveEyeColor() {
        this.name = "RemoveEyeColor";
        this.help = "remove one element from the collection whose eyeColor field value is equivalent to the specified one";
    }

    /**
     * remove the element with specified eye color.Only accept one parameter
     *{@link CommandManager#executeRemoveEyeColor(String)}
     * @param commandManager
     * @param args
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager,String args[],String Saver) throws ParaInapproException{
        if(args.length>2||args.length==1){
            throw new ParaInapproException("this command only accept one parameter\n");
        }else {
            boolean belong=false;
            out:for(EyeColor eyeColor:EyeColor.values()){
                if(eyeColor.name().equalsIgnoreCase(args[1])){
                    belong=true;
                    break out;
                }
            }
            if(!belong){
                throw new ParaInapproException("no such color\n");
            }else {
                commandManager.executeRemoveEyeColor(args[1].toUpperCase());
            }
        }
    }
}
