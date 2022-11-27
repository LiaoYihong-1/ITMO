package Collection;

/**
 * the colors of Eyes
 *
 * @author liao yihong
 */
public enum EyeColor {
    BLACK,
    YELLOW,
    BROWN;

    /**
     * show all the element with String
     *
     * @return String
     */
    public static String List() {
        StringBuilder list = new StringBuilder();
        for (EyeColor eyeColor : EyeColor.values()) {
            list.append(eyeColor.name()).append("\n");
        }
        return list.toString();
    }
}