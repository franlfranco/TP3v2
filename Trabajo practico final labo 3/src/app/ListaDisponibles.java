package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Clase contenedora de ArrayList con los elementos disponibles
 * en partida (Incluye los iniciales y los que se desbloquean)
 *
 */
public class ListaDisponibles extends Lista {
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
		if(index<disponibles.size())
			disponibles.remove(index);
	}
	
	/**
	 * Retornar elemento por index en el array
	 * @param index
	 * @return
	 */
	public Elemento getElemento (int index) {
		if(index<disponibles.size())
			return disponibles.get(index);
		else
			return null;
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
	 * Implementacion de getIterador
	 * @return iterador necesario para recorrer
	 */
	@Override
	public Iterator getIterador() {
		return disponibles.iterator();
	}
	
}
