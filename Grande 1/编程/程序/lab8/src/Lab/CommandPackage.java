package Lab;

import Collection.Person;
import Command.AbstractCommand;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;

public class CommandPackage implements Serializable {
    public CommandPackage() {
        this.arg = null;
        this.abstractCommand = null;
        this.P = null;
        this.linkedHashSet = null;
        this.list = null;
        this.Set = false;
    }

    public CommandPackage(String[] arg, AbstractCommand command) {
        this.arg = arg;
        this.abstractCommand = command;
        this.P = null;
        this.linkedHashSet = null;
        this.list = null;
        this.Set = false;
    }

    public CommandPackage(AbstractCommand command, Person people) {
        this.P = people;
        this.arg = null;
        this.abstractCommand = command;
        this.linkedHashSet = null;
        this.list = null;
        this.Set = false;
    }

    CommandPackage(AbstractCommand command, LinkedHashSet<Person> linkedHashSet) {
        this.linkedHashSet = linkedHashSet;
        this.abstractCommand = command;
        this.arg = null;
        this.P = null;
        this.list = null;
        this.Set = false;
    }

    public CommandPackage(String[] S, AbstractCommand command, Person P) {
        this.arg = S;
        this.P = P;
        this.abstractCommand = command;
        this.linkedHashSet = null;
        this.list = null;
        this.Set = false;
    }

    public CommandPackage(List<CommandPackage> list) {
        this.list = list;
        this.abstractCommand = null;
        this.linkedHashSet = null;
        this.arg = null;
        this.P = null;
        this.Set = false;
    }

    private final AbstractCommand abstractCommand;
    private final String[] arg;
    private final Person P;
    private final LinkedHashSet<Person> linkedHashSet;
    private static final long serialVersionUID = 1L;
    private final List<CommandPackage> list;
    private final boolean Set;

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

}
