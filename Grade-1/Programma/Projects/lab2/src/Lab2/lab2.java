package Lab2;
import ru.ifmo.se.pokemon.*;
import mypokemon.*;
public class lab2 {
    // https://pokemondb.net/pokedex/sableye
    // https://pokemondb.net/pokedex/teddiursa
    // https://pokemondb.net/pokedex/ursaring
    // https://pokemondb.net/pokedex/togepi
    // https://pokemondb.net/search?q=Togetic
    // https://pokemondb.net/pokedex/togekiss
    public static void main(String [] args){
        Battle b = new Battle();
        /*Teddiursa p1 =new Teddiursa ("teddiursa", 100);
        Togetic p2 = new Togetic("togetic", 100);
        Sableye p3=new Sableye("sableye",100);
        Togekiss p4=new Togekiss("togekiss",100);
        Togepi p5=new Togepi("togepi",100);
        Ursaring p6=new Ursaring("ursaring",100);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();*/
        Togekiss p1=new Togekiss("22",100);
        Sableye p2=new Sableye("33",100);
        b.addFoe(p1);
        b.addAlly(p2);
        b.go();
    }
    public static boolean Chance(double d) {
        if (d >= Math.random()) {
            return true;
        } else {
            return false;
        }
    }
}