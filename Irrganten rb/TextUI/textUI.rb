
require 'io/console'
require_relative '../irrgarten/Directions'

module UI

  class TextUI

    #https://gist.github.com/acook/4190379
    def read_char
      STDIN.echo = false
      STDIN.raw!
    
      input = STDIN.getc.chr
      if input == "\e" 
        input << STDIN.read_nonblock(3) rescue nil
        input << STDIN.read_nonblock(2) rescue nil
      end
    ensure
      #STDIN.echo=true
      #STDIN.cooked!
    
      return input
    end

    def next_move
      print "Where? "
      got_input = false
      while (!got_input)
        c = read_char
        case c
          when "w"
            puts "UP ARROW"
            output = Irrgarten::Directions::UP
            got_input = true
          when "s"
            puts "DOWN ARROW"
            output = Irrgarten::Directions::DOWN
            got_input = true
          when "d"
            puts "RIGHT ARROW"
            output = Irrgarten::Directions::RIGHT
            got_input = true
          when "a"
            puts "LEFT ARROW"
            output = Irrgarten::Directions::LEFT
            got_input = true
          when "\u0003"
            puts "CONTROL-C"
            got_input = true
            exit(1)
          else
            #Error
        end
      end
      output
    end

    def show_game(get_game_state)
      puts "Laberinto\n"
      puts get_game_state.labyrinth
      puts "Jugadores\n" #imprimir el array de jugadores
      puts get_game_state.players
      puts "Monstruos\n" #imprimir el array de monstruos
      puts get_game_state.monsters
      puts "Indice del jugador actual\n"
      puts get_game_state.current_player
      if get_game_state.winner then
        puts "GANADOR"
      end
      puts get_game_state.log
      puts "\n" + "-------------     ---     -------------" + "\n"
    end
  end # class
end #module


