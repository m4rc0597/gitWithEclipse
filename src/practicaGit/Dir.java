package practicaGit;

import java.io.File;
import java.text.SimpleDateFormat;

public class Dir {

	private String directorio;
	private File carpeta;
	private File[] archivos;
	
	public Dir() {
		carpeta = new File(".");
		directorio = ".";
	}
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
	
	public boolean longitudDirectorio() {
		if (directorio.length()>0) return true;
		else return false;
	}
	
	public void generarArchivos() {
		archivos = new File[carpeta.list().length];
		for (int i = 0; i < archivos.length; i++) {
			File temp = new File (carpeta, carpeta.list()[i]);  
			archivos[i] = temp;
		}
	}
	
	public String generarEspacios(int mayorEspacios) {
		String espacios = "";
		for (int i = 0; i < mayorEspacios; i++) {
			espacios = espacios + " ";
		}
		return espacios;
	}
	
	public String comprobarNumeroEspacios() {
		int mayorEspacios = 0;
		
		for (int i = 0; i < archivos.length; i++) {
			if ( (Long.toString(archivos[i].length())).length() > mayorEspacios) {
				mayorEspacios = (Long.toString(archivos[i].length())).length();
			}
		}
		return generarEspacios(mayorEspacios);
	}
	
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
