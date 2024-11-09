
require_relative 'Dice'
require_relative 'Orientation'

module Irrgarten
  class Labyrinth
    @@BLOCK_CHAR = 'X'
    @@EMPTY_CHAR = '-'
    @@MONSTER_CHAR = 'M'
    @@COMBAT_CHAR = 'C'
    @@EXIT_CHAR = 'E'
    @@ROW = 0
    @@COL = 1

    attr_reader :n_rows
    attr_reader :n_cols


    #Métodos
    # Constructor
    def initialize(nr, nc, er, ec)
      @n_rows=nr
      @n_cols=nc
      @exit_row=er
      @exit_col=ec

      #EN RUBY solo hace falta declarar los arrays en el constructor (DISTINTO QUE EN JAVA)
      @monsters_square = Array.new(@n_rows) { Array.new(@n_cols) }
      @players_square = Array.new(@n_rows) { Array.new(@n_cols) }
      @labyrinth_square = Array.new(@n_rows) { Array.new(@n_cols) }

      for i in 0...@n_rows
        for j in 0...@n_cols
          @labyrinth_square[i][j] = @@EMPTY_CHAR
        end
      end

      #para poner la casilla de salida
      @labyrinth_square[@n_rows-1][@n_cols-1]=@@EXIT_CHAR

    end

    #Cómo se crea el laberinto en el main
    #Labyrinth.new(5,7,2,3)

    #Devuelve true si hay un jugador en la casilla de salida y false si no hay
    # ninguno //NOS QUEDAMOS POR AQUI
    def have_a_winner
      return (@players_square[@exit_row][@exit_col] != nil)
    end

    #Genera un representación del estado completo del laberinto en
    #forma de cadena de caracteres.
    def to_string
      s = ""
      for i in 0...@n_rows
        for j in 0...@n_cols
          s += @labyrinth_square[i][j].to_s
          s += " "
        end
        s += "\n"
      end
      #@labyrinth_square.each_with_index  do | i, @n_rows|
      #  @labyrinth_square.each_with_index do | j, @n_cols|
      #    s += @labyrith_square[i-1][j-1].to_s
      #    s += " "
      #  end
      #  s += "\n"
      #end
      return s
    end

      #Si la posición suministrada está dentro del tablero y
      # está vacía, anota en el laberinto la presencia de un monstruo, guarda la referencia del monstruo en el
      # atributo contenedor adecuado e indica al monstruo cual es su posición actual (setPos).
      def add_monster (row, col, m)
        @labyrinth_square[row][col] = @@MONSTER_CHAR
        m.set_pos(row,col)
        @monsters_square[row][col] = m
      end


    def add_block (orientation, start_row, start_col, lenght)
      if (orientation == Orientation::VERTICAL) then
        inc_row = 1
        inc_col = 0
      else
        inc_row = 0
        inc_col = 1
      end

      row = start_row
      col = start_col

      while (((pos_ok(row, col)) && (empty_pos(row, col)) && (lenght > 0)))
        @labyrinth_square [row][col] = @@BLOCK_CHAR
        lenght = lenght -1
        row = row + inc_row
        col = col + inc_col
      end
    end



      #Devuelve true si la posición proporcionada está dentro del
      # laberinto
    def pos_ok (row, col)
      posicion_valida=((row >= 0) && (row < @n_rows) && (col >= 0) && (col < @n_cols))
      return posicion_valida
    end

    #Devuelve true si la posición suministrada está vacía.
    def empty_pos (row, col)
      return (pos_ok(row, col) && @labyrinth_square[row][col]==@@EMPTY_CHAR)
    end

    #Devuelve true si la posición suministrada alberga exclusivamente un
    # monstruo.
    def monster_pos (row, col)
      return (pos_ok(row, col) && @labyrinth_square[row][col]==@@MONSTER_CHAR)
    end

    #Devuelve true si la posición suministrada es la de salida.
    def exit_pos (row, col)
      return (pos_ok(row,col) && @labyrinth_square[row][col]==@@EXIT_CHAR)
    end

    #Devuelve true si la posición suministrada contiene a la vez un monstruo
    #y un jugador (carácter ‘C’)
    def combat_pos (row, col)
      return (pos_ok(row,col) && @labyrinth_square[row][col]==@@COMBAT_CHAR)
    end

    #Indica si la posición suministrada está dentro del laberinto y se
    # corresponde con una de estas tres opciones: casilla vacía, casilla donde habita un monstruo o salida
    # (posOK, emptyPos, monsterPos, exitPos).
    def can_step_on (row, col)

      return (pos_ok(row,col) && (empty_pos(row, col) || monster_pos(row, col) || exit_pos(row, col)))
    end

    #Este método solo realiza su función si la posición suministrada estádentro del laberinto.
    # Si es el caso, si en esa posición el laberinto estaba indicando el estado de
    # combate, el estado de esa casilla del laberinto pasa a indicar que simplemente hay un monstruo. En
    # otro caso, el estado de esa casilla del laberinto pasa a indicar que está vacía. Este método es llamado
    # cuando un jugador abandona una casilla y se encarga de dejar la casilla que se abandona en el
    # estado correcto.
    def update_old_pos (row, col)
      if(pos_ok(row, col))
        if(@labyrinth_square[row][col]==@@COMBAT_CHAR)
          @labyrinth_square[row][col]=@@MONSTER_CHAR
        else
          @labyrinth_square[row][col]=@@EMPTY_CHAR
        end
      end
    end

    #calcula la posición del laberinto a la que se llegaría si desde la
    #posición suministrada se avanza en la dirección pasada como parámetro
    def dir_2_pos (row, col, direction)
      sol = [] # pos 0 = fila, pos 1 = columna
      sol[@@ROW] = row
      sol[@@COL] = col
      '''
      case direction
        when :UP
          sol[@@ROW] = sol[@@ROW]-1
        when :DOWN
          sol[@@ROW] = sol[@@ROW]+1
        when :LEFT
          sol[@@COL] = sol[@@COL]-1
        when :RIGHT
          sol[@@COL] = sol[@@COL]+1
      end
      '''
      if(direction==Directions::UP)
        sol[@@ROW] = sol[@@ROW]-1
      end
      if(direction==Directions::DOWN)
        sol[@@ROW] = sol[@@ROW]+1
      end
      if(direction==Directions::LEFT)
        sol[@@COL] = sol[@@COL]-1
      end
      if(direction==Directions::RIGHT)
        sol[@@COL] = sol[@@COL]+1
      end

      return  sol
    end

    # Utilizando el dado, genera una posición aleatoria en el laberinto (fila y
    # columna) asegurando que esta esté vacía. Genera internamente posiciones hasta que se cumple esta
    # restricción y una vez generada se devuelve. Si no hay posiciones vacías se producirá un bucle
    # infinito
    def ramdom_empty_pos
      sol = Array.new(2)
      begin
        sol[@@ROW] = Dice.random_pos(@n_rows)
        sol[@@COL] = Dice.random_pos(@n_cols)
      end while (!(empty_pos(sol[@@ROW], sol[@@COL])))
      return sol
    end

    #Método para distribuir jugadores en un array.
    def spread_players (players)
      pos=[]
      oldRow=-1
      oldCol=-1

      players.each do |player|
        pos= ramdom_empty_pos
        put_player_2D(oldRow, oldCol, pos[@@ROW], pos[@@COL], player)

        #oldRow = pos[@@ROW]
        #oldCol = pos[@@COL]

      end
    end

    #Método para colocar un jugador en una dirección.
    def put_player (direction, player)
      old_row=player.row
      old_col=player.col

      new_pos =[]
      new_pos = dir_2_pos(old_row, old_col, direction)
      monster = put_player_2D(old_row, old_col, new_pos[@@ROW],new_pos[@@COL], player)
      puts "put_player #{new_pos}"
      return monster
    end

    #Método para obtener movimientos válidos en una posición dada
    def valid_moves (row, col)
      output = []

      if(can_step_on(row+1,col))
        output.push(Directions::DOWN)
      end

      if(can_step_on(row-1,col))
        output.push(Directions::UP)
      end

      if(can_step_on(row,col+1))
        output.push(Directions::RIGHT)
      end

      if(can_step_on(row,col-1))
        output.push(Directions::LEFT)
      end
      puts "Output tiene tamaño #{output.size}"
      return output
    end

    #Método para colocar un jugador en 2D
    # Devuelve
    def put_player_2D (old_row, old_col, row, col, player)
      output = nil
      if (can_step_on(row, col)) then
        if (pos_ok(old_row, old_col)) then
          p = @players_square[old_row][old_col]
          if (p == player) then
            update_old_pos(old_row, old_col)
            @players_square[old_row][old_col] = nil
          end
        end
        monster_pos = monster_pos(row, col)
        if (monster_pos) then
          @labyrinth_square[row][col] = @@COMBAT_CHAR
          output = @monsters_square[row][col]
        else
          number = player.number
          @labyrinth_square[row][col] = number
        end
        @players_square[row][col] = player
        player.set_pos(row,col)
      end
      return output
    end

    #Funcion de la p4
    def update_player(fuzzy)
      update_old_pos(fuzzy.row, fuzzy.col)
      put_player_2D(-1,-1, fuzzy.row, fuzzy.col, fuzzy)
    end
  end

end

