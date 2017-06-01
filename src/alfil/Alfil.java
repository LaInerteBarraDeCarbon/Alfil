package alfil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.table.TableStringConverter;

public class Alfil {

	private int dimension;
	private int[][] tablero;
	private int filInicial;
	private int colInicial;
	private int filDestino;
	private int colDestino;
	private String posible = "SI";
	private int cantidadMovimientos = 0;
	private int movimMenor = 100000;
	private String orientacion;
	private String orientacionAnt = "NN";

	public Alfil(String path) {
		try {
			leerArchivo(path);
		} catch (Exception e) {
			System.out.println("Error abrir archivo.");
			e.printStackTrace();
		}
	}

	public void calcula() {
		// primero pregunto si la posición inicial y la final están vinculadas
		// según lógica del movimiento del alfil
		if (((this.filInicial + this.colInicial) % 2 == 0) && ((this.filDestino + this.colDestino) % 2 == 0)
				|| ((this.filInicial + this.colInicial) % 2 != 0) && ((this.filDestino + this.colDestino) % 2 != 0)) {
			if (mover(this.filInicial - 1, this.colInicial - 1) == 0) {
				this.posible = "NO";
				this.movimMenor = 0;
			}
		}
	}

	public int mover(int fila, int columna) {
		int suma = 0;
		if (fila + 1 == filDestino && columna + 1 == colDestino) {
			if (this.cantidadMovimientos < this.movimMenor)
				this.movimMenor = this.cantidadMovimientos;
			return 1;
		}

		if (fila <= this.dimension && columna <= this.dimension && !this.orientacionAnt.equals("SE")
				&& this.tablero[fila + 1][columna + 1] == 0) {
			this.orientacion = "SE";
			if (!this.orientacion.equals(this.orientacionAnt)) {
				this.cantidadMovimientos++;
				this.orientacionAnt = this.orientacion;
			}
			suma += mover(fila, columna);
		}

		if (fila <= this.dimension && columna >= this.dimension && !this.orientacionAnt.equals("SO")
				&& this.tablero[fila + 1][columna - 1] == 0) {
			this.orientacion = "SO";
			if (!this.orientacion.equals(this.orientacionAnt)) {
				this.cantidadMovimientos++;
				this.orientacionAnt = this.orientacion;
			}
			suma += mover(fila, columna);
		}

		if (fila >= this.dimension && columna >= this.dimension && !this.orientacionAnt.equals("NO")
				&& this.tablero[fila - 1][columna - 1] == 0) {
			this.orientacion = "NO";
			if (!this.orientacion.equals(this.orientacionAnt)) {
				this.cantidadMovimientos++;
				this.orientacionAnt = this.orientacion;
			}
			suma += mover(fila, columna);
		}

		if (fila >= this.dimension && columna <= this.dimension && !this.orientacionAnt.equals("NE")
				&& this.tablero[fila - 1][columna + 1] == 0) {
			this.orientacion = "NE";
			if (!this.orientacion.equals(this.orientacionAnt)) {
				this.cantidadMovimientos++;
				this.orientacionAnt = this.orientacion;
			}
			suma += mover(fila, columna);
		}

		return suma;
	}

	public void leerArchivo(String path) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
			this.dimension = sc.nextInt();
			this.tablero = new int[this.dimension][this.dimension];
			for (int j = 0; j < this.dimension; j++)
				for (int i = 0; i < this.dimension; i++)
					this.tablero[i][j] = sc.nextInt();

			this.filInicial = sc.nextInt();
			this.colInicial = sc.nextInt();
			this.filDestino = sc.nextInt();
			this.colDestino = sc.nextInt();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		sc.close();
	}

	public void escribirTablero() {
		for (int j = 0; j < this.dimension; j++) {
			for (int i = 0; i < this.dimension; i++)
				System.out.print(this.tablero[i][j] + " ");
			System.out.print("\n");
		}
	}

	public void grabarArchivo(String path) {
		PrintWriter salida;
		try {
			salida = new PrintWriter(new FileWriter(path));
			salida.println(this.posible);
			salida.println(this.cantidadMovimientos);

			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
