package app;

import java.util.Iterator;

/**
 * Clase abstracta. Toda lista del juego hereda de Lista.
 *
 */
public abstract class Lista {

	/**
	 * Retorna el iterador necesario para la lista que use.
	 * @return
	 */
	public Iterator getIterador() {
		return null;
	}

}
