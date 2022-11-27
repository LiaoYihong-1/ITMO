package Command;

import CSV.CSVWriter;
import Collection.*;
import Tools.Tools;
import java.io.*;
import java.util.*;

/**
 * command manager
 */
public class CommandManager {
    public CommandManager(){
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
        commands.add(new Save());
        commands.add(new Show());
        commands.add(new UpdateID());
    }

    private static LinkedHashSet<AbstractCommand> commands=new LinkedHashSet<>();
    private boolean findid=false;

    /**
     * get static LinkedHashSet<AbstrcteCommand> commands
     * @return LinkedHashSet
     */
    public LinkedHashSet getCommands() {
        return this.commands;
    }

    /**
     * use Iterator to read LinkedHashSet<AbstractCommand> commands,and print all helps
     */
    public void executeHelp() {
        for (AbstractCommand A : commands) {
            System.out.print(A.getName() + ":" + A.getHelp() + "\n");
        }
    }

    /**
     * user set and add a new object with the help of static method {@link Person#PeopleCreate()}
     * @param person
     * @throws ValueTooBigException
     * @throws ValueTooSmallException
     * @throws NullException
     */
    public void executeAdd(Person person) throws ValueTooBigException,ValueTooSmallException,NullException{
        new CollectionsofPerson().doInitialization();
        new CollectionsofPerson().getPeople().add(person);
    }

    /**
     * If user set a person smaller than him, add the new person
     * @param p
     */
    public void executeAddifmin(Person p) {
        boolean judge=true;
        Iterator<Person> iterator=new CollectionsofPerson().getPeople().iterator();
        while(iterator.hasNext()){
            if(p.compareTo(iterator.next())==1){
                judge=false;
            }
        }
        if(judge==true){
            new CollectionsofPerson().getPeople().add(p);
        }
    }

    /**
     * print the average of height
     */
    public void executeAverage() {
        Iterator<Person> iterator=new CollectionsofPerson().getPeople().iterator();
        if(iterator.hasNext()) {
            Integer Total = 0;
            while (iterator.hasNext()) {
                Total = Total + iterator.next().getHeight();
            }
            System.out.print(Total / (new CollectionsofPerson().getPeople().size()) + "\n");
        }else{
            throw new NullException("collections of people still empty\n");
        }
    }

    /**
     * clear all elements in collections
     */
    public void executeClear() {
        new CollectionsofPerson().getPeople().clear();
    }

    /**
     * exit the program
     */
    public void executeExit() {
        System.exit(2);
    }

    /**
     * print the information of collection(type,amount of elements,when it is created)
     */
    public void executeInfo() {
        if(!CollectionsofPerson.Initialization){
            throw new NotInitializationException("collections was initialized");
        }else {
            System.out.print("the date of initialization is "+new CollectionsofPerson().getInitializationTime()+"\n");
        }
        System.out.print("the amount of elements is "+ new CollectionsofPerson().getPeople().size()+"\n");
        System.out.print("the type of collection is "+ new CollectionsofPerson().getPeople().getClass() +"\n");
    }

    /**
     * print all the elements
     * When collections is empty,throw NullException
     * {@link CommandManager#findByLocationHash(Integer)}
     * {@link CommandManager#getValues()}
     * @throws NullException
     */
    public void executePrint() throws NullException{
        if(new CollectionsofPerson().getPeople().size()==0){
            System.out.print("collections still empty\n");
        }else {
            Integer[] list = getValues();
            int[] values = new int[list.length];
            for (int i = 0; i < values.length; i++) {
                values[i] = list[i];
            }
            Arrays.sort(values);
            for (int i = 0; i < values.length; i++) {
                findByLocationHash(values[i]);
            }
        }
    }

    /**
     * get the location of all people
     * @return
     */
    private LinkedHashSet<Location> getLocations(){
        LinkedHashSet<Location> linkedHashSet=new LinkedHashSet<>();
        Iterator<Person> iterator=new CollectionsofPerson().getPeople().iterator();
        while(iterator.hasNext()){
            linkedHashSet.add(iterator.next().getLocation());
        }
        return linkedHashSet;
    }

    /**
     * return the values(hashcode) of all locations
     * {@link CommandManager#getLocations()}
     * @return Integer[]
     */
    private Integer[] getValues(){
        LinkedHashSet<Integer> arrayList=new LinkedHashSet<>();
        Iterator<Location> iterator=getLocations().iterator();
        while(iterator.hasNext()){
            arrayList.add(iterator.next().hashCode());
        }
        Integer []list=arrayList.toArray(new Integer[arrayList.size()]);
        return list;
    }

    /**
     * print the location with specified hashcode
     * @param value
     */
    private void findByLocationHash(Integer value){
        Location location;
        Location print;
        Iterator<Person> iterator=new CollectionsofPerson().getPeople().iterator();
        out:while (iterator.hasNext()){
            if((location=iterator.next().getLocation()).hashCode()==value){
                print=location;
                System.out.print(print.toString()+"\n");
                break out;
            }
        }
    }

    public void executeRemoveById(Integer id) {
        Person p=findByid(id);
        if(findid==false){
            throw new ParaInapproException("no such a person with this id\n");
        }
        findid=false;
        new CollectionsofPerson().getPeople().remove(p);
    }

    /**
     * return person with specified id
     * @param id
     * @return Person
     */
    private Person findByid(Integer id){
        Person p=null;
        Person m;
        Iterator<Person> iterator=new CollectionsofPerson().getPeople().iterator();
        out:while(iterator.hasNext()){
            if((m = iterator.next()).getId().equals(id)){
                p=m;
                findid=true;
                break out;
            }
        }
        return p;
    }

    /**
     * remove person with specified eye color
     * {@link CommandManager#findbyEye(String)}
     * @param eye
     */
    public void executeRemoveEyeColor(String eye){
        for(Person p:findbyEye(eye)) {
            new CollectionsofPerson().getPeople().remove(p);
        }
    }

    /**
     * find person with Specified eye color
     * @param eye
     * @return
     */
    private LinkedHashSet<Person> findbyEye(String eye){
        LinkedHashSet<Person> linkedHashSet=new LinkedHashSet<>();
        Person A;
        EyeColor eyeColor=EyeColor.valueOf(eye);
        Iterator<Person> iterator=new CollectionsofPerson().getPeople().iterator();
        while(iterator.hasNext()){
            if((A=iterator.next()).getEyeColor()==eyeColor){
                linkedHashSet.add(A);
            }
        }
        return linkedHashSet;
    }

    /**
     * Remove all the people,whose if bigger than specified
     * {@link CommandManager#findByid(Integer)}
     * @param in
     * @throws NullException
     */
    public void executeRemoveGreater(Integer in) throws NullException{
        Person B=findByid(Integer.valueOf(in));
        if(B==null){
            throw new NullException("No element is available");
        }else {
            Person p;
            Iterator<Person> iterator=new CollectionsofPerson().getPeople().iterator();
            while(iterator.hasNext()){
                if((p=iterator.next()).compareTo(B)==1){
                    new CollectionsofPerson().getPeople().remove(p);
                }
            }
        }
    }

    /**
     * write static LinkedHashSet in format csv
     * {@link CSVWriter#WriterToFile(LinkedHashSet, String)}
     * @throws IOException
     */
    public void executeSave(String path) throws IOException {
        LinkedHashSet<Person> linkedHashSet=new CollectionsofPerson().getPeople();
        new CSVWriter().WriterToFile(linkedHashSet,path);
    }

    /**
     * print all the elements
     */
    public void executeShow() {
        if(new CollectionsofPerson().getPeople().size()==0){
            System.out.print("collections of people still empty\n");
        }else{
            new Tools().PrintPersonSet(new CollectionsofPerson().getPeople());
        }
    }

    /**
     * reset element with specified id.
     * {@link CommandManager#findByid(Integer)}
     * @param
     * @throws ParaInapproException
     */
    public void executeUpdateID(String in) throws ParaInapproException{
        Integer id=Integer.valueOf(in);
        Person p;
        Iterator<Person> iterator=new CollectionsofPerson().getPeople().iterator();
        out:while(iterator.hasNext()){
            if(findByid(id)==null){
                throw new ParaInapproException("no such a id\n");
            }
            if((p=iterator.next()).getId()==id){
                new CollectionsofPerson().getPeople().remove(p);
                Person insert=Person.PeopleCreate();
                insert.changeId(id);
                Person.balaceicode();
                new CollectionsofPerson().getPeople().add(insert);
                break out;
            }
        }
    }

    /**
     * print the last 7 commands
     */
    public void executeHistory() {
        int size=new History().getHistory().size();
        Iterator<String> iterator=new History().getHistory().iterator();
        if(new History().getHistory().size()<=7){
            while(iterator.hasNext()){
                System.out.print(iterator.next());
            }
        }else{
            while(size>7){
                iterator.next();
                size--;
            }
            while(iterator.hasNext()){
                System.out.print(iterator.next());
            }
        }
    }

    /**
     * Execute the command written in file
     * {@link CommandManager#findCommand(String)}
     * @param name
     * @throws IOException
     * @throws ParaInapproException
     */
    public void executeExecuteScript(String name,CommandManager commandManager,String Saver) throws IOException,ParaInapproException{
        FileReader f=new FileReader(new File(name));
        BufferedReader bufferedReader=new BufferedReader(f);
        String commandtext="";
        while((commandtext=bufferedReader.readLine())!=null){
            String []split=commandtext.split(" ");
            AbstractCommand command=findCommand(split[0]);
            if(command!=null&&!(command.getName().equals("ExecuteScript")&&split[1].equals(name))) {
                command.execute(commandManager, split,Saver);
                new History().getHistory().add(command.getName() + "\n");
            }
        }
        bufferedReader.close();
    }

    /**
     * find the command with specified name
     * @param name
     * @return
     */
    public AbstractCommand findCommand(String name){
        AbstractCommand A=null;
        AbstractCommand B;
        for (AbstractCommand command : commands) {
            if ((B = command).getName().equalsIgnoreCase(name)) {
                A = B;
            }
        }
        return A;
    }
}

