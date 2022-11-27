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
import java.util.LinkedHashSet;

/**
 * command removebyid
 */
public class RemoveById extends AbstractCommand {
    public RemoveById() {
        this.name = "RemoveByid";
        this.help = "remove one element from the collection whose eyeColor field value is equivalent to the specified one";
    }

    /**
     * remove a element that contains specified id of collections.Only accept one int parameter
     *
     * @throws ParaInapproException when para incorrect
     */
    public void execute(CommandManager manager, MainRequest request, CollectionsofPerson collection) throws NullException, SQLException {
        if (collection.getPeople().size() != 0) {
            Person find = manager.findByid(request.getCommandPackage().getPerson().getId(), collection);
            if(find!=null&&find.getHost().equals(request.getCilentInformation().getUser())) {
                ClientInformation clientInformation = request.getCilentInformation();
                String sq = "jdbc:postgresql://" + clientInformation.getIp() + ":" + clientInformation.getPort() + "/" + clientInformation.getDatabase();
                try(Connection connection = DriverManager.getConnection(sq, clientInformation.getUser(), clientInformation.getPassword())){
                    try(PreparedStatement ps = connection.prepareStatement("DELETE FROM people WHERE id=?")){
                        ps.setObject(1,request.getCommandPackage().getPerson().getId());
                        ps.executeUpdate();
                    }
                }
                LinkedHashSet<Person> newone = new LinkedHashSet<>();
                collection.getPeople().stream().filter(People -> !People.getId().equals(request.getCommandPackage().getPerson().getId())).forEach(newone::add);
                collection.setPeople(newone);
                manager.setOut("You removed:\n" + request.getCommandPackage().getPerson().toString(), false);
            }else if(find==null){
                manager.setOut("No such person with this id\n",false);
            }else if(!find.getHost().equals(request.getCilentInformation().getUser())){
                manager.setOut("You can't remove a person that doesn't belong to you\n",false);
            }
        } else {
            throw new NullException("Collection is empty\n");
        }
    }
}
