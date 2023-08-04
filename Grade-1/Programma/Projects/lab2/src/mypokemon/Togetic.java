package mypokemon;

import move.DoubleTeam;
import move.DreamEater;
import move.FlameThrower;
import move.SmartStrike;
import ru.ifmo.se.pokemon.*;

public class Togetic extends ru.ifmo.se.pokemon.Pokemon{
    public Togetic(String name, int level) {
        super(name, level);
        super.setStats(55, 40, 85, 80, 105, 40);

        DreamEater skill1 =new DreamEater(100,100);
        FlameThrower skill2 = new FlameThrower(90,100);
        SmartStrike skill3 = new SmartStrike(70,100);

        super.setMove(skill1,skill2,skill3);
    }
}

