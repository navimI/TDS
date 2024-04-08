package umu.tds.vista;



import javax.swing.JFrame;
import java.awt.CardLayout;

import java.awt.Dimension;


public class VentanaLoginRegistro {

	private JFrame frmAppmusic;
	

	

	/**
	 * Launch the application.
	 */
	public void mostrarVentana() {
		try {
			//UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
			VentanaLoginRegistro window = new VentanaLoginRegistro();
			window.frmAppmusic.setVisible(true);
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
		frmAppmusic = new JFrame();
		frmAppmusic.setMinimumSize(new Dimension(350, 400));
		frmAppmusic.setTitle("AppMusic");
		frmAppmusic.setBounds(100, 100, 500, 500);
		frmAppmusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppmusic.getContentPane().setLayout(new CardLayout(0, 0));
		
		new LoginPanel(frmAppmusic);
		
		new RegistroPanel(frmAppmusic);
		
		//Definicion del panel registro con GitHub
		
		new RegistroGHPanel(frmAppmusic);

		
	}

}