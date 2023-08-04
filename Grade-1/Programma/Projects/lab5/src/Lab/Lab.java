package Lab;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashSet;

import CSV.CSVReader;
import Collection.*;
import Command.*;
import Tools.Tools;

//命令行参数，把所有的Person.csv改为args[0]
public class Lab {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String args []) throws IOException {
        try{
            if((args.length!=1)){
                throw new ParaInapproException("this system only accept input of one file name\n");
            }
        }catch (ParaInapproException e){
            System.out.print(e.getMessage());
        }
        int idset = 0;
        CommandManager commandManager=new CommandManager();
        new CollectionsofPerson().doInitialization();
        File file=new File("Person.csv");
        if(!file.exists()){
            file.createNewFile();
        }else{
            LinkedHashSet<Person> linkedHashSet = new LinkedHashSet<>();
            new CSVReader().ReadFile(linkedHashSet, "Person.csv");
            Iterator<Person> iteratorP = linkedHashSet.iterator();
            Person P;
            while (iteratorP.hasNext()) {
                if (idset < (P = iteratorP.next()).getId()) {
                    idset = P.getId();
                }
            }
        }
        Person.idcode=idset;
        while(true) {
            boolean exist=false;//make sure command exists
            AbstractCommand abstractCommand;
            Iterator<AbstractCommand> iterator = commandManager.getCommands().iterator();
            System.out.print("input your command:\n");
            String[] command = Tools.Input().split(" ");
            try {
                while (iterator.hasNext()) {
                    if ((abstractCommand = iterator.next()).getName().equalsIgnoreCase(command[0])) {
                        abstractCommand.execute(commandManager, command,"Person.csv");
                        new History().getHistory().add(abstractCommand.getName()+"\n");
                        exist=true;//set true when command exists
                    }
                }
                if(!exist){
                    throw new NoSuchCommandException("No such command, please enter another one\n");
                }
            }catch (NoSuchCommandException e){
                System.out.print(e.getMessage());
            }catch (ParaInapproException e){
                System.out.print(e.getMessage());
                System.out.print("Input another command\n");
            }catch (NullException e){
                System.out.print(e.getMessage()+"\n");
            }catch (NumberFormatException e){
                System.out.print("Here should enter a number instead of char\n");
            }catch (ValueTooSmallException e){
                System.out.print(e.getMessage());
            }catch (ValueTooBigException e){
                System.out.print(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.print(e.getMessage()+"\n");
            }
        }
    }
}
