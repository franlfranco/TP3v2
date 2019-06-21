package app;

import java.util.ArrayList;

//primer cambio
/**
 * Clase que define un personaje como eslabón
 * final de herencia
 *
 */
@SuppressWarnings("serial")
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
	 * @param codigo
	 * @param nombreElemento
	 * @param codigoAcceso
	 */
	public Personaje(String codigo,String nombreElemento,String codigoAcceso) {
		super(codigo,nombreElemento,codigoAcceso);
		mejoresPuntajes = new ListaPartidas(nombreElemento);
		cantRegistros = 0;
	}

	public Personaje(String codigo,String nombreElemento,String codigoAcceso,ListaPartidas mejoresPuntajes) {
		super(codigo,nombreElemento,codigoAcceso);
		this.mejoresPuntajes = mejoresPuntajes;
		setCantRegistros();
	}
	@Override
	public String toString() {
		return getNombreElemento();
	}
	
	public void nuevoPuntaje(RegistroPartida nuevo) {
		mejoresPuntajes.agregarATop(nuevo);
		cantRegistros++;
	}

	public void cargarPuntajes(RegistroPartida registro) {
		mejoresPuntajes.agregarATop(registro);
	}
	
	public int getCantRegistros() {
		return cantRegistros;
	}

	public ArrayList<RegistroPartida> getColeccion(){
		return mejoresPuntajes.getColeccion();
	}
	
	public ListaPartidas getListaPartidas() {
		return mejoresPuntajes;
	}
	
	public void setCantRegistros() {
		this.cantRegistros = mejoresPuntajes.getSize();
	}
	
	public void inicializarlistaPartidas() {
		mejoresPuntajes = new ListaPartidas(getNombreElemento(),5);
	}
	
}
