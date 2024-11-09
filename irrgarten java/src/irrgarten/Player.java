package irrgarten;

import java.util.ArrayList; //para poder usar arrays dinámicos

/**
 *
 * @author laurazafra mercheleon
 */
public class Player extends LabyrinthCharacter {

    static final private int MAX_WEAPONS = 2;
    static final private int MAX_SHIELDS = 3;
    static final private int INITIAL_HEALTH = 10;
    static final private int HITS2LOSE = 3;

    private int consecutiveHits = 0;
    private char number;

    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<Shield> shields = new ArrayList<>();

    private ShieldCardDeck shieldCardDeck;
    private WeaponCardDeck weaponCardDeck;

    public Player(char number, float intelligence, float strength) {
        super("Player " + number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        shieldCardDeck = new ShieldCardDeck();
        weaponCardDeck = new WeaponCardDeck();
    }

    //constructor copia debe utilizar el constructor copia de
    //LabyrinthCharater y además copiar atributos propios, incluyendo la posición
    public Player(Player other) {
        super(other);
        this.number = other.number;
        shieldCardDeck = new ShieldCardDeck();
        weaponCardDeck = new WeaponCardDeck();
        consecutiveHits = other.consecutiveHits;
        weapons = other.weapons;
        shields = other.shields;
    }

    //Este método realiza las tareas asociadas a la resurrección
    public void resurrect() {
        weapons.clear();
        shields.clear();
        setHealth(INITIAL_HEALTH);
        resetHits(); //consecutiveHits = 0;   
    }

    public char getNumber() {
        return number;
    }

    //Devuelve la dirección en la que se moverá el jagador (tomando como preferente la que ha solicitado)
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);

        if (size > 0 && (!contained)) {
            Directions firstElement = validMoves.get(0);
            return firstElement;
        } else {
            return direction;
        }
    }

    //Calcula la suma de la fuerza del jugador y la suma de lo aportado 
    //por sus armas (sumWeapons)
    @Override
    public float attack() {
        return getStrength() + sumWeapons();
    }

    //Este método delega su funcionalidad en el método manageHit
    @Override
    public boolean defend(float receivedAttack) {
        return manageHit(receivedAttack);
    }

    //Maneja las recompensas del jugador (armas, escudos y salud)
    public void receiveReward() {
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        Weapon wnew;
        Shield snew;

        for (int i = 1; i <= wReward; i++) {
            wnew = weaponCardDeck.nextCard();
            receiveWeapon(wnew);
        }

        for (int i = 1; i <= sReward; i++) {
            snew = shieldCardDeck.nextCard();
            receiveShield(snew);
        }

        int extraHealth = Dice.healthReward();
        setHealth(getHealth() + extraHealth);
        if (getHealth() > INITIAL_HEALTH) {
            setHealth(INITIAL_HEALTH);
        }
    }

    //Genera un representación del estado completo del jugador en 
    //forma de cadena de caracteres.
    @Override
    public String toString() {
        return super.toString();
    }

    //Descarta armas y da una nueva
    private void receiveWeapon(Weapon w) {
        for (int i = weapons.size() - 1; i >= 0; i--) {
            boolean discard = weapons.get(i).discard();
            if (discard) {
                weapons.remove(i);
            }
        }
        int size = weapons.size();
        if (size < MAX_WEAPONS) {
            weapons.add(w);
        }
    }

    //Descarta escudos y da uno nuevo
    private void receiveShield(Shield s) {
        for (int i = shields.size() - 1; i >= 0; i--) {
            boolean discard = shields.get(i).discard();
            if (discard) {
                shields.remove(i);
            }
        }
        int size = shields.size();
        if (size < MAX_SHIELDS) {
            shields.add(s);
        }
    }

    //Genera una nueva instancia de arma
    private Weapon newWeapon() {
        Weapon w = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        return w;
    }

    //Genera una nueva instancia de escudo
    private Shield newShield() {
        Shield s = new Shield(Dice.shieldPower(), Dice.usesLeft());
        return s;
    }

    //Devuelve la suma del resultado de llamar al método attack de todas sus armas.
    protected float sumWeapons() {
        float sol = 0;
        for (int i = 0; i < weapons.size(); ++i) {
            sol += weapons.get(i).attack();
        }
        return sol;
    }

    //Devuelve la suma del resultado de llamar al método protect de todas sus escudos.
    protected float sumShields() {
        float sol = 0;
        for (int i = 0; i < shields.size(); ++i) {
            sol += shields.get(i).protect();
        }
        return sol;
    }

    //Calcula la suma de la inteligencia con el aporte de los escudos
    protected float defensiveEnergy() {
        return getIntelligence() + sumWeapons();
    }

    //Defensa del jugador ante un ataque, devuelve si el jugador ha perdido
    private boolean manageHit(float receivedAttack) {
        boolean lose;

        float defense = defensiveEnergy();
        if (defense < receivedAttack) {
            gotWounded();
            incConsecutiveHits();
        } else {
            resetHits();
        }

        if ((consecutiveHits == HITS2LOSE) || (dead())) {
            resetHits();
            lose = true;

        } else {
            lose = false;
        }
        return lose;
    }

    //Fija el valor del contador de impactos consecutivos a cero
    private void resetHits() {
        consecutiveHits = 0;
    }

    // Incrementa en una unidad el contador de impactos consecutivos.
    private void incConsecutiveHits() {
        consecutiveHits++;
    }

}
