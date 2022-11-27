package Command;

import Collection.CollectionsofPerson;
import Collection.NullException;
import Collection.Person;
import Lab.ClientInformation;
import Lab.CommandPackage;
import Lab.MainRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * command RemoveGreater
 */
public class RemoveGreater extends AbstractCommand {
    public RemoveGreater() {
        this.name = "RemoveGreater";
        this.help = "remove all elements greater than the specified one from the collection";
    }

    /**
     * remove all the elements,whose ip is bigger than input.
     *
     * @param commandManager CommandManager
     * @throws ParaInapproException,Collection.NullException by executeRemoveGreater
     */
    public void execute(CommandManager commandManager, MainRequest request, CollectionsofPerson collection) throws SQLException {
        LinkedHashSet<Person> set = new LinkedHashSet<>();
        LinkedHashSet<Person> delete = new LinkedHashSet<>();
        Integer in = request.getCommandPackage().getPerson().getId();
        Person B = commandManager.findByid(Integer.valueOf(in),collection);
        if (B == null) {
            commandManager.setOut("No element is available\n", false);
            throw new NullException("No element is available\n");
        } else {
            ClientInformation clientInformation = request.getCilentInformation();
            collection.getPeople().stream().filter(P -> P.getId()> in).forEach(set::add);
            String sq = "jdbc:postgresql://" + clientInformation.getIp() + ":" + clientInformation.getPort() + "/" + clientInformation.getDatabase();
            try (Connection connection = DriverManager.getConnection(sq, clientInformation.getUser(), clientInformation.getPassword())) {
                for(Person P: set) {
                    if(P.getHost().equals(request.getCilentInformation().getUser())) {
                        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM people WHERE id=?")) {
                            ps.setObject(1, P.getId());
                            ps.executeUpdate();
                            delete.add(P);
                            collection.remove(P);
                        }
                    }
                }
            }
            commandManager.setOut("You remove:\n", false);
            collection.getPeople().stream().filter(P -> P.getId() > in).forEach(P -> {
                commandManager.setOut(P.toString(), true);
            });
        }
    }
}
