package lab3;

public class Expedition{
    Expedition(String name){
        this.name=name;
        System.out.printf("%s started their journey\n",this.name);
    }

    String name;
    int number=0;

    Human inexpediton [] = new Human [4];

    public static class destination{
        destination(String name){
            this.name=name;
        }
        String name;

        public void showdes(){
            System.out.printf("this expedition aimed to %s\n",this.name);
        }
    }

    public void addmember(Human person){
        System.out.printf("%s enter the expedition\n", person.name);
        inexpediton[number]=person;
        number = number + 1;
    }

    public int getNumber(){
        System.out.printf("we have %d people now",this.number);
        return number;
    }
    class action {

        public void arrive(Place place) {
            System.out.printf("expedition reach %s\n", place.placename);
        }

        public void walkto(Place place) {
            System.out.printf("expedition is moving to %s\n", place.placename);
        }

    }

    public void crossshore(final String shorename,final Feature shoreFeture) {
        class Shore{
            Shore(String nameofshore,Feature featureofshore) {
                this.feature=featureofshore;
                this.name=nameofshore;
            }

            public String name;
            public Feature feature;
        }
        Shore shore = new Shore(shorename,shoreFeture);
        if (shore.feature == Feature.NARROW) {
            System.out.print("They were crossing narrrow shore carefully\n");
        } else if (shore.feature == Feature.WIDE) {
            System.out.print("Shore became wider.They saw grassland\n");
        }
    }
}
