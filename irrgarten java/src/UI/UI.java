
package UI;

import irrgarten.Directions;
import irrgarten.GameState;

//interfaz UI común a la interfaz de usuario de texto (TextUI) y la gráfica (GraphicUI)
public interface UI {
    public Directions nextMove();
    public void showGame (GameState gs);
}
