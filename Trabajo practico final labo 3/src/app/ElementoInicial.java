package app;
/**
 * Clase de elementos iniciales.
 * Hija de Elementos
 * Utilizada para combinar y crear Elementos Compuestos y Personajes
 */
@SuppressWarnings("serial")
public class ElementoInicial extends Elemento {
	/**
	 * Constructor sin parametros
	 * Codigo y nombre de elemento = sin definir
	 */
	public ElementoInicial() {
		super();
	}
	
	/**
	 * Constructor con parametros
	 * @param codigo
	 * @param nombreElemento
	 */
	public ElementoInicial(String codigo,String nombreElemento) {
		super(codigo,nombreElemento);
	}
}
