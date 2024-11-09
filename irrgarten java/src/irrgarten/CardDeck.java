
package irrgarten;
import java.util.ArrayList; //para poder usar arrays dinámicos
import java.util.Collections; //

/**
 *
 * @author laurazafra
 */
public abstract class CardDeck <T> {
    private ArrayList<T> cardDeck; 
    
    public CardDeck (){
        cardDeck = new ArrayList <T> ();
    }
    
    //añade una carta del tipo correspondiente al contenedor
    //disponible para tal fin.
    protected void addCard(T card) {
        cardDeck.add(card); 
    }
    
    //añade todas las cartas del tipo correspondiente que contendrá la baraja 
    //al contenedor disponible para tal fin. En un caso se añadirán armas y en 
    //el otro se añadirán escudos. Así, addCards utilizará internamente el 
    //método anterior addCard para añadir cada una de las cartas.
    protected abstract void addCards();
    
    //proporciona la siguiente carta que corresponda
    public T nextCard(){
        //Si no hay cartas en la colección, se llama al método addCards (para 
        //que la baraja tenga contenido) y se baraja
        if (cardDeck.isEmpty()){
            addCards();
            Collections.shuffle(cardDeck);
        }
        //se selecciona la primera carta, se elimina la misma de la colección 
        //de cartas y se devuelva la carta seleccionada.
        T carta = cardDeck.get(0);
        cardDeck.remove(0);
        return carta;
    }
}