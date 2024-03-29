package appInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;

import app.ListaDeElementos;
import app.ManejadoraArchivos;
import app.Partida;
import app.Personaje;

import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GraficaNuevaPartida extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static String nombreJugador = null;
	private static Personaje personajeObjetivo = null;
	private static Partida partidaNueva = null;
	private JTextField tf_nombre;
	private static JComboBox comboBox = new JComboBox();
	private static boolean archivoCargado = false;
	private static int cantidadCargados = 0;
	private static ListaDeElementos personajes;
	private static JLabel imagenPersonaje;
	private static String rutaPersonajes;
	private static String rutaIniciales;

	/**
	 * Main de la interfaz
	 */
	public static void main(String[] args) {
		try {
			GraficaNuevaPartida dialog = new GraficaNuevaPartida(rutaPersonajes, rutaIniciales);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GraficaNuevaPartida(String rutaPersonajes, String rutaIniciales) {
		this.rutaPersonajes = rutaPersonajes;
		this.rutaIniciales = rutaIniciales;
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		if (!archivoCargado) {
			personajes = new ListaDeElementos();
			ManejadoraArchivos archi = new ManejadoraArchivos(personajes, rutaPersonajes);
			archi.leerArchivoPersonajes();
			archivoCargado = true;
			for (String key : personajes.getColeccion().keySet()) {
				comboBox.addItem(personajes.getElemento(key));
				cantidadCargados++;
			}
		}

		tf_nombre = new JTextField();
		tf_nombre.setBounds(29, 54, 231, 20);
		contentPanel.add(tf_nombre);
		tf_nombre.setColumns(10);

		/**
		 * Actualiza la imagen de los personajes objetivos
		 */
		JLabel lblNombreDelJugador = new JLabel("Nombre del jugador :");
		lblNombreDelJugador.setBounds(29, 23, 174, 20);
		contentPanel.add(lblNombreDelJugador);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if (state == e.SELECTED) {
					Personaje seleccionado = (Personaje) e.getItem();
					try {
						imagenPersonaje.setIcon(new ImageIcon(GraficaNuevaPartida.class
								.getResource("/appImagenes/" + seleccionado.getNombreElemento() + ".jpg")));
					} catch (Exception ex) {
						System.out.println("El seleccionado no tiene imagen");
						imagenPersonaje.setIcon(new ImageIcon(
								GraficaNuevaPartida.class.getResource("/appImagenes/noImage140x140.png")));
					}
				}
			}
		});

		comboBox.setBounds(29, 126, 149, 20);
		contentPanel.add(comboBox);

		JLabel lblPersonajeObjetivo = new JLabel("Personaje objetivo: ");
		lblPersonajeObjetivo.setBounds(29, 101, 123, 14);
		contentPanel.add(lblPersonajeObjetivo);

		JRadioButton random = new JRadioButton("Aleatorio");
		random.setBounds(29, 173, 109, 23);
		contentPanel.add(random);

		imagenPersonaje = new JLabel("");
		imagenPersonaje.setBounds(286, 23, 140, 140);
		Personaje seleccionado = (Personaje) comboBox.getSelectedItem();
		try {
			imagenPersonaje.setIcon(new ImageIcon(GraficaNuevaPartida.class
					.getResource("/appImagenes/" + seleccionado.getNombreElemento() + ".jpg")));
		} catch (Exception ex) {
			imagenPersonaje
					.setIcon(new ImageIcon(GraficaNuevaPartida.class.getResource("/appImagenes/noImage140x140.png")));
		}
		contentPanel.add(imagenPersonaje);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (!tf_nombre.getText().equals("")) {
							nombreJugador = tf_nombre.getText();
							if (random.isSelected()) {
								personajeObjetivo = (Personaje) comboBox.getItemAt(getNumeroRandom(cantidadCargados));
							} else {
								personajeObjetivo = (Personaje) comboBox.getItemAt(comboBox.getSelectedIndex());
							}
							partidaNueva = new Partida(personajeObjetivo, nombreJugador, rutaIniciales);
							dispose();
						} else
							JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre");

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						partidaNueva = null;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Retorna la partida nueva
	 * 
	 * @return null en caso de que no se completara la petici�n
	 */
	public Partida getPartidaNueva() {
		return partidaNueva;
	}

	/**
	 * Retorna numero aleatorio
	 * 
	 * @param max
	 * @return int aleatorio entre 0 y max
	 */
	private int getNumeroRandom(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}
}
