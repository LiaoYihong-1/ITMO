package Command;

import Collection.NullException;
import Lab.CommandPackage;

public class Average extends AbstractCommand {
    public Average() {
        this.name = "Average";
        this.help = "out put the average of the heights of all collections(People)";
    }

    public void execute(CommandManager commandManager, CommandPackage commandPackage) throws ParaInapproException, NullException {
        commandManager.executeAverage();
    }
}
