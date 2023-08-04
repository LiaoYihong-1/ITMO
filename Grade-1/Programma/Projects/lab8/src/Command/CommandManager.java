package Command;

import CSV.CSVWriter;
import Collection.*;
import Lab.ClientInformation;
import Lab.MainRequest;

import java.sql.*;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * command manager
 */
public class CommandManager {
    public CommandManager() {
        commands.add(new Add());
        commands.add(new Addifmin());
        commands.add(new Average());
        commands.add(new Clear());
        commands.add(new ExecuteScript());
        commands.add(new Exit());
        commands.add(new Help());
        commands.add(new History());
        commands.add(new Info());
        commands.add(new Print());
        commands.add(new RemoveById());
        commands.add(new RemoveEyeColor());
        commands.add(new RemoveGreater());
        commands.add(new Show());
        commands.add(new UpdateID());
    }

    private final LinkedHashSet<AbstractCommand> commands = new LinkedHashSet<>();
    private boolean findid = false;

    //set as response of Server
    private String out = "";

    public void setOut(String out, boolean ADD) {
        if (!ADD) {
            this.out = out;
        } else {
            this.out = this.out + out;
        }
    }

    public String getOut() {
        return out;
    }

    /**
     * get static LinkedHashSet<AbstrcteCommand> commands
     *
     * @return LinkedHashSet
     */
    public LinkedHashSet<AbstractCommand> getCommands() {
        return this.commands;
    }

    /**
     * use Iterator to read LinkedHashSet<AbstractCommand> commands,and print all helps
     */
    public void executeHelp() {
        setOut("All helps:\n", false);
        commands.stream().forEach(command -> {
            setOut(command.getName() + ":" + command.getHelp() + "\n", true);
        });
    }

    /**
     * user set and add a new object with the help of static method {@link Person#peoplecreate()}
     *
     * @param person
     * @throws ValueTooBigException
     * @throws ValueTooSmallException
     * @throws NullException
     */
    public void executeAdd(Person person) throws ValueTooBigException, ValueTooSmallException, NullException {
        new CollectionsofPerson().doInitialization();
        new CollectionsofPerson().getPeople().add(person);
    }

    /**
     * print the average of height
     */
    public void executeAverage(CollectionsofPerson collection) {
        Integer result;
        Iterator<Person> iterator = collection.getPeople().iterator();
        if (iterator.hasNext()) {
            Integer Total = 0;
            while (iterator.hasNext()) {
                Total = Total + iterator.next().getHeight();
            }
            result = Total / (collection.getPeople().size());
            setOut("the average of heights is " + result + "\n", false);
            System.out.print(out);
        } else {
            setOut("collections of people is still empty\n", false);
            throw new NullException("collections of people is still empty\n");
        }
    }

    /**
     * clear all elements in collections
     */
    public void executeClear(CollectionsofPerson collectionsofPerson) throws NullException {
        setOut("Now collection is cleared anyway\n", false);
        LinkedHashSet<Person> set = collectionsofPerson.getPeople();
        set.clear();
        collectionsofPerson.setPeople(set);
    }

    /**
     * exit the program
     */
    public void executeExit() {
        setOut("Exit", false);
    }

    /**
     * print the information of collection(type,amount of elements,when it is created)
     */
    public void executeInfo(CollectionsofPerson collection) {
        if (!collection.Initialization) {
            throw new NotInitializationException("collections wasn't initialized");
        } else {
            System.out.print("the date of initialization is " + new CollectionsofPerson().getInitializationTime() + "\n");
        }
        setOut("the amount of elements is " + collection.getPeople().size() + "\n" + "the type of collection is " + collection.getPeople().getClass() + "\n", false);
        System.out.print(out);
    }

    /**
     * print all the elements
     * When collections is empty,throw NullException
     *
     * @throws NullException
     */
    public void executePrint(CollectionsofPerson collection) throws NullException {
        if (collection.getPeople().size() == 0) {
            setOut("Collection is still empty\n", false);
            System.out.print(out);
        } else {
            setOut("Informations of locations:\n", false);
            collection.getPeople().stream().forEach(P -> setOut(P.getId() + ":" + P.getLocation().toString(), true));
        }
    }

    /**
     * get the location of all people
     *
     * @return
     */
    private LinkedHashSet<Location> getLocations() {
        LinkedHashSet<Location> linkedHashSet = new LinkedHashSet<>();
        Iterator<Person> iterator = new CollectionsofPerson().getPeople().iterator();
        while (iterator.hasNext()) {
            linkedHashSet.add(iterator.next().getLocation());
        }
        return linkedHashSet;
    }

    public void executeRemoveById(Integer id, CollectionsofPerson collection) {
        Person p = findByid(id,collection);
        if (findid == false) {
            throw new ParaInapproException("no such a person with this id\n");
        } else {
            findid = false;
            new CollectionsofPerson().getPeople().remove(p);
        }
    }

    /**
     * return person with specified id
     *
     * @param id
     * @return Person
     */
    public Person findByid(Integer id,CollectionsofPerson collection) {
        Person p = null;
        Person m;
        Iterator<Person> iterator =collection.getPeople().iterator();
        out:
        while (iterator.hasNext()) {
            if ((m = iterator.next()).getId().equals(id)) {
                p = m;
                findid = true;
                break out;
            }
        }
        if (!findid) {
            findid = false;
            throw new NullException("no such a person with this id\n");
        }
        findid = false;
        return p;
    }

    /**
     * find person with Specified eye color
     *
     * @param eye
     * @return
     */
    public LinkedHashSet<Person> findbyEye(String eye,CollectionsofPerson collection) throws IllegalArgumentException{
        LinkedHashSet<Person> linkedHashSet = new LinkedHashSet<>();
        Person A;
        EyeColor eyeColor = EyeColor.valueOf(eye.toUpperCase());
        Iterator<Person> iterator = collection.getPeople().iterator();
        while (iterator.hasNext()) {
            if ((A = iterator.next()).getEyeColor()==eyeColor) {
                linkedHashSet.add(A);
            }
        }
        return linkedHashSet;
    }

    /**
     * write static LinkedHashSet in format csv
     * {@link CSVWriter#writetofile(LinkedHashSet, String)}
     *
     * @throws IOException
     */
    public void executeSave(ClientInformation path) throws IOException,ClassNotFoundException,SQLException{
        /*LinkedHashSet<Person> linkedHashSet = new CollectionsofPerson().getPeople();
        new CSVWriter().writetofile(linkedHashSet, path);*/
        Class.forName("org.postgresql.Driver");
        HashSet<Person> people = new CollectionsofPerson().getPeople();
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/studs","postgres","123456")){
            try(PreparedStatement ps = connection.prepareStatement("INSERT INTO people (name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday,username) values (?,?,?,?,?,?,?,?,?,?,?)")){
                for(Person P:people){
                    ps.setObject(1,P.getName());
                    ps.setObject(2,P.getHairColor().toString());
                    ps.setObject(3,P.getEyeColor().toString());
                    ps.setObject(4,P.getHeight());
                    ps.setObject(5,P.getLocation().getName());
                    ps.setObject(6,P.getLocation().getX());
                    ps.setObject(7,P.getLocation().getY());
                    ps.setObject(8,P.getLocation().getZ());
                    ps.setObject(9,P.getLocation().getCreationdate().toString());
                    ps.setObject(10,P.getBirthday().toString());
                    ps.setObject(11,P.getHost());
                    int n = ps.executeUpdate();
                    System.out.print(n);
                }
            }
        }
    }

    /**
     * print all the elements
     */
    public void executeShow(CollectionsofPerson collection) {
        String Show = "";
        setOut("Informations of people:\n" + "id,name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday\n", false);
        if (collection.getPeople().size() == 0) {
            setOut("Collection of people is still empty\n", false);
        } else {
            collection.getPeople().stream().forEach(P -> setOut(P.toString(), true));
        }
    }

    public void executeUpdateID(String in, Person P, CollectionsofPerson collection, ClientInformation clientInformation, MainRequest request) throws ParaInapproException,SQLException{
        try {
            Integer id = Integer.valueOf(in);
            Person p;
            String before = "";
            String after = "";
            boolean match = false;
            Iterator<Person> iterator = collection.getPeople().iterator();
            out:
            while (iterator.hasNext()) {
                if (findByid(id, collection) == null) {
                    throw new ParaInapproException("no such a person with this id\n");
                }
                if ((p = iterator.next()).getId().equals(id)) {
                    if (p.getHost().equals(P.getHost())) {
                        match = true;
                        String sq = "jdbc:postgresql://" + clientInformation.getIp() + ":" + clientInformation.getPort() + "/" + clientInformation.getDatabase();
                        try (Connection connection = DriverManager.getConnection(sq, clientInformation.getUser(), clientInformation.getPassword())) {
                            try (PreparedStatement ps = connection.prepareStatement("UPDATE people SET name=?,haircolor=?,eyecolor=?,height=?,location=?,x=?,y=?,z=?,creationdate=?,birthday=?,username=?,password=? WHERE id=?")) {
                                ps.setObject(1, P.getName());
                                ps.setObject(2, P.getHairColor().toString());
                                ps.setObject(3, P.getEyeColor().toString());
                                ps.setObject(4, P.getHeight());
                                ps.setObject(5, P.getLocation().getName());
                                ps.setObject(6, P.getLocation().getX());
                                ps.setObject(7, P.getLocation().getY());
                                ps.setObject(8, P.getLocation().getZ());
                                ps.setObject(9, P.getLocation().getCreationdate().toString());
                                ps.setObject(10, P.getBirthday().toString());
                                ps.setObject(11, P.getHost());
                                ps.setObject(12, request.getCilentInformation().getHash());
                                ps.setObject(13, p.getId());
                                ps.executeUpdate();
                            }
                        }
                        collection.remove(p);
                        before = p.toString();
                        Person insert = P;
                        insert.changeId(id);
                        collection.add(insert);
                        after = insert.toString();
                        setOut("You replace the\n" + before + "to\n" + after, false);
                    }
                    break out;
                }
            }
            if (!match) {
                setOut("You can't update a person that doesn't belong your account!\n", false);
            }
        }catch (NullPointerException N){
            setOut("No such a id\n",false);
        }
    }


    /**
     * print the last 7 commands
     */
    public void executeHistory() {
        String history = "";
        int size = new History().getHistory().size();
        Iterator<String> iterator = new History().getHistory().iterator();
        if (new History().getHistory().size() <= 7) {
            while (iterator.hasNext()) {
                history = history + iterator.next();
            }
        } else {
            while (size > 7) {
                iterator.next();
                size--;
            }
            while (iterator.hasNext()) {
                history = history + iterator.next();
            }
        }
        setOut("last 7 commands:\n" + history, false);
    }

    public void executeScript(String[] commarg) {
        setOut("you execute script " + commarg[1] + "\n", false);
    }
}