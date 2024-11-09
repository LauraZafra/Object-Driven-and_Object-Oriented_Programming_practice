#encoding:utf-8

require_relative 'Dice'
require_relative 'Weapon'
require_relative 'Shield'
require_relative 'LabyrinthCharacter'

module Irrgarten
  class Player < LabyrinthCharacter

    attr_accessor :row
    attr_accessor :col
    attr_accessor :number
    attr_accessor :consecutive_hits
    attr_accessor :weapons
    attr_accessor :shields

    #atributos
    @@MAX_WEAPONS = 2
    @@MAX_SHIELDS = 3
    #@@INITIAL_HEALTH = 10
    @@INITIAL_HEALTH = 5
    @@HITS2LOSE = 3


    #Métodos
    #Consrtuctor
    def initialize(n, i, s)
      super("Player" + n.to_s, i,s, @@INITIAL_HEALTH)
      @number=n
      @consecutive_hits = 0
      @weapons = []  #Array del diagrama
      @shields = []   #Array del diagrama
    end

    def copy(other)
      super(other)
      @consecutive_hits = other.consecutive_hits
      @weapons = other.weapons.dup  #Array del diagrama
      @shields = other.shields.dup   #Array del diagrama
    end

    # Este método realiza las tareas asociadas a la resurrección. Debe hacer que las listas
    # de armas y escudos sean listas vacías, que el nivel de salud sea el determinado para el inicio del
    # juego y el número consecutivo de impactos cero
    def resurrect
      @weapons.clear
      @shields.clear
      @health=@@INITIAL_HEALTH
      reset_hits
    end

    #getnumber() esta antes con el 'attr_accessor'

    def move(direction, valid_moves)
      size=valid_moves.size
      contained = valid_moves.include?(direction)

      if((size>0) && (!contained))
        first_element=valid_moves[0]
        return first_element
      else
        return direction
      end
    end

    #Calcula la suma de la fuerza del jugador y la suma de lo aportado
    #por sus armas (sumWeapons)
    def attack
      return @strength + sum_weapons
    end

    #Este método delega su funcionalidad en el método manageHit
    def defend (received_attack)
      return manage_hit(received_attack)
    end

    def receive_reward
      wReward = Dice.weapons_reward
      sReward = Dice.shields_reward
      for _ in 1..wReward
        wnew = new_weapon
        receive_weapon(wnew)
      end

      for _ in 1..sReward
        snew = new_shield
        receive_shield(snew)
      end

      extra_health = Dice.health_reward
      @health = @health + extra_health
      if (@health > @@INITIAL_HEALTH)
        @health = @@INITIAL_HEALTH
      end
    end

    # Genera un representación del estado completo del jugador en forma de cadena de
    # caracteres.
    def to_string
      #return "Name: " + @name + "\n" +
      #  "Number: " + @number.to_s + "\n" +
      #  "Intelligence: " + @intelligence.to_s + "\n" +
      #  "Strength: " + @strength.to_s + "\n" +
      # "Health: " + @health.to_s + "\n" +
      # "Row: " + @row.to_s + "\n" +
      # "Col: " + @col.to_s + "\n" +
      # "Consecutive hits: " + @consecutive_hits.to_s + "\n"
      res="Number: " + @number.to_s + "\n" + super
      return res

    end

    private def receive_weapon(w)
      #@weapons.each { |i|
      #  if (i.discard) then
      #    @weapons.delete(i)
      #  end }
      @weapons.delete_if { |i| i.discard }

      size = @weapons.size
      if (size < @@MAX_WEAPONS) then
        @weapons.push(w)
      end
    end

    private def receive_shield(s)
      #@shield.each {|i|
      #  if(i.discard)
      #    @shields.delete(i)
      #  end }

      @shields.delete_if { |i| i.discard }

      size=@shields.size
      if(size<@@MAX_SHIELDS)
        @shields.push(s)
      end

    end

    #. Genera una nueva instancia de arma. Los parámetros necesarios para
    # construir el arma se le solicitarán al dado.
    private def new_weapon
      w = Weapon.new(Dice.weapon_power, Dice.uses_left)
      return w
    end

    #Genera una nueva instancia de escudo
    private def new_shield
      s = Shield.new(Dice.shield_power, Dice.uses_left)
      return s
    end

    # Devuelve la suma del resultado de llamar al método attack de todas sus armas.
    protected def sum_weapons
      sol=0.0
      for i in 0...@weapons.size
        sol+=@weapons[i].attack
      end
      return sol
    end


    #Devuelve la suma del resultado de llamar al método protect de todas sus escudos.
    protected def sum_shields
      sol = 0
      for i in 0...@shields.size
        sol += @shields[i].protect
      end
      return sol
    end

    #. Calcula la suma de la inteligencia con el aporte de los escudos
    # (sumShields).
    protected def defensive_energy
      return @intelligence + sum_weapons
    end

    private def manage_hit(received_attack)
      defense = defensive_energy

      if(defense<received_attack)
        got_wounded
        inc_consecutive_hits
      else
        reset_hits
      end

      if((@consecutive_hits==@@HITS2LOSE) || (dead))
        reset_hits
        lose = true
      else
        lose = false
      end
      return lose
    end

    #Fija el valor del contador de impactos consecutivos a cero
    private def reset_hits
      @consecutive_hits = 0
    end

    #Incrementa en una unidad el contador de impactos consecutivos
    private def inc_consecutive_hits
      @consecutive_hits=@consecutive_hits+1
    end

    #para los metodos abstractps
    public_class_method :new

  end
end

