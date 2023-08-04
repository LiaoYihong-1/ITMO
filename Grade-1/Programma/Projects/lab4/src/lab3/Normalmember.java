package lab3;

public class Normalmember extends Human{
    Normalmember(String name, int age, Sex sex){
        super(name,age,sex);
    }

    public void walkto(Human hum){
        System.out.printf("%s walked to %s\n",this.name,hum.name);
    }
}
