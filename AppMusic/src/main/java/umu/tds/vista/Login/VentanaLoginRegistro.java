package umu.tds.vista.Login;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


import umu.tds.vista.AppMusic.VentanaAppMusic;

public class VentanaLoginRegistro extends JFrame {

	//private JFrame frmAppmusic;

	/**
	 * Launch the application.
	 */
	public void mostrarVentana() {
		try {
			// UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
			VentanaLoginRegistro window = new VentanaLoginRegistro();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the application.
	 */
	public VentanaLoginRegistro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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

	

	public void lanzarVentanaMain() {

//		VentanaMain main = new VentanaMain();
//		main.setVisible(true);

		VentanaAppMusic ventanaAppMusic = new VentanaAppMusic();
		ventanaAppMusic.setVisible(true);
		setVisible(false);
	}

}