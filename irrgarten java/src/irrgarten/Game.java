
package irrgarten;
import java.util.ArrayList;

/**
 *
 * @author laurazafra mercheleon
 */
public class Game {
    static final private int MAX_ROUNDS = 10;
    static final private int NROW = 5; //no lo pedía, añadido
    static final private int NCOL = 5; //no lo pedía, añadido
    static final private int ROWEXIT = 4; //no lo pedía, añadido
    static final private int COLEXIT = 4; //no lo pedía, añadido
    private int currentPlayerIndex;
    private String log = "";
    
    //creamos un vector dinámico que almacene los monstruos de la partida
    //tiene métodos como add, remove...
    private  ArrayList <Monster> monsters;
    private Labyrinth labyrinth;
    private Player currentPlayer;
    private ArrayList <Player> players;
    
    public Game (int nplayers){
        
        monsters = new ArrayList <> (); //no hace falta especificar el tipo aquí
        
        //CONSTRUCCIÓN LABYRINTH
        /*
        -nRows : int 
        -nCols : int 
        -exitRow : int 
        -exitCol : int
        */
        //casilla de salida debe estar dentro del laberinto
        labyrinth = new Labyrinth(NROW, NCOL,ROWEXIT, COLEXIT);
        
        //CONSTRUCCIÓN PLAYERS y añadirlos al array
        /*
        -name : String 
        -number : char 
        -intelligence : float 
        -strength : float 
        -health : float 
        -row : int 
        -col : int 
        -consecutiveHits : int = 0
        */
        players = new ArrayList <> ();
        for (int i = 0; i < nplayers; ++i){
            currentPlayer = new Player ((char)(i+48), Dice.randomIntelligence(), 
                    Dice.randomStrength());
            players.add(currentPlayer);
        }
        
        //quién empieza
        currentPlayerIndex = Dice.whoStarts(nplayers);
        currentPlayer = players.get(currentPlayerIndex);
        
        configureLabyrinth();
        
        //repartir jugadores en el tablero
        labyrinth.spreadPlayers(players);
                
    }
    
    //Delega en el método del laberinto que indica si hay un ganador.
    public boolean finished(){
        //haveAWinner es un método de instancia (como todos los de Labyrinth)
        return labyrinth.haveAWinner();
    }
    
    
    //CREADO POR NOSOTRAS: recomendado por el profesor
    //toString de cada jugador en un solo string
    private String playersToString (){
        String s = "";
        for (int i = 0; i < players.size(); ++i){
            s += players.get(i).toString();
            s += "\n";
        }
        return s;
    }
    
    //CREADO POR NOSOTRAS: recomendado por el profesor
    //toString de cada monster en un solo string
    private String monstersToString (){
        String s = "";
        for (int i = 0; i < monsters.size(); ++i){
            s += monsters.get(i).toString();
            s += "\n";
        }
        return s;
    }
    
    //Genera una instancia de GameState integrando toda la información del
    //estado del juego
    public GameState getGameState(){
        /*
        • labyrinthv de tipo String
        • players de tipo String
        • monsters de tipo String
        • currentPlayer de tipo int (representa el índice del jugador actual)
        • winner de tipo lógico/boolean
        • log de tipo String
        */
        //log es el historial del juego y lo que va sucediendo
        
        GameState gs = new GameState(labyrinth.toString(), 
                playersToString(), monstersToString(), currentPlayerIndex, 
                labyrinth.haveAWinner(),log);
        return gs;
    }
    
    //Configura el laberinto añadiendo bloques de obstáculos y monstruos. Los 
    //monstruos, además de en el laberinto son guardados en el contenedor propio 
    //de esta clase para este tipo de objetos. 
    private void configureLabyrinth(){
        //MONSTRUOS
        Monster m1 = new Monster("Dragón", Dice.randomIntelligence(), Dice.randomStrength());
        Monster m2 = new Monster("Cíclope", Dice.randomIntelligence(), Dice.randomStrength());
        Monster m3 = new Monster("Zombie", Dice.randomIntelligence(), Dice.randomStrength());
        //añadir los monstruos al tablero
        labyrinth.addMonster(1, 3, m1);
        labyrinth.addMonster(3, 2, m2);
        labyrinth.addMonster(4, 3, m3);
        //añadir los monstruos al vector
        monsters.add(m1);
        monsters.add(m2);
        monsters.add(m3);
        
        //BLOQUES
        labyrinth.addBlock(Orientation.HORIZONTAL, 0, 2, 1);
        labyrinth.addBlock(Orientation.HORIZONTAL, 1, 0, 1);
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 1, 1);
        labyrinth.addBlock(Orientation.HORIZONTAL, 3, 0, 1);
        labyrinth.addBlock(Orientation.HORIZONTAL, 4, 1, 1);
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 3, 2);
        
    }
    
    //Actualiza los dos atributos que indican el jugador (current*) 
    //con el turno pasando al siguiente jugador.
    private void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); //para no salir del array
        currentPlayer = players.get(currentPlayerIndex);
      
    }
    
    
    // Función para registrar que el jugador ha ganado el combate.
    private void logPlayerWon() {
        log += "El jugador ha ganado el combate.\n";
    }

    // Función para registrar que el monstruo ha ganado el combate.
    private void logMonsterWon() {
        log += "El monstruo ha ganado el combate.\n";
    }

    // Función para registrar que el jugador ha resucitado.
    private void logResurrected() {
        log += "El jugador ha resucitado.\n";
    }

    // Función para registrar que el jugador ha perdido el turno por estar muerto.
    private void logPlayerSkipTurn() {
        log += "El jugador ha perdido el turno por estar muerto.\n";
    }

    // Función para registrar que el jugador no ha seguido las instrucciones del jugador humano.
    private void logPlayerNoOrders() {
        log += "El jugador no ha seguido las instrucciones del jugador humano (no fue posible).\n";
    }

    // Función para registrar que el jugador se ha movido a una celda vacía o no le ha sido posible moverse.
    private void logNoMonster() {
        log += "El jugador se ha movido a una celda vacía o no le ha sido posible moverse.\n";
    }

    // Función para registrar el número de rondas de combate.
    private void logRounds(int rounds, int max) {
        log += "Se han producido " + rounds + " de " + max + " rondas de combate.\n";
    }
    
    // Método público para el siguiente paso, no es privado.
    //Si el juego no ha acabado, se pasa el turno al siguiente jugador
    public boolean nextStep(Directions preferredDirection) {
        log = "";
        boolean dead = currentPlayer.dead();
        if (!dead) { //si el jugador no está muerto se mueve
            Directions direction = actualDirection (preferredDirection);
            if (direction != preferredDirection) {
                logPlayerNoOrders();
            }
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            if (monster == null) {
                logNoMonster();
            }
            else {
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }
        else {
            manageResurrection(); //si está muerto resucita
        }
        boolean endGame = finished();
        if (!endGame) nextPlayer();
        return endGame;
    }

    // Función privada para el cálculo de la dirección actual.
    private Directions actualDirection(Directions preferredDirection) {
        int currentRow=currentPlayer.getRow();
        int currentCol=currentPlayer.getCol();
        
        ArrayList<Directions> validMoves= labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);       
        return output;
    }

    // Función privada para el combate entre un personaje del juego y un monstruo.
    private GameCharacter combat(Monster monster) {
        //lo de la nota (las dos siguientes lineas)
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack=currentPlayer.attack();
        boolean lose = monster.defend((float)playerAttack); 
        float monsterAttack;
        
        while ((!lose)&& (rounds < MAX_ROUNDS)){
            winner = GameCharacter.MONSTER;
            rounds++;
            monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if (!lose){
                playerAttack = currentPlayer.attack(); 
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            } 
        }
        this.logRounds(rounds, MAX_ROUNDS);
        return winner;
    }

    // Función privada para gestionar la recompensa para el ganador del combate.
    private void manageReward(GameCharacter winner) {
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        }else{
            logMonsterWon();
        }
    }

    // Función privada para gestionar la resurrección de un personaje.
    private void manageResurrection() {
        boolean resurrect = Dice.resurrectPlayer();
        if (resurrect){
            currentPlayer.resurrect();
            FuzzyPlayer fuzzy = new FuzzyPlayer(currentPlayer);
            currentPlayer = fuzzy;
            players.set(currentPlayerIndex, currentPlayer);
            labyrinth.updatePlayer(fuzzy);
            logResurrected();
        }
        else logPlayerSkipTurn();
    }
    

}
