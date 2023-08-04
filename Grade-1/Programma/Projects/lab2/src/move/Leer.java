package move;

import ru.ifmo.se.pokemon.*;

import javax.swing.plaf.BorderUIResource;

public class Leer extends StatusMove {
    public Leer (double pow,double acc){
        super(Type.NORMAL,pow,acc);
    }

    @Override
    protected void applyOppEffects(Pokemon P) {
        super.applySelfEffects(P);
        Effect def = new Effect().stat(Stat.DEFENSE,-1);
    }

    protected String describe(){
        String[] des=this.getClass().toString().split("\\.");
        return "uses " + des[des.length-1];
    }
}

