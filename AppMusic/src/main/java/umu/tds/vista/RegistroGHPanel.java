package umu.tds.vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class RegistroGHPanel {
	
	private JPanel panelRegistroGH;
	
	private JTextField textField;
	private JTextField fieldEmailGH;
	private JTextField fieldNombreGH;
	private JPanel panelFormularioGH;
	
	private JLabel lblUsuarioGH;
	private JLabel lblEmailGH;
	private JLabel lblNombreGH;
	private JDateChooser dateChooserGH;
	private JButton btnLoginGH;
	
	
	public RegistroGHPanel(JFrame frmAppmusic) {
		
		crearPanel(frmAppmusic);
		
		crearPanelLogo();
		
		crearPanelFormulario();
		
		crearPanelBoton(frmAppmusic);
		
	}
	
	private void crearPanel(JFrame frmAppmusic) {
		panelRegistroGH = new JPanel();
		frmAppmusic.getContentPane().add(panelRegistroGH, "panelRegistroGH");
		
		GridBagLayout gbl_panelRegistroGH = new GridBagLayout();
		gbl_panelRegistroGH.columnWidths = new int[] {0, 0, 0};
		gbl_panelRegistroGH.columnWeights = new double[]{0.0, 1.0};
		panelRegistroGH.setLayout(gbl_panelRegistroGH);
	}
	
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
		
		lblUsuarioGH = new JLabel("Usuario:");
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
		
		lblEmailGH = new JLabel("Email:");
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
		
		lblNombreGH = new JLabel("Nombre:");
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
		
		dateChooserGH = new JDateChooser();
		dateChooserGH.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooserGH = new GridBagConstraints();
		gbc_dateChooserGH.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooserGH.fill = GridBagConstraints.BOTH;
		gbc_dateChooserGH.gridx = 4;
		gbc_dateChooserGH.gridy = 1;
		panelFormularioGH.add(dateChooserGH, gbc_dateChooserGH);
	}
	
	private void crearPanelBoton(JFrame frmAppmusic) {
		btnLoginGH = new JButton("Login Con GitHub");
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
