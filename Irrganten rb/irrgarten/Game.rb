#encoding:utf-8

require_relative 'Dice'
require_relative 'Labyrinth'
require_relative 'Player'
require_relative 'GameCharacter'
require_relative 'GameState'
require_relative 'Monster'
require_relative 'FuzzyPlayer'

module Irrgarten
  class Game

    #atributos
    @@MAX_ROUND = 10
    @@N_ROW = 5
    @@N_COL = 5
    @@ROW_EXIT = 4
    @@COL_EXIT = 4


    #Métodos (en ruby siempre son privados)
    #Constuctor
    def initialize(nplayers)
      @current_player_index = Dice.who_starts(nplayers)
      @log = ""
      @monsters = Array.new
      @labyrinth = Labyrinth.new(@@N_ROW, @@N_COL, @@ROW_EXIT, @@COL_EXIT)
      @nplayers = nplayers
      puts "Se ha creado un juego con #{@nplayers} jugadores"
      @players = Array.new
      puts "Se crea el array de jugadores vacío: #{@players.size}"

      nplayers.times { |i|
        @players.append(Player.new(i, 0, 0))
        #@players.append(Player.new(i, Dice.random_intelligence, Dice.random_strength))
      }


      @current_player_index = Dice.who_starts(@nplayers)
      @current_player = @players[@current_player_index]

      configure_labyrinth
      @labyrinth.spread_players(@players)

    end

    #Delega en el método del laberinto que indica si hay un ganador.
    def finished
      return @labyrinth.have_a_winner
    end


    # CREADO POR NOSOTRAS
    # toString de cada jugador en un solo string
    def players_to_string
      String s = ""
      @players.each do |player|
        s += player.to_string
        s += "\n"
      end
      return s
    end

    # CREADO POR NOSOTRAS
    # toString de cada monstruo en un solo string
    def monster_to_string
      String s = ""
      @monsters.each do |monster|
        s += monster.to_string
        s += "\n"
      end
      return s
    end


      # Genera una instancia de GameState integrando toda la información del
    # estado del juego.
    def get_game_state
      gs = GameState.new(@labyrinth.to_string, players_to_string,
                                   monster_to_string, @current_player_index,
                                   @labyrinth.have_a_winner, @log)
      return gs
    end

    #Configura el laberinto añadiendo bloques de obstáculos y monstruos. Los
    #monstruos, además de en el laberinto son guardados en el contenedor propio
    #de esta clase para este tipo de objetos.
    def configure_labyrinth
      puts "Configurando laberinto\n"
      m1 = Monster.new("Dragón", Dice.random_intelligence, Dice.random_strength)
      m2 = Monster.new("Cíclope", Dice.random_intelligence, Dice.random_strength)
      m3 = Monster.new("Zombie", Dice.random_intelligence, Dice.random_strength)

      @labyrinth.add_monster(1, 3, m1)
      @labyrinth.add_monster(3, 2, m2)
      @labyrinth.add_monster(4, 3, m3)

      @monsters.push(m1)
      @monsters.push(m2)
      @monsters.push(m3)

      #añadir bloques
      #añadir paredes al laberinto
      @labyrinth.add_block(Orientation::HORIZONTAL,1,0,1);#funciona
      @labyrinth.add_block(Orientation::HORIZONTAL,0,2,1);#funciona
      @labyrinth.add_block(Orientation::HORIZONTAL,2,4,1);#funciona
      @labyrinth.add_block(Orientation::HORIZONTAL,2,1,1);#funciona
      @labyrinth.add_block(Orientation::HORIZONTAL,2,3,1);#funciona
      @labyrinth.add_block(Orientation::HORIZONTAL,3,0,1);#funciona
      @labyrinth.add_block(Orientation::HORIZONTAL,4,1,1);#funcionqa


    end

    #Actualiza los dos atributos que indican el jugador (current*)
    #con el turno pasando al siguiente jugador.
    def next_player
      @current_player_index = (@current_player_index + 1) % @players.size
      @current_player = @players[@current_player_index]
    end

    # Función para registrar que el jugador ha ganado el combate.
    def log_player_won
      @log += "El jugador ha ganado el combate.\n"
    end

    # Función para registrar que el monstruo ha ganado el combate.
    def log_monster_won
      @log += "El monstruo ha ganado el combate.\n"
    end

    # Función para registrar que el jugador ha resucitado.
    def log_resurrected
      @log += "El jugador ha resucitado.\n"
    end

    # Función para registrar que el jugador ha perdido el turno por estar muerto.
    def log_player_skip_turn
      @log += "El jugador ha perdido el turno por estar muerto.\n"
    end

    # Función para registrar que el jugador no ha seguido las instrucciones del jugador humano.
    def log_player_no_orders
      @log += "El jugador no ha seguido las instrucciones del jugador humano (no fue posible).\n"
    end

    # Función para registrar que el jugador se ha movido a una celda vacía o no le ha sido posible moverse.
    def log_no_monster
      @log += "El jugador se ha movido a una celda vacía o no le ha sido posible moverse.\n"
    end

    # Función para registrar el número de rondas de combate.
    def log_rounds(rounds, max)
      @log += "Se han producido #{rounds} de #{max} rondas de combate.\n"
    end

    # Método público para el siguiente paso, no es privado.
    def next_step(preferred_direction)
      @log = ""
      dead=@current_player.dead

      if(!dead)
        direction=actual_direction(preferred_direction)
        if (direction != preferred_direction) then
          log_player_no_orders
        end

        monster = @labyrinth.put_player(direction, @current_player)
        if(monster==nil)
          log_no_monster
        else
          winner=combat(monster)
          manage_reward(winner)
        end
      else
        manage_resurrection
      end

      end_game=finished
      if (!end_game) then
        next_player
      end

      return end_game
    end

    # Función privada para el cálculo de la dirección actual.
    def actual_direction(preferred_direction)
      current_row=@current_player.row
      current_col = @current_player.col
      valid_moves=[]
      valid_moves = @labyrinth.valid_moves(current_row, current_col)
      output = @current_player.move(preferred_direction, valid_moves)
      return output

    end

    # Función privada para el combate entre un personaje del juego y un monstruo.
    def combat(monster)
      rounds = 0
      winner = GameCharacter::PLAYER

      player_attack = @current_player.attack
      lose = monster.defend(player_attack)

      while ((!lose) && (rounds<@@MAX_ROUND))
        winner=GameCharacter::MONSTER
        rounds=rounds+1
        monster_attack=monster.attack
        lose=@current_player.defend(monster_attack)
        if (!lose) then
          player_attack = @current_player.attack
          winner = GameCharacter::PLAYER
          lose = monster.defend(player_attack)
        end
      end
      log_rounds(rounds, @@MAX_ROUND)
      return winner
    end

    # Función privada para gestionar la recompensa para el ganador del combate.
    def manage_reward(winner)
      if(winner==GameCharacter::PLAYER)
        @current_player.receive_reward
        log_player_won
      else
        log_monster_won
      end

    end

    # Función privada para gestionar la resurrección de un personaje.
    def manage_resurrection
      resurrect=Dice.resurrect_player
      if(resurrect)
        @current_player.resurrect
        fuzzy=FuzzyPlayer.new(@current_player)
        @current_player=fuzzy
        @players.insert(@current_player_index, @current_player)
        @labyrinth.update_player(fuzzy)
        log_resurrected
      else
        log_player_skip_turn
      end
    end

  end
end


