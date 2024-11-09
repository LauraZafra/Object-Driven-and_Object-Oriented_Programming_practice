
package UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    

    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
    
    //mostrar en consola información
    public void showGame(GameState gameState) {  
        System.out.print("Laberinto:  " + "\n");
        System.out.print(gameState.getLabyrinthv().toString());
        System.out.print("\n" + "Jugadores:  " + "\n");
        System.out.print(gameState.getPlayers().toString());
        System.out.print("\n" + "Monstruos:  " + "\n");
        System.out.print(gameState.getMonsters().toString());
        System.out.print("\n" + "Jugador actual (índice):  ");
        System.out.print(gameState.getCurrentPlayer() + "\n");
        if (gameState.getWinner()) System.out.print("GANADOR" + "\n");
        System.out.print(gameState.getLog());
        System.out.print("\n" + "-------------     ---     -------------" + "\n");
    }
    
}
