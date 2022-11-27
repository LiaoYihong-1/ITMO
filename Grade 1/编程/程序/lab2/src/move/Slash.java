package move;

import ru.ifmo.se.pokemon.*;

public class Slash extends PhysicalMove {
    String str1="";
    int int1 = 0;

    public Slash(double pow, double acc) {
        super(Type.PSYCHIC, pow, acc);
    }

    @Override
    protected double calcCriticalHit(Pokemon pokemon, Pokemon pokemon1) {
        if(1/8D < Math.random()){
            System.out.print("Critical!\n");
            return 2.0D;
        }
        else {
            return 1.0D;
        }
    }

    @Override
    protected String describe() {
        String[] des = this.getClass().toString().split("\\.");
        return "uses " + des[des.length - 1];
    }
}
