/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @brief Esta clase representa las armas que utiliza el jugador en los 
 * ataques durante los combates.
 * @author laurazafra mercheleon
 */
public class Weapon extends CombatElement {
    
    //constructor
    public Weapon ( float p, int u ) {
        super(p, u); //usa el constructor de la clase padre
    }
    
    public float attack (){
        return produceEffect();
    }
    
    //La anotación @Override simplemente se utiliza, para forzar al compilador 
    //a comprobar en tiempo de compilación que estás sobrescribiendo 
    //correctamente un método, y de este modo evitar errores en tiempo 
    //de ejecución, los cuales serían mucho más difíciles de detectar.
    //String tiene que ir con mayúscula. Si no, error
    @Override public String toString(){ 
        return "W" + super.toString();
    }
}
