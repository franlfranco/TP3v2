package appInterfazGrafica;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import app.ListaDeElementos;
import app.ListaPartidas;
import app.Personaje;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class GraficaTopMejores extends JDialog {

	private static JComboBox<Personaje> personajeObjetivo = new JComboBox();
	private static ListaDeElementos personajes = new ListaDeElementos();
	private static JLabel top1;
	private static JLabel top2;
	private static JLabel top3;
	private static JLabel top4;
	private static JLabel top5;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GraficaTopMejores dialog = new GraficaTopMejores();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					actualizarJComboBox();
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
		
		personajeObjetivo.setBounds(300, 231, 124, 20);
			personajes = new ListaDeElementos();
			personajes.leerDeArchivoPersonajes();
			if(personajes.getCantidadElementos()==0) {
				personajeObjetivo.addItem(new Personaje("vacio","vacio","vacio"));
			}else {
				for(String key : personajes.getColeccion().keySet())
					personajeObjetivo.addItem((Personaje) personajes.getElemento(key));
			}
		getContentPane().add(personajeObjetivo);
		
		top1 = new JLabel("1 - --------------------------");
		top1.setForeground(new Color(255, 0, 0));
		top1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		top1.setBounds(24, 24, 400, 33);
		getContentPane().add(top1);
		
		top2 = new JLabel("2 - --------------------------");
		top2.setForeground(new Color(0, 0, 255));
		top2.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
		top2.setBounds(24, 68, 400, 37);
		getContentPane().add(top2);
		
		top3 = new JLabel("3 - --------------------------");
		top3.setForeground(new Color(0, 128, 0));
		top3.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		top3.setBounds(24, 116, 400, 35);
		getContentPane().add(top3);
		
		top4 = new JLabel("4 - --------------------------");
		top4.setForeground(new Color(128, 128, 128));
		top4.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		top4.setBounds(24, 162, 400, 20);
		getContentPane().add(top4);
		
		top5 = new JLabel("5 - --------------------------");
		top5.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		top5.setBounds(23, 193, 401, 27);
		getContentPane().add(top5);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Personaje aux = (Personaje)personajeObjetivo.getItemAt(personajeObjetivo.getSelectedIndex());
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
		});
		btnCargar.setBounds(193, 230, 89, 23);
		getContentPane().add(btnCargar);
		
		JLabel background = new JLabel("");
		try{
			background.setIcon(new ImageIcon(GraficaTopMejores.class.getResource("/appImagenes/cover.jpeg")));
		}catch(Exception e) {
			System.out.println("No se pudo cargar la imagen: background");
		}
		background.setBounds(0, 0, 434, 262);
		getContentPane().add(background);

	}
	
	public static void actualizarJComboBox () {
		personajeObjetivo.removeAll();
		personajes.leerDeArchivoPersonajes();
		for(String key : personajes.getColeccion().keySet())
			personajeObjetivo.addItem((Personaje) personajes.getElemento(key));
		
	}
}
