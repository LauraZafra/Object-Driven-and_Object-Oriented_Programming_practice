package irrgarten;

import java.util.ArrayList; //para poder usar arrays dinámicos

public class FuzzyPlayer extends Player {

    FuzzyPlayer(Player other) {
        super(other);
    }

    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        Directions dirPlayer = super.move(direction, validMoves);
        return Dice.nextStep(dirPlayer, validMoves, getIntelligence());
    }

    //La energía de ataque de un FuzzyPlayer se calcula sumando a la aportación de las armas el
    //resultado de usar el método intensity del dado sobre su fuerza.
    @Override
    public float attack() {
        return this.sumWeapons() + Dice.intensity(getStrength());
    }

    //La intensidad defensiva también recurre al resultado de aplicar el mismo 
    //método intensity sobre la inteligencia para sumar el resultado a la aportación 
    //de los escudos.
    @Override
    protected float defensiveEnergy() {
        return this.sumShields() + Dice.intensity(getIntelligence());
    }

    @Override
    public String toString() {
        return "Fuzzy " + super.toString();
    }

}
