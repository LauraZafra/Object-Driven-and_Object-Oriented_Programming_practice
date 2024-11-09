/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @brief Representación del estado completo del juego
 * @author laurazafra
 */
public class GameState {
    private String labyrinthv;
    private String players;
    private String monsters;
    private int currentPlayer; //representa el indice del jugador actual
    private boolean winner;
    private String log;

    public GameState (String la, String p, String m, int c, boolean w, String lo){
        labyrinthv = la;
        players = p;
        monsters = m;
        currentPlayer = c;
        winner = w;
        log = lo;
    }
    
    //Todos los consultores
    public String getLabyrinthv () { return labyrinthv; }
    public String getPlayers () { return players; }
    public String getMonsters () { return monsters; }
    public int getCurrentPlayer () { return currentPlayer; }
    public boolean getWinner () { return winner; }
    public String getLog () {return log; }
    
}
