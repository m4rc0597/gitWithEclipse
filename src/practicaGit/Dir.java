package practicaGit;

import java.io.File;
import java.text.SimpleDateFormat;

public class Dir {

	private String directorio;
	private File carpeta;
	private File[] archivos;
	/**
	 * constructor de la clase por defecto, asigna como ruta el directorio actual
	 */
	public Dir() {
		carpeta = new File(".");
		directorio = ".";
	}
	/**
	 * constructor de la clase
	 * @param directorio ruta de la que se mostraran los archivos o directorios que contiene
	 */
	public Dir(String directorio) {
		this.directorio = directorio;
		if (longitudDirectorio()) {
			carpeta = new File(this.directorio.replace('/', File.separatorChar));
			if (!carpeta.exists()) {
				System.out.println("Ruta no valida, se asigna la del directorio actual.");
				System.out.println("");
				carpeta = new File(".");
				this.directorio = ".";
			}
		}else {
			carpeta = new File(".");
			this.directorio = ".";
		}
	}
	/**
	 * funcion que comprueba que la longitud de la ruta facilitada sea mayor que 0
	 * @return true, si longitud>0, y false para todos los demas casos
	 */
	public boolean longitudDirectorio() {
		if (directorio.length()>0) return true;
		else return false;
	}
	/**
	 * genera los objetos de tipo File asignandolos a los archivos o directorios que se 
	 * encuentran dentro del directorio facilitado
	 */
	public void generarArchivos() {
		archivos = new File[carpeta.list().length];
		for (int i = 0; i < archivos.length; i++) {
			File temp = new File (carpeta, carpeta.list()[i]);  
			archivos[i] = temp;
		}
	}
	/**
	 * genera un string que contiene un numero de espacios indicado
	 * @param mayorEspacios indica el numero de espacios que genera la funcion
	 * @return String con el numero de espacios indicado
	 */
	public String generarEspacios(int mayorEspacios) {
		String espacios = "";
		for (int i = 0; i < mayorEspacios; i++) {
			espacios = espacios + " ";
		}
		return espacios;
	}
	/**
	 * comprueba cual de los nombres de los archivos o directorios que se encuentran en la ruta indicada es el que 
	 * tiene un mayor numero de caracteres
	 * @return string que contiene un numero de espacios igual al numero de caracteres del nombre mas largo 
	 * del directorio
	 */
	public String comprobarNumeroEspacios() {
		int mayorEspacios = 0;
		
		for (int i = 0; i < archivos.length; i++) {
			if ( (Long.toString(archivos[i].length())).length() > mayorEspacios) {
				mayorEspacios = (Long.toString(archivos[i].length())).length();
			}
		}
		return generarEspacios(mayorEspacios);
	}
	/**
	 * muestra el contenido del directorio indicado ordenado por columnas y con sus diferentes caracteristicas
	 */
	public void mostrar() {
		int numArchivos = 0;
		int numDirs = 0;
		long totalBytesArchvivos = 0;
		long totalBytesDirs = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		System.out.println("\t== Directorio de "+carpeta.getAbsolutePath()+" ==");
		for (int i = 0; i < archivos.length; i++) {
			if (archivos[i].isDirectory()) {
				System.out.println(simpleDateFormat.format(archivos[i].lastModified()) + "\t<DIR>\t"+comprobarNumeroEspacios()+" "
						+ archivos[i].getName());
				numDirs++;
				totalBytesDirs += archivos[i].length();
			}else {
				int espacios = comprobarNumeroEspacios().length() - (Long.toString(archivos[i].length())).length();
				System.out.println(simpleDateFormat.format(archivos[i].lastModified()) + "\t     \t"+archivos[i].length()+generarEspacios(espacios)+" "
						+ archivos[i].getName());
				numArchivos++;
				totalBytesArchvivos += archivos[i].length();
			}
		}
		System.out.println("\t"+numArchivos+" archivos\t"+totalBytesArchvivos+" bytes");
		System.out.println("\t"+numDirs+" dirs \t"+totalBytesDirs+" bytes");
		for (int i = 0; i<5; i++) {
			System.out.println("");
		}
	}
}
