package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Clase contenedora de HashMap de lista de elementos totales
 *
 */
public class ListaDeElementos extends Lista {
	HashMap <String,Elemento>listaElementos;
	
	/**
	 * Constructor de ListaDeElementos
	 */
	public ListaDeElementos() {
		super();
		listaElementos = new HashMap <String,Elemento>();
	}

	/**
	 * Agrega un elemento al HashMap
	 * @param codigoAcceso
	 * @param elemento
	 */
	public void agregarElemento (String codigoAcceso, Elemento elemento) {
		listaElementos.put(codigoAcceso, elemento);
	}

	/**
	 * Elimina un elemento del HashMap
	 * @param codigoAcceso
	 */
	public void eliminarElemento(String codigoAcceso) {
		listaElementos.remove(codigoAcceso);
	}
	
	/**
	 * A través del código de acceso, retorna un Elemento
	 * @param codigoAcceso
	 * @return Elemento o null si no lo encuentra
	 */
	public Elemento getElemento(String codigoAcceso) {
		return listaElementos.get(codigoAcceso);
	}
	
	/**
	 * Retorna true si el elemento (por codigo de acceso) existe en la colección
	 * @param codigoAcceso
	 * @return
	 */
	public boolean isElemento (String codigoAcceso) {
		if(listaElementos.containsKey(codigoAcceso))
			return true;
		else
			return false;
	}
	
	/**
	 * Retorna true si el elemento (por Objeto Elemento) existe en la colección
	 * @param elemento
	 * @return
	 */
	public boolean isElemento(Elemento elemento) {
		if(listaElementos.containsValue(elemento))
			return true;
		else return false;
	}

	/**
	 * 
	 * @return Hashmap para uso de iteración
	 */
	public HashMap<String,Elemento> getColeccion(){
		return listaElementos;
	}
	
	/**
	 * 
	 * @return cantidad de elementos cargados en la lista
	 */
	public int getCantidadElementos() {
		return listaElementos.size();
	}
	
	/**
	 * Implementacion de getIterador
	 * @return iterador necesario para recorrer
	 */
	@Override
	public Iterator getIterador() {
		return listaElementos.entrySet().iterator();
	}
	
}
