package irrgarten;

/**
 *
 * @author laurazafra mercheleon
 */
public class Irrgarten {

    /*
    public class Test_P1 {
        /*
        public static void main(String[] args){
        //Funciona main
        System.out.println("hola\n");
        
        //Funciona Weapon
        Weapon w1 = new Weapon((float)2.0, 2);
        System.out.println(w1.toString());
        w1.attack();
        System.out.println(w1.toString());
        System.out.println("\n");
        
        //Funciona Shield
        Shield s1 = new Shield ((float)3.0, 2);
        System.out.println(s1.toString());
        s1.protect();
        System.out.println(s1.toString());
        System.out.println("\n");
        
        //Funciona Dice
        Dice d1 = new Dice ();
        for (int i=0; i<5; ++i){
            System.out.println(d1.randomPos(5));
        }
        System.out.println("\n");
        
        for (int i=0; i<5; ++i){
            System.out.println(d1.whoStarts(5));
        }
        System.out.println("\n");
        
        for (int i=0; i<5; ++i){
            System.out.println(d1.randomIntelligence());
        }
        System.out.println("\n");
        
        for (int i=0; i<5; ++i){
            System.out.println(d1.randomStrength());
        }
        System.out.println("\n");
        
        for (int i=0; i<20; ++i){
            System.out.println(d1.resurrectPlayer());
        }
        System.out.println("\n");
        
        for (int i=0; i<5; ++i){
            System.out.println(d1.weaponsReward());
        }
        System.out.println("\n");
        
        for (int i=0; i<15; ++i){
            System.out.println(d1.shieldsReward());
        }
        System.out.println("\n");
        
        for (int i=0; i<15; ++i){
            System.out.println(d1.healthReward());
        }
        System.out.println("\n");
        
        for (int i=0; i<15; ++i){
            System.out.println(d1.weaponPower());
        }
        System.out.println("\n");
        
        for (int i=0; i<15; ++i){
            System.out.println(d1.shieldPower());
        }
        System.out.println("\n");
        
        for (int i=0; i<15; ++i){
            System.out.println(d1.usesLeft());
        }
        System.out.println("\n");
        
        for (int i=0; i<15; ++i){
            System.out.println((float)d1.intensity(8));
        }
        System.out.println("\n");
        
        for (int i=0; i<15; ++i){
            System.out.println(d1.discardElement(d1.usesLeft()));
        }
        System.out.println("\n");
        
        //Funciona discard
        for (int i=0; i<15; ++i) System.out.println(w1.discard());
        for (int i=0; i<15; ++i) System.out.println(s1.discard());
        
        //Funciona GameState
        GameState g1 = new GameState("a", "b", "c", 7, true, "d");
        System.out.println(g1.getLabyrinthv());
        
        }
        
        
    }
*/
    
//    public class Test_P2 {
        /*public static void main(String[] args){
            
            System.out.println("hola");
            
            //Prueba monster
            Monster m = new Monster ("Alberto", (float)5.0, (float)5.0);
            System.out.println(m.toString());
            System.out.println(m.dead());
            System.out.println(m.attack());
            m.setPos(3,2);
            System.out.println(m.toString());
            
            //Prueba Player
            Player p = new Player ('0', Dice.randomIntelligence(), Dice.randomStrength());
            System.out.println(p.toString());
            p.setPos(0, 0);
            System.out.println(p.dead());
            System.out.println(p.attack());
            System.out.println(p.toString());
            p.resurrect();
            System.out.println(p.toString());
            
            //Prueba laberinto
            Labyrinth l = new Labyrinth (5,5,4,4);
            System.out.println(l.haveAWinner());
            System.out.println(l.toString());
            l.addMonster(1, 3, m);
            System.out.println(l.toString());
            
            //Prueba game
            Game g = new Game(1);
            System.out.println(g.finished());
            //g.nextStep(Directions.RIGHT);
            //g.getGameState().toString();
        }
*/
//    }
    
    
}
