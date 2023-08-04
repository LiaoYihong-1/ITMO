package move;

import ru.ifmo.se.pokemon.*;

public class NastyPlot extends StatusMove {
    public NastyPlot (double pow,double acc){
        super(Type.DARK,pow,acc);
    }

    protected void applySelfEffects(Pokemon P){
        super.applySelfEffects(P);
        Effect raise =new Effect().stat(Stat.SPECIAL_ATTACK,2);
        P.addEffect(raise);
    }

    protected String describe(){
        String[] des=this.getClass().toString().split("\\.");
        return "uses " + des[des.length-1];
    }
}
