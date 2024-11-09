/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import UI.GraphicalUI;
import irrgarten.Game;
import controller.Controller;

/**
 *
 * @author laurazafra
 */
public class MainGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GraphicalUI vista = new GraphicalUI();
        Game game = new Game(2);
        Controller controller = new Controller(game,vista);
        controller.play();
    }
    
}
