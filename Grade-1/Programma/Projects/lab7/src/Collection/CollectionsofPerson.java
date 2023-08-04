package Collection;

import java.time.LocalDate;
import java.util.LinkedHashSet;

/**
 * this class deal with the LinkedHashSet,which save all the people that was set by user.
 */
public class CollectionsofPerson {
    private static LocalDate InitializationTime;
    private LinkedHashSet<Person> people;
    public static boolean Initialization = false;

    /**
     * When static linkedhashset people wasn't initialized,throw NotInitializationException
     *
     * @return static linkedhashset people
     * @throws NotInitializationException while Initialization == true
     */
    public LinkedHashSet<Person> getPeople() throws NotInitializationException {
        if (!Initialization) {
            throw new NotInitializationException("collections wasn't initialized\n");
        } else {
            return people;
        }
    }

    /**
     * just do the initialization of people,at the same time,get the current time of initialization
     */
    public void doInitialization() {
        if (!Initialization) {
            people = new LinkedHashSet<>();
            InitializationTime = LocalDate.now();
            Initialization = true;
        }
    }

    /**
     * return InitializationTime
     *
     * @return LocalDate
     */
    public LocalDate getInitializationTime() {
        return InitializationTime;
    }

    public synchronized void add(Person person){
        this.people.add(person);
    }

    public synchronized void setPeople(LinkedHashSet<Person> newone) {
        this.people = newone;
    }

    public synchronized Person remove(Person P){
        this.people.remove(P);
        return P;
    }

    public synchronized void clear(){
        this.people.clear();
    }

}
