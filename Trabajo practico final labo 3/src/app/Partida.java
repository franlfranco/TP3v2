package app;

import java.util.ArrayList;

/**
 * Clase que define una partida
 *
 */
public class Partida {
	private Personaje personaje;
	private String nombreJugador;
	private int puntajeJugador;
	private ListaDisponibles listaDisponibles;
	private HiloCronometro cronometro;
	
	/**
	 * Constructor sin parametros, inicializa strings y personajes en "sin definir"
	 * y puntaje en 0
	 */
	public Partida() {
		super();
		personaje = new Personaje("Sin definir","Sin definir","Sin definir");
		setNombreJugador("Sin definir");
		this.puntajeJugador=0;
		listaDisponibles = new ListaDisponibles();
		listaDisponibles.leerDeArchivo();
		cronometro = new HiloCronometro(new Cronometro());
		
	}
	
	/**
	 * Inicializa la partida con el personaje objetivo y el nombre del jugador
	 * @param personaje
	 * @param nombreJugador
	 */
	public Partida(Personaje personaje,String nombreJugador) {
		super();
		this.personaje = personaje;
		setNombreJugador(nombreJugador);
		this.puntajeJugador=0;
		cronometro = new HiloCronometro(new Cronometro());
		listaDisponibles = new ListaDisponibles ();
		listaDisponibles.leerDeArchivo();
	}

	/**
	 * Inicializa la partida con el personaje objetivo, nombre del jugador y referencia
	 * a un cronometro que es controlado desde afuera de la clase
	 * @param personaje
	 * @param nombreJugador
	 * @param hiloCronometro
	 */
	public Partida(Personaje personaje,String nombreJugador,HiloCronometro hiloCronometro) {
		super();
		this.personaje = personaje;
		setNombreJugador(nombreJugador);
		this.puntajeJugador=0;
		cronometro = hiloCronometro;
		listaDisponibles = new ListaDisponibles ();
		listaDisponibles.leerDeArchivo();
	}
	/**
	 * Retorna el nombre del jugador
	 * @return String nombreJugador
	 */
	public String getNombreJugador() {
		return nombreJugador;
	}

	/**
	 * Define el nombre del jugador
	 * @param nombreJugador
	 */
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	/**
	 * Retorna el puntaje del jugador
	 * @return puntaje del jugador
	 */
	public int getPuntajeJugador() {
		return puntajeJugador;
	}

	/**
	 * Define el puntaje del jugador
	 * @param puntajeJugador
	 */
	public void setPuntajeJugadorPorCronometro() {
		this.puntajeJugador = cronometro.getPuntajeMilisegundos();
	}
	
	/**
	 * Retorna la coleccion ArrayList de la lista de disponibles
	 * @return ArrayList<Elemento>
	 */
	public ArrayList<Elemento> getColeccionDisponibles() {
		return listaDisponibles.getColeccion();
	}
	
	public ListaDisponibles getListaDisponibles() {
		return listaDisponibles;
	}
	
	/**
	 * Retorna elemento a partir del index de la lista de disponibles.
	 * @param index
	 * @return
	 */
	public Elemento getElementoDeDisponibles (int index) {
		return listaDisponibles.getElemento(index);
	}
	
	/**
	 * Agrega a elementos disponibles.
	 * @param elemento
	 */
	public void agregarElementoDisponible(Elemento elemento) {
		listaDisponibles.agregar(elemento);
	}
	
	/**
	 * Retorna true en caso de ya existir el elemento como disponible
	 * @param elemento
	 * @return boolean
	 */
	public boolean elementoYaDisponible(Elemento elemento) {
		return listaDisponibles.isElemento(elemento);
	}
	
	/**
	 * Carga en la lista de disponibles, los elementos iniciales definidos
	 * en archivos.
	 * @param listaDisponibles
	 */
	
	public String getNombrePersonajeObjetivo() {
		return personaje.getNombreElemento();
	}
	
	public Personaje getPersonaje() {
		return personaje;
	}
	
	public HiloCronometro getCronometro() {
		return cronometro;
	}
	
	public String getTimer() {
		return cronometro.getTimer();
	}
	
	public void setCronometro(HiloCronometro cronometro) {
		this.cronometro = cronometro;
	}
}
