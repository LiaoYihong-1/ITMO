package Collection;

/**
 * the color of hair
 *
 * @author liao yihong
 */
public enum HairColor {
    RED,
    BLACK,
    ORANGE,
    WHITE;

    /**
     * show all the elements in String
     *
     * @return
     */
    public static String List() {
        StringBuilder list = new StringBuilder();
        for (HairColor hairColor : HairColor.values()) {
            list.append(hairColor.name()).append("\n");
        }
        return list.toString();
    }
}
