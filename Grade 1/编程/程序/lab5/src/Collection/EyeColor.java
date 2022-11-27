package Collection;

/**
 * the colors of Eyes
 * @author liao yihong
 */
public enum EyeColor{
    BLACK,
    YELLOW,
    BROWN;

    /**
     * show all the element with String
     * @return String
     */
    public static String List() {
        String list="";
        for (EyeColor eyeColor : EyeColor.values()) {
            list=list+eyeColor.name()+"\n";
        }
        return list;
    }
}