package practicaGit;

import java.util.Scanner;

public class AppDir {

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente
		//dir sin especificar ruta: en la carpeta del proyecto:
				Dir dirSinRuta = new Dir();
				dirSinRuta.generarArchivos();
				dirSinRuta.mostrar();
				
				//dir especificando una ruta valida:
				Dir dirRutaValida = new Dir("C:/Users/tarde/Desktop");
				dirRutaValida.generarArchivos();
				dirRutaValida.mostrar();
				
				//dir con ruta no valida:
				Dir dirRutaNoValida = new Dir("C:/Users/rde/Desktop");
				dirRutaNoValida.generarArchivos();
				dirRutaNoValida.mostrar();
				
				Scanner sc = new Scanner(System.in);
				System.out.println("Introduce la ruta");
				Dir dirRutaPedida = new Dir(sc.nextLine());
				dirRutaPedida.generarArchivos();
				dirRutaPedida.mostrar();

				sc.close();
	}

}
