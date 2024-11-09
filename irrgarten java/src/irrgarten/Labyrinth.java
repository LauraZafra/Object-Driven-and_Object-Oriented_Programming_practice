
package irrgarten;
import java.util.ArrayList;

/**
 *
 * @author laurazafra mercheleon
 */ 
public class Labyrinth {
    //final = constante
    static final private char BLOCK_CHAR = 'X';
    static final private char EMPTY_CHAR = '-';
    static final private char MONSTER_CHAR = 'M';
    static final private char COMBAT_CHAR = 'C';
    static final private char EXIT_CHAR = 'E';
    static final private int ROW = 0;
    static final private int COL = 1;
    
    //hay que inicializarlos
    private int nRows = 5;
    private int nCols = 5;
    private int exitRow = 4;
    private int exitCol = 4;
    
    private Monster[][] monstersSquare;
    private Player[][] playersSquare;
    private char [][] labyrinthSquare;
    
    public Labyrinth (int nr, int nc, int er, int ec){
        nRows = nr;
        nCols = nc;
        exitRow = er;
        exitCol = ec;
        monstersSquare = new Monster [nRows][nCols];
        playersSquare = new Player [nRows][nCols];
        labyrinthSquare = new char [nRows][nCols];
        
        //Inicializar squares
        for (int i = ROW; i < nRows; ++i){
            for (int j = 0; j < nCols; ++j){
                monstersSquare[i][j] = null; 
                playersSquare[i][j] = null;
                labyrinthSquare[i][j] = EMPTY_CHAR;
            }
        }
        labyrinthSquare[exitRow][exitCol] = EXIT_CHAR;
    }
    
    //Devuelve true si hay un jugador en la casilla de salida y 
    //false si no hay ninguno
    public boolean haveAWinner(){
        return (playersSquare [exitRow][exitCol] != null);
    }
    
    //Genera un representación del estado completo del laberinto en 
    //forma de cadena de caracteres.
    public String toString(){
        String s = "";
        for (int fila = ROW; fila < nRows; fila++) {
            for (int columna = 0; columna < nCols; columna++) {
                   s += labyrinthSquare[fila][columna] + " ";
            }
            s += "\n";
        }
        return s;
    }
    
    //Si la posición suministrada está dentro del tablero y está vacía, 
    //anota en el laberinto la presencia de un monstruo, guarda la referencia 
    //del monstruo en el atributo contenedor adecuado e indica al monstruo 
    //cual es su posición actual (setPos)
    public void addMonster (int row, int col, Monster m){
        if (emptyPos(row, col)){
                labyrinthSquare[row][col] = MONSTER_CHAR;
                m.setPos(row, col);
                monstersSquare [row][col] = m;
                
        }
    } 
    
    //Añade un bloque al laberinto
    public void addBlock (Orientation orientation, int startRow, int startCol, int length){ 
        
        int incRow, incCol;
        int row, col;
        if(orientation==Orientation.VERTICAL){
            incRow=1;
            incCol=0;
        }else{
            incRow=0;
            incCol=1;
        }
        
        row=startRow;
        col=startCol;
        
        while(((posOK(row,col)) && (emptyPos(row,col)) && (length>0))){
            labyrinthSquare[row][col] = BLOCK_CHAR;   //En el diagrama pone laberinth pero nosotros nos referimos a laberinthSquare
            length-=1;
            row+=incRow;
            col+=incCol;
        }
        
    }
    
    
    //Devuelve true si la posición proporcionada está dentro del laberinto
    private boolean posOK (int row, int col){
        return (row >= 0 && row < nRows && col >= 0 && col < nCols);
    }
    
    
    //Devuelve true si la posición suministrada está vacía.
    private boolean emptyPos (int row, int col){
        return (posOK(row, col) && labyrinthSquare[row][col] == EMPTY_CHAR);
    }
    
    
    //Devuelve true si la posición suministrada alberga exclusivamente un
    //monstruo.
    private boolean monsterPos(int row, int col){
        return (posOK(row, col) && labyrinthSquare[row][col] == MONSTER_CHAR);
    }
    
    
    //Devuelve true si la posición suministrada es la de salida.
    private boolean exitPos(int row, int col){
        return (posOK(row, col) && labyrinthSquare[row][col] == EXIT_CHAR);
    }
    
    //Devuelve true si la posición suministrada contiene a la vez un monstruo 
    //y un jugador (carácter ‘C’).
    private boolean combatPos(int row, int col){
        return (posOK(row, col) && labyrinthSquare[row][col] == COMBAT_CHAR);
    }
    
    
    //Indica si la posición suministrada está dentro del laberinto y 
    //se corresponde con una de estas tres opciones: casilla vacía, 
    //casilla donde habita un monstruo o salida
    private boolean canStepOn (int row, int col){        
        return (posOK (row, col) && (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col)));
    }
    
    /*
    en esa posición el laberinto estaba indicando el estado de combate, 
    el estado de esa casilla del laberinto pasa a indicar que simplemente hay un 
    monstruo. En otro caso, el estado de esa casilla del laberinto pasa a indicar 
    que está vacía. Este método es llamado cuando un jugador abandona una 
    casilla y se encarga de dejar la casilla que se abandona en el estado correcto.
    */
    private void updateOldPos (int row, int col){
        if (posOK(row, col)){
            if (labyrinthSquare[row][col] == COMBAT_CHAR) labyrinthSquare[row][col] = MONSTER_CHAR;
            else labyrinthSquare[row][col] = EMPTY_CHAR;
        }
    }
    
    
   
    //calcula la posición del laberinto a la que se llegaría si desde la 
    //posición suministrada se avanza en la dirección pasada como parámetro.
    private int [] dir2pos (int row, int col, Directions direction){
        int [] sol = new int [2]; //pos 0 = fila (ROW), pos 1 = columna (COL)
        sol [ROW] = row;
        sol [COL] = col;
        switch (direction) {
            case UP:
                sol[ROW]--;
                break;
            case DOWN:
                sol[ROW]++;
                break;
            case LEFT:
                sol[COL]--;
                break;
            case RIGHT:
                sol[COL]++;
                break;
        }
        return sol;
    }
    
    
    //genera una posición aleatoria en el laberinto (fila y columna) asegurando 
    //que esta esté vacía. Genera internamente posiciones hasta que se cumple 
    //esta restricción y una vez generada se devuelve. Si no hay posiciones 
    //vacías se producirá un bucle infinito.
    private int [] randomEmptyPos(){
        int [] sol = new int [2]; //pos 0 = fila, pos 1 = coluna
        do{
            sol [ROW] = Dice.randomPos(nRows);
            sol [COL] = Dice.randomPos(nCols); 
        } while (labyrinthSquare[sol[ROW]][sol[COL]] != EMPTY_CHAR);
        return sol;
    }
    
    //Método para distribuir jugadores en el tablero, en posiciones vacías
    public void spreadPlayers (ArrayList <Player> players) {
         //descomentar si queremos que sea random
        //int[] pos = {0,0};
         int oldRow = -1;
         int oldCol = -1;
        //bucle for para recorrer el ArrayList de players si queremos que sea random
        
        int[] pos;
        for (int i=0; i<players.size(); i++){
            pos = randomEmptyPos();
            putPlayer2D(oldRow, oldCol, pos[ROW], pos[COL], players.get(i));
        }
        
        //putPlayer2D(oldRow, oldCol, pos[ROW], pos[COL], players.get(0));
    }

    // Método para colocar un jugador en una dirección.
    public Monster putPlayer(Directions direction, Player player) {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int newPos[] = dir2pos(oldRow,oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        return monster;
    }


    // Método para obtener movimientos válidos desde una posición dada.
    public ArrayList<Directions> validMoves(int row, int col) {
        ArrayList <Directions> output = new ArrayList <> ();
        if(canStepOn(row+1,col)){
               output.add(Directions.DOWN);
        }
        
        if(canStepOn(row-1,col)){
               output.add(Directions.UP);
        }
        
        if(canStepOn(row,col+1)){
               output.add(Directions.RIGHT);
        }
        
        if(canStepOn(row,col-1)){
               output.add(Directions.LEFT);
        }
        return output;
    }

    // Método para colocar un jugador en 2D, actualiza la pos ant¡gua, actualiza laberinto
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
        Monster output=null;
        
        if(canStepOn(row,col)){
            if(posOK(oldRow, oldCol)){
                Player p=playersSquare[oldRow][oldCol];
                if(p==player){
                    updateOldPos(oldRow,oldCol);
                    playersSquare[oldRow][oldCol]=null;
                }
            }
            boolean monsterPos=monsterPos(row,col);
            if(monsterPos){
                labyrinthSquare[row][col]=COMBAT_CHAR;
                output=monstersSquare[row][col];
            }else{
                char number=(char)player.getNumber();
                labyrinthSquare[row][col]=number;
            }
            playersSquare[row][col]=player;
            player.setPos(row, col);
        }
        return output;
    }
    
    //de la P4
    public void updatePlayer (FuzzyPlayer fuzzy){
        updateOldPos (fuzzy.getRow(), fuzzy.getCol());
        putPlayer2D (-1, -1, fuzzy.getRow(), fuzzy.getCol(), fuzzy);
    }
    
  
}
