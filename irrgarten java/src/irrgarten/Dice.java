
package irrgarten;
import java.util.Random;
import java.util.ArrayList;

/**
 * @brief Esta clase tiene la responsabilidad de tomar todas las decisiones que 
 * dependen del azar en el juego. Es como una especie de dado, 
 * pero algo más sofisticado
 * @author laurazafra
 */
public class Dice {
    //final en Java = const en C++
    //con static hacemos la constante un atributo de clase(≠ de objeto): solo una copia
    static private final int MAX_USES = 5; //(número máximo de usos de armas y escudos)
    static private final float MAX_INTELLIGENCE = (float)10.0; //(valor máximo para la inteligencia de jugadores y monstruos) 
    static private final float MAX_STRENGTH = (float)10.0; //(valor máximo para la fuerza de jugadores y monstruos) 
    static private final float RESURRECT_PROB = (float)0.3; //(probabilidad de que un jugador sea resucitado en cada turno) 
    static private final int WEAPONS_REWARD = 2; //(numero máximo de armas recibidas al ganar un combate) 
    static private final int SHIELDS_REWARD = 3; //(numero máximo de escudos recibidos al ganar un combate) 
    static private final int HEALTH_REWARD = 5; //(numero máximo de unidades de salud recibidas al ganar un combate) 
    static private final int MAX_ATTACK = 3; //(máxima potencia de las armas)
    static private final int MAX_SHIELD = 2; //(máxima potencia de los escudos)
    
    static private Random generator = new Random();
    
    //Metodo nuevo para la p4
    //Devuelve la direccion de movimiento 
    public static Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence){
        if (generator.nextFloat(1) < 1.0*(intelligence/MAX_INTELLIGENCE)){ 
            return preference;
        }else{
            return validMoves.get(generator.nextInt(validMoves.size()));
        }
    }
    
    
    //Devuelve un número de fila o columna aleatoria siendo el valor del 
    //parámetro el número de filas o columnas del tablero
    public static int randomPos(int max){
        return generator.nextInt(max);
    }
    
    //Devuelve el índice del jugador que comenzará la partida
    public static int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    //Devuelve un valor aleatorio de inteligencia
    public static float randomIntelligence(){
        return generator.nextFloat(MAX_INTELLIGENCE);
    }
    
    //Devuelve una valor aleatorio de fuerza
    public static float randomStrength(){
        return generator.nextFloat(MAX_STRENGTH);
    }
    
    //Indica si un jugador muerto debe ser resucitado o no.
    public static boolean resurrectPlayer(){
        boolean resucita = false;
        //generamos un número de 0 a 0.99 -> si es < que mi probabilidad resucita
        if (generator.nextFloat(1) < RESURRECT_PROB) resucita = true;
        return resucita;
    }
    
    //Indica la cantidad de armas que recibirá el jugador por ganar el combate.
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD+1);
    }
    
    //Indica la cantidad de escudos que recibirá el jugador por ganar el combate.
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    
    //Indica la cantidad de salud que recibirá el jugador por ganar el combate.
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1);
    }
    
    //Devuelve un valor aleatorio de arma
    public static float weaponPower(){
        return generator.nextFloat(MAX_ATTACK);
    }
    
    //Devuelve un valor aleatorio de arma
    public static float shieldPower(){
        return generator.nextFloat(MAX_SHIELD);
    }
    
    //Devuelve el número de usos que se asignará a un arma o escudo
    public static int usesLeft(){
        return generator.nextInt(MAX_USES+1);
    }
    
    public static float intensity(float competence){
        return generator.nextFloat(competence);
    }
    
    public static boolean discardElement(int usesLeft){
        if (generator.nextFloat(1) < 1.0*(MAX_USES-usesLeft)/MAX_USES) return true;
        else return false;
    }
    
}
