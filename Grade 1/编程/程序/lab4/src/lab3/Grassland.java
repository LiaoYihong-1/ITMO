package lab3;

public class Grassland extends Place{
    public Grassland(String name, Feature feature){
        super(name);
        this.feature=feature;
    }

    public Feature feature;

    public void sefeature(Feature kind) {
        this.feature = kind;
    }

    public void getfeature(){
        if (this.feature==Feature.FLAT){
            System.out.print("this is just a pretty good grassland for resting.They wanted to rest at there\n");
        }
        else if(this.feature==Feature.UNEVEN){
            System.out.print("this grassland is not flat enough for them. They dont' want to rest there\n");
        }
    }
}
