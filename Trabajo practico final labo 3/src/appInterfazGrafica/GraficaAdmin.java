package appInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Elemento;
import app.ElementoCompuesto;
import app.ElementoInicial;
import app.ListaDeElementos;
import app.ListaDisponibles;
import app.Personaje;

import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import java.awt.List;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GraficaAdmin extends JFrame {

	private static ListaDisponibles listaDisponibles = new ListaDisponibles();
	private static ListaDeElementos listaDeElementosCompuestos = new ListaDeElementos();
	private static ArrayList<String> codigoAccesoCompuestos = new ArrayList<String>();
	private static ListaDeElementos listaDeElementosPersonajes = new ListaDeElementos();
	private static ArrayList<String> codigoAccesoPersonajes = new ArrayList<String>();
	private static List list_iniciales;
	private static List list_compuestos;
	private static List list_personajes;
	
	private JPanel contentPane;
	private JTextField tf_codigoInicial;
	private JTextField tf_nombreInicial;
	private JTextField tf_codigoCompuesto;
	private JTextField tf_nombreCompuesto;
	private JTextField tf_codigoAccesoCompuesto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraficaAdmin frame = new GraficaAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GraficaAdmin() {
		setTitle("Modo admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblElementosIniciales = new JLabel("Elementos iniciales");
		lblElementosIniciales.setBounds(10, 46, 121, 14);
		contentPane.add(lblElementosIniciales);
		
		JLabel lblElementosCompuestos = new JLabel("Elementos compuestos");
		lblElementosCompuestos.setBounds(10, 163, 156, 14);
		contentPane.add(lblElementosCompuestos);
		
		JLabel lblPersonajes = new JLabel("Personajes");
		lblPersonajes.setBounds(10, 346, 121, 14);
		contentPane.add(lblPersonajes);
		
		list_iniciales = new List();
		list_iniciales.setBounds(10, 67, 110, 90);
		list_iniciales.add("Cargar elementos");
		contentPane.add(list_iniciales);
		
		list_compuestos = new List();
		list_compuestos.setBounds(10, 182, 110, 158);
		list_compuestos.add("Cargar elementos");
		contentPane.add(list_compuestos);
		
		list_personajes = new List();
		list_personajes.setBounds(10, 366, 110, 73);
		list_personajes.add("Cargar elementos");
		contentPane.add(list_personajes);
		
		Button save_iniciales = new Button("Guardar");
		save_iniciales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaDisponibles.cargarArchivo();
			}
		});
		save_iniciales.setBounds(129, 87, 70, 22);
		contentPane.add(save_iniciales);
		
		Button load_compuestos = new Button("Cargar");
		load_compuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaDeElementosCompuestos.leerDeArchivoCompuestos();
				actualizarCompuestos();
			}
		});
		load_compuestos.setBounds(129, 183, 70, 22);
		contentPane.add(load_compuestos);
		
		Button load_personajes = new Button("Cargar");
		load_personajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaDeElementosPersonajes.leerDeArchivoPersonajes();
				actualizarPersonajes();
			}
		});
		load_personajes.setBounds(129, 366, 70, 22);
		contentPane.add(load_personajes);
		
		Button load_iniciales = new Button("Cargar");
		load_iniciales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaDisponibles.getColeccion().clear();
				listaDisponibles.leerDeArchivo();
				actualizarIniciales();
			}
		});
		load_iniciales.setBounds(129, 65, 70, 22);
		contentPane.add(load_iniciales);
		
		Button save_compuestos = new Button("Guardar");
		save_compuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaDeElementosCompuestos.cargarArchivoCompuestos();
			}
		});
		save_compuestos.setBounds(129, 208, 70, 22);
		contentPane.add(save_compuestos);
		
		Button save_personajes = new Button("Guardar");
		save_personajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaDeElementosPersonajes.cargarArchivoPersonajes();
			}
		});
		save_personajes.setBounds(129, 394, 70, 22);
		contentPane.add(save_personajes);
		
		JLabel lblAgregar = new JLabel("Agregar");
		lblAgregar.setBounds(219, 46, 46, 14);
		contentPane.add(lblAgregar);
		
		JLabel lblAgregar_1 = new JLabel("Agregar");
		lblAgregar_1.setBounds(219, 163, 46, 14);
		contentPane.add(lblAgregar_1);
		
		tf_codigoInicial = new JTextField();
		tf_codigoInicial.setBounds(219, 67, 86, 20);
		contentPane.add(tf_codigoInicial);
		tf_codigoInicial.setColumns(10);
		
		tf_nombreInicial = new JTextField();
		tf_nombreInicial.setText("");
		tf_nombreInicial.setBounds(219, 89, 86, 20);
		contentPane.add(tf_nombreInicial);
		tf_nombreInicial.setColumns(10);
		
		tf_codigoCompuesto = new JTextField();
		tf_codigoCompuesto.setBounds(219, 185, 86, 20);
		contentPane.add(tf_codigoCompuesto);
		tf_codigoCompuesto.setColumns(10);
		
		tf_nombreCompuesto = new JTextField();
		tf_nombreCompuesto.setBounds(219, 208, 86, 20);
		contentPane.add(tf_nombreCompuesto);
		tf_nombreCompuesto.setColumns(10);
		
		tf_codigoAccesoCompuesto = new JTextField();
		tf_codigoAccesoCompuesto.setBounds(219, 232, 86, 20);
		contentPane.add(tf_codigoAccesoCompuesto);
		tf_codigoAccesoCompuesto.setColumns(10);
		
		JButton bt_addInicial = new JButton("Inicial");
		bt_addInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!tf_codigoInicial.equals("") && !tf_nombreInicial.equals("")) {
						ElementoInicial nuevo = new ElementoInicial(tf_codigoInicial.getText(),tf_nombreInicial.getText());
						if(!listaDisponibles.isElemento(nuevo)) {
							listaDisponibles.agregar(nuevo);
							actualizarIniciales();
						}
							
					}else{
						System.out.println("Algun campo vacio");
					}
				}catch(Exception ex) {
					
				}
			}
		});
		bt_addInicial.setBounds(219, 111, 86, 23);
		contentPane.add(bt_addInicial);
		
		JButton bt_addCompuesto = new JButton("Compuesto");
		bt_addCompuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tf_codigoCompuesto.getText().equals("") && !tf_nombreCompuesto.getText().equals("") && !tf_codigoAccesoCompuesto.getText().equals("")) {
					ElementoCompuesto aux = new ElementoCompuesto(tf_codigoCompuesto.getText(),tf_nombreCompuesto.getText(),tf_codigoAccesoCompuesto.getText());
					if(!listaDeElementosCompuestos.isElemento(aux.getCodigoAcceso())) {
						listaDeElementosCompuestos.agregarElemento(aux.getCodigoAcceso(), aux);
						actualizarCompuestos();
					}
				}
			}
		});
		bt_addCompuesto.setBounds(216, 263, 89, 23);
		contentPane.add(bt_addCompuesto);
		
		JButton bt_addPersonaje = new JButton("Personaje");
		bt_addPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tf_codigoCompuesto.getText().equals("") && !tf_nombreCompuesto.getText().equals("") && !tf_codigoAccesoCompuesto.getText().equals("")) {
					Personaje aux = new Personaje(tf_codigoCompuesto.getText(),tf_nombreCompuesto.getText(),tf_codigoAccesoCompuesto.getText());
					if(!listaDeElementosPersonajes.isElemento(aux.getCodigoAcceso())) {
						listaDeElementosPersonajes.agregarElemento(aux.getCodigoAcceso(), aux);
						actualizarPersonajes();
					}
				}
			}
		});
		bt_addPersonaje.setBounds(216, 297, 89, 23);
		contentPane.add(bt_addPersonaje);
		
		Button bt_removeInicial = new Button("Eliminar");
		bt_removeInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos=list_iniciales.getSelectedIndex();
				if(pos!=-1) {
					listaDisponibles.eliminar(pos);
					actualizarIniciales();
				}
			}
		});
		bt_removeInicial.setBounds(126, 112, 70, 22);
		contentPane.add(bt_removeInicial);
		
		Button remove_compuestos = new Button("Eliminar");
		remove_compuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = list_compuestos.getSelectedIndex();
				if(pos!=-1) {
					String codigoAcceso = codigoAccesoCompuestos.get(pos);
					listaDeElementosCompuestos.eliminarElemento(codigoAcceso);
					actualizarCompuestos();
				}
			}
		});
		remove_compuestos.setBounds(129, 290, 70, 22);
		contentPane.add(remove_compuestos);
		
		Button remove_personajes = new Button("Eliminar");
		remove_personajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = list_personajes.getSelectedIndex();
				if(pos!=-1) {
					String codigoAcceso = codigoAccesoPersonajes.get(pos);
					listaDeElementosPersonajes.eliminarElemento(codigoAcceso);
					actualizarPersonajes();
				}
			}
		});
		remove_personajes.setBounds(129, 422, 70, 22);
		contentPane.add(remove_personajes);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(310, 67, 56, 14);
		contentPane.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(310, 87, 56, 14);
		contentPane.add(lblNombre);
		
		JLabel lblCodigo_1 = new JLabel("Codigo");
		lblCodigo_1.setBounds(309, 191, 75, 14);
		contentPane.add(lblCodigo_1);
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setBounds(310, 213, 74, 14);
		contentPane.add(lblNombre_1);
		
		JLabel lblCodigoAcceso = new JLabel("Codigo acceso");
		lblCodigoAcceso.setBounds(310, 235, 74, 14);
		contentPane.add(lblCodigoAcceso);
	}
	
	public void actualizarIniciales() {
		list_iniciales.removeAll();
		for(Elemento el: listaDisponibles.getColeccion())
			list_iniciales.add(el.getNombreElemento()+" - "+el.getCodigo());
	}
	
	public void actualizarCompuestos() {
		list_compuestos.removeAll();
		codigoAccesoCompuestos.clear();
		for(String key : listaDeElementosCompuestos.getColeccion().keySet()) {
			ElementoCompuesto aux = (ElementoCompuesto)listaDeElementosCompuestos.getElemento(key);
			codigoAccesoCompuestos.add(aux.getCodigoAcceso());
			list_compuestos.add(aux.getNombreElemento() +" - "+ aux.getCodigo()+" - Acc:"+ aux.getCodigoAcceso());
		}
	}
	
	public void actualizarPersonajes() {
		list_personajes.removeAll();
		codigoAccesoPersonajes.clear();
		for(String key : listaDeElementosPersonajes.getColeccion().keySet()) {
			Personaje aux = (Personaje)listaDeElementosPersonajes.getElemento(key);
			codigoAccesoPersonajes.add(aux.getCodigoAcceso());
			list_personajes.add(aux.getNombreElemento() +" - "+ aux.getCodigoAcceso());
		}
	}
}
