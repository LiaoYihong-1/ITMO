package lab3;

public class CR extends Human{
    CR(String name, int age, Sex sex){
        super(name,age,sex);
    }

    public void estimate(){
        System.out.printf("%s estimated the environment\n",this.name);
        setStatus(Status.ESTIMATE);
    }

    public void stayornot(Grassland grassland) throws GrassLandNotFlatException{
        if(grassland.feature==Feature.FLAT){
            say("stop!\n");
        } else{
            throw new GrassLandNotFlatException("Grassland is not flat\n");
        }
    }
}
