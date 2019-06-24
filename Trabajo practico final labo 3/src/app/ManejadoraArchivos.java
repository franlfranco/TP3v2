package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

public class ManejadoraArchivos<T extends Lista, E> {
	private T lista;
	private E elemento;
	private String ruta;
	private Iterator it;

	public ManejadoraArchivos(T lista, String ruta) {
		this.lista = lista;
		this.ruta = ruta;
		it = lista.getIterador();
	}

	public void cargarArchivo() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(ruta);
			oos = new ObjectOutputStream(fos);
			while (it.hasNext()) {
				elemento = (E) it.next();
				oos.writeObject(elemento);
				if (elemento instanceof Personaje) {
					if (((Personaje) elemento).getCantRegistros() > 0) {
						for (RegistroPartida r : ((Personaje) elemento).getColeccion()) {
							oos.writeObject(r);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void leerArchivo() {
		FileInputStream fis;
		ObjectInputStream ois;
		try {
			fis = new FileInputStream(ruta);
			ois = new ObjectInputStream(fis);
			if (lista instanceof ListaDisponibles) {
				ElementoInicial aux = (ElementoInicial) ois.readObject();
				while (aux != null) {
					((ListaDisponibles) lista).agregar(aux);
					aux = (ElementoInicial) ois.readObject();
				}
			}
			if (lista instanceof ListaDeElementos) {
				ElementoCompuesto aux = (ElementoCompuesto) ois.readObject();
				while (aux != null) {
					((ListaDeElementos) lista).agregarElemento(aux.getCodigoAcceso(), aux);
					aux = (ElementoCompuesto) ois.readObject();
				}
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}
	}

	public void leerArchivoPersonajes() {
		ObjectInputStream objetoEntrada;
		ObjectInputStream registroEntrada;
		FileInputStream archivoEntrada;
		if (lista instanceof ListaDeElementos) {
			try {
				archivoEntrada = new FileInputStream(ruta);
				objetoEntrada = new ObjectInputStream(archivoEntrada);
				registroEntrada = new ObjectInputStream(archivoEntrada);
				Personaje aux = (Personaje) objetoEntrada.readObject();
				aux.inicializarlistaPartidas();
				RegistroPartida aux2;
				while (aux != null) {
					for (int i = 0; i < aux.getCantRegistros(); i++) {
						aux2 = (RegistroPartida) registroEntrada.readObject();
						aux.cargarPuntajes(aux2);
					}
					((ListaDeElementos) lista).agregarElemento(aux.getCodigoAcceso(), aux);
					aux = (Personaje) objetoEntrada.readObject();
					if (aux != null)
						aux.inicializarlistaPartidas();
				}
				objetoEntrada.close();
				archivoEntrada.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (IOException e) {
				System.out.println("IO exception personaje");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No se puede leer de personaje en lista de disponibles");
		}
	}

	public String getRuta() {
		return ruta;
	}

}
