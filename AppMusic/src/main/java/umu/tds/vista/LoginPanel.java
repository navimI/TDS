package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import umu.tds.controlador.Controlador;
import umu.tds.utils.LoginGitHub;

public class LoginPanel {

	private static Controlador controlador;
	
	private JPanel panelLogin;
	private JPanel panelFormularioLogin;
	
	private JLabel loginUsuario;
	private JLabel loginPassword;
	
	private JTextField userField;
	private JPasswordField passwordValue;
	
	
	
	public LoginPanel(JFrame frmAppmusic) {
		
		controlador = Controlador.getUnicaInstancia();

		crearPanel(frmAppmusic);

		crearPanelLogo();
		
		crearPanelFormulario();
		
		crearPanelBoton(frmAppmusic);
		
		
	}
	
	private void crearPanel(JFrame frmAppmusic) {
		panelLogin = new JPanel();
		panelLogin.setPreferredSize(new Dimension(400, 300));
		panelLogin.setMinimumSize(new Dimension(300, 200));
		frmAppmusic.getContentPane().add(panelLogin, "panelLogin");
		panelLogin.setLayout(new BorderLayout(0, 0));
	}
	
	private void crearPanelLogo() {
		JPanel panelLogo = new JPanel();
		URL urlLogo = getClass().getResource("/vista/imagenes/logo.png");
		JLabel lblLogolabel = new JLabel();
		lblLogolabel.setIcon(new ImageIcon(urlLogo));
		panelLogo.add(lblLogolabel);
		panelLogin.add(panelLogo, BorderLayout.NORTH); 
	}
	
	private void crearPanelFormulario() {
		//Crea panel para el formulario
		panelFormularioLogin = new JPanel();
		panelFormularioLogin.setAutoscrolls(true);
		GridBagLayout gbl_panelFormularioLogin = new GridBagLayout();
		gbl_panelFormularioLogin.columnWidths = new int[] {10, 100, 50, 20};
		gbl_panelFormularioLogin.rowHeights = new int[] {20, 0, 0, 30};
		gbl_panelFormularioLogin.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_panelFormularioLogin.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		panelFormularioLogin.setLayout(gbl_panelFormularioLogin);
		panelLogin.add(panelFormularioLogin, BorderLayout.CENTER);
		
		//Campos Usuario
		loginUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_loginUsuario = new GridBagConstraints();
		gbc_loginUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_loginUsuario.gridx = 1;
		gbc_loginUsuario.gridy = 1;
		panelFormularioLogin.add(loginUsuario, gbc_loginUsuario);
		
		userField = new JTextField();
		GridBagConstraints gbc_userValue = new GridBagConstraints();
		gbc_userValue.insets = new Insets(0, 0, 5, 5);
		gbc_userValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_userValue.gridx = 2;
		gbc_userValue.gridy = 1;
		panelFormularioLogin.add(userField, gbc_userValue);
		userField.setColumns(10);
		
		//Campos Password
		loginPassword = new JLabel("Password");
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
	}
	
	private void crearPanelBoton(JFrame frmAppmusic) {
		JPanel botonPanel = new JPanel();
		panelLogin.add(botonPanel, BorderLayout.SOUTH);

		// Manejador del boton de login 
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean login = controlador.loginUsuario(userField.getText(), 
						new String(passwordValue.getPassword()));
				if(login) {
					JOptionPane.showMessageDialog(frmAppmusic, "Bienvenido "+userField.getText(),"Login Correcto",JOptionPane.INFORMATION_MESSAGE);
					VentanaMain main = new VentanaMain();
					main.setVisible(true);
					frmAppmusic.setVisible(false);
				} else
					JOptionPane.showMessageDialog(frmAppmusic, "Nombre de usuario o contraseña no valido","Error",JOptionPane.ERROR_MESSAGE);
			}
		});
		botonPanel.add(btnLogin);
		
		// Manejador del boton de registro
		JButton btnRegistro = new JButton("Ir a Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelRegistro");
				
			}
		});
		botonPanel.add(btnRegistro);
		
		// Manejador del boton de login con GitHub
		JButton btnGH = new JButton("GitHub Login");
		btnGH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Abrir selector de ficheros y cargar fichero con Key
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileFilter() {
					public String getDescription() {
						return "GitHub Properties File (*.properties)";
					}

					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".properties");
						}
					}
				});
				fileChooser.setAcceptAllFileFilterUsed(false);
				File workingDirectory = new File(System.getProperty("user.dir"));
				fileChooser.setCurrentDirectory(workingDirectory);
				int result = fileChooser.showOpenDialog(frmAppmusic);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
					//Comprueba si el fichero es valido la clave de Gh o no
					if (LoginGitHub.INSTANCE.verificar(userField.getText(), selectedFile.getAbsolutePath())) {
						boolean registred = controlador.esUsuarioRegistrado(userField.getText());
						if(registred) {
							controlador.setUsuarioActual(userField.getText());
							JOptionPane.showMessageDialog(frmAppmusic, "Bienvenido "+userField.getText(),"Login Correcto",JOptionPane.INFORMATION_MESSAGE);
							VentanaMain main = new VentanaMain();
							main.setVisible(true);
							frmAppmusic.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(frmAppmusic, "Usuario no registrado", "Login",
									JOptionPane.INFORMATION_MESSAGE);
							controlador.setUsuarioTemporal(userField.getText());
							CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
							card.show(frmAppmusic.getContentPane(), "panelRegistroGH");
						}
						
					} else {
						
						JOptionPane.showMessageDialog(frmAppmusic, "Login Fallido, clave de GH no corresponde a usuario", "Login", JOptionPane.WARNING_MESSAGE);
						
						
					}
				}
			}
				
				  
				 	
				
			
		});
		botonPanel.add(btnGH);
	}
	
}
