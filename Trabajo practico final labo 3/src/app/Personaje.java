package app;

import java.util.ArrayList;

//primer cambio
/**
 * Clase que define un personaje como eslabón final de herencia
 *
 */
public final class Personaje extends ElementoCompuesto {

	transient private ListaPartidas mejoresPuntajes;
	private int cantRegistros;

	/**
	 * Constructor vacio, inicializa en "sin definir"
	 */
	public Personaje() {
		super();
		mejoresPuntajes = new ListaPartidas();
		cantRegistros = 0;
	}

	/**
	 * Constructor
	 * 
	 * @param codigo
	 * @param nombreElemento
	 * @param codigoAcceso
	 */
	public Personaje(String codigo, String nombreElemento, String codigoAcceso) {
		super(codigo, nombreElemento, codigoAcceso);
		mejoresPuntajes = new ListaPartidas(nombreElemento);
		cantRegistros = 0;
	}

	/**
	 * Constructor
	 * 
	 * @param codigo
	 * @param nombreElemento
	 * @param codigoAcceso
	 * @param mejoresPuntajes
	 */
	public Personaje(String codigo, String nombreElemento, String codigoAcceso, ListaPartidas mejoresPuntajes) {
		super(codigo, nombreElemento, codigoAcceso);
		this.mejoresPuntajes = mejoresPuntajes;
		setCantRegistros();
	}

	/**
	 * @return String con el nombre del Personaje
	 */
	@Override
	public String toString() {
		return getNombreElemento();
	}

	/**
	 * Agrega un nuevo puntaje a su registro de records
	 * 
	 * @param nuevo
	 */
	public void nuevoPuntaje(RegistroPartida nuevo) {
		mejoresPuntajes.agregarATop(nuevo);
		if (getCantRegistros() < 5)
			cantRegistros++;
	}

	/**
	 * Agrega al registro de records los que vienen por el archivo
	 * 
	 * @param registro
	 */
	public void cargarPuntajes(RegistroPartida registro) {
		mejoresPuntajes.agregarATop(registro);
	}

	/**
	 * 
	 * @return Cantidad de registros cargados
	 */
	public int getCantRegistros() {
		return cantRegistros;
	}

	/**
	 * 
	 * @return ArrayList<RegistroPartida> colección utilizada
	 */
	public ArrayList<RegistroPartida> getColeccion() {
		return mejoresPuntajes.getColeccion();
	}

	/**
	 * 
	 * @return retorna la ListaPartidas
	 */
	public ListaPartidas getListaPartidas() {
		return mejoresPuntajes;
	}

	/**
	 * Actualiza la cantidad de registros
	 */
	public void setCantRegistros() {
		this.cantRegistros = mejoresPuntajes.getSize();
	}

	/**
	 * Inicializa su lista de partidas
	 */
	public void inicializarlistaPartidas() {
		mejoresPuntajes = new ListaPartidas(getNombreElemento(), 5);
	}

}
