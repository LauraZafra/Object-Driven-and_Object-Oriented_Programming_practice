# frozen_string_literal: true
require_relative 'Dice'
require_relative 'Weapon'
require_relative 'Shield'

module Irrgarten
  class LabyrinthCharacter
    attr_accessor :row
    attr_accessor :col
    attr_accessor :health         #permitiendo leer su valor desde dentro de la clase o sus subclases.
    attr_reader   :intelligence      #Sin embargo, intentar acceder al atributo desde fuera de la clase generará un error
    attr_reader   :strength
    attr_accessor :name

    #Metodos
    #Constructor
    def initialize(n, i, s, h)
      @name=n
      @intelligence=i
      @strength=s
      @health = h
      @row=-1
      @col=-1
    end

    def copy(other)
      @name = other.name
      @intelligence = other.intelligence
      @strength = other.strength
      @health = other.health
      @row= other.row
      @col= other.col
    end

    #Devuelve true si está muerto.
    def dead
      return (@health<=0)
    end

    #El GETROW() Y GETCOL(), estos consultares estan impicitos con lo que se ha puesto al principio de ' attr_accessor '
    # Igual para GETHELATH(), GETSTRENGT() y GETINTELLIGENCE() y SETHEALTH()

    #Es un modificador en una única llamada de los atributos row y col.
    def set_pos (row, col)
      @row = row
      @col = col
    end

    def to_string
      return "Name: " + @name + "\n" +
        "Intelligence: " + @intelligence.to_s + "\n" +
        "Strength: " + @strength.to_s + "\n" +
        "Health: " + @health.to_s + "\n" +
        "Row: " + @row.to_s + "\n" +
        "Col: " + @col.to_s + "\n"
    end

    #Este método decrementa en una unidad el atributo que representa la salud
    protected def got_wounded
      if (@health > 0) then
        @health=@health-1
      end
    end

    #métodos abstractos no se declaran: attack() y defend()

    #para los metodos abstractps
    private_class_method :new

  end
end
