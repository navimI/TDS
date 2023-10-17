package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMain extends JFrame {

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
		
		
		JPanel panelCardLayout = new JPanel();

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelBuscar");
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/lupa.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panelBotonera.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gestion Playlists");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelGestion");
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/mas.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		panelBotonera.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Recientes");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/reloj.png")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 2;
		panelBotonera.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Mis Playlists");
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/altavoz.png")));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 3;
		panelBotonera.add(btnNewButton_3, gbc_btnNewButton_3);
		
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
		
		JLabel lblPanelbuscar = new JLabel("PanelBuscar");
		panelBuscar.add(lblPanelbuscar);
		
		JPanel panelGestion = new JPanel();
		panelCardLayout.add(panelGestion, "panelGestion");
		
		JLabel lblPanelgestion = new JLabel("PanelGestion");
		panelGestion.add(lblPanelgestion);
		
		JPanel panelRecientes = new JPanel();
		panelCardLayout.add(panelRecientes, "panelRecientes");
		
		JPanel panelPlaylists = new JPanel();
		panelCardLayout.add(panelPlaylists, "panelPlaylists");
	}

}
