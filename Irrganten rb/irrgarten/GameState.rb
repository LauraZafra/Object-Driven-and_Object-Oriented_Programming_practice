#encoding:utf-8


module Irrgarten
class GameState
  
    def initialize(labyrinth, players, monsters, current_player, winner, log)
      @labyrinth = labyrinth
      @players = players #string
      @monsters = monsters #string
      @current_player = current_player
      @winner = winner
      @log = log

    end

    def labyrinth
        return @labyrinth
    end

    def players
        return @players
    end

    def monsters
        return @monsters
    end

    def current_player
        return @current_player
    end

    def winner
        return @winner
    end

    def log
        return @log
    end

  end
end