package Command;

import Collection.CollectionsofPerson;
import Collection.Person;
import Lab.ClientInformation;
import Lab.MainRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashSet;

/**
 * command clear
 */
public class Clear extends AbstractCommand {
    public Clear() {
        this.name = "Clear";
        this.help = "clear collections";
    }

    /**
     * clear the collections
     *
     * @param commandManager
     * @throws ParaInapproException
     */
    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws SQLException, ParaInapproException {
        ClientInformation clientInformation = request.getCilentInformation();
        String sq = "jdbc:postgresql://" + clientInformation.getIp() + ":" + clientInformation.getPort() + "/" + clientInformation.getDatabase();
        try(Connection connection = DriverManager.getConnection(sq,"s291007","pgt813")) {
            try (PreparedStatement ps = connection.prepareStatement("TRUNCATE TABLE people RESTART IDENTITY ")) {
                ps.executeUpdate();
            }
        }
        commandManager.setOut("Now collection is cleared anyway\n", false);
        LinkedHashSet<Person> set = collection.getPeople();
        set.clear();
        collection.setPeople(set);
        Person.setIdcode(0);
    }
}
