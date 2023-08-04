package ru.itmo.classes;

import java.io.Serializable;
import java.util.HashMap;

public class Person implements Comparable<Person>, Serializable {
    private String name;
    private Long height;
    private Color HeirColor;
    private Country nationality;
    private Location location;

    public static HashMap<String, String> personFieldsDescription;

    public Person(String name, Long height, Color heirColor, Country nationality, Location location) {
        this.setName(name);
        this.setHeight(height);
        this.setHeirColor(heirColor);
        this.setNationality(nationality);
        this.setLocation(location);
    }

    public Person() {}

    static {
        personFieldsDescription = new HashMap<>();
        personFieldsDescription.put("name",        "Field front man 'name' {String} can't be null or empty string.");
        personFieldsDescription.put("height",      "Field front man 'height' {Long} must be null or greater than zero.");
        personFieldsDescription.put("heirColor",   "Field front man 'heirColor' {Color} could be null or must have value from: \n" + Color.showValues());
        personFieldsDescription.put("nationality", "Field front man 'nationality' {Country} could be null or must have value from: \n" + Country.showValues());
        personFieldsDescription.put("location",    "Field front man 'location' {Location} can't be null.");
    }

    @Override
    public String toString() {
        return "Person{" + "\n" +
                "        name = ...... " + name + "\n" +
                "        height = .... " + height + "\n" +
                "        HeirColor = . " + HeirColor + "\n" +
                "        nationality = " + nationality + "\n" + "\n" +
                "        location = " + location + "\n" + "\n" +
                "    }";
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        if (name == null || name.matches("[\\s]*")) throw new IllegalArgumentException(personFieldsDescription.get("name"));
        this.name = name;
    }


    public Long getHeight() {
        return height;
    }


    public void setHeight(Long height) {
        if (height != null && height <= 0) throw new IllegalArgumentException(personFieldsDescription.get("height"));
        this.height = height;
    }


    public Color getHeirColor() {
        return HeirColor;
    }


    public void setHeirColor(Color heirColor) {
        HeirColor = heirColor; //This field is allowed to be "null"
    }


    public Country getNationality() {
        return nationality;
    }


    public void setNationality(Country nationality) {
        this.nationality = nationality; //This field is allowed to be "null"
    }


    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        if (location == null) throw new IllegalArgumentException(personFieldsDescription.get("location"));
        this.location = location;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        if (this.getHeight() == null)
            return -1;
        else if (anotherPerson.getHeight() == null)
            return 1;
        else return (int) (this.getHeight() - anotherPerson.getHeight());
    }

    public static int compare(Person person, Person anotherPerson) {
        if (person == null & anotherPerson == null) {
            return 1;
        } else if (person == null) {
            return -1;
        } else return person.compareTo(anotherPerson);
    }
}
