package app;

import java.io.Serializable;
/**
 * Clase abstracta de Elemento
 *
 */
@SuppressWarnings("serial")
public abstract class Elemento implements Serializable {
	private String codigo;
	private String nombreElemento;
	
	/**
	 * Constructor de Elemento.
	 * codigo = "sin definir"
	 * nombreElemento = "sin definir"
	 */
	public Elemento() {
		super();
		codigo="sin definir";
		setNombreElemento("sin definir");
	}
	
	/**
	 * Constructor de Elemento
	 * @param codigo
	 * @param nombreElemento
	 */
	public Elemento(String codigo,String nombreElemento) {
		super();
		this.codigo=codigo;
		this.nombreElemento=nombreElemento;
	}

	/**
	 * 
	 * @return Codigo del elemento
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * 
	 * @return Nombre del elemento
	 */
	public String getNombreElemento() {
		return nombreElemento;
	}

	/**
	 * Define nombre del elemento
	 * @param nombreElemento
	 */
	public void setNombreElemento(String nombreElemento) {
		this.nombreElemento = nombreElemento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elemento other = (Elemento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
}
