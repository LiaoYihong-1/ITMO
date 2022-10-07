package mypokemon;

import move.DoubleTeam;
import move.DreamEater;
import move.Leer;
import move.NastyPlot;
import ru.ifmo.se.pokemon.Type;

public class Sableye extends ru.ifmo.se.pokemon.Pokemon {
    public Sableye(String name,int level) {
        super(name, level);
        super.setStats(50, 75, 75, 65, 65, 50);
        super.setType(Type.DARK,Type.GHOST);

        DreamEater skill1 = new DreamEater(100,100);
        DoubleTeam skill2 = new DoubleTeam(0,100);
        Leer skill3 = new Leer(0,100);
        NastyPlot skill4 = new NastyPlot(0,0);

        super.setMove(skill1,skill2,skill4,skill4);
    }
}
