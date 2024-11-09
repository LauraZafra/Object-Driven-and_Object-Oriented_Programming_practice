
package irrgarten;

/**
 *
 * @author laurazafra
 */

//añade todas las cartas del tipo correspondiente que contendrá la baraja 
//al contenedor disponible para tal fin. En un caso se añadirán armas y en 
//el otro se añadirán escudos. Así, addCards utilizará internamente el 
//método anterior addCard para añadir cada una de las cartas.
public class WeaponCardDeck extends CardDeck<Weapon> {
    protected void addCards (){
        Weapon card1 = new Weapon(Dice.weaponPower(), 3);
        addCard(card1);
        Weapon card2 = new Weapon(Dice.weaponPower(), 4);
        addCard(card2);
    }
}
