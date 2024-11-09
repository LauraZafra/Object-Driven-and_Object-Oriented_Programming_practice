/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author laurazafra
 */
//añade todas las cartas del tipo correspondiente que contendrá la baraja 
//al contenedor disponible para tal fin. En un caso se añadirán armas y en 
//el otro se añadirán escudos. Así, addCards utilizará internamente el 
//método anterior addCard para añadir cada una de las cartas.
public class ShieldCardDeck extends CardDeck<Shield> {
    protected void addCards (){
        Shield card1 = new Shield(Dice.shieldPower(), 3);
        addCard(card1);
        Shield card2 = new Shield(Dice.shieldPower(), 4);
        addCard(card2);
    }
}
