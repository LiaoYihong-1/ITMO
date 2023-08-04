package mypokemon;

import move.DoubleTeam;
import move.RockSlide;
import move.Slash;
import move.StoneEdge;
import ru.ifmo.se.pokemon.*;

public class Ursaring extends ru.ifmo.se.pokemon.Pokemon{
    public Ursaring(String name, int level) {
        super(name, level);
        super.setStats(90, 130, 75, 75, 75, 55);
        super.setType(Type.NORMAL);

        Slash skill1 = new Slash(70,100);
        RockSlide skill2 = new RockSlide(75,90);
        DoubleTeam skill3 = new DoubleTeam(0,0);
        StoneEdge skill4 = new StoneEdge(100,80);

        super.setMove(skill1,skill2,skill3,skill4);
    }
}
