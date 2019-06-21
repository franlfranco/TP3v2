package app;

import javax.swing.JLabel;

public class Cronometro implements Runnable {
	private int minutos;
	private int segundos;
	private int milisegundos;
	private boolean play;
	private boolean activo;
	private JLabel visor;
	
	/**
	 * Constructor del cronometro sin parámetros
	 */
	public Cronometro() {
		super();
		this.minutos=0;
		this.segundos=0;
		this.milisegundos=0;
		play=false;
		activo=true;
		visor=null;
	}
	
	/**
	 * Constructor del cronometro con parametro JLabel
	 * permite compatibilidad con interfaz gráfica. Al utilizarlo se actualizara en pantalla.
	 * @param visor
	 */
	public Cronometro(JLabel visor) {
		super();
		this.minutos=0;
		this.segundos=0;
		this.milisegundos=0;
		play=false;
		activo=true;
		this.visor=visor;
	}

	/**
	 * Override de toString
	 * @return String formateado 00:00:00
	 */
	@Override
	public String toString() {
		String patron = "%02d : %02d : %02d";
		String timer = String.format(patron, getMinutos(),getSegundos(),getMilisegundos());
		return timer;
	}


	/**
	 * Cuenta el tiempo transcurrido siempre que activo == true y play == true
	 */
	@Override
	public void run() {
		while(activo) {
			try {
				if(play) {
					Thread.sleep(10);
					milisegundos++;
					if(milisegundos==100) {
						milisegundos=0;
						segundos++;
					}
					else if(segundos==60) {
						segundos=0;
						minutos++;
					}
					else if(visor!=null)
						visor.setText(toString());
				}else {
					wait();
				}
			}catch (Exception e) {
			}
		}
	}

	/**
	 * Pausa el cronometro (play==false)
	 */
	public void pause() {
		if(play) 
			play=false;
	}
	
	/**
	 * Reinicia el cronometro (valores = 0, play == false)
	 */
	public void reset() {
		this.minutos=0;
		this.segundos=0;
		this.milisegundos=0;
		play=false;
	}
	
	/**
	 * Termina el proceso.
	 * El cronometro puede seguir siendo consultado, pero no cambiara nunca su valor
	 */
	public void close() {
		activo=false;
	}
	
	/**
	 * Play al cronometro (Play == true)
	 */
	public void play() {
		if(!play) {
			play=true;
		}
	}

	/**
	 * 
	 * @return minutos
	 */
	public int getMinutos() {
		return minutos;
	}

	/**
	 * 
	 * @return segundos
	 */
	public int getSegundos() {
		return segundos;
	}

	/**
	 * 
	 * @return milisegundos
	 */
	public int getMilisegundos() {
		return milisegundos;
	}
	
	/**
	 * 
	 * @return estado de play
	 */
	public boolean getPlay() {
		return play;
	}
}
