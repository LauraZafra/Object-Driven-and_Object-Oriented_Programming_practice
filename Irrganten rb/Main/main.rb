# frozen_string_literal: true

require_relative '../irrgarten/Game.rb'
#require_relative 'irrgarten/rb'
require_relative '../TextUI/textUI'
require_relative '../Controller/controller'

#includes
include UI
include Control
include Irrgarten

module Main
  class TestP3_1
    def self.main

      puts "Bienvenido al juego\n"

      g=Game.new(2) #Para la prueba solo necesitamos un jugador

      v = TextUI.new
      v.show_game(g.get_game_state) #Monstramos toda la informacion

      puts "Intentamos mover jugador a la derecha"
      g.next_step(Directions::RIGHT) #Intentamos volver al jugador un paso a la izquierda
      v.show_game(g.get_game_state) #Monstramos toda la informacion

      g.next_step(Directions::DOWN) #Intentamos volver al jugador un paso a la derecha

      v.show_game(g.get_game_state) #Monstramos toda la informacion

      g.next_step(Directions::RIGHT) #Intentamos volver al jugador un paso a la derecha

      v.show_game(g.get_game_state) #Monstramos toda la informacion
      g.next_step(Directions::DOWN) #Intentamos volver al jugador un paso a la derecha

      v.show_game(g.get_game_state) #Monstramos toda la informacion

      g.next_step(Directions::DOWN) #Intentamos volver al jugador un paso a la derecha

      v.show_game(g.get_game_state) #Monstramos toda la informacion
      g.next_step(Directions::RIGHT) #Intentamos volver al jugador un paso a la derecha

      v.show_game(g.get_game_state) #Monstramos toda la informacion
      g.next_step(Directions::RIGHT) #Intentamos volver al jugador un paso a la derecha

      v.show_game(g.get_game_state) #Monstramos toda la informacion
      g.next_step(Directions::DOWN) #Intentamos volver al jugador un paso a la derecha

      v.show_game(g.get_game_state) #Monstramos toda la informacion

    end
  end

  class TestP3_2
    def self.main
      juego = Game.new(1)
      vista = TextUI.new
      controlador = Controller.new(juego, vista)
      controlador.play
    end
  end

  TestP3_2.main
end


