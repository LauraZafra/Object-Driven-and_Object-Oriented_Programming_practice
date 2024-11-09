#encoding:utf-8

#tener cuidado con require_relative, solo ponerlo en los que se invoquen en el archivo
require_relative 'Weapon'
require_relative 'Shield'
require_relative 'Dice'
require_relative 'Monster'
require_relative 'Player'
require_relative 'Labyrinth'
require_relative 'Game'

module Irrgarten


    class Test_p1
        def self.main

        #En weapon y shiedl se llama con obejeto. porque son métodos de instancia, mientras que dice se llama sobre la clase porque son métodos de clase
        #PROBAR FUNCIONES WEAPON
        w = Irrgarten::Weapon.new(2.9,5)
        #w=Weapon.new(2.0,5)
        w.attack
        w.to_s
        w.discard

        #PROBAR FUNCIONES SHIELD
        s=Shield.new(3.0,4)
        s.protect
        s.to_s
        s.discard

        #PROBAR FUNCIONES DICE
        
        puts Irrgarten::Dice.random_pos(5)
        puts Irrgarten::Dice.who_starts(100)
        puts Irrgarten::Dice.random_intelligence
        puts Irrgarten::Dice.random_strength
        puts Irrgarten::Dice.resurrect_player
        puts Irrgarten::Dice.weapons_reward
        puts Irrgarten::Dice.shield_reward
        puts Irrgarten::Dice.health_reward
        puts Irrgarten::Dice.weapon_power
        puts Irrgarten::Dice.shield_power
        puts Irrgarten::Dice.uses_left
        puts Irrgarten::Dice.intensity(10.0)
        puts Irrgarten::Dice.discard_ele
        end
    end



    class Test_p2
        def self.main
            #Comprobar funciones de Monster
            m =Irrgarten::Monster.new('Alberto', Dice.random_intelligence, Dice.random_strength)
            puts m.to_string
            puts m.dead
            puts m.attack
            m.set_pos(2,3)
            puts m.to_string

            #Comprobar funciones Player
            p = Irrgarten::Player.new('0', Dice.random_intelligence, Dice.random_strength)
            puts p.to_string
            p.set_pos(0,0)
            puts p.dead
            puts p.attack
            puts p.to_string
            p.resurrect
            puts p.to_string

            #Comprobar funciones de laberinto
            puts "LABERINTO"
            l = Irrgarten::Labyrinth.new(5,5,4,4)
            puts l.have_a_winner
            puts l.to_string
            l.add_monster(1,3, m)
            puts l.to_string

            #Comprobar funciones game
            g = Irrgarten::Game.new(1)
            puts g.finished
        end
    end

   #en ruby hay que llamar al main para que funcione la clase de main creada
   #Test_p1.main
   Test_p2.main

end