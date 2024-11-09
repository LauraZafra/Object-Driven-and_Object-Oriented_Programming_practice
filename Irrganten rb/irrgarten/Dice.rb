#encoding:utf-8


module Irrgarten
class Dice

    @@MAX_USES = 5 #(número máximo de usos de armas y escudos)
    @@MAX_INTELLIGENCE = 10.0 #(valor máximo para la inteligencia de jugadores y monstruos)
    @@MAX_STRENGTH = 10.0 #(valor máximo para la fuerza de jugadores y monstruos)
    @@RESURRECT_PROB = 0.3 #(probabilidad de que un jugador sea resucitado en cada turno)
    @@WEAPONS_REWARD = 2 #(numero máximo de armas recibidas al ganar un combate)
    @@SHIELDS_REWARD = 3 #(numero máximo de escudos recibidos al ganar un combate)
    @@HEALTH_REWARD = 5 #(numero máximo de unidades de salud recibidas al ganar un  combate)
    @@MAX_ATTACK = 3.0 #(máxima potencia de las armas)
    @@MAX_SHIELD = 2.0 #(máxima potencia de los escudos)
    
    @@generator = Random.new()

    #Métodos

    #Nuevo metodo para la p4
    def self.next_step(preference, valid_moves, intelligence)
        if @@generator.rand(1.0) < 1.0*(intelligence/@@MAX_INTELLIGENCE)
            return preference
        else
            return valid_moves.sample #El método sample en Ruby se utiliza en un array para obtener un elemento aleatorio de ese array.
        end
    end

    def self.random_pos ( max )
        return @@generator.rand(max)
    end

    def self.who_starts( nplayers)
        return @@generator.rand(nplayers)
    end

    def self.random_intelligence()
        return @@generator.rand(@@MAX_INTELLIGENCE)
    end
    
    def self.random_strength()
        return @@generator.rand(@@MAX_STRENGTH)
    end

    def self.resurrect_player()
        if (@@generator.rand < @@RESURRECT_PROB) 
            return true
        else
            return false
        end
    end

    def self.weapons_reward()
        return @@generator.rand(@@WEAPONS_REWARD+1)
    end
    

    def self.shields_reward()
        return @@generator.rand(@@SHIELDS_REWARD+1)
    end


    def self.health_reward()
        return @@generator.rand(@@HEALTH_REWARD+1)
    end

    def self.weapon_power
        return @@generator.rand(@@MAX_ATTACK)
    end

    def self.shield_power
        return @@generator.rand(@@MAX_SHIELD)
    end
        
    def self.uses_left
        return @@generator.rand(@@MAX_USES)
    end

    def self.intensity(competence)
        return @@generator.rand(competence)
    end

    def self.discard_element(usesLeft)
        if (@@generator.rand < 1.0*(@@MAX_USES-usesLeft)/@@MAX_USES) then
          return true
        else
          return false
        end
    end


        #PROBAR FUNCIONES
        #s=Dice.new()
         #puts s.random_pos(5)
         #puts s.who_starts(100)
         #puts s.random_intelligence
         #puts s.random_strength
         #puts s.resurrect_player
         #puts s.weapons_reward
         #puts s.shield_reward
         #puts s.health_reward
         #puts s.weapon_power
         #puts s.shield_power
         #puts s.uses_left
         #puts s.intensity(10.0)
         #puts s.discard_element(1)

end
end