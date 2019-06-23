/**
 * 
 */
package app;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author francisco
 *
 */
public class ManejadorDeArchivos <T,E>{
	
	private String ruta;
	private T lista;
	
	public ManejadorDeArchivos(String ruta, T lista) {
		this.ruta = ruta;
		this.lista = lista;
	}
	
	public void cargarArchivo() {
		FileOutputStream fos = null;
		ObjectOutputStream oos= null;
		
		fos = new FileOutputStream(getRuta());
		oos = new ObjectOutputStream(fos);
		E elemento = null;
		Iterator it = 
		
		while(lista.)
		
	}
	
	public String getRuta() {
		return ruta;
	}
	
	/*
	public void cargarArchivoCompuestos() {
		FileOutputStream archivoSalidaCompuestos = null;
		ObjectOutputStream objetoSalidaCompuestos = null;
		
		try {
			archivoSalidaCompuestos = new FileOutputStream("./archivos/elementosCompuestos.dat");
			objetoSalidaCompuestos = new ObjectOutputStream(archivoSalidaCompuestos);
			ElementoCompuesto aux = null;
			
			for(String key : listaElementos.keySet()){ //Fuente StackOverFlow (Iterar en map a traves del key
				if(!(listaElementos.get(key) instanceof Personaje)) {
					aux=(ElementoCompuesto)listaElementos.get(key);
					objetoSalidaCompuestos.writeObject(aux);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO exception");
		}finally {
			try {
				objetoSalidaCompuestos.close();
				archivoSalidaCompuestos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
}
