package ru.itmo.classes;

import java.io.Serializable;
import java.util.Arrays;

public enum Color implements Serializable {
    GREEN,
    RED,
    ORANGE,
    WHITE;

    public static String showValues() {
        return Arrays.toString(Color.values());
    }
}
