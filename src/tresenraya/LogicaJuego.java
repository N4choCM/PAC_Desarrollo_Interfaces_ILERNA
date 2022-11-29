package tresenraya;

import java.awt.Color;

public class LogicaJuego {

    int turno, pX, pO; // Turno del jugador
    boolean habilitado; // Habilita y deshabilita el tablero

    /**
     * Inicializaremos el juego con las siguientes variables_
     *
     * @param turno (Nos indicará el turno del jugador: 0 - X, 1 - O)
     * @param pX (Contiene el valor total de las victorias de este jugador)
     * @param pO (Contiene el valor total de las victorias de este jugador)
     */
    public LogicaJuego(int turno, int pX, int pO) {
        this.turno = turno;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Obtener turno
     *
     * @return
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Insertar turno
     *
     * @param turno
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }

    /**
     * Llamaremos a este método para cambiar de turno
     */
    public void cambioTurno() {
        if (getTurno() == 0) {
            turno = 1;
        } else {
            turno = 0;
        }
    }

    /**
     * Comprobar si se ha conseguido un tres en raya, en caso que se haya
     * conseguido devolverá 1, en caso contrario retorna 0 y continúa el juego
     *
     * @param matriz (Tablero de juego)
     * @return
     */
    public int comprobarJuego(int matriz[][]) {

        Juego juego = new Juego();

        // Comprobar si existe tres en raya
        // Comprobar horizontal
        if (matriz[2][0] == matriz[2][1] && matriz[2][1] == matriz[2][2]) {
            return 1;
        }
        if (matriz[1][0] == matriz[1][1] && matriz[1][1] == matriz[1][2]) {
            return 1;
        }
        if (matriz[0][0] == matriz[0][1] && matriz[0][1] == matriz[0][2]) {
            return 1;
        }

        // Comprobar vertical
        if (matriz[0][2] == matriz[1][2] && matriz[1][2] == matriz[2][2]) {
            return 1;
        }
        if (matriz[0][1] == matriz[1][1] && matriz[1][1] == matriz[2][1]) {
            return 1;
        }
        if (matriz[0][0] == matriz[1][0] && matriz[1][0] == matriz[2][0]) {
            return 1;

        }

        // Comprobar diagonal
        if (matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2]) {
            return 1;
        }
        if (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0]) {
            return 1;
        }

        // Si no hay tres en raya
        return 0;
    }

    /**
     * Deshabilitará el botón para evitar que se vuelva a posicionar una ficha
     * en ese hueco
     *
     * @param bt (Botón seleccionado)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     * @return
     */
    public int tiradaJugador(javax.swing.JButton bt, int x, int y,
            int matriz[][], javax.swing.JPanel jp, javax.swing.JLabel lX,
            javax.swing.JLabel lO) {
        // Insertar código aquí...

        // Deshabilitar el botón
        bt.setEnabled(false);
        // Insertar la ficha en el botón
        ponerFicha(matriz, x, y, bt);
        // Comprobar si se ha ganado la partida
        if (comprobarJuego(matriz) == 0) {
            /* Si no se ha ganado la partida, el juego continúa
            y llamamos al método cambiaTurno()
             */
            cambioTurno();
        } else {
            /* Si se gana la partida, el juego termina, 
            llamamos al método ganador() y se deshabilita el tablero.
             */
            ganador(lX, lO);
            habilitado = false;
            habilitarTablero(jp);

        }
        return 0;
    }

    /**
     * Actualizar la puntuación de cada jugador al ganar la partida. Al
     * finalizar el incremento de puntuación, es necesario cambiar de turno.
     *
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     */
    public void ganador(javax.swing.JLabel lX, javax.swing.JLabel lO) {
        // Insertar código aquí...

        // Se incrementa el contador del jugador ganador y se actualiza su jLabel.    
        if (getTurno() == 0) {
            pX++;
            lX.setText("" + pX);
        } else {
            pO++;
            lO.setText("" + pO);
        }
        /* Se llama al procedimiento cambioTurno() para 
        que empiece la siguiente partida el jugador que perdió. 
         */
        cambioTurno();
    }

    /**
     * Habilitará o deshabilitará el tablero dependiendo del estado de la
     * variable habilitado
     *
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void habilitarTablero(javax.swing.JPanel jp) {
        // Insertar código aquí...
        // Se deshabilitan todos los botones del JPanel jp.
        for (int i = 0; i < jp.getComponents().length; i++) {
            jp.getComponent(i).setEnabled(habilitado);

        }
    }

    /**
     * Insertaremos la ficha en la posición correspondiente de la matriz
     * Llamaremos al método pintarFicha
     *
     * @param matriz (Tablero de juego)
     * @param t (Turno)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param bt (Botón pulsado)
     */
    public void ponerFicha(int matriz[][], int x, int y, javax.swing.JButton bt) {
        // Insertar código aquí...        

        // Se inserta la ficha en la posición de la matriz y se pinta.
        matriz[x][y] = getTurno();
        pintarFicha(bt);

    }

    /**
     * Pintará la ficha en el tablero de juego visual, es decir, en el botón
     *
     * @param bt (Botón pulsado)
     */
    private void pintarFicha(javax.swing.JButton bt) {
        // Insertar código aquí...
        // Si el turno es de 0 pintará una X en rojo
        if (getTurno() == 0) {
            bt.setForeground(Color.red);
            bt.setText("X");

        } else {
            // Si el turno es de 1, pintará una O en azul 
            bt.setForeground(Color.blue);
            bt.setText("O");
        }

    }

    /**
     * Inicializa una nueva partida, reinicia la matriz (Tablero de juego) y
     * habilita el tablero
     *
     * ¡¡¡¡No es necesario modificar este método!!!!.
     *
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void iniciarPartida(int matriz[][], javax.swing.JPanel jp) {
        // Rellenamos la matriz por primera vez, evitando que se repitan los números
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                matriz[x][y] = (x + 10) * (y + 10);
            }
        }

        // Habilitar tablero
        habilitado = true;
        habilitarTablero(jp);
    }
}
