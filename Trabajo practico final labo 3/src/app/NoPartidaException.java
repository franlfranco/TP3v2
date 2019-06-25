package app;

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
