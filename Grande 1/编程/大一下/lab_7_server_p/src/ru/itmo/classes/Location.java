package ru.itmo.classes;

import java.io.Serializable;
import java.util.HashMap;

public class Location implements Serializable {
    private Integer x;
    private int y;
    private String name;

    public static HashMap<String, String> locationFieldsDescription;

    public Location(Integer x, int y, String name) {
        this.setX(x);
        this.setY(y);
        this.setName(name);
    }

    public Location() {}

    static {
        locationFieldsDescription = new HashMap<>();
        locationFieldsDescription.put("x",    "Field 'x' {Integer} location coordinate can't be null.");
        locationFieldsDescription.put("y",    "Field 'y' {int} location coordinate.");
        locationFieldsDescription.put("name", "Field location 'name' {String} can't be null or empty.");
    }


    @Override
    public String toString() {
        return "Location{" + "\n" +
                "            x = .. " + x + "\n" +
                "            y = .. " + y + "\n" +
                "            name = " + name + "\n" +
                "        }";
    }

    public Integer getX() {
        return x;
    }


    public void setX(Integer x) {
        if (x == null) throw new IllegalArgumentException(locationFieldsDescription.get("x"));
        this.x = x;
    }


    public int getY() {
        return y;
    }


    public void setY(int y) { //No information about 'y' values
        this.y = y;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        if (name == null || name.isEmpty() || name.matches("[\\s]*")) throw new IllegalArgumentException(locationFieldsDescription.get("name"));
        this.name = name;
    }
}
