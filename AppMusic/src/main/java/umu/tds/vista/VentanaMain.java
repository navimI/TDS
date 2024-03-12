package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;


public class VentanaMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain frame = new VentanaMain();
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
	
	//Trying git
	
	
	public VentanaMain() {
		setTitle("AppMusic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelBotonera = new JPanel();
		contentPane.add(panelBotonera, BorderLayout.WEST);
		GridBagLayout gbl_panelBotonera = new GridBagLayout();
		gbl_panelBotonera.columnWidths = new int[]{0, 0};
		gbl_panelBotonera.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelBotonera.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelBotonera.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBotonera.setLayout(gbl_panelBotonera);
		
		this.setMinimumSize(new Dimension(600, 400));
	    this.setMaximumSize(new Dimension(700, 500));
	    this.setSize(new Dimension(600, 400));
		
		JPanel panelCardLayout = new JPanel();

		/*JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBuscarCanciones ventanaBuscar = new VentanaBuscarCanciones();
				Point botonLocation = getLocationOnScreen();
				ventanaBuscar.setLocation(botonLocation);
				ventanaBuscar.setVisible(true);
				//dispose();
				//Cuando el user hace click en el botón "Buscar":
				//Creamos la ventana VEntanaBuscarCanciones
				//contentPane.setVisible(false);
			}
		});*/
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelBuscar");
			}
		});
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/lupa.png")));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 0;
		panelBotonera.add(btnBuscar, gbc_btnBuscar);
		
		JButton btnGestionPlaylists = new JButton("Gestion Playlists");
		btnGestionPlaylists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelGestion");
			}
		});
		btnGestionPlaylists.setHorizontalAlignment(SwingConstants.LEFT);
		btnGestionPlaylists.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/mas.png")));
		btnGestionPlaylists.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnGestionPlaylists = new GridBagConstraints();
		gbc_btnGestionPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGestionPlaylists.insets = new Insets(0, 0, 5, 0);
		gbc_btnGestionPlaylists.gridx = 0;
		gbc_btnGestionPlaylists.gridy = 1;
		panelBotonera.add(btnGestionPlaylists, gbc_btnGestionPlaylists);
		
		JButton btnRecientes = new JButton("Recientes");
		btnRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Botón Recientes clicado"); //msg para depurar
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelRecientes");
				//System.out.println("Panel actual: " + card.toString());//msg para depurar
			}
		});
		btnRecientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecientes.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/reloj.png")));
		btnRecientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnRecientes = new GridBagConstraints();
		gbc_btnRecientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRecientes.insets = new Insets(0, 0, 5, 0);
		gbc_btnRecientes.gridx = 0;
		gbc_btnRecientes.gridy = 2;
		panelBotonera.add(btnRecientes, gbc_btnRecientes);
		
		JButton btnMisPlaylists = new JButton("Mis Playlists");
		btnMisPlaylists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelPlaylists");
			}
		});
		btnMisPlaylists.setHorizontalAlignment(SwingConstants.LEFT);
		btnMisPlaylists.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/altavoz.png")));
		btnMisPlaylists.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnMisPlaylists = new GridBagConstraints();
		gbc_btnMisPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMisPlaylists.gridx = 0;
		gbc_btnMisPlaylists.gridy = 3;
		panelBotonera.add(btnMisPlaylists, gbc_btnMisPlaylists);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCentro.add(panel, BorderLayout.NORTH);
		
		JLabel lblBienvenido = new JLabel("Bienvenido, ");
		panel.add(lblBienvenido);
		
		JButton btnPremium = new JButton("Premium");
		panel.add(btnPremium);
		
		JButton btnSalir = new JButton("Salir");
		panel.add(btnSalir);
		
		panelCentro.add(panelCardLayout, BorderLayout.CENTER);
		panelCardLayout.setLayout(new CardLayout(0, 0));
		
		JPanel panelBuscar = new JPanel();
		panelCardLayout.add(panelBuscar, "panelBuscar");
		
		//Componentes para el panel Buscar:
		JLabel lblInterprete = new JLabel("Intérprete:");
		panelBuscar.add(lblInterprete);
		// onfigurar el tamaño mínimo y máximo del panelBuscar
		panelBuscar.setMinimumSize(new Dimension(600, 400));
		panelBuscar.setMaximumSize(new Dimension(700, 500));
		//Para que se vean de forma vertical:
		//panelBuscar.setLayout(new BoxLayout(panelBuscar, BoxLayout.Y_AXIS));
		
		JTextField textFieldInterprete = new JTextField();
		panelBuscar.add(textFieldInterprete);
		textFieldInterprete.setColumns(15);

		JLabel lblTitulo = new JLabel("Título:");
		panelBuscar.add(lblTitulo);

		JTextField textFieldTitulo = new JTextField();
		panelBuscar.add(textFieldTitulo);
		textFieldTitulo.setColumns(15);

		JLabel lblEstilo = new JLabel("Estilo:");
		panelBuscar.add(lblEstilo);
		//Lista desplegable (combobox) para el estilo musical
		String[] estilosMusicales = {"Pop", "Rock", "Electrónica", "Hip-hop", "Otros"};
		JComboBox<String> comboBoxEstilo = new JComboBox<>(estilosMusicales);
		panelBuscar.add(comboBoxEstilo);
		
		JLabel lblFavoritas = new JLabel("Favoritas:");
		panelBuscar.add(lblFavoritas);

		//Casilla de marcado (checkbox) para favoritas
		JCheckBox checkBoxFavoritas = new JCheckBox();
		panelBuscar.add(checkBoxFavoritas);

		
		
		//Botón para realizar la búsqueda
		JButton btnRealizarBusqueda = new JButton("Buscar");
		btnRealizarBusqueda.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        //Obtener los valores de los campos y realizar la búsqueda
		        String interprete = textFieldInterprete.getText();
		        String titulo = textFieldTitulo.getText();
		        String estilo = (String) comboBoxEstilo.getSelectedItem();
		        boolean favoritas = checkBoxFavoritas.isSelected();

		        //Realizar la acción correspondiente con los valores de búsqueda
		        System.out.println("Realizando búsqueda con los siguientes filtros:");
		        System.out.println("Intérprete: " + interprete);
		        System.out.println("Título: " + titulo);
		        System.out.println("Estilo: " + estilo);
		        System.out.println("Favoritas: " + favoritas);
		    }
		});
		panelBuscar.add(btnRealizarBusqueda);
		
		
		JLabel lblPanelbuscar = new JLabel("PanelBuscar");
		panelBuscar.add(lblPanelbuscar);
		
		JPanel panelGestion = new JPanel();
		panelCardLayout.add(panelGestion, "panelGestion");
		
		JLabel lblPanelgestion = new JLabel("PanelGestion");
		panelGestion.add(lblPanelgestion);
		
		JPanel panelRecientes = new JPanel();
		panelCardLayout.add(panelRecientes, "panelRecientes");
		
		JLabel lblpanelRecientes = new JLabel("PanelRecientes");
		panelRecientes.add(lblpanelRecientes);
		
		JPanel panelPlaylists = new JPanel();
		panelCardLayout.add(panelPlaylists, "panelPlaylists");
		
		JLabel lblpanelPlaylists = new JLabel("PanelPlaylists");
		panelPlaylists.add(lblpanelPlaylists);
		
	}
	

}
