package lab3;

public class PO extends Human{
    public PO(String name, int age, Sex sex){
        super(name,age,sex);
    }

    public void ambushis(){
        System.out.printf("%s knowed what is ambushi\n",this.name);
        say("once a raspberry bush suddenly bumped into him when he, he fell from a tree, and then he had to pull out thorns for a whole week.\n");
    }
}
