package app;

/**
 * Clase utilizada para la combinación de elementos
 *
 */
public class Mezcladora {
	private ListaDeElementos listaDeElementos;
	
	/**
	 * Constructor que necesita de una listaDeElementos ya cargada
	 * para referenciarla en su lista interna
	 * @param listaDeElementos
	 */
	public Mezcladora (ListaDeElementos listaDeElementos) {
		super();
		this.listaDeElementos = listaDeElementos;
	}
	
	/**
	 * Ordena los codigos de los elementos ingresados alfabeticamente
	 * que luego es utilizado para buscar si existe la combinación
	 * @param a
	 * @param b
	 * @return String a+b o String b+a
	 */
	public String ordenarCodigo (Elemento a,Elemento b) {
		if(a.getCodigo().compareTo(b.getCodigo())<0)
			return a.getCodigo()+b.getCodigo();
		else
			return b.getCodigo()+a.getCodigo();
	}

	/**
	 * Verifica por codigo de acceso si existe
	 * el elemento en su listaDeElementos
	 * @param codigoAcceso
	 * @return
	 */
	public boolean isElemento(String codigoAcceso) {
		return listaDeElementos.isElemento(codigoAcceso);
	}
	
	/**
	 * Retorna un elemento llamado por su codigo
	 * @param codigo
	 * @return
	 */
	public Elemento getElemento (String codigo) {
		return listaDeElementos.getElemento(codigo);
	}
	
	/**
	 * Retorna elemento compuesto a travez de su codigo de acceso
	 * @param codigoAcceso
	 * @return elementoCompuesto o null en caso de no encontrarlo
	 */
	public ElementoCompuesto getElementoCompuesto (String codigoAcceso) {
		return (ElementoCompuesto)listaDeElementos.getElemento(codigoAcceso);
	}
	
	/**
	 * Mezcla los elementos y retorna el compuesto en caso de existir
	 * @param a
	 * @param b
	 * @return elementoCompuesto nuevo o null si no existe.
	 */
	public ElementoCompuesto mezclar (Elemento a, Elemento b) {
		String codigoAcceso = ordenarCodigo(a,b);
		if(isElemento(codigoAcceso))
			return getElementoCompuesto(codigoAcceso);
		else
			return null;
	}
}
