#encoding:utf-8

require_relative 'Dice'

module Irrgarten
  class Monster < LabyrinthCharacter

    #atributos
    @@INITIAL_HEALTH=5

    #Métodos (en ruby siempre son privados)
    #Consrtuctor
    def initialize(n, i, s)
      super(n,i,s,@@INITIAL_HEALTH)
    end

    #Genera el resultado delegando en el método intensity del dado pasando como
    # parámetro la fuerza del monstruo
    def attack
      return Dice.intensity(@strength)
    end

    def defend (received_attack)
      is_dead = dead

      if(!is_dead)
        defensive_energy=Dice.intensity(@intelligence)
        if (defensive_energy < received_attack)
          got_wounded
          is_dead = dead
        end
      end
      return is_dead
    end

    #para los metodos abstractps
    public_class_method :new

  end
end
