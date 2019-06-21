package app;

import java.io.Serializable;

/**
 * Clase Elemento Compuesto (Todos los formados por iniciales y compuestos)
 * Hija de Elemento
 * Padre de Clase Personajes
 */
@SuppressWarnings("serial")
public class ElementoCompuesto extends Elemento implements Serializable {
	private String codigoAcceso;
	
	/**
	 * Constructor sin parametros
	 * codigoAcceso = sin definir
	 */
	public ElementoCompuesto() {
		super();
		codigoAcceso="sin definir";
	}
	
	/**
	 * Constructor con parametros
	 * @param codigo
	 * @param nombreElemento
	 * @param codigoAcceso
	 */
	public ElementoCompuesto(String codigo,String nombreElemento,String codigoAcceso) {
		super(codigo,nombreElemento);
		this.codigoAcceso=codigoAcceso;
	}

	/**
	 * 
	 * @return Codigo de Acceso
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}
	
	
}
