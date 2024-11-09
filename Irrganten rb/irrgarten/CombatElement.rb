require_relative 'Dice'

module Irrgarten
  class CombatElement
    #Métodos
    #Constructor
    def initialize(e,u)
      @effect=e
      @uses=u
    end

    protected def produce_effect
      if (@uses > 0) then @uses=@uses-1
        return @effect
      else
        return 0
      end
    end


    def discard
      return Dice.discard_element(@uses)
    end

    def to_s
      return "["+@power.to_s+", "+@uses.to_s+"]"
    end


  end
end
