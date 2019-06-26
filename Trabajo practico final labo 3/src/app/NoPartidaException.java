package app;

/**
 * Excepción utilizada para declarar que no
 * se inicializó una partida
 *
 */
public class NoPartidaException extends Exception {
	String mensaje;
	
	public NoPartidaException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	@Override
	public String getMessage() {
		return mensaje;
	}
}
