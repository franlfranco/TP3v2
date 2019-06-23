package appInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Cronometro;
import app.Elemento;
import app.HiloCronometro;
import app.ListaDeElementos;
import app.ListaDisponibles;
import app.Mezcladora;
import app.Partida;
import app.Personaje;
import app.RegistroPartida;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.List;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class GraficaMain extends JFrame {

	///ELEMENTOS QUE UTILIZAMOS PARA PARTIDA
	private static JLabel cover; //Imagen offGame (Cubre todo)
	private static boolean inGame; //Estado inGame
	private JPanel contentPane; //Contenedor de grafica
	private static Partida partida; //Partida actual
	private static JLabel personajeObjetivo; //Personaje objetivo
	private static Cronometro cronometro; //Cronometro
	private static HiloCronometro hiloCronometro; //Controlador de Cronometro
	private static JLabel tiempoPartida; //Representacion del cronometro
	private static ListaDeElementos listaDeElementos; //Lista de todos los elementos existentes en el juego (Son pasados a la mezcladora)
	private static Mezcladora mezcladora; //Mezcladora del juego
	private static ListaDisponibles listaDisponibles; //Lista de los elementos iniciales y desbloqueados en juego (Van a las listas 1 y 2)
	private static List list1; //Lista izquierda
	private static List list2; //Lista derecha
	private static JLabel resultado; //Resultado de la mezcla
	private static JButton btnCombinar;


	//Componentes graficos utilizados y sus lógicas
	public GraficaMain() {
		setResizable(false); //No se puede redimensionar la ventana
		setTitle("Trabajo practico laboratorio III"); //Titulo de ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Funcion al cerrar app = terminar ejecución
		setBounds(100, 100, 700, 487); //Tamaño de la pantalla
		
		JMenuBar menuBar = new JMenuBar(); //Barra de menu (Partida, top, consultas)
		setJMenuBar(menuBar);
		
		JMenu mnPartida = new JMenu("Partida"); //Menu partida
		menuBar.add(mnPartida);
		
		/**
		 * Menu -> nueva partida
		 */
		JMenuItem mntmNuevaPartida = new JMenuItem("Nueva Partida");
		mntmNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevaPartida();
			}
		});
		mnPartida.add(mntmNuevaPartida);
		
		JMenuItem mntmCancelarPartida = new JMenuItem("Cancelar partida");
		mntmCancelarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarPartida();
			}
		});
		mnPartida.add(mntmCancelarPartida);
		
		JMenu mnTop = new JMenu("TOP"); //Menu top
		menuBar.add(mnTop);
		
		JMenuItem mntmMejoresTiempos = new JMenuItem("Mejores tiempos");
		mntmMejoresTiempos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog top = new GraficaTopMejores();
				top.setVisible(true);
			}
		});
		mnTop.add(mntmMejoresTiempos);
		
		JMenu mnAcercaDe = new JMenu("?"); //Menu de consultas
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmComoJugar = new JMenuItem("Como jugar?"); //Explicacion del juego
		mnAcercaDe.add(mntmComoJugar);
		
		JMenuItem mntmModoAdmin = new JMenuItem("Modo Admin"); //Sobre los creadores y el proyecto
		mntmModoAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String contrasena = JOptionPane.showInputDialog(null);
					if(contrasena.equals("1234")) {
						JFrame admin = new GraficaAdmin();
						admin.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "contrasena incorrecta");
					}
				}catch(Exception ex) {
				}
			}
		});
		mnAcercaDe.add(mntmModoAdmin);
		
		JMenuBar menuBar_1 = new JMenuBar(); //Menu bar
		menuBar.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cover = new JLabel("");
		cover.setIcon(new ImageIcon(GraficaMain.class.getResource("/appImagenes/cover.jpg")));
		cover.setBounds(0, 0, 694, 440);
		contentPane.add(cover);
		
		JLabel lblObjetivo = new JLabel("Objetivo: "); //Texto "Objetivo: "
		lblObjetivo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblObjetivo.setBounds(0, 11, 92, 14);
		contentPane.add(lblObjetivo);
		
		personajeObjetivo = new JLabel(); //Contenedor del nombre del objetivo
		personajeObjetivo.setText("Personaje");
		personajeObjetivo.setFont(new Font("Tahoma", Font.BOLD, 15));
		personajeObjetivo.setForeground(Color.WHITE);
		personajeObjetivo.setBounds(71, 11, 128, 14);
		contentPane.add(personajeObjetivo);
		
		list1 = new List(); //Lista izquierda
		list1.setForeground(Color.BLACK);
		list1.setBounds(71, 283, 110, 137);
		list1.add("Vacio");
		contentPane.add(list1);
		
		list2 = new List(); //Lista derecha
		list2.setForeground(Color.BLACK);
		list2.setBounds(494, 283, 110, 137);
		list2.add("Vacio");
		contentPane.add(list2);
		
		tiempoPartida = new JLabel("00 : 00 : 00"); //Representacion de cronometro
		tiempoPartida.setForeground(Color.WHITE);
		tiempoPartida.setFont(new Font("Tahoma", Font.BOLD, 20));
		tiempoPartida.setBounds(547, 11, 127, 32);
		contentPane.add(tiempoPartida);
		
		JLabel iconFondoCronometro = new JLabel(""); //Imagen de fondo del cronometro
		try{
			iconFondoCronometro.setIcon(new ImageIcon(GraficaMain.class.getResource("/appImagenes/backText.png")));
		}catch (Exception e){
			System.out.println("No se pudo cargar la imagen: fondo cronómetro");
		}
		iconFondoCronometro.setBounds(540, 0, 130, 52);
		contentPane.add(iconFondoCronometro);
		
		resultado = new JLabel(); //Resultado de la mezcla
		resultado.setText("Resultado Mezcladora");
		resultado.setForeground(new Color(0, 0, 128));
		resultado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		resultado.setBounds(202, 162, 339, 46);
		contentPane.add(resultado);
		
		btnCombinar = new JButton("Combinar");
		btnCombinar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCombinar.setForeground(Color.GREEN);
		btnCombinar.setVisible(false);
		btnCombinar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				combinar();
			}
		});
		btnCombinar.setBounds(291, 339, 89, 23);
		contentPane.add(btnCombinar);
		
		JLabel lblNewLabel = new JLabel("");
		try {
			lblNewLabel.setIcon(new ImageIcon(GraficaMain.class.getResource("/appImagenes/caldera.png")));
		}catch(Exception e) {
			System.out.println("No se pudo cargar la imagen: caldera");
		}
		
		JLabel fondoPersonaje = new JLabel("");
		fondoPersonaje.setIcon(new ImageIcon(GraficaMain.class.getResource("/appImagenes/backText.png")));
		fondoPersonaje.setBounds(0, 0, 220, 43);
		contentPane.add(fondoPersonaje);
		
		lblNewLabel.setBounds(233, 215, 200, 228);
		contentPane.add(lblNewLabel);
		
		JLabel iconFondo = new JLabel(""); //Imagen de fondo
		try {
			iconFondo.setIcon(new ImageIcon(GraficaMain.class.getResource("/appImagenes/backgroundProvisorio.jpg")));
		}catch(Exception e) {
			System.out.println("No se pudo cargar la imagen: background");
		}
		iconFondo.setBounds(0, 0, 694, 440);
		contentPane.add(iconFondo);
		try{
		}catch(Exception e){
			System.out.println("No se pudo cargar la imagen: cover");
		}
	}
	
	
	/**
	 * Main de la gráfica
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Levantar de archivo y cargar a listaDeElementos
					//crear la mezcladora, pasarle como parametro la listaDeElementos
					//Levantar de archivo y cargar a listaDisponibles
					listaDeElementos = new ListaDeElementos();
					listaDeElementos.leerDeArchivo();
					mezcladora = new Mezcladora(listaDeElementos);
					JFrame main = new GraficaMain();
					main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	///ACTUALIZA LOS LISTADOS
	public static void actualizarListados() {
		list1.removeAll();
		list2.removeAll();
		for(Elemento e: listaDisponibles.getColeccion()) {
			list1.add(e.getNombreElemento());
			list2.add(e.getNombreElemento());
		}
	}
	
	public static void nuevaPartida() {
		//Abre pantalla de nueva partida
		GraficaNuevaPartida nuevaPartida = new GraficaNuevaPartida();
		nuevaPartida.setVisible(true);
		
		//Corroboramos si se completaron los datos
		if(nuevaPartida.getPartidaNueva()!=null) {
			
			//Ponemos en funcionamiento el cronometro
			//En caso de que esta sea la segunda partida creada, cerramos el hilo del cronometro de la primera
			if(hiloCronometro==null)
			hiloCronometro = new HiloCronometro(new Cronometro(tiempoPartida));
			else {
				hiloCronometro.close();
				hiloCronometro = new HiloCronometro(new Cronometro(tiempoPartida));
			}
			
			//Se cargan los datos de la partida nueva
			partida = nuevaPartida.getPartidaNueva();
			//Test
			personajeObjetivo.setText(partida.getNombrePersonajeObjetivo());
			System.out.println(partida.getNombreJugador()+" con personaje objetivo: "+partida.getNombrePersonajeObjetivo());
			//Quitamos el cover
			cover.setVisible(false);
			btnCombinar.setVisible(true);
			
			//Adaptacion para interfaz Swing, cerrando el cronometro existente y utilizar el de la interfaz grafica para controlar
			partida.getCronometro().close();
			partida.setCronometro(hiloCronometro);
			hiloCronometro.play();
			listaDisponibles = partida.getListaDisponibles();
			actualizarListados();
			
			//Activamos el inGame
			inGame = true;
		}
			
	}
	
	public static void cancelarPartida() {
		if(!inGame) {
			JOptionPane.showMessageDialog(null, "No hay ninguna partida en curso");
		}else {
			if(JOptionPane.showConfirmDialog(null, "Estas seguro que desea cancelar la partida?")==0) {
				partida=null;
				hiloCronometro.close();
				cover.setVisible(true);
				inGame = false;
				btnCombinar.setVisible(false);
			}
		}
	}
	
	
	public static void combinar() {
		Elemento temp1 = null;
		if(list1.getSelectedIndex()!=-1)
		//temp1 = listaDisponibles.getElemento(list1.getSelectedIndex());
			temp1 = partida.getElementoDeDisponibles(list1.getSelectedIndex());
		Elemento temp2 = null;
		if(list2.getSelectedIndex()!=-1)
			//temp2 = listaDisponibles.getElemento(list2.getSelectedIndex());
			temp2 = partida.getElementoDeDisponibles(list2.getSelectedIndex());
		Elemento temp3 = null;
		if(temp1!=null && temp2!=null) {
			temp3=mezcladora.mezclar(temp1, temp2);
		}
		if(temp3 != null) {
			if(!partida.elementoYaDisponible(temp3)) {
				//partida.agregarElementoDisponible(temp3);
				listaDisponibles.agregar(temp3);
				resultado.setText("Desbloqueaste a: "+temp3.getNombreElemento());
				actualizarListados();
				if(temp3.equals(partida.getPersonaje())) {
					btnCombinar.setVisible(false);
					partidaGanada();
				}
					
				
			}else {
				resultado.setText("Elemento ya desbloqueado");
			}
		}else if(temp3 == null) {
			resultado.setText("Nada surge de la combinación");
		}
	}
	
	public static void partidaGanada() {
		hiloCronometro.close();
		partida.setPuntajeJugadorPorCronometro();
		System.out.println("Ganaste!, tu puntuacion fue de: "+partida.getPuntajeJugador()+" con tiempo de: "+partida.getTimer());
		cover.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "Ganaste!, tu tiempo fue de: "+partida.getTimer());
		inGame = false;
		RegistroPartida nuevo = new RegistroPartida(partida.getNombreJugador(),partida.getNombrePersonajeObjetivo(),
				partida.getTimer(),partida.getPuntajeJugador());
		System.out.println(nuevo.toString());
		Personaje ref = (Personaje)listaDeElementos.getElemento(partida.getPersonaje().getCodigoAcceso());
		ref.nuevoPuntaje(nuevo);
		listaDeElementos.cargarArchivoPersonajes();
		System.out.println("Este personaje tiene : "+ref.getCantRegistros()+" registros de juego");
	}
}
