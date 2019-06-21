package appInterfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import app.Partida;
import app.Personaje;

import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GraficaNuevaPartida dialog = new GraficaNuevaPartida();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GraficaNuevaPartida() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		if(!archivoCargado) {
			personajes = new ListaDeElementos();
			personajes.leerDeArchivoPersonajes();
			archivoCargado=true;
		//}
		for(String key : personajes.getColeccion().keySet()){
			comboBox.addItem(personajes.getElemento(key));
			cantidadCargados++;
			}
		}
		
		
		tf_nombre = new JTextField();
		tf_nombre.setBounds(29, 54, 231, 20);
		contentPanel.add(tf_nombre);
		tf_nombre.setColumns(10);
		
		JLabel lblNombreDelJugador = new JLabel("Nombre del jugador :");
		lblNombreDelJugador.setBounds(29, 23, 174, 20);
		contentPanel.add(lblNombreDelJugador);
		
		comboBox.setBounds(29, 126, 149, 20);
		contentPanel.add(comboBox);
		
		JLabel lblPersonajeObjetivo = new JLabel("Personaje objetivo: ");
		lblPersonajeObjetivo.setBounds(29, 101, 123, 14);
		contentPanel.add(lblPersonajeObjetivo);
		
		JRadioButton random = new JRadioButton("Aleatorio");
		random.setBounds(29, 173, 109, 23);
		contentPanel.add(random);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(!tf_nombre.getText().equals("")) {
							nombreJugador=tf_nombre.getText();
							if(random.isSelected()) {
								personajeObjetivo = (Personaje)comboBox.getItemAt(getNumeroRandom(cantidadCargados));
							}else{
								personajeObjetivo = (Personaje)comboBox.getItemAt(comboBox.getSelectedIndex());
							}
							partidaNueva = new Partida(personajeObjetivo,nombreJugador);
							dispose();
						}else
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
	
	public Partida getPartidaNueva() {
		return partidaNueva;
	}
	
	private int getNumeroRandom(int max) {
			Random r = new Random();
			return r.nextInt(max);
	}
}
