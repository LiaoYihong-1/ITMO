package ru.itmo.classes;

import java.io.Serializable;
import java.util.Arrays;

public enum Country implements Serializable {
    GERMANY,
    CHINA,
    ITALY;

    public static String showValues() {
        return Arrays.toString(Country.values());
    }

}
