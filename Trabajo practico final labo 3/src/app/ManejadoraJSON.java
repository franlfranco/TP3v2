package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Clase que genera, almacena y lee JSON.
 * Contiene una lista y una ruta para crear el archivo.
 */
public class ManejadoraJSON implements IManejadoras {

	ListaDisponibles lista;
	JSONObject root;
	private String rutaElementosIniciales = "./archivos/ElementosInicialesJSON.jso";
	File archivo;

	/**
	 * Constructor de clase
	 * 
	 * @param lista
	 * @return Instancia de la manejadora
	 */
	public ManejadoraJSON(ListaDisponibles lista) {
		this.lista = lista;
		root = new JSONObject();
		archivo = new File(rutaElementosIniciales);
	}

	/**
	 * Agrega la lista al JSON
	 */
	private void agregarAJSON() {
		JSONArray elementosIniciales = new JSONArray();
		Iterator it = lista.getIterador();
		while (it.hasNext()) {
			JSONObject elementoInicial = new JSONObject();
			ElementoInicial e = (ElementoInicial) it.next();
			elementoInicial.put("codigo", e.getCodigo());
			elementoInicial.put("nombre", e.getNombreElemento());

			elementosIniciales.add(elementoInicial);
		}

		root.put("elementosIniciales", elementosIniciales);
	}

	/**
	 * Crea archivo y le carga un JSON 
	 * Llama a agregarAJSON()
	 */
	public void cargarArchivo() {
		agregarAJSON();

		try (PrintWriter writer = new PrintWriter(archivo)) {
			writer.print(root.toJSONString());
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Metodo que lee un archivo con un JSON.
	 * La ruta del archivo esta contenida en la misma clase.
	 * @return lista de elementos iniciales
	 */
	public void leerArchivo() {
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(new FileReader(rutaElementosIniciales));
			JSONArray json = (JSONArray) obj.get("elementosIniciales");
			lista = new ListaDisponibles();
			for (int i = 0; i < json.size(); i++) {
				JSONObject object = (JSONObject) json.get(i);
				
				String codigo = object.get("codigo").toString();
				String nombre = object.get("nombre").toString();
				
				lista.agregar(new ElementoInicial(codigo, nombre));
			}
		} catch (IOException | ParseException e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * LeeDeArchivo() y retorna la lista
	 * @return lista del archivo JSON
	 */
	public ListaDisponibles getLista() {
		leerArchivo();
		return lista;
	}
	
}