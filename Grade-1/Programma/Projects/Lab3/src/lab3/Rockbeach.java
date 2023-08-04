package lab3;

public class Rockbeach extends Place{
    Rockbeach(String name){
        super(name);
    }

    @Override
    public boolean allcome() {
        if (numbermember == 3) {
            return true;
        } else {
            return false;
        }
    }
    public void meet(){
        if (allcome()==true){
            System.out.print("they meet each others and they were happy\n");
        }else if(allcome()==false){
            System.out.print("Still waiting\n");
        }
    }
}
