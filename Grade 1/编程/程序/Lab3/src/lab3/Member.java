package lab3;

public class Member extends Human{
    Member(String name, int age, Sex sex){
        super(name, age,sex);
    }

    public void detailmove(){
        if(getStatus()==Status.WAITING){
            waiting();
        }else if(getStatus()==Status.WALKING&&destname!=null){
            walk(destname);
        }
    }
}
