package move;

import Lab2.lab2;
import ru.ifmo.se.pokemon.*;

public class FlameThrower extends SpecialMove {
    public FlameThrower (double pow,double acc){
        super(Type.FIRE,pow,acc);
    }

    protected void applyOppEffects(Pokemon P){
        super.applyOppEffects(P);
        if (lab2.Chance(0.1)){
            Effect.burn(P);
        }
    }

    protected String describe(){
        String[] des =this.getClass().toString().split("\\.");
        return "uses "+des[des.length-1];
    }
}
