require_relative 'Dice'
require_relative 'Weapon'
require_relative 'Shield'

module Irrgarten
  class FuzzyPlayer < Player

    def initialize(other)
      copy(other)
    end

    def move(directions, valid_moves)
      dir_player=super(directions, valid_moves)
      return Dice.next_step(dir_player, valid_moves, @intelligence)
    end

    protected def defensive_energy
      return sum_shields + Dice.intensity(@intelligence)
    end

    def attack
      return sum_weapons + Dice.intensity(@strength)
    end

    def t_s
      return "Fuzzy " + super
    end

  end
end

