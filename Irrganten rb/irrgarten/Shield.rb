#encoding:utf-8

require_relative 'Dice'

module Irrgarten
class Shield < CombatElement

    #Métodos
    #Consrtuctor
    def initialize(p,u)
        super(p,u)
    end

    #Devuelve el numero de ataques 
    def protect
        return produce_effect
    end

    #Decuelve 
    def to_s
        return "S" + super
    end

end
end