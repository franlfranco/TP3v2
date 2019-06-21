package app;

/**
 *  Clase contenedora y crontroladora de Cronometro
 */

public class HiloCronometro
{
	Cronometro cronometro;
	Thread thread=null;
	
	/**
	 * Toma el cronometro por parametro y inicializa su thread.
	 * El cronometro inicia pausado.
	 * @param cronometro
	 */
	public HiloCronometro(Cronometro cronometro) { ///Constructor inicializa con un cronometro (new Cronometro();)
		this.cronometro = cronometro;
		thread = new Thread(cronometro);
		thread.start();
	}

	/**
	 * Play al cronometro
	 */
	public void play() {
		if(!cronometro.getPlay()) {
			cronometro.play();
		}
	}

	/**
	 * Pausa al cronometro
	 */
	public void pause() {
		if(cronometro.getPlay() && thread!=null) {
			cronometro.pause();
		}
			
	}
	
	/**
	 * Reinicia al cronometro (Todos los valores a 0 y pausado)
	 */
	public void reset() {
		if(thread != null)
			cronometro.reset();
	}
	
	/**
	 * Pausa y finaliza el proceso del cronometro.
	 * Se puede obtener sus valores pero no modificarlo.
	 */
	public void close() { ///Pausa y finaliza el proceso hilo (No se puede reanudar el play, solo obtener el tiempo
		if(thread != null)
			cronometro.close();
	}

	/**
	 * 
	 * @return Minutos
	 */
	public int getMinutos() {
		return cronometro.getMinutos();
	}

	/**
	 * 
	 * @return Segundos
	 */
	public int getSegundos() {
		return cronometro.getSegundos();
	}

	/**
	 * 
	 * @return Milisegundos
	 */
	public int getMilisegundos() {
		return cronometro.getMilisegundos();
	}
	
	/**
	 * 
	 * @return Estado del cronometro
	 */
	public boolean getPlay() {
		return cronometro.getPlay();
	}
	
	/**
	 * 
	 * @return String en formato 00:00:00
	 */
	public String getTimer() { ///Retorna string formato: 00:00:00
		return cronometro.toString();
	}
	
	/**
	 * 
	 * @return Valor entero de milisegundos de juego, utilizado para acomodar en top.
	 */
	public int getPuntajeMilisegundos() {
		return getMinutos()*60*1000+getSegundos()*1000+getMilisegundos();
	}
}
