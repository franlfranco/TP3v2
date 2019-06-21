package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Clase contenedora de HashMap de lista de elementos totales
 *
 */
public class ListaDeElementos implements IArchivos {
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
	 * Lee de los archivos elementosCompuestos.dat y personajes.dat para cargar a la lista
	 */
	@Override
	public void leerDeArchivo() {

		leerDeArchivoCompuestos();
		leerDeArchivoPersonajes();
		
	}

	public void leerDeArchivoPersonajes() {
		ObjectInputStream objetoEntrada;
		ObjectInputStream registroEntrada;
		FileInputStream archivoEntrada;
		try {
			archivoEntrada = new FileInputStream("./archivos/personajes.dat");
			objetoEntrada = new ObjectInputStream(archivoEntrada);
			registroEntrada = new ObjectInputStream(archivoEntrada);

			Personaje aux = (Personaje)objetoEntrada.readObject();
			aux.inicializarlistaPartidas();
			RegistroPartida aux2;
			while(aux!=null) {
				for (int i = 0;i<aux.getCantRegistros();i++) {
					aux2 = (RegistroPartida)registroEntrada.readObject();
					aux.cargarPuntajes(aux2);
				}
				listaElementos.put(aux.getCodigoAcceso(), aux);
				aux=(Personaje)objetoEntrada.readObject();
				if(aux!=null)
				aux.inicializarlistaPartidas();
			}
			objetoEntrada.close();
			archivoEntrada.close();
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		} catch (ClassNotFoundException e) {

		}
	}

	public void leerDeArchivoCompuestos() {
		ObjectInputStream objetoEntrada;
		FileInputStream archivoEntrada;
		try {
			archivoEntrada = new FileInputStream("./archivos/elementosCompuestos.dat");
			objetoEntrada = new ObjectInputStream(archivoEntrada);

			ElementoCompuesto aux = (ElementoCompuesto)objetoEntrada.readObject();
			while(aux!=null) {
				listaElementos.put(aux.getCodigoAcceso(), aux);
				aux=(ElementoCompuesto)objetoEntrada.readObject();
			}
			objetoEntrada.close();
			archivoEntrada.close();
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		} catch (ClassNotFoundException e) {

		}
	}
	
	/**
	 * Carga a los archivos elementosCompuestos.dat y personajes.dat a partir de la lista de elementos
	 */
	@Override
	public void cargarArchivo() {
		cargarArchivoCompuestos();
		cargarArchivoPersonajes();
	}
	
	
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
	}
	
	public void cargarArchivoPersonajes() {
		FileOutputStream archivoSalidaPersonajes = null;
		ObjectOutputStream objetoSalidaPersonajes = null;
		ObjectOutputStream objetoSalidaRegistros = null;
		
		try {
			archivoSalidaPersonajes = new FileOutputStream("./archivos/personajes.dat");
			objetoSalidaPersonajes = new ObjectOutputStream(archivoSalidaPersonajes);
			objetoSalidaRegistros = new ObjectOutputStream(archivoSalidaPersonajes);
			Personaje aux = null;
			for(String key : listaElementos.keySet()){ //Fuente StackOverFlow (Iterar en map a traves del key
				if(listaElementos.get(key) instanceof Personaje) {
					aux=(Personaje)listaElementos.get(key);
					objetoSalidaPersonajes.writeObject(aux);
					System.out.println("Graba un pj");
					if(aux.getCantRegistros()>0)
					for(RegistroPartida r : aux.getColeccion()) {
						objetoSalidaRegistros.writeObject(r);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO exception personaje");
		}finally {
			try {
				objetoSalidaPersonajes.close();
				archivoSalidaPersonajes.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public HashMap<String,Elemento> getColeccion(){
		return listaElementos;
	}
	
	public int getCantidadElementos() {
		return listaElementos.size();
	}
	
}
