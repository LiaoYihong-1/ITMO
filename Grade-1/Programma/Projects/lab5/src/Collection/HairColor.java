package Collection;

/**
 * the color of hair
 * @author liao yihong
 */
public enum HairColor{
    RED,
    BLACK,
    ORANGE,
    WHITE;

    /**
     * show all the elements in String
     * @return
     */
    public static String List() {
        String list="";
        for(HairColor hairColor:HairColor.values()){
            list=list+hairColor.name()+"\n";
        }
        return list;
    }
}
