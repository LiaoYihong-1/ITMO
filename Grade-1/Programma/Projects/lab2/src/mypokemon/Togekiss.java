package mypokemon;

import move.DreamEater;
import move.FlameThrower;
import move.SmartStrike;
import ru.ifmo.se.pokemon.*;

public class Togekiss extends ru.ifmo.se.pokemon.Pokemon{
    public Togekiss(String name, int level) {
        super(name, level);
        super.setStats(85, 50, 95, 120, 115, 80);
        super.setType(Type.FAIRY,Type.FLYING);

        DreamEater skill1 =new DreamEater(100,100);
        FlameThrower skill2 = new FlameThrower(90,100);
        SmartStrike skill3 = new SmartStrike(70,100);

        super.setMove(skill1,skill2);
    }
}
