package lab3;

public class Place {
    public String placename;
    int numbermember=0;

    public Place(String placename){
        this.placename=placename;
        System.out.printf("Here is the %s\n",this.placename);
    }


    public String getPlacename(){
        return this.placename;
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
