package move;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class StoneEdge extends PhysicalMove {
    public StoneEdge (double pow,double acc){
        super(Type.ROCK,pow,acc);
    }
    protected double calcCriticalHit(Pokemon pokemon, Pokemon pokemon1) {
        if(1/8D < Math.random()){
            System.out.print("Critical!\n");
            return 2.0D;
        }
        else {
            return 1.0D;
        }
    }

    protected String describe(){
        String[] des =this.getClass().toString().split("\\.");
        return "uses "+des[des.length-1];
    }
}
