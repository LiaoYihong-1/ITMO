package move;

import ru.ifmo.se.pokemon.*;

public class DreamEater extends SpecialMove {
    public DreamEater (double pow,double acc){
        super(Type.PSYCHIC,pow,acc);
    }

    int distingish;
    int recovery;

    protected void applyOppEffects (Pokemon O){
        if (O.getCondition()==Status.SLEEP) {
            distingish = 1;
        }
        else{
            distingish = 0;
        }
    }

    protected void applySelfEffects(Pokemon P) {
        super.applySelfEffects(P);
        if (distingish == 1) {
            Effect gh = new Effect();
            gh.stat(Stat.HP,(int)Math.round(P.getStat(Stat.HP)-P.getHP())/2);
            P.addEffect(gh);
            int recovery= (int)Math.round(P.getStat(Stat.HP)-P.getHP());
        }
    }

    @Override
    protected void applyOppDamage(Pokemon P, double dam) {
        if (distingish==0)
        {
            super.applyOppDamage(P,0);
        }else{
            super.applyOppDamage(P,dam);
        }
    }

    protected String describe(){
        String[] des=this.getClass().toString().split("\\.");
        return "uses " + des[des.length-1]+ " and HP+ "+recovery;
    }
}
