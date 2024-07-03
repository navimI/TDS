package umu.tds.vista.Login;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import umu.tds.controlador.Controlador;

/**
 * Panel que contiene los campos de registro de la aplicación.
 * <p>
 * Se encarga de gestionar el registro de los usuarios que acceden a la aplicación.
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see VentanaLoginRegistro
 * @see Controlador
 */
public class PanelRegistro   {
	
	private JPanel panelRegistro;
	private JPanel panelFormulario;
	
	private JLabel lblUsuario;
	private JLabel lblPass;
	private JLabel lblNombre;
	private JLabel lblEmail;
	private JLabel lblFecha;
	private JTextField userField;
	private JPasswordField passwordField;
	private JTextField nombreField;
	private JTextField emailField;
	private JDateChooser dateField;
	
	private SimpleDateFormat dateFormat;

	/**
	 * Crea el panel de registro.
	 * <p>
	 * Se encarga de crear los paneles y campos necesarios para el registro de un usuario.
	 * @param frmLogin Ventana de login y registro.
	 */
	public PanelRegistro(VentanaLoginRegistro frmLogin) {
		
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		crearPanel(frmLogin);

		crearPanelLogo();
		
		crearPanelFormulario();
		
		crearPanelBoton(frmLogin);
		
	}
	
	/**
	 * Crea el panel de registro.
	 * <p>
	 * Se encarga de crear el panel de registro y establecer el layout.
	 * @param frmLogin Ventana de login y registro.
	 */
	private void crearPanel(VentanaLoginRegistro frmLogin) {
		panelRegistro = new JPanel();
		frmLogin.getContentPane().add(panelRegistro, "panelRegistro");
		
		GridBagLayout gbl_panelRegistro = new GridBagLayout();
		gbl_panelRegistro.columnWeights = new double[]{1.0};
		panelRegistro.setLayout(gbl_panelRegistro);
	}
	
	/**
	 * Crea el panel de registro.
	 * <p>
	 * Se encarga de crear el panel de logo y añadirlo al panel de registro.
	 */
	private void crearPanelLogo() {
		JLabel lblLogoReg = new JLabel();
		URL urlLogo = getClass().getResource("/vista/imagenes/logo.png");
		lblLogoReg.setIcon(new ImageIcon(urlLogo));
		GridBagConstraints gbc_panelRegistro = new GridBagConstraints();
		gbc_panelRegistro.insets = new Insets(0, 0, 5, 0);
		gbc_panelRegistro.gridx = 0;
		gbc_panelRegistro.gridy = 0;
		panelRegistro.add(lblLogoReg, gbc_panelRegistro);
	}	
	
	/**
	 * Crea el panel de registro.
	 * <p>
	 * Se encarga de crear el panel de formulario y añadirlo al panel de registro.
	 */
	private void crearPanelFormulario() {
		panelFormulario = new JPanel();
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
		
		//Campos Usuario
		
		lblUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		panelFormulario.add(lblUsuario, gbc_lblUsuario);
		
		userField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panelFormulario.add(userField, gbc_textField);
		userField.setColumns(15);
		
		//Campos Password
		
		lblPass = new JLabel("Password:");
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
		
		
		//Campos nombre
		
		lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		panelFormulario.add(lblNombre, gbc_lblNombre);
		
		nombreField = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		panelFormulario.add(nombreField, gbc_textField_1);
		nombreField.setColumns(10);
		
		//Campos Email
		
		lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panelFormulario.add(lblEmail, gbc_lblNewLabel);
		
		emailField = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		panelFormulario.add(emailField, gbc_textField_2);
		emailField.setColumns(10);
		
		//Campos fecha
		
		lblFecha = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 3;
		gbc_lblFecha.gridy = 3;
		panelFormulario.add(lblFecha, gbc_lblFecha);
		
		dateField = new JDateChooser();
		dateField.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 4;
		gbc_dateChooser.gridy = 3;
		panelFormulario.add(dateField, gbc_dateChooser);
		
		
	}
	
	/**
	 * Crea el panel de registro.
	 * <p>
	 * Se encarga de crear el panel de botones y añadirlo al panel de registro.
	 * <p>
	 * Los listeners de los botones se encargan de comprobar los campos y registrar al usuario.
	 * @param frmLogin Ventana de login y registro.
	 */
	private void crearPanelBoton(VentanaLoginRegistro frmLogin) {
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
				String fieldsStatus = checkFields();
				
				if (fieldsStatus == "OK") {
					boolean registrado = false;
					registrado = Controlador.getUnicaInstancia().registrarUsuario(nombreField.getText(),
							emailField.getText(), userField.getText(),
							new String(passwordField.getPassword()), 
							dateFormat.format(dateField.getDate()));
					if (registrado) {
						JOptionPane.showMessageDialog(frmLogin, "Usuario registrado correctamente.", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
								emptyFields();
								frmLogin.lanzarVentanaMain();
					} else {
						JOptionPane.showMessageDialog(frmLogin, "El usuario ya existe.\n",
								"Registro", JOptionPane.ERROR_MESSAGE);
						frmLogin.setTitle("Registro Erroneo");
						lblUsuario.setForeground(Color.RED);
						userField.setBackground(Color.RED);
						userField.setFont(userField.getFont().deriveFont(Font.BOLD));
					}
				} else {
					JOptionPane.showMessageDialog(frmLogin, fieldsStatus,
							"Registro", JOptionPane.ERROR_MESSAGE);
					frmLogin.setTitle("Registro Erroneo");
				}
				
			}
		});
		
		JButton btnIrALogin = new JButton("Ir a Login");
		panel_1.add(btnIrALogin);
		btnIrALogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetFields();
				emptyFields();
				CardLayout card = (CardLayout) frmLogin.getContentPane().getLayout();
				card.show(frmLogin.getContentPane(), "panelLogin");
				
			}
		});
		
	}
	
	//--------------------------Auxiliares--------------------------
	
	/**
	 * Comprueba los campos del formulario.
	 * <p>
	 * Si los campos no son válidos, se resaltan en rojo y se muestra un mensaje de error.
	 * @return String con el estado de los campos.
	 */
	private String checkFields() { 
			  
		String status = "";
		resetFields();

		if (!isValidUsername(userField.getText())) {
			lblUsuario.setForeground(Color.RED);
			userField.setBackground(Color.RED);
			status = status + "El usuario no es válido.\n";
		}
		if (!isValidPassword(new String(passwordField.getPassword()))) {
			lblPass.setForeground(Color.RED);
			passwordField.setBackground(Color.RED);
			status = status + "La contraseña no es válida, minimo 7 caracteres.\n";
		}
		if (!isValidName(nombreField.getText())) {
			lblNombre.setForeground(Color.RED);
			nombreField.setBackground(Color.RED);
			status = status + "El nombre no es válido.\n";
		}
		if (!isValidEmail(emailField.getText())) {
			lblEmail.setForeground(Color.RED);
			emailField.setBackground(Color.RED);
			status = status + "El email no es válido.\n";
		}
		if (dateField.getDate() == null) {
			lblFecha.setForeground(Color.RED);
			dateField.setBackground(Color.RED);
			status = status + "No se ha introducido una fecha.\n";
		}
		else if (!isValidDate(dateField.getDate())) {
			lblFecha.setForeground(Color.RED);
			dateField.setBackground(Color.RED);
			status = status + "La fecha no puede ser posterior a la fecha actual.\n";
		}
		return status.equals("") ? "OK" : status;
	}
	
	/**
	 * Resetea los campos del formulario.
	 * <p>
	 * Deja los elementos JLabel, los campos donde se rellena información y la font por defecto.
	 */
	private void resetFields() {
		//Deja los elementos JLabel, los campos donde se rellena información y la font por defecto
		lblUsuario.setForeground(Color.BLACK);
		userField.setBackground(Color.WHITE);
		userField.setFont(userField.getFont().deriveFont(Font.PLAIN));
		
		lblPass.setForeground(Color.BLACK);
		passwordField.setBackground(Color.WHITE);
		
		lblNombre.setForeground(Color.BLACK);
		nombreField.setBackground(Color.WHITE);
		
		lblEmail.setForeground(Color.BLACK);
		emailField.setBackground(Color.WHITE);
		
		lblFecha.setForeground(Color.BLACK);
		dateField.setBackground(Color.WHITE);
	}

	/**
	 * Resetea los campos del formulario.
	 * <p>
	 * Deja los campos de texto vacíos.
	 */
	private void emptyFields() {
		userField.setText("");
		passwordField.setText("");
		nombreField.setText("");
		emailField.setText("");
		dateField.setDate(null);
	}
		  
	/**
	 * Comprueba si el nombre introducido es válido.
	 * @param input Nombre introducido.
	 * @return true si el nombre es válido, false en caso contrario.
	 */
	private boolean isValidUsername(String input) {
		String regex = "^[a-zA-Z0-9\\s-_]+$";
		return input.matches(regex);
	}
	
	/**
	 * Comprueba si la contraseña introducida es válida.
	 * @param input Contraseña introducida.
	 * @return true si la contraseña es válida, false en caso contrario.
	 */
	private boolean isValidPassword(String input) {
		return input.length() > 6;
	}

	/**
	 * Comprueba si el nombre introducido es válido.
	 * @param input Nombre introducido.
	 * @return true si el nombre es válido, false en caso contrario.
	 */
	private boolean isValidName(String input) {
		String regex = "^[a-zA-Z\\s]+$";
		return input.matches(regex);
	}
	
	/**
	 * Comprueba si el email introducido es válido.
	 * @param input Email introducido.
	 * @return true si el email es válido, false en caso contrario.
	 */
	private boolean isValidEmail(String input) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return input.matches(regex);
	}

	/**
	 * Comprueba si la fecha introducida es válida.
	 * @param input Fecha introducida.
	 * @return true si la fecha es válida, false en caso contrario.
	 */
	private boolean isValidDate(Date input) {
		return input.before(new Date(System.currentTimeMillis()));
		
	}
}
