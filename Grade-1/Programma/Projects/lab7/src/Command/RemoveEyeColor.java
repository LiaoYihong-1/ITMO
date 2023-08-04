package Command;

import Collection.CollectionsofPerson;
import Collection.EyeColor;
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
 * command RemovEeyeColor
 */
public class RemoveEyeColor extends AbstractCommand {
    public RemoveEyeColor() {
        this.name = "RemoveEyeColor";
        this.help = "remove one element from the collection whose eyeColor field value is equivalent to the specified one";
    }

    /**
     * remove the element with specified eye color.Only accept one parameter*
     * @param manager CommandManger
     */
    public void execute(CommandManager manager, MainRequest request, CollectionsofPerson collection) throws SQLException {
        try {
            if (collection.getPeople().size() != 0) {
                HashSet<Person> set = manager.findbyEye(request.getCommandPackage().getArg()[1].toUpperCase(), collection);
                LinkedHashSet<Person> delete = new LinkedHashSet<>();
                if (set != null) {
                    ClientInformation clientInformation = request.getCilentInformation();
                    String sq = "jdbc:postgresql://" + clientInformation.getIp() + ":" + clientInformation.getPort() + "/" + clientInformation.getDatabase();
                    try (Connection connection = DriverManager.getConnection(sq, "s291007", "pgt813")) {
                        for (Person p : set) {
                            if(p.getHost().equals(request.getCilentInformation().getUser())) {
                                try (PreparedStatement ps = connection.prepareStatement("DELETE FROM people WHERE id=?")) {
                                    ps.setObject(1, p.getId());
                                    ps.executeUpdate();
                                    delete.add(p);
                                    collection.remove(p);
                                }
                            }
                        }
                    }
                    manager.setOut("", false);
                    for (Person P : delete) {
                        manager.setOut("You removed:\n" + P.toString(), true);
                    }
                } else {
                    manager.setOut("No such person with this id\n", false);
                }
            } else {
                throw new NullException("Collection is empty\n");
            }
        }catch (IllegalArgumentException illegalArgumentException){
            manager.setOut("No such eyecolor!\n",false);
        }
    }
}
