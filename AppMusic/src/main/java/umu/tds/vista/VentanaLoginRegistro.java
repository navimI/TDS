package umu.tds.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import java.awt.Insets;
import javax.swing.JPasswordField;


public class VentanaLoginRegistro {

	private JFrame frmAppmusic;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
					VentanaLoginRegistro window = new VentanaLoginRegistro();
					window.frmAppmusic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		frmAppmusic.setTitle("AppMusic");
		frmAppmusic.setBounds(100, 100, 525, 344);
		frmAppmusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppmusic.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelLogin = new JPanel();
		frmAppmusic.getContentPane().add(panelLogin, "panelLogin");
		panelLogin.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelLogin.add(panel, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMain main = new VentanaMain();
				main.setVisible(true);
				frmAppmusic.setVisible(false);
			}
		});
		panel.add(btnLogin);
		
		JButton btnNewButton = new JButton("Ir a Registro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelRegistro");
				
			}
		});
		panel.add(btnNewButton);
		
		JPanel panelRegistro = new JPanel();
		frmAppmusic.getContentPane().add(panelRegistro, "panelRegistro");
		
		GridBagLayout gbl_panelRegistro = new GridBagLayout();
		gbl_panelRegistro.columnWeights = new double[]{1.0};
		panelRegistro.setLayout(gbl_panelRegistro);
		
		JPanel panelFormulario = new JPanel();
		GridBagConstraints gbc_panelFormulario = new GridBagConstraints();
		gbc_panelFormulario.fill = GridBagConstraints.HORIZONTAL;
		panelRegistro.add(panelFormulario, gbc_panelFormulario);
		
		GridBagLayout gbl_panelFormulario = new GridBagLayout();
		gbl_panelFormulario.columnWidths = new int[]{15, 0, 0, 0, 0, 15, 0};
		gbl_panelFormulario.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelFormulario.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelFormulario.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panelFormulario.setLayout(gbl_panelFormulario);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		panelFormulario.add(lblUsuario, gbc_lblUsuario);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panelFormulario.add(textField, gbc_textField);
		textField.setColumns(15);
		
		JLabel lblClave = new JLabel("Clave:");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.anchor = GridBagConstraints.EAST;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 3;
		gbc_lblClave.gridy = 1;
		panelFormulario.add(lblClave, gbc_lblClave);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(15);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 4;
		gbc_passwordField.gridy = 1;
		panelFormulario.add(passwordField, gbc_passwordField);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		panelFormulario.add(lblNombre, gbc_lblNombre);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		panelFormulario.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 3;
		gbc_lblFecha.gridy = 3;
		panelFormulario.add(lblFecha, gbc_lblFecha);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 4;
		gbc_dateChooser.gridy = 3;
		panelFormulario.add(dateChooser, gbc_dateChooser);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;
		panelFormulario.add(panel_1, gbc_panel_1);
		
		JButton btnRegistrar = new JButton("Registrar");
		panel_1.add(btnRegistrar);
		
		JButton btnIrALogin = new JButton("Ir a Login");
		panel_1.add(btnIrALogin);
	}

}
