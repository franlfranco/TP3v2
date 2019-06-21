package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * Clase contenedora de ArrayList con los elementos disponibles
 * en partida (Incluye los iniciales y los que se desbloquean)
 *
 */
public class ListaDisponibles implements IArchivos {
	private ArrayList <Elemento> disponibles;
	
	/**
	 * Constructor de clase
	 */
	public ListaDisponibles () {
		disponibles=new ArrayList<Elemento>();
	}
	

	/**
	 * Agregar elemento a la lista de disponibles
	 * @param elemento
	 */
	public void agregar (Elemento elemento) {
		disponibles.add(elemento);
	}
	
	/**
	 * Quita elemento de la lista de disponibles
	 */
	public void eliminar(int index) {
		disponibles.remove(index);
	}
	
	/**
	 * Retornar elemento por index en el array
	 * @param index
	 * @return
	 */
	public Elemento getElemento (int index) {
		return disponibles.get(index);
	}
	
	/**
	 * Retorna la colección ArrayList para ser
	 * utilizada en caso de iteración
	 * @return
	 */
	public ArrayList<Elemento> getColeccion () {
		return disponibles;
	}
	
	/**
	 * Retorna true en caso de encontrar el elemento dentro del
	 * array a partir de un elemento
	 * @param elemento
	 * @return booleano true en caso de estar en el array
	 */
	public boolean isElemento (Elemento elemento) {
		for(Elemento e:disponibles) {
			if(e.getNombreElemento().equals(elemento.getNombreElemento()))
				return true;
		}
		return false;
	}

	/**
	 * Lee del archivo elementosIniciales.dat y lo agrega al ArrayList
	 */
	@Override
	public void leerDeArchivo() {
		FileInputStream archivoEntrada;
		ObjectInputStream objetoEntrada;
		try {
			archivoEntrada= new FileInputStream("./archivos/elementosIniciales.dat");
			objetoEntrada = new ObjectInputStream(archivoEntrada);
			ElementoInicial aux=(ElementoInicial)objetoEntrada.readObject();
			while(aux!=null) {
				disponibles.add(aux);
				aux=(ElementoInicial)objetoEntrada.readObject();
			}
			objetoEntrada.close();
			archivoEntrada.close();
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		} catch (ClassNotFoundException e) {

		}
	}

	/**
	 * Carga a partir del ArrayList al archivo elementosIniciales.dat
	 */
	@Override
	public void cargarArchivo() {
		FileOutputStream archivoSalida = null;
		ObjectOutputStream objetoSalida = null;
		try {
			archivoSalida = new FileOutputStream("./archivos/elementosIniciales.dat");
			objetoSalida = new ObjectOutputStream(archivoSalida);
			for(Elemento e : disponibles) { 
				objetoSalida.writeObject(e);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				objetoSalida.close();
				archivoSalida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
