package appInterfazGrafica;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import app.ListaDeElementos;
import app.ListaPartidas;
import app.ManejadoraArchivos;
import app.Personaje;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GraficaTopMejores extends JDialog {

	private static JComboBox personajeObjetivo = new JComboBox();
	private static ListaDeElementos personajes = new ListaDeElementos();
	private static JLabel top1;
	private static JLabel top2;
	private static JLabel top3;
	private static JLabel top4;
	private static JLabel top5;
	private static String rutaPersonajes = "./archivos/personajes.dat";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					actualizarJComboBox();
					GraficaTopMejores dialog = new GraficaTopMejores();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public GraficaTopMejores() {
		setTitle("Mejores tiempos");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		personajeObjetivo = new JComboBox();
		personajeObjetivo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state == e.SELECTED) {
					Personaje seleccionado = (Personaje)e.getItem();
					try {
						cargarTop(seleccionado);
					}catch(Exception ex) {
						System.out.println("No hay top para cargar");
					}
					
				}
			}
		});
		
		personajeObjetivo.setBounds(300, 231, 124, 20);
			personajes = new ListaDeElementos();
			personajes.leerDeArchivoPersonajes();
			if(personajes.getCantidadElementos()==0) {
				personajeObjetivo.addItem(new Personaje("vacio","vacio","vacio"));
			}else {
				for(String key : personajes.getColeccion().keySet())
					personajeObjetivo.addItem((Personaje) personajes.getElemento(key));
			}
		
		JButton btnSeleccionar = new JButton("Ver records");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarTop((Personaje)personajeObjetivo.getSelectedItem());
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setVisible(false);
			}
		});
		btnSeleccionar.setBounds(300, 230, 124, 23);
		getContentPane().add(btnSeleccionar);
		getContentPane().add(personajeObjetivo);
		
		top1 = new JLabel("1 - --------------------------");
		top1.setForeground(Color.BLUE);
		top1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		top1.setBounds(54, 24, 336, 33);
		getContentPane().add(top1);
		
		top2 = new JLabel("2 - --------------------------");
		top2.setForeground(new Color(0, 0, 255));
		top2.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
		top2.setBounds(45, 68, 245, 37);
		getContentPane().add(top2);
		
		top3 = new JLabel("3 - --------------------------");
		top3.setForeground(Color.BLACK);
		top3.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		top3.setBounds(45, 116, 245, 35);
		getContentPane().add(top3);
		
		top4 = new JLabel("4 - --------------------------");
		top4.setForeground(Color.BLACK);
		top4.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		top4.setBounds(45, 162, 245, 20);
		getContentPane().add(top4);
		
		top5 = new JLabel("5 - --------------------------");
		top5.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		top5.setBounds(45, 193, 245, 27);
		getContentPane().add(top5);
		JLabel background = new JLabel("");
		background.setForeground(Color.BLACK);
		try{
			background.setIcon(new ImageIcon(GraficaTopMejores.class.getResource("/appImagenes/mejores tiempos.png")));
		}catch(Exception e) {
			System.out.println("No se pudo cargar la imagen: background");
		}
		background.setBounds(0, 0, 434, 262);
		getContentPane().add(background);

	}
	
	public static void actualizarJComboBox () {
		personajeObjetivo.removeAll();
		//personajes.leerDeArchivoPersonajes();
		ManejadoraArchivos manejadoraArchivos = new ManejadoraArchivos(personajes,rutaPersonajes);
		manejadoraArchivos.leerArchivoPersonajes();
		for(String key : personajes.getColeccion().keySet())
			personajeObjetivo.addItem((Personaje) personajes.getElemento(key));
		
		
	}
	
	public static void cargarTop(Personaje aux) {
		aux = (Personaje)personajeObjetivo.getItemAt(personajeObjetivo.getSelectedIndex());
		ListaPartidas aux2 = aux.getListaPartidas();
		top1.setText("1 - --------------------------");
		top2.setText("2 - --------------------------");
		top3.setText("3 - --------------------------");
		top4.setText("4 - --------------------------");
		top5.setText("5 - --------------------------");

		try {
			top1.setText(aux2.getTop(1));
			top2.setText(aux2.getTop(2));
			top3.setText(aux2.getTop(3));
			top4.setText(aux2.getTop(4));
			top5.setText(aux2.getTop(5));
		} catch(Exception e) {
			
		}
	}
}
