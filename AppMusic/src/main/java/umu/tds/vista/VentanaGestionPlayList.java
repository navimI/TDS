package umu.tds.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;

public class VentanaGestionPlayList {

	private JFrame frame;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestionPlayList window = new VentanaGestionPlayList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaGestionPlayList() {
		initialize();
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelCentro = new JPanel();
		frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 1, 0, 0));
		
		Panel panelBotones = new Panel();
		panelCentro.add(panelBotones);
		panelBotones.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		panelBotones.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		panelBotones.add(btnNewButton);
		
		textField = new JTextField();
		textField.setColumns(15);
		textField.setBackground(Color.WHITE);
		panelBotones.add(textField);
		
		Panel panelTabla = new Panel();
		panelCentro.add(panelTabla);
		panelTabla.setLayout(new GridLayout(0, 1, 0, 0));
		
		table = new JTable();
		panelTabla.add(table);
		
		Panel panelNorte = new Panel();
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 25));
		
		Panel panelSur = new Panel();
		frame.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 25));
		
		Panel panel = new Panel();
		panelSur.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//INICIO BOTONES CON IMAGEN:
		//GridBagConstraints común para todos los botones
		GridBagConstraints commonGBC = new GridBagConstraints();
		commonGBC.fill = GridBagConstraints.BOTH;
		commonGBC.anchor = GridBagConstraints.CENTER;

		// tamaño uniforme para todos los botones (ancho y alto)
		/*commonGBC.ipadx = 64; // Cambia
		commonGBC.ipady = 64; // Cambia
		commonGBC.gridwidth = 1;
		commonGBC.gridheight = 1;*/
		
		//BOTONES:
		// 2
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/anterior.jpg")));
		btnNewButton_2.setPreferredSize(new Dimension(30, 30)); //para redimensionar
		panel.add(btnNewButton_2);

		// 3
		JButton btnNewButton_3 = new JButton();
		btnNewButton_3.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/stop.jpg")));
		btnNewButton_3.setPreferredSize(new Dimension(30, 30)); //para redimensionar
		panel.add(btnNewButton_3);
		
		// 4
		JButton btnNewButton_4 = new JButton();
		btnNewButton_4.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/pause.jpg")));
		btnNewButton_4.setPreferredSize(new Dimension(30, 30)); //para redimensionar
		panel.add(btnNewButton_4); 

		// 5
		JButton btnNewButton_5 = new JButton();
		btnNewButton_5.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/play.jpg")));
		btnNewButton_5.setPreferredSize(new Dimension(30, 30)); //para redimensionar
		panel.add(btnNewButton_5);

		// 6
		JButton btnNewButton_6 = new JButton();
		btnNewButton_6.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/siguiente.jpg")));
		btnNewButton_6.setPreferredSize(new Dimension(30, 30)); //para redimensionar
		panel.add(btnNewButton_6); 

		
		
		JButton EliminarLista = new JButton("Eliminar Lista");
		EliminarLista.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(EliminarLista);
		
		Panel panelOeste = new Panel();
		frame.getContentPane().add(panelOeste, BorderLayout.WEST);
		panelOeste.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 25));
	}

}