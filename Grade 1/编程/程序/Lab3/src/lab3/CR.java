package lab3;

public class CR extends Human{
    CR(String name, int age, Sex sex){
        super(name,age,sex);
    }

    CR(){
        super();
    }

    public void detailmove(){
        if(getStatus()==Status.WAITING){
            waiting();
        }else if(getStatus()==Status.WALKING&&destname!=null){
            walk(destname);
        }else if(getStatus()==Status.STANDINGBYRIVER){
            System.out.print(this.name+" looked around the environment\n");
        }
    }

    public void getSecialexperiment(){
        System.out.print("He has known what is ambush\n");
    }

}
