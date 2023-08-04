package Lab;

import Collection.Person;
import Command.AbstractCommand;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;

public class CommandPackage implements Serializable {
    CommandPackage() {
        this.arg = null;
        this.abstractCommand = null;
        this.FileName = null;
        this.P = null;
        this.linkedHashSet = null;
        this.list = null;
        this.Set = false;
    }

    CommandPackage(String[] arg, AbstractCommand command, String FileName) {
        this.arg = arg;
        this.abstractCommand = command;
        this.FileName = FileName;
        this.P = null;
        this.linkedHashSet = null;
        this.list = null;
        this.Set = false;
    }

    CommandPackage(AbstractCommand command, Person people, String FileName) {
        this.FileName = FileName;
        this.P = people;
        this.arg = null;
        this.abstractCommand = command;
        this.linkedHashSet = null;
        this.list = null;
        this.Set = false;
    }

    CommandPackage(AbstractCommand command, LinkedHashSet<Person> linkedHashSet, String FileName) {
        this.FileName = FileName;
        this.linkedHashSet = linkedHashSet;
        this.abstractCommand = command;
        this.arg = null;
        this.P = null;
        this.list = null;
        this.Set = false;
    }

    CommandPackage(String[] S, AbstractCommand command, Person P, String FileName) {
        this.FileName = FileName;
        this.arg = S;
        this.P = P;
        this.abstractCommand = command;
        this.linkedHashSet = null;
        this.list = null;
        this.Set = false;
    }


    CommandPackage(String name,boolean Set){
        this.FileName = name;
        this.linkedHashSet = null;
        this.arg = null;
        this.abstractCommand = null;
        this.P = null;
        this.list = null;
        this.Set = Set;
    }

    CommandPackage(List<CommandPackage> list) {
        this.list = list;
        this.abstractCommand = null;
        this.linkedHashSet = null;
        this.arg = null;
        this.FileName = null;
        this.P = null;
        this.Set = false;
    }

    private final AbstractCommand abstractCommand;
    private final String[] arg;
    private final Person P;
    private final LinkedHashSet<Person> linkedHashSet;
    private static final long serialVersionUID = 1L;
    private final String FileName;
    private final List<CommandPackage> list;
    private final boolean Set;

    public String getFileName() {
        return this.FileName;
    }

    public AbstractCommand getAbstractCommand() {
        return this.abstractCommand;
    }

    public String[] getArg() {
        return this.arg;
    }

    public Person getPerson() {
        return P;
    }

    public LinkedHashSet<Person> getLinkedHashSet() {
        return linkedHashSet;
    }

    public List<CommandPackage> getList() {
        return this.list;
    }

    public boolean isSet() {
        return Set;
    }

    public boolean connect(){
        if(FileName == null|abstractCommand == null| list == null){
            return true;
        }
        else {
            return false;
        }
    }
}
