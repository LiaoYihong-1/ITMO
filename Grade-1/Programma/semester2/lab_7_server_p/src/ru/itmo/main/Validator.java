package ru.itmo.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {

    // Validator создан для того, чтобы можно было удобно работать с входными значениями, которые означают null
    private static String[] symbolsForNullValues;

    static {
        symbolsForNullValues = new String[]{};
    }


    public static int validateIntegerPrimitive(String value) {
        validateNotNullArgs(value);
        return Integer.parseInt(value);
    }


    public static double validateDoublePrimitive(String value) {
        validateNotNullArgs(value);
        return Double.parseDouble(value);
    }


    public static long validateLongPrimitive(String value) {
        validateNotNullArgs(value);
        return Long.parseLong(value);
    }


    public static Integer validateInteger(String value) {
        validateNotNullArgs(value);
        if (isNullValue(value)) return null;
        else return Integer.parseInt(value);
    }


    public static Double validateDouble(String value) {
        validateNotNullArgs(value);
        if (isNullValue(value)) return null;
        else return Double.parseDouble(value);
    }


    public static Long validateLong(String value) {
        validateNotNullArgs(value);
        if (isNullValue(value)) return null;
        else return Long.parseLong(value);
    }


    public static String validateString(String value) {
        validateNotNullArgs(value);
        if (isNullValue(value)) return null;
        else return value;
    }


    public static <T extends Enum<T>> T validateEnum(Class<T> enumType, String value) {
        validateNotNullArgs(enumType, value);
        if (isNullValue(value)) return null;
        try {
            return Enum.valueOf(enumType, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }


    public static List validateArguments(String[] inputArgs, String[] validationArgs, String[] symbolsForNullValues) {

        validateNotNullArgs(inputArgs, validationArgs, symbolsForNullValues);

        ArrayList<String> inputArgsList = new ArrayList(Arrays.asList(inputArgs));
        ArrayList<String> validationArgsList = new ArrayList<>(Arrays.asList(validationArgs));
        ArrayList<String> symbolsForNullValuesList = new ArrayList<>(Arrays.asList(symbolsForNullValues));
        ArrayList validatedList = new ArrayList();

        if (inputArgsList.size() != validationArgsList.size())
            throw new NumberFormatException("Error: inputArgs array and validationArgs array are different sizes.");

        for (int i = 0; i < inputArgsList.size(); i++) {
            switch (validationArgsList.get(i)) {

                case ("Integer"):
                    if (symbolsForNullValuesList.contains(inputArgsList.get(i))) validatedList.add(null);
                    else validatedList.add(Integer.parseInt(inputArgsList.get(i)));
                    break;

                case ("Long"):
                    if (symbolsForNullValuesList.contains(inputArgsList.get(i))) validatedList.add(null);
                    else validatedList.add(Long.parseLong(inputArgsList.get(i)));
                    break;

                case ("Double"):
                    if (symbolsForNullValuesList.contains(inputArgsList.get(i))) validatedList.add(null);
                    else validatedList.add(Double.parseDouble(inputArgsList.get(i)));
                    break;

                case ("int"):
                    if (symbolsForNullValuesList.contains(inputArgsList.get(i)))
                        throw new NumberFormatException("Error: int primitive can't be null.");
                    else validatedList.add(Integer.parseInt(inputArgsList.get(i)));
                    break;

                case ("long"):
                    if (symbolsForNullValuesList.contains(inputArgsList.get(i)))
                        throw new NumberFormatException("Error: long primitive can't be null.");
                    else validatedList.add(Long.parseLong(inputArgsList.get(i)));
                    break;

                case ("double"):
                    if (symbolsForNullValuesList.contains(inputArgsList.get(i)))
                        throw new NumberFormatException("Error: long primitive can't be null.");
                    else validatedList.add(Double.parseDouble(inputArgsList.get(i)));
                    break;

                case ("String"):
                    if (symbolsForNullValuesList.contains(inputArgsList.get(i))) validatedList.add(null);
                    else validatedList.add(inputArgsList.get(i));
                    break;
                default:
                    throw new NumberFormatException(String.format("Unsupported type : '%s'", validationArgsList.get(i)));

            }
        }
        return validatedList;
    }


    public static List validateArguments(String[] inputArgs, String[] validationArgs) {
        return validateArguments(inputArgs, validationArgs, symbolsForNullValues);
    }


    public static Object validateArguments(String inputParam, String validationParam) {
        return validateArguments(new String[]{inputParam}, new String[] {validationParam}).get(0);
    }


    private static void validateNotNullArgs(Object...args) {
        for (Object arg : args) {
            if (arg == null) throw new NumberFormatException("Error: Some of the input is null. Can't validate.");
        }
    }


    private static boolean isNullValue(String value) {
        return (Arrays.asList(symbolsForNullValues).contains(value));
    }


    public static String[] getSymbolsForNullValues() {
        return symbolsForNullValues;
    }


    public static void setSymbolsForNullValues(String[] symbolsForNullValues) {
        Validator.symbolsForNullValues = symbolsForNullValues;
    }
}
