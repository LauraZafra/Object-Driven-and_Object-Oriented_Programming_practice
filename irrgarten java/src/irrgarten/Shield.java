/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @brief Esta clase representa los escudos que utiliza el jugador cuando 
 * se defiende de un ataque de un monstruo.
 * @author laurazafra
 */
public class Shield extends CombatElement{

    public Shield(float p, int u){
        super(p, u); //usa el constructor de la clase padre
    }
    
    public float protect(){
        return produceEffect();
    }
    
    @Override public String toString(){
        return "S" + super.toString();
    }
    
    
}
