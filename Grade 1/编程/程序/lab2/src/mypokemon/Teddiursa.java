package mypokemon;

import move.DoubleTeam;
import move.NastyPlot;
import move.RockSlide;
import move.Slash;
import ru.ifmo.se.pokemon.*;

public class Teddiursa extends ru.ifmo.se.pokemon.Pokemon {
    public Teddiursa(String name, int level) {
        super(name, level);
        super.setStats(60, 80, 50, 50, 50, 40);
        super.setType(Type.NORMAL);

        Slash skill1 = new Slash(70,100);
        RockSlide skill2 = new RockSlide(75,90);
        DoubleTeam skill3 = new DoubleTeam(0,0);

        super.setMove(skill1,skill2,skill3);
    }
}