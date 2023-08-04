package lab3;

public abstract class Place {
    public String placename;
    Human[] discoveryteam=new Human[3];
    int numbermember=0;

    public Place(){
        placename="place without name";
        System.out.print("this is just a unnamed place");
    }

    public Place(String placename){
        this.placename=placename;
        System.out.printf("Here is the %s\n",this.placename);
    }

    public void membercome(Human humam){
        numbermember = numbermember + 1;
        if (numbermember <= 3 && humam.getname() != "unknowed") {
            discoveryteam[numbermember-1] = humam;
            System.out.printf("%s join us at %s\n", humam.getname(), this.getPlacename());
        } else if (numbermember > 3) {
            System.out.printf("%s join us at %s\n", humam.getname(), this.getPlacename());
            System.out.printf("ChristopherRobin:\"who is that last guy,there are only 3 people in out team\"\n");
            System.out.printf("ChristopherRobin let %s go\n",humam.getname());
            numbermember = numbermember - 1;
        } else if (humam.sex == Sex.UNKNOWED) {
            System.out.printf("%s join us at %s\n", humam.getname(), this.getPlacename());
            System.out.printf("ChristopherRobin:\"who is that guy,he is not our teamate\"\n");
            System.out.printf("ChristopherRobin let %s go\n",humam.getname());
            numbermember = numbermember - 1;
        }
    }

    public String getPlacename(){
        return this.placename;
    }

    public abstract boolean allcome();

    public void moveto(Place newplace){
        if(allcome()==true) {
            System.out.printf("They are walking to %s\n", newplace.getPlacename());
        }else {
            System.out.printf(discoveryteam[0].name+":\"NO,NO,NO.We need to wait.Someone still not here\"\n");
        }
    }

    public void arriveat(Place destination){
        if(allcome()) {
            System.out.printf("They arrived at %s\n", destination.getPlacename());
        }else{
            System.out.print(discoveryteam[0].getname()+":\"Be patient\"");
        }
    }

    public void getmembername(int i){
        if(i==1) {
            System.out.printf("our 1st guy is %s",discoveryteam[i].getname());
        }else if(i==2){
            System.out.printf("our 2nd guy is %s",discoveryteam[i].getname());
        }else if(i==3){
            System.out.printf("our 3rd guy is %s",discoveryteam[i].getname());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Place pla=(Place)o;
        if(this.hashCode()!=o.hashCode()){
            return false;
        }
        return (this.placename==pla.placename);
    }

    @Override
    public int hashCode() {
        int code=0;
        for(int i=0;i<this.placename.length();i++){
            code=code+(int)placename.charAt(i);
        }
        return code;
    }

    @Override
    public String toString() {
        return "here is\n"+getPlacename();
    }
}
