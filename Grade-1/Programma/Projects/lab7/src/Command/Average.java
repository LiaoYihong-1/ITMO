package Command;

import Collection.CollectionsofPerson;
import Collection.NullException;
import Lab.MainRequest;

public class Average extends AbstractCommand {
    public Average() {
        this.name = "Average";
        this.help = "out put the average of the heights of all collections(People)";
    }

    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws ParaInapproException, NullException {
        commandManager.executeAverage(collection);
    }
}
