package ru.itmo.classes;

import java.io.Serializable;
import java.util.HashMap;

public class Coordinates implements Serializable {

    private double x;
    private int y;

    public static HashMap<String, String> coordinatesFieldsDescription;


    public Coordinates(double x, int y) {
        this.setX(x);
        this.setY(y);
    }


    public Coordinates(){}


    static {
        coordinatesFieldsDescription = new HashMap<>();
        coordinatesFieldsDescription.put("x", "Field 'x' {double} coordinate must be greater than -965.");
        coordinatesFieldsDescription.put("y", "Field 'y' {int} coordinate must not be greater than 649.");
    }


    @Override
    public String toString() {
        return "Coordinates{" + "\n" +
                "        x = " + x + "\n" +
                "        y = " + y + "\n" +
                "    }";
    }


    public double getX() {
        return x;
    }


    public void setX(double x) {
        if (x <= -965) throw new IllegalArgumentException(coordinatesFieldsDescription.get("x"));
        this.x = x;
    }


    public int getY() {
        return y;
    }


    public void setY(int y) {
        if (y > 649) throw new IllegalArgumentException(coordinatesFieldsDescription.get("y"));
        this.y = y;
    }
}
