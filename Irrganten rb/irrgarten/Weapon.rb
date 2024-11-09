#encoding:utf-8

require_relative 'Dice'
require_relative 'CombatElement'

module Irrgarten
class Weapon < CombatElement

    #Métodos
    #Consrtuctor
    def initialize(p,u)
        super(p,u)
    end

    #Devuelve el numero de ataques 
    def attack
        return produce_effect
    end

    #Decuelve 
    def to_s
        return "W" + super
    end


end
end