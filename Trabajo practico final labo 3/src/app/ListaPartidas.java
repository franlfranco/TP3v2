package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * Clase utilizada para el registro de las partidas terminadas
 * Organizadas a partir del puntaje y en base al personaje objetivo
 */
public class ListaPartidas implements IArchivos{
	private String personajeObjetivo;
	private ArrayList <RegistroPartida> listaPartidas;
	private int maxTop;

	/**
	 * Constructor vacio. Inicializa en "sin definir"
	 * Top de 0
	 */
	public ListaPartidas() {
		this.personajeObjetivo = "sin definir";
		listaPartidas = new ArrayList<RegistroPartida>();
		maxTop = 5;
	}
	
	/**
	 * Constructor que inicializa con el personaje objetivo de referencia
	 * Top de 5
	 */
	
	public ListaPartidas(String personajeObjetivo) {
		this.personajeObjetivo=personajeObjetivo;
		listaPartidas = new ArrayList<RegistroPartida>();
		maxTop = 5;
	}
	
	/**
	 * Constructor que inicializa con el personaje objetivo de referencia
	 * Top por parametro
	 */
	
	public ListaPartidas(String personajeObjetivo,int maxTop) {
		this.personajeObjetivo=personajeObjetivo;
		listaPartidas = new ArrayList<RegistroPartida>();
		this.maxTop = maxTop;
	}
	
	/**
	 * Constructor que inicializa con el personaje objetivo
	 * y con el Arraylist precargado
	 * Top de 5
	 */
	public ListaPartidas (String personajeObjetivo,ArrayList<RegistroPartida> listaPartidas) {
		this.personajeObjetivo = personajeObjetivo;
		this.listaPartidas = listaPartidas;
		maxTop = 5;
	}
	
	/**
	 * Constructor que inicializa con el personaje objetivo
	 * y con el Arraylist precargado
	 * Top pasado por parametro
	 */
	public ListaPartidas (String personajeObjetivo,ArrayList<RegistroPartida> listaPartidas,int maxTop) {
		this.personajeObjetivo = personajeObjetivo;
		this.listaPartidas = listaPartidas;
		this.maxTop = maxTop;
	}
	
	/**
	 * Agregar en Top.
	 * - Si no hay nada y el maxTop lo permite, lo agrega
	 * - Busca su posicion y lo agrega
	 * - En caso de agregarlo, se elimina el puesto sobrante del top
	 */
	
	public void agregarATop(RegistroPartida registroNuevo) {
		int pos = 0;
		boolean ubicado = false;
		if(listaPartidas.size()==0 && maxTop>0) {
			listaPartidas.add(registroNuevo);
			System.out.println("Se agrego registro a mejoresPartidas");
		}else if(maxTop>0){
			while(pos<listaPartidas.size() && pos<maxTop && !ubicado) {
				if(registroNuevo.compareTo(listaPartidas.get(pos))==-1) {
					pos++;
				}else {
					ubicado=true;
				}
			}
				listaPartidas.add(pos,registroNuevo);
		}
		if(listaPartidas.size()>maxTop) {
			listaPartidas.remove(maxTop);
		}
	}
	
	public void testMostrarTop() {
		for(int i = 0;i<listaPartidas.size();i++)
			System.out.println(""+(i+1)+" - "+listaPartidas.get(i).toString());
	}
	
	/**
	 * Devuelve la informacion en string del top ingresado
	 * @param posicion
	 * @return formato: 1 - 00:00:00 - NombreJugador
	 */
	public String getTop(int posicion) {
		if(posicion == 0) throw new IllegalArgumentException("Posicion minima 1");
		else if(posicion >listaPartidas.size()) throw new IllegalArgumentException("La posicion no entra en la cantidad de registros exitentes");
		return ""+posicion+" - "+listaPartidas.get(posicion-1).getTimer()+" - "+listaPartidas.get(posicion-1).getNombreJugador();
	}
	
	public int getSize() {
		return listaPartidas.size();
	}

	public String getPersonajeObjetivo() {
		return personajeObjetivo;
	}
	/**
	 * toString : muestra el personaje objetivo de la lista. (Utilizado para comboBox)
	 */
	@Override
	public String toString() {
		return getPersonajeObjetivo();
	}

	@Override
	public void leerDeArchivo() {
		FileInputStream archivoEntrada;
		ObjectInputStream objetoEntrada;
		try {
			archivoEntrada= new FileInputStream(".\\archivos\\top"+maxTop+getPersonajeObjetivo()+".dat");
			objetoEntrada = new ObjectInputStream(archivoEntrada);
			RegistroPartida aux=(RegistroPartida)objetoEntrada.readObject();
			while(aux!=null) {
				listaPartidas.add(aux);
				aux=(RegistroPartida)objetoEntrada.readObject();
			}
			objetoEntrada.close();
			archivoEntrada.close();
		}catch(FileNotFoundException e) {
			cargarArchivo(); //Si el archivo no existe, crea uno vacio o con la data actual
		}catch(IOException e) {
			
		} catch (ClassNotFoundException e) {

		}
		
	}

	@Override
	public void cargarArchivo() {
		FileOutputStream archivoSalida = null;
		ObjectOutputStream objetoSalida = null;
		try {
			archivoSalida = new FileOutputStream(".\\archivos\\top"+maxTop+getPersonajeObjetivo()+".dat");
			objetoSalida = new ObjectOutputStream(archivoSalida);
			for(RegistroPartida registro : listaPartidas) { //Fuente stackoverflow
				objetoSalida.writeObject(registro);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ponele a");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				objetoSalida.close();
				archivoSalida.close();
			} catch (IOException e) {
			}
		}
		
	}
	
	public ArrayList<RegistroPartida> getColeccion(){
		return listaPartidas;
	}
	
}

