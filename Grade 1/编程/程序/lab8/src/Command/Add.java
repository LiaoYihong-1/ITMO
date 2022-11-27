package Command;

import Collection.CollectionsofPerson;
import Collection.Person;
import Lab.ClientInformation;
import Lab.MainRequest;

import java.sql.*;

/**
 * Add command allows user set and add a new element to collection
 */
public class Add extends AbstractCommand {
    public Add() {
        this.name = "add";
        this.help = "add a new element to collection";
    }

    /**
     * when length of command line argument bigger than 1,throw ParaInapproException
     * {@link Command.CommandManager#executeAdd(Person)}
     *
     * @param manager CommandManager
     */
    public void execute(CommandManager manager, MainRequest request, CollectionsofPerson collection) throws SQLException {
        int a = request.getCommandPackage().getPerson().getId();
        int b = Person.getidcode();
        Person person = request.getCommandPackage().getPerson();
        if(a<=b){
            Integer newcode = Person.getidcode()+1;
            person.resetid(newcode);
        }
        ClientInformation clientInformation = request.getCilentInformation();
        String sq = "jdbc:postgresql://" + clientInformation.getIp() + ":" + clientInformation.getPort() + "/" + clientInformation.getDatabase();
        try(Connection connection = DriverManager.getConnection(sq,clientInformation.getUser(),clientInformation.getPassword())){
            try(PreparedStatement ps = connection.prepareStatement("INSERT INTO people (name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday,username,password) values (?,?,?,?,?,?,?,?,?,?,?,?)")){
                ps.setObject(1,person.getName());
                ps.setObject(2,person.getHairColor().toString());
                ps.setObject(3,person.getEyeColor().toString());
                ps.setObject(4,person.getHeight());
                ps.setObject(5,person.getLocation().getName());
                ps.setObject(6,person.getLocation().getX());
                ps.setObject(7,person.getLocation().getY());
                ps.setObject(8,person.getLocation().getZ());
                ps.setObject(9,person.getLocation().getCreationdate().toString());
                ps.setObject(10,person.getBirthday().toString());
                ps.setObject(11,person.getHost());
                ps.setObject(12,request.getCilentInformation().getHash());
                ps.executeUpdate();
            }
            }
        collection.add(person);
        Person.setIdcode(person.getId());
        manager.setOut("You add person:\n" + person.toString() + "to collection\n", false);
    }
}
