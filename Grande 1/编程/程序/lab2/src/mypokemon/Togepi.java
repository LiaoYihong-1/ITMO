package mypokemon;

import move.DreamEater;
import move.FlameThrower;
import ru.ifmo.se.pokemon.*;

public class Togepi extends ru.ifmo.se.pokemon.Pokemon{
    public Togepi(String name, int level) {
        super(name, level);
        super.setStats(35, 20, 65, 40, 65, 20);
        super.setType(Type.FAIRY);

        DreamEater skill1 =new DreamEater(100,100);
        FlameThrower skill2 = new FlameThrower(90,100);

        super.setMove(skill1,skill2);
    }
}

