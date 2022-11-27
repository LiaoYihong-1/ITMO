package move;

import Lab2.lab2;
import ru.ifmo.se.pokemon.*;

public class RockSlide extends PhysicalMove {
    public RockSlide(double pow,double acc){
        super(Type.ROCK,pow,acc);
    }

    @Override
    protected void applyOppEffects(Pokemon P) {
        super.applyOppEffects(P);
        if(lab2.Chance(0.3)){
            Effect.flinch(P);
        }
    }

    @Override
    protected String describe() {
        String[] des=this.getClass().toString().split("\\.");
        return "uses "+des[des.length-1];
    }
}
