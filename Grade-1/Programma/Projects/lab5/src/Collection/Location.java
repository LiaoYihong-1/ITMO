package Collection;

import java.time.LocalDate;

/**
 * class Location
 */
public class Location {
    private Integer x;//Поле не может быть null
    private double y;
    private Integer z;//Поле не может быть null
    private String name; //Поле может быть null
    private Coordinates coordinates;//Здесь я сам добавил поль. Использую делегирование.
    private LocalDate creationdate=LocalDate.now();

    /**
     * constructor Location
     * @param coordinates
     * @param name
     * @param z
     * @throws NullException
     * @throws ValueTooBigException
     */
    public Location(Coordinates coordinates,String name,Integer z) throws NullException,ValueTooBigException{
        setCoordinates(coordinates);
        setName(name);
        setZ(z);
        setX(coordinates.getX());
        setY(coordinates.getY().longValue());
    }

    /**
     * set the para coordinates.When coordinates is null, throw NullException
     * @param coordinates
     * @throws NullException
     */
    public void setCoordinates(Coordinates coordinates) throws NullException{
        if(coordinates==null){
            throw new NullException("coordinates can't be null\n");
        }else {
            this.coordinates=coordinates;
        }
    }

    /**
     * set the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set z.When z is null,throws NullException
     * @param z
     * @throws NullException
     */
    public void setZ(Integer z) throws NullException{
        if(z==null){
            throw new NullException("z can't be null\n");
        }else {
            this.z = z;
        }
    }

    /**
     * set x.When x null,throws NullException
     * @param x
     * @throws NullException
     */
    public void setX(Integer x) throws NullException{
        if(x==null){
            throw new NullException("x can't be null\n");
        }else {
            this.x = x;
        }
    }

    /**
     * set y
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * get x
     * @return x
     */
    public Integer getX() {
        return x;
    }

    /**
     * get creationdate
     * @return LocalDate
     */
    public LocalDate getCreationdate() {
        return creationdate;
    }

    /**
     * get name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get coordinate
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * get y
     * @return double
     */
    public double getY() {
        return y;
    }

    /**
     * get z
     * @return Integer
     */
    public Integer getZ() {
        return z;
    }

    /**
     * print the information(values of parameters) of a person
     * @return String
     */
    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                ", creationdate=" + creationdate +
                '}';
    }
}
