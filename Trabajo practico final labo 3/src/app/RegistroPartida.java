package app;

import java.io.Serializable;

/**
 * Clase que guarda la información de una partida
 * finalizada. No se pueden cambiar los datos
 * una vez inicializado el registro (Solo métodos get).
 *
 */
public class RegistroPartida implements Serializable {
	private String nombreJugador;
	private String personajeObjetivo;
	private String timer;
	private int puntaje;

	/**
	 * Constructor vacio, inicializa en "sin definir" y 0
	 */
	public RegistroPartida() {
		nombreJugador = "sin definir";
		personajeObjetivo = "sin definir";
		timer = "00 : 00 : 00";
		puntaje = 0;
	}
	
	/**
	 * Constructor de partida
	 * @param nombreJugador
	 * @param personajeObjetivo
	 * @param timer
	 * @param puntaje
	 */
	public RegistroPartida(String nombreJugador,String personajeObjetivo,String timer,int puntaje) {
		this.nombreJugador = nombreJugador;
		this.personajeObjetivo = personajeObjetivo;
		this.timer = timer;
		this.puntaje = puntaje;
	}

	/**
	 * 
	 * @return Nombre del jugador
	 */
	public String getNombreJugador() {
		return nombreJugador;
	}

	/**
	 * 
	 * @return personajeObjetivo
	 */
	public String getPersonajeObjetivo() {
		return personajeObjetivo;
	}

	/**
	 * 
	 * @return Timer en formato 00:00:00
	 */
	public String getTimer() {
		return timer;
	}

	/**
	 * 
	 * @return puntaje
	 */
	public int getPuntaje() {
		return puntaje;
	}
	
	public int compareTo(RegistroPartida b) {
		if(getPuntaje()>b.getPuntaje())
			return -1;
		else if(getPuntaje()<b.getPuntaje())
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return ""+getNombreJugador()+" - "+getTimer();
	}
	

	
}
