package umu.tds.vista.Login;

import java.awt.Color;
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
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import umu.tds.controlador.Controlador;

/**
 * Panel que contiene los campos de registro de la aplicación.
 * <p>
 * Se encarga de gestionar el registro de los usuarios que acceden a la aplicación con un fichero de propiedades de GitHub.
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see VentanaLoginRegistro
 * @see Controlador
*/

public class PanelRegistroGH {
	
	private JPanel panelRegistroGH;

	private JPanel panelFormularioGH;
	
	private JLabel lblNombreGH;
	private JLabel lblEmailGH;
	private JLabel lblFechaGH;

	private JTextField fieldNombreGH;
	private JTextField fieldEmailGH;
	private JDateChooser dateChooserGH;
	
	private JButton btnLoginGH;
	
	private SimpleDateFormat dateFormat;
	
	/**
	 * Crea el panel de registro.
	 * <p>
	 * Se encarga de crear los paneles y campos necesarios para el registro de un usuario por GitHub.
	 * @param frmLogin Ventana de login y registro.
	 */
	public PanelRegistroGH(VentanaLoginRegistro frmLogin) {
		
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		crearPanel(frmLogin);
		
		crearPanelLogo();
		
		crearPanelFormulario();
		
		crearPanelBoton(frmLogin);
		
	}
	
	/**
	 * Crea el panel de registro.
	 * <p>
	 * Se encarga de crear los paneles y el layout del panel de registro.
	 * @param frmLogin Ventana de login y registro.
	 */
	private void crearPanel(VentanaLoginRegistro frmLogin) {
		panelRegistroGH = new JPanel();
		frmLogin.getContentPane().add(panelRegistroGH, "panelRegistroGH");
		
		GridBagLayout gbl_panelRegistroGH = new GridBagLayout();
		gbl_panelRegistroGH.columnWidths = new int[] {0, 0, 0};
		gbl_panelRegistroGH.columnWeights = new double[]{0.0, 1.0};
		panelRegistroGH.setLayout(gbl_panelRegistroGH);
	}
	
	/**
	 * Crea el panel de logo.
	 * <p>
	 * Se encarga de añadir el logo de la aplicación al panel.
	 */
	private void crearPanelLogo() {
		JLabel lblLogoRegGH = new JLabel();
		URL urlLogo = getClass().getResource("/vista/imagenes/logo.png");
		lblLogoRegGH.setIcon(new ImageIcon(urlLogo));
		GridBagConstraints gbc_LogoRegGH = new GridBagConstraints();
		gbc_LogoRegGH.insets = new Insets(0, 0, 5, 5);
		gbc_LogoRegGH.gridx = 1;
		gbc_LogoRegGH.gridy = 0;
		panelRegistroGH.add(lblLogoRegGH, gbc_LogoRegGH);
	}

	/**
	 * Crea el panel de formulario.
	 * <p>
	 * Se encarga de añadir los campos necesarios para el registro de un usuario.
	 */
	private void crearPanelFormulario() {
		panelFormularioGH = new JPanel();
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
		
		lblNombreGH = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombreGH = new GridBagConstraints();
		gbc_lblNombreGH.anchor = GridBagConstraints.EAST;
		gbc_lblNombreGH.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreGH.gridx = 0;
		gbc_lblNombreGH.gridy = 0;
		panelFormularioGH.add(lblNombreGH, gbc_lblNombreGH);
		
		fieldNombreGH = new JTextField();
		GridBagConstraints gbc_fieldNombreGH = new GridBagConstraints();
		gbc_fieldNombreGH.gridwidth = 4;
		gbc_fieldNombreGH.insets = new Insets(0, 0, 5, 0);
		gbc_fieldNombreGH.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldNombreGH.gridx = 1;
		gbc_fieldNombreGH.gridy = 0;
		panelFormularioGH.add(fieldNombreGH, gbc_fieldNombreGH);
		fieldNombreGH.setColumns(10);
		
		lblEmailGH = new JLabel("Email:");
		GridBagConstraints gbc_lblEmailGH = new GridBagConstraints();
		gbc_lblEmailGH.anchor = GridBagConstraints.EAST;
		gbc_lblEmailGH.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailGH.gridx = 0;
		gbc_lblEmailGH.gridy = 1;
		panelFormularioGH.add(lblEmailGH, gbc_lblEmailGH);
		
		fieldEmailGH = new JTextField();
		GridBagConstraints gbc_fieldEmailGH = new GridBagConstraints();
		gbc_fieldEmailGH.insets = new Insets(0, 0, 5, 5);
		gbc_fieldEmailGH.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldEmailGH.gridx = 1;
		gbc_fieldEmailGH.gridy = 1;
		panelFormularioGH.add(fieldEmailGH, gbc_fieldEmailGH);
		fieldEmailGH.setColumns(10);
		
		lblFechaGH = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFechaGH = new GridBagConstraints();
		gbc_lblFechaGH.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaGH.anchor = GridBagConstraints.EAST;
		gbc_lblFechaGH.gridx = 3;
		gbc_lblFechaGH.gridy = 1;
		panelFormularioGH.add(lblFechaGH, gbc_lblFechaGH);
		
		dateChooserGH = new JDateChooser();
		dateChooserGH.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooserGH = new GridBagConstraints();
		gbc_dateChooserGH.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooserGH.fill = GridBagConstraints.BOTH;
		gbc_dateChooserGH.gridx = 4;
		gbc_dateChooserGH.gridy = 1;
		panelFormularioGH.add(dateChooserGH, gbc_dateChooserGH);
	}
	
	/**
	 * Crea el panel de botón.
	 * <p>
	 * Se encarga de añadir el botón de registro al panel.
	 * @param frmLogin Ventana de login y registro.
	 */
	private void crearPanelBoton(VentanaLoginRegistro frmLogin) {
		btnLoginGH = new JButton("Login Con GitHub");
		GridBagConstraints gbc_btnLoginGH = new GridBagConstraints();
		gbc_btnLoginGH.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoginGH.gridx = 1;
		gbc_btnLoginGH.gridy = 2;
		btnLoginGH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				String fieldsStatus = checkFields();
				String usuario = Controlador.getUnicaInstancia().getUsuarioTemporal();
				
				if (fieldsStatus == "OK") {
					boolean registrado = false;
					registrado = Controlador.getUnicaInstancia().registrarUsuario(fieldNombreGH.getText(),
							fieldEmailGH.getText(), usuario, "",
							dateFormat.format(dateChooserGH.getDate()));
					if (registrado) {
						JOptionPane.showMessageDialog(frmLogin, "Usuario registrado correctamente.", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
						
								frmLogin.lanzarVentanaMain();
					} 
				} else {
					JOptionPane.showMessageDialog(frmLogin, fieldsStatus,
							"Registro", JOptionPane.ERROR_MESSAGE);
					frmLogin.setTitle("Registro Erroneo");
				}
				
			}
			
		});
		panelRegistroGH.add(btnLoginGH, gbc_btnLoginGH);
	}
	
	
	//--------------------------Auxiliares--------------------------
	
	/**
	 * Comprueba que los campos introducidos son válidos.
	 * <p>
	 * Si los campos no son válidos, se resaltan en rojo y se muestra un mensaje de error.
	 * @return String con el estado de los campos.
	 */
	private String checkFields() { 
		  
		String status = "";
		resetFields();

		if (!isValidName(fieldNombreGH.getText())) {
			lblNombreGH.setForeground(Color.RED);
			fieldNombreGH.setBackground(Color.RED);
			status = status + "El nombre no es válido.\n";
		}
		if (!isValidEmail(fieldEmailGH.getText())) {
			lblEmailGH.setForeground(Color.RED);
			fieldEmailGH.setBackground(Color.RED);
			status = status + "El email no es válido.\n";
		}
		if (dateChooserGH.getDate() == null) {
			lblFechaGH.setForeground(Color.RED);
			dateChooserGH.setBackground(Color.RED);
			status = status + "No se ha introducido una fecha.\n";
		}
		else if (!isValidDate(dateChooserGH.getDate())) {
			lblFechaGH.setForeground(Color.RED);
			dateChooserGH.setBackground(Color.RED);
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
				
		lblNombreGH.setForeground(Color.BLACK);
		fieldNombreGH.setBackground(Color.WHITE);
		
		lblEmailGH.setForeground(Color.BLACK);
		fieldEmailGH.setBackground(Color.WHITE);
		
		lblFechaGH.setForeground(Color.BLACK);
		dateChooserGH.setBackground(Color.WHITE);
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
