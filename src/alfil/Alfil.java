package alfil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Alfil {

	private int dimension;
	private int[][] tablero;
	private int filInicial;
	private int colInicial;
	private int filDestino;
	private int colDestino;
	private String posible = "NO";
	private int cantidadMovimientos = 0;

	public Alfil(String path) {
		try {
			leerArchivo(path);
		} catch (Exception e) {
			System.out.println("Error abrir archivo.");
			e.printStackTrace();
		}
	}
	
	public void calcula(){
		
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
