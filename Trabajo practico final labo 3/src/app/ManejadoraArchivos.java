package app;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;

public class ManejadoraArchivos<T extends Lista> implements IManejadoras {
	private T lista;
	private String ruta;
	private Iterator it;

	public ManejadoraArchivos(T lista, String ruta) {
		this.lista = lista;
		this.ruta = ruta;
		it = lista.getIterador();
	}

	/**
	 * Carga del archivo los ElementosIniciales y los ElementosCompuestos de la ruta especificada
	 */
	public void cargarArchivo() {
		FileOutputStream archivoSalida = null;
		ObjectOutputStream objetoSalida = null;
		try {
			archivoSalida = new FileOutputStream(ruta);
			objetoSalida = new ObjectOutputStream(archivoSalida);
			while (it.hasNext()) {
				if(lista instanceof ListaDisponibles) {
					ElementoInicial aux = (ElementoInicial)it.next();
					objetoSalida.writeObject(aux);
				}else if(lista instanceof ListaDeElementos) {
					Map.Entry<String, ElementoCompuesto> e = (Map.Entry<String, ElementoCompuesto>)it.next();
					ElementoCompuesto aux2=(ElementoCompuesto) ((ListaDeElementos) lista).getElemento(e.getKey());
					objetoSalida.writeObject(aux2);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				objetoSalida.close();
				archivoSalida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Carga del archivo los Personajes de la ruta especificada. SOLO PERSONAJES
	 * Si se intenta cargar ElementosIniciales, no hará nada.
	 */
	public void cargarArchivoPersonajes() {
		if(lista instanceof ListaDeElementos) {
			FileOutputStream archivoSalidaPersonajes = null;
			ObjectOutputStream objetoSalidaPersonajes = null;
			ObjectOutputStream objetoSalidaRegistros = null;
			try {
				archivoSalidaPersonajes = new FileOutputStream(ruta);
				objetoSalidaPersonajes = new ObjectOutputStream(archivoSalidaPersonajes);
				objetoSalidaRegistros = new ObjectOutputStream(archivoSalidaPersonajes);
				ElementoCompuesto aux = null;
				while(it.hasNext()) {
					Map.Entry<String, ElementoCompuesto> e = (Map.Entry<String, ElementoCompuesto>)it.next();
					aux=(ElementoCompuesto) ((ListaDeElementos) lista).getElemento(e.getKey());
					if(aux instanceof Personaje) {
						objetoSalidaPersonajes.writeObject(aux);
						//aux=(ElementoCompuesto)it.next();
						//objetoSalidaPersonajes.writeObject(aux);
						if(((Personaje)aux).getCantRegistros()>0) {
							for(RegistroPartida r : ((Personaje)aux).getColeccion()) {
								objetoSalidaRegistros.writeObject(r);
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (IOException e) {
				System.out.println("IO exception personaje");
			}finally {
				try {
					objetoSalidaPersonajes.close();
					archivoSalidaPersonajes.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Lee del archivo los ElementosIniciales y los ElementosCompuestos de la ruta especificada
	 */
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

	/**
	 * Lee del archivo los Personajes de la ruta especificada. SOLO PERSONAJES
	 * Si se intenta leer ElementosIniciales, no hará nada.
	 */
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
			}catch(EOFException e) {
				System.out.println("Fin del archivo");
			}	catch (IOException e) {
				System.out.println("IO exception");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No se puede leer de personaje en lista de disponibles");
		}
	}

	/**
	 * 
	 * @return String con ruta del archivo
	 */
	public String getRuta() {
		return ruta;
	}

}
