package alfil;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlfilTest {

	private static String archivoIn = "Preparacion de Prueba/Lote de Prueba/Entrada/";
	private static String archivoOut = "Ejecucion de Prueba/Salida Obtenida/";

	@Test
	public void test() {
		Alfil alfil = new Alfil(archivoIn + "00_Enunciado.in");
		alfil.escribirTablero();
		// alfil.calcula();
		// ladrillos.grabarArchivo(archivoOut + "00_Enunciado.out");
	}

}
