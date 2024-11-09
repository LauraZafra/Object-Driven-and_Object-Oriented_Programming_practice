
package irrgarten;

/**
 *
 * @author laurazafra mercheleon
 */
public class Monster extends LabyrinthCharacter{
    static final private int INITIAL_HEALTH = 5;
    
    
    public Monster (String n, float i, float s){
        super (n, i, s, INITIAL_HEALTH);
    }
    
    
    //Genera el resultado delegando en el método intensity del dado 
    //pasando como parámetro la fuerza del monstruo
    @Override //sobreescribe attack de la clase abstracta LabyrinthCharacter
    public float attack(){
       return Dice.intensity(getStrength());
    }
    
   //Devuelve si un monstruo está muerto después de atacarlo
    @Override
    public boolean defend (float receivedAttack){
        boolean isDead = dead();
        float defensiveEnergy;
        if (!isDead){
            defensiveEnergy = Dice.intensity(getIntelligence());
            if (defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
       return isDead;
    }
}
