package move;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import Lab2.lab2;

public class SmartStrike extends PhysicalMove {
    public SmartStrike (double pow,double acc){
        super(Type.STEEL,pow,acc);
    }

    protected boolean checkAccuracy(Pokemon attack,Pokemon defence){
        super.checkAccuracy(attack,defence);
        return true;
    }

    protected String describe(){
        String[] des =this.getClass().toString().split("\\.");
        return "uses "+des[des.length-1];
    }
}


