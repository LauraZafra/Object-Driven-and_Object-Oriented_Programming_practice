
package irrgarten;

/**
 *
 * @author laurazafra
 */
public abstract class LabyrinthCharacter {
    //si la clase es abstracta tiene que tener algún método abstracto
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public LabyrinthCharacter (String n, float i, float s, float h){
        name = n;
        intelligence = i;
        strength = s;
        health = h;
        row = -1;
        col = -1;
    }
    
    public LabyrinthCharacter (LabyrinthCharacter other){
        name = other.name;
        intelligence = other.intelligence;
        strength = other.strength;
        row = other.row;
        col = other.col;
        health = other.health;
    }
    
    //Devuelve true si el monstruo está muerto
    public boolean dead (){
        return (health <= 0);
    }
    
    //METODOS PARA DEVOLVER VALOR DE LA VARIALE
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

    //Nuevas de la practica 4
    protected float getIntelligence(){
        return intelligence;
    }

    protected float getStrength(){
        return strength;
    }

    protected float getHealth(){
        return health;
    }

    protected void setHealth(float s){
        health=s;
    }
    
    //Es un modificador en una única llamada de los atributos row y col.
    public void setPos (int row, int col){
        this.row = row;
        this.col = col;
    }
    
    //Genera un representación del estado completo del monstruo en forma de cadena de caracteres.
    public String toString (){
        return "Name: " + name + "\n" +
                "Intelligence: " + intelligence + "\n" +
                "Strength: " + strength + "\n" +
                "Health: " + health + "\n" +
                "Row: " + row + "\n" +
                "Col: " + col + "\n";
    }
    
    //decrementa en una unidad el atributo que representa la salud del monstruo.
    protected void gotWounded(){
        if (health > 0) health--;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);
}
