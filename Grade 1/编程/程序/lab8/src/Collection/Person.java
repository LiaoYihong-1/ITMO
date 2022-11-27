package Collection;

import Tools.Tools;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Collections class Person
 */
public class Person implements Comparable<Person>, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int height; //Значение поля должно быть больше 0
    private java.time.ZonedDateTime birthday; //Поле может быть null
    private EyeColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null
    private Location location; //Поле не может быть null
    private String host;
    public static int idcode = 0;

    /**
     * constructor
     *
     * @param location  Location
     * @param hairColor color of Hair
     * @param eyeColor  color of eyes
     * @param name      name of person
     * @param height    height
     * @throws NullException                               when set location or eyecolor as null
     * @throws ValueTooSmallException,ValueTooBigException when value of height or x,y,z not suitable
     */
    public Person(Location location, HairColor hairColor, EyeColor eyeColor, String name, int height) throws NullException, ValueTooSmallException, ValueTooBigException {
        setName(name);
        setLocation(location);
        setCoordinates(location.getCoordinates());
        setHeight(height);
        setEyeColor(eyeColor);
        setHairColor(hairColor);
        this.id = setId();
        this.creationDate = location.getCreationdate();
        this.birthday = ZonedDateTime.now();
        this.host = null;
    }

    /**
     * set id
     *
     * @return void
     */
    public static Integer setId() {
        idcode = idcode + 1;
        return idcode;
    }

    public void setHost(String host){
        this.host = host;
    }


    public void resetid(Integer id){
        this.id = id;
    }

    public static void setIdcode(Integer code){
        idcode = code;
    }

    public static void balaceicode() {
        idcode = idcode - 1;
    }

    /**
     * set name
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set coordinates.When coordinates=null,throws NullException
     *
     * @param coordinates Coordinates
     * @throws NullException when Coordinate set as null
     */
    public void setCoordinates(Coordinates coordinates) throws NullException {
        if (coordinates == null) {
            throw new NullException("coordinates can't be null");
        } else {
            this.coordinates = coordinates;
        }
    }

    /**
     * set the creationdate
     *
     * @param creationDate When location was created
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * set birthday
     *
     * @param birthday when Person was born
     */
    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    /**
     * set eyecolor.When eyecolor=null throws NullException
     *
     * @param eyeColor Eyecolor
     * @throws NullException when set eyecolor as null
     */
    public void setEyeColor(EyeColor eyeColor) throws NullException {
        if (eyeColor == null) {
            throw new NullException("eyecolor can't be null\n");
        } else {
            this.eyeColor = eyeColor;
        }
    }

    /**
     * set location.When location==null throws NullException
     *
     * @param location Location
     * @throws NullException when set Location as null
     */
    public void setLocation(Location location) throws NullException {
        if (location == null) {
            throw new NullException("location can't be null\n");
        }
        this.location = location;
    }

    /**
     * set height.When height<=0 throws ValueTooSmallException
     *
     * @param height int
     * @throws ValueTooSmallException when height smaller than 0
     */
    public void setHeight(int height) throws ValueTooSmallException {
        if (!(height > 0)) {
            throw new ValueTooSmallException("height should bigger than 0\n");
        } else {
            this.height = height;
        }
    }

    /**
     * set hair color.When haircolor=null throws NullException
     *
     * @param hairColor HairColor
     * @throws NullException when haircolor set as null
     */
    public void setHairColor(HairColor hairColor) throws NullException {
        if (hairColor == null) {
            throw new NullException("haircolor can't be null\n");
        } else {
            this.hairColor = hairColor;
        }
    }

    /**
     * return the name of person
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * return the color of a person
     *
     * @return String
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /**
     * return the id of a person
     *
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    public String getHost() {
        return host;
    }

    /**
     * return the height of a perosn
     *
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * return the location of a person
     *
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    /**
     * set the id of a person
     *
     * @param id Integer
     */
    public void changeId(Integer id) {
        this.id = id;
    }

    public static Integer getidcode(){
        return idcode;
    }

    /**
     * here we set toString in order to write Person to the file in format csv.
     *
     * @return information of a person
     */
    @Override
    public String toString() {
        return this.id + "," + this.name + "," + this.hairColor + "," + this.eyeColor + "," + this.height + "," + this.location.getName() + "," + this.location.getX() + "," + this.coordinates.getY() + "," + this.location.getZ() + "," + this.creationDate + "," + this.birthday + ","+this.host+"\n";
    }

    /**
     * user set a person by himself
     *
     * @return Person
     * @throws NullException,ValueTooBigException,ValueTooSmallException just like {@link Collection.Person#Person}
     */
    public static Person peoplecreate() throws NullException, ValueTooBigException, ValueTooSmallException, IllegalArgumentException {
        System.out.print("You have two chances to initialize every property\n");

        String sx;
        Integer x;
        try {
            System.out.print("Input x(<=79):\n");
            sx = Tools.input();
            x = Integer.valueOf(sx);
            if (x > 79)
                throw new ValueTooBigException("x should smaller than 79\n");
        } catch (ValueTooBigException v) {
            System.out.print(v.getMessage() + "try again:\n");
            sx = Tools.input();
            x = Integer.valueOf(sx);
            if (x > 79)
                throw new ValueTooBigException("x should smaller than 79\n");
        } catch (NumberFormatException N) {
            System.out.print("enter a number instead of a string\n" + "try again:\n");
            sx = Tools.input();
            x = Integer.valueOf(sx);
            if (x > 79)
                throw new ValueTooBigException("x should smaller than 79\n");
        }

        String sy;
        Integer y;
        try {
            System.out.print("Input y(y<=794):\n");
            sy = Tools.input();
            y = Integer.valueOf(sy);
            if (y > 794)
                throw new ValueTooBigException("y should smaller than 794\n");
        } catch (ValueTooBigException v) {
            System.out.print(v.getMessage() + "try again:\n");
            sy = Tools.input();
            y = Integer.valueOf(sy);
            if (y > 794)
                throw new ValueTooBigException("y should smaller than 794\n");
        } catch (NumberFormatException N) {
            System.out.print("please enter a number instead of string\n" + "try again:\n");
            sy = Tools.input();
            y = Integer.valueOf(sy);
            if (y > 794)
                throw new ValueTooBigException("y should smaller than 794");
        }

        String sz;
        Integer z;
        try {
            System.out.print("Input z(>0):\n");
            sz = Tools.input();
            z = Integer.valueOf(sz);
            if (z <= 0)
                throw new ValueTooSmallException("z should bigger than 0\n");
        } catch (ValueTooSmallException v) {
            System.out.print(v.getMessage() + "try again:\n");
            sz = Tools.input();
            z = Integer.valueOf(sz);
            if (z <= 0)
                throw new ValueTooSmallException("z should bigger than 0\n");
        } catch (NumberFormatException N) {
            System.out.print("please enter a number instead of string\n" + "try again:\n");
            sz = Tools.input();
            z = Integer.valueOf(sy);
            if (z > 794)
                throw new ValueTooBigException("y should smaller than 794");
        }

        System.out.print("name of location:\n");
        String nameoflocation = Tools.input();

        System.out.print("name of Person:\n");
        String name = Tools.input();

        String HC;
        HairColor hairColor;
        try {
            System.out.print("set hairColor from\n" + HairColor.List() + ":");
            HC = Tools.input();
            HC = HC.toUpperCase();
            hairColor = HairColor.valueOf(HC);
        } catch (IllegalArgumentException i) {
            System.out.print("no Such color in list\n" + "try again:\n");
            HC = Tools.input();
            HC = HC.toUpperCase();
            hairColor = HairColor.valueOf(HC);
        }
        String EC;
        EyeColor eyeColor;
        try {
            System.out.print("set eyecolor from\n" + EyeColor.List() + ":");
            EC = Tools.input();
            EC = EC.toUpperCase();
            eyeColor = EyeColor.valueOf(EC);
        } catch (IllegalArgumentException i) {
            System.out.print("no such color in list\n" + "try again:\n");
            EC = Tools.input();
            EC = EC.toUpperCase();
            eyeColor = EyeColor.valueOf(EC);
        }
        String H;
        int height;
        try {
            System.out.print("set height(>0)\n");
            H = Tools.input();
            height = Integer.valueOf(H);
            if (height <= 0) {
                throw new ValueTooSmallException("height should bigger than 0\n");
            }
        } catch (ValueTooSmallException v) {
            System.out.print(v.getMessage() + "try again:\n");
            H = Tools.input();
            height = Integer.valueOf(H);
            if (height <= 0)
                throw new ValueTooSmallException("height should bigger than 0\n");
        }
        Coordinates coordinates = new Coordinates(x, y);
        Location location = new Location(coordinates, nameoflocation, z);
        return new Person(location, hairColor, eyeColor, name, height);
    }


    /**
     * implements from comparable.
     * Used to do comparation
     *
     * @param o another Person
     * @return 0-the same as another,-1-smaller,1-bigger
     */
    @Override
    public int compareTo(Person o) {
        if (o == null) {
            throw new NullPointerException("Null can't be compared");
        } else if (this.getId().equals(o.getId())) {
            return 0;
        } else if (this.getId() > o.getId()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * @param o Person
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return height == person.height &&
                id.equals(person.id) &&
                name.equals(person.name) &&
                coordinates.equals(person.coordinates) &&
                creationDate.equals(person.creationDate) &&
                Objects.equals(birthday, person.birthday) &&
                eyeColor == person.eyeColor &&
                hairColor == person.hairColor &&
                location.equals(person.location);
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, height, birthday, eyeColor, hairColor, location);
    }
}
