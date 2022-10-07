package Collection;

import java.io.Serializable;

/**
 * class Coordinate,one of collection,which helps set location
 */
public class Coordinates implements Serializable {
    private Integer x; //Максимальное значение поля: 79, Поле не может быть null
    private Integer y; //Максимальное значение поля: 794, Поле не может быть null

    /**
     * constructor
     * @param x x
     * @param y y
     * @throws NullException,ValueTooBigException by setX and setY
     */
    public Coordinates(Integer x,Integer y) throws NullException,ValueTooBigException{
        setX(x);
        setY(y);
    }

    /**
     * set x .Throw exception when x>79 throw ValueTooBigException.when x=null throw NullException.
     * @param x x
     * @throws ValueTooBigException,NullException
     */
    public void setX(Integer x) throws ValueTooBigException, NullException{
        if(x>94){
            throw new ValueTooBigException("x can't bigger than 79\n");
        }
        else if(x==null){
            throw new NullException("x can't be wrong");
        }
        else{
            this.x = x;
        }
    }

    /**
     * set y.When y>794 throw ValueTooBigException.When y=null throw NullException
     * @param y
     * @throws ValueTooBigException,NullException
     */
    public void setY(Integer y) throws ValueTooBigException, NullException {
        if(y>794){
            throw new ValueTooBigException("y can't bigger than 794\n");
        }else if(y==null){
            throw new NullException();
        }
        else {
            this.y = y;
        }
    }

    /**
     * get x
     * @return Integer
     */
    public Integer getX() {
        return x;
    }

    /**
     * get y
     * @return Integer
     */
    public Integer getY() {
        return y;
    }
    /**
     * print the information of coordination
     * @return String
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

