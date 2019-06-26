package app;

/**
 * Excepci�n utilizada para declarar que no
 * se inicializ� una partida
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
