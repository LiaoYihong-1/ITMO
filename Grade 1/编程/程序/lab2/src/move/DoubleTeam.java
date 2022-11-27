package move;

import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove {
    public DoubleTeam (double pow,double acc){
        super(Type.NORMAL,pow,acc);
    }

    protected void applySelfEffects(Pokemon P){
        super.applySelfEffects(P);
        Effect raise = new Effect().stat(Stat.EVASION,1);

        P.addEffect(raise);
    }

    protected String describe(){
        String[] des=this.getClass().toString().split("\\.");
        return "uses " + des[des.length-1];
    }
}
