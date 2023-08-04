package Command;

import Lab.CommandPackage;

import java.io.IOException;
import java.sql.SQLException;

public class Save extends AbstractCommand {
    public Save() {
        this.name = "Save";
        this.help = "save collection to file";
    }

    /**
     * save data to file Person.csv.Don't accept any parameter
     * {@link CommandManager#executeSave(String Saver)}
     *
     * @param commandManager CommandManager
     * @throws IOException,ParaInapproException by executeSave
     */
    public void execute(CommandManager commandManager, CommandPackage commandPackage) throws IOException,SQLException, ClassNotFoundException {
        commandManager.executeSave(commandPackage.getFileName());
    }
}
