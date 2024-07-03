package umu.tds.vista.Login;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


import umu.tds.vista.AppMusic.VentanaAppMusic;

/**
 * Ventana principal de la aplicacion, donde se muestran los paneles de login y
 * registro
 */
public class VentanaLoginRegistro extends JFrame {

	

	/**
	 * Metodo que lanza la ventana de login y registro
	 */
	public void mostrarVentana() {
		try {
			
			VentanaLoginRegistro window = new VentanaLoginRegistro();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Inicializa la ventana
	 */
	public VentanaLoginRegistro() {
		initialize();
	}

	/**
	 * Inicializa el contenido de la ventana
	 * <p>
	 * Se definen los paneles de login y registro
	 * </p>
	 */
	private void initialize() {
		//frmAppmusic = new JFrame();
		setMinimumSize(new Dimension(350, 400));
		setTitle("AppMusic");
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));

		new PanelLogin(this);

		new PanelRegistro(this);

		// Definicion del panel registro con GitHub

		new PanelRegistroGH(this);

	}

	/**
	 * Metodo que lanza la ventana principal de la aplicacion
	 * <p>
	 * Se lanza la ventana principal de la aplicacion y se oculta la ventana de
	 * login y registro
	 */
	public void lanzarVentanaMain() {
		VentanaAppMusic ventanaAppMusic = new VentanaAppMusic();
		ventanaAppMusic.setVisible(true);
		setVisible(false);
	}

}