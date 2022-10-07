package Lab;

import Collection.Person;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class Response implements Serializable {

    Response(Integer id,LinkedHashSet<Person> people, String managerout){
        this.people = people;
        this.managerout = managerout;
        this.id = id;
    }

    Response(Integer id,String managerout){
        this.people = null;
        this.id = id;
        this.managerout = managerout;
    }

    private final LinkedHashSet<Person> people;

    private final String managerout;

    private final Integer id;

    private boolean Exception = false;

    public LinkedHashSet<Person> getPeople() {
        return people;
    }

    public String getManagerout() {
        return managerout;
    }

    public Integer getId() {
        return id;
    }

    public void setException(boolean exception) {
        Exception = exception;
    }

    public boolean isException() {
        return Exception;
    }
}
