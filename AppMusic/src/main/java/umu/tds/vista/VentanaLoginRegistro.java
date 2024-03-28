package umu.tds.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import java.awt.Insets;
import javax.swing.JPasswordField;
import java.awt.Dimension;


public class VentanaLoginRegistro {

	private JFrame frmAppmusic;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField userValue;
	private JPasswordField passwordValue;
	private JTextField textField_2;
	private JTextField fieldEmailGH;
	private JTextField fieldNombreGH;
	private JTextField textField_5;

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
		
		JPanel panelLogin = new JPanel();
		panelLogin.setPreferredSize(new Dimension(400, 300));
		panelLogin.setMinimumSize(new Dimension(300, 200));
		frmAppmusic.getContentPane().add(panelLogin, "panelLogin");
		panelLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelLogo = new JPanel();
		URL urlLogo = getClass().getResource("/vista/imagenes/logo.png");
		System.out.println(urlLogo);
		JLabel lblLogolabel = new JLabel();
		lblLogolabel.setIcon(new ImageIcon(urlLogo));
		panelLogo.add(lblLogolabel);
		panelLogin.add(panelLogo, BorderLayout.NORTH); 
		
		
		JPanel panelFormularioLogin = new JPanel();
		panelFormularioLogin.setAutoscrolls(true);
		GridBagLayout gbl_panelFormularioLogin = new GridBagLayout();
		gbl_panelFormularioLogin.columnWidths = new int[] {10, 100, 50, 20};
		gbl_panelFormularioLogin.rowHeights = new int[] {20, 0, 0, 30};
		gbl_panelFormularioLogin.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_panelFormularioLogin.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		panelFormularioLogin.setLayout(gbl_panelFormularioLogin);
		panelLogin.add(panelFormularioLogin, BorderLayout.CENTER);
		
		JLabel loginUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_loginUsuario = new GridBagConstraints();
		gbc_loginUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_loginUsuario.gridx = 1;
		gbc_loginUsuario.gridy = 1;
		panelFormularioLogin.add(loginUsuario, gbc_loginUsuario);
		
		userValue = new JTextField();
		GridBagConstraints gbc_userValue = new GridBagConstraints();
		gbc_userValue.insets = new Insets(0, 0, 5, 5);
		gbc_userValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_userValue.gridx = 2;
		gbc_userValue.gridy = 1;
		panelFormularioLogin.add(userValue, gbc_userValue);
		userValue.setColumns(10);
		
		JLabel loginPassword = new JLabel("Password");
		GridBagConstraints gbc_loginPassword = new GridBagConstraints();
		gbc_loginPassword.insets = new Insets(0, 0, 5, 5);
		gbc_loginPassword.gridx = 1;
		gbc_loginPassword.gridy = 2;
		panelFormularioLogin.add(loginPassword, gbc_loginPassword);
		
		passwordValue = new JPasswordField();
		GridBagConstraints gbc_passwordValue = new GridBagConstraints();
		gbc_passwordValue.insets = new Insets(0, 0, 5, 5);
		gbc_passwordValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordValue.gridx = 2;
		gbc_passwordValue.gridy = 2;
		panelFormularioLogin.add(passwordValue, gbc_passwordValue);
		

		JPanel botonPanel = new JPanel();
		panelLogin.add(botonPanel, BorderLayout.SOUTH);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMain main = new VentanaMain();
				main.setVisible(true);
				frmAppmusic.setVisible(false);
			}
		});
		botonPanel.add(btnLogin);
		
		JButton btnRegistro = new JButton("Ir a Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelRegistro");
				
			}
		});
		botonPanel.add(btnRegistro);
		
		JButton btnGH = new JButton("GitHub Login");
		btnGH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelRegistroGH");
				
			}
		});
		botonPanel.add(btnGH);
		
		JPanel panelRegistro = new JPanel();
		frmAppmusic.getContentPane().add(panelRegistro, "panelRegistro");
		
		GridBagLayout gbl_panelRegistro = new GridBagLayout();
		gbl_panelRegistro.columnWeights = new double[]{1.0};
		panelRegistro.setLayout(gbl_panelRegistro);
		
		JLabel lblLogoReg = new JLabel();
		lblLogoReg.setIcon(new ImageIcon(urlLogo));
		GridBagConstraints gbc_panelRegistro = new GridBagConstraints();
		gbc_panelRegistro.insets = new Insets(0, 0, 5, 0);
		gbc_panelRegistro.gridx = 0;
		gbc_panelRegistro.gridy = 0;
		panelRegistro.add(lblLogoReg, gbc_panelRegistro);
		
		JPanel panelFormulario = new JPanel();
		GridBagConstraints gbc_panelFormulario = new GridBagConstraints();
		gbc_panelFormulario.gridx = 0;
		gbc_panelFormulario.gridy = 1;
		gbc_panelFormulario.fill = GridBagConstraints.HORIZONTAL;
		panelRegistro.add(panelFormulario, gbc_panelFormulario);
		
		GridBagLayout gbl_panelFormulario = new GridBagLayout();
		gbl_panelFormulario.columnWidths = new int[]{10, 0, 0, 0, 0, 15, 0};
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
		
		JLabel lblPass = new JLabel("Password:");
		GridBagConstraints gbc_lblPass = new GridBagConstraints();
		gbc_lblPass.anchor = GridBagConstraints.EAST;
		gbc_lblPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPass.gridx = 3;
		gbc_lblPass.gridy = 1;
		panelFormulario.add(lblPass, gbc_lblPass);
		
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
		
		JLabel lblNewLabel = new JLabel("Email:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panelFormulario.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		panelFormulario.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
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
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMain main = new VentanaMain();
				main.setVisible(true);
				frmAppmusic.setVisible(false);
			}
		});
		
		JButton btnIrALogin = new JButton("Ir a Login");
		panel_1.add(btnIrALogin);
		btnIrALogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelLogin");
				
			}
		});
		
		JPanel panelRegistroGH = new JPanel();
		frmAppmusic.getContentPane().add(panelRegistroGH, "panelRegistroGH");
		
		GridBagLayout gbl_panelRegistroGH = new GridBagLayout();
		gbl_panelRegistroGH.columnWidths = new int[] {0, 0, 0};
		gbl_panelRegistroGH.columnWeights = new double[]{0.0, 1.0};
		panelRegistroGH.setLayout(gbl_panelRegistroGH);
		
		JLabel lblLogoRegGH = new JLabel();
		lblLogoRegGH.setIcon(new ImageIcon(urlLogo));
		GridBagConstraints gbc_LogoRegGH = new GridBagConstraints();
		gbc_LogoRegGH.insets = new Insets(0, 0, 5, 5);
		gbc_LogoRegGH.gridx = 1;
		gbc_LogoRegGH.gridy = 0;
		panelRegistroGH.add(lblLogoRegGH, gbc_LogoRegGH);
		
		JPanel panelFormularioGH = new JPanel();
		GridBagConstraints gbc_panelFormularioGH = new GridBagConstraints();
		gbc_panelFormularioGH.insets = new Insets(0, 0, 5, 5);
		gbc_panelFormularioGH.gridx = 1;
		gbc_panelFormularioGH.gridy = 1;
		gbc_panelFormularioGH.fill = GridBagConstraints.HORIZONTAL;
		panelRegistroGH.add(panelFormularioGH, gbc_panelFormularioGH);
		
		GridBagLayout gbl_panelFormularioGH = new GridBagLayout();
		gbl_panelFormularioGH.columnWidths = new int[] {0, 30, 0, 15, 0};
		gbl_panelFormularioGH.rowHeights = new int[] {0, 0, 0, 0};
		gbl_panelFormularioGH.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0};
		gbl_panelFormularioGH.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0};
		panelFormularioGH.setLayout(gbl_panelFormularioGH);
		
		JLabel lblUsuarioGH = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuarioGH = new GridBagConstraints();
		gbc_lblUsuarioGH.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuarioGH.anchor = GridBagConstraints.EAST;
		gbc_lblUsuarioGH.gridx = 0;
		gbc_lblUsuarioGH.gridy = 0;
		panelFormularioGH.add(lblUsuarioGH, gbc_lblUsuarioGH);
		
		textField = new JTextField();
		GridBagConstraints gbc_textFieldGH = new GridBagConstraints();
		gbc_textFieldGH.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldGH.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGH.gridx = 1;
		gbc_textFieldGH.gridy = 0;
		panelFormularioGH.add(textField, gbc_textFieldGH);
		textField.setColumns(15);
		
		JLabel lblEmailGH = new JLabel("Email:");
		GridBagConstraints gbc_lblEmailGH = new GridBagConstraints();
		gbc_lblEmailGH.anchor = GridBagConstraints.EAST;
		gbc_lblEmailGH.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailGH.gridx = 3;
		gbc_lblEmailGH.gridy = 0;
		panelFormularioGH.add(lblEmailGH, gbc_lblEmailGH);
		
		fieldEmailGH = new JTextField();
		GridBagConstraints gbc_fieldEmailGH = new GridBagConstraints();
		gbc_fieldEmailGH.insets = new Insets(0, 0, 5, 0);
		gbc_fieldEmailGH.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldEmailGH.gridx = 4;
		gbc_fieldEmailGH.gridy = 0;
		panelFormularioGH.add(fieldEmailGH, gbc_fieldEmailGH);
		fieldEmailGH.setColumns(10);
		
		JLabel lblNombreGH = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombreGH = new GridBagConstraints();
		gbc_lblNombreGH.anchor = GridBagConstraints.EAST;
		gbc_lblNombreGH.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreGH.gridx = 0;
		gbc_lblNombreGH.gridy = 1;
		panelFormularioGH.add(lblNombreGH, gbc_lblNombreGH);
		
		fieldNombreGH = new JTextField();
		GridBagConstraints gbc_fieldNombreGH = new GridBagConstraints();
		gbc_fieldNombreGH.insets = new Insets(0, 0, 5, 5);
		gbc_fieldNombreGH.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldNombreGH.gridx = 1;
		gbc_fieldNombreGH.gridy = 1;
		panelFormularioGH.add(fieldNombreGH, gbc_fieldNombreGH);
		fieldNombreGH.setColumns(10);
		
		JLabel lblFechaGH = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFechaGH = new GridBagConstraints();
		gbc_lblFechaGH.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaGH.anchor = GridBagConstraints.EAST;
		gbc_lblFechaGH.gridx = 3;
		gbc_lblFechaGH.gridy = 1;
		panelFormularioGH.add(lblFechaGH, gbc_lblFechaGH);
		
		JDateChooser dateChooserGH = new JDateChooser();
		dateChooserGH.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooserGH = new GridBagConstraints();
		gbc_dateChooserGH.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooserGH.fill = GridBagConstraints.BOTH;
		gbc_dateChooserGH.gridx = 4;
		gbc_dateChooserGH.gridy = 1;
		panelFormularioGH.add(dateChooserGH, gbc_dateChooserGH);
		
		JButton btnLoginGH = new JButton("Login Con GitHub");
		GridBagConstraints gbc_btnLoginGH = new GridBagConstraints();
		gbc_btnLoginGH.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoginGH.gridx = 1;
		gbc_btnLoginGH.gridy = 2;
		btnLoginGH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMain main = new VentanaMain();
				main.setVisible(true);
				frmAppmusic.setVisible(false);
			}
		});
		panelRegistroGH.add(btnLoginGH, gbc_btnLoginGH);
	}

}