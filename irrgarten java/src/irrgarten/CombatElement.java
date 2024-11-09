package irrgarten;

/**
 *
 * @author laurazafra
 */
public abstract class CombatElement {

    private float effect;
    private int uses;

    public CombatElement(float effect, int uses) {
        this.effect = effect;
        this.uses = uses;
    }

    //generaliza attack y protect
    protected float produceEffect() {
        if (uses > 0) {
            --uses;
            return effect;
        } else {
            return 0;
        }
    }

    public boolean discard() {
        return Dice.discardElement(uses); //usa la clase Dice
    }

    @Override
    public String toString() {
        return "[" + effect + ", " + uses + "]";
    }
}
