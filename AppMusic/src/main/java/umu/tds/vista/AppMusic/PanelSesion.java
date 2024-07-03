package umu.tds.vista.AppMusic;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import umu.tds.controlador.Controlador;
import umu.tds.vista.Login.VentanaLoginRegistro;

/**
 * Panel que contiene los botones de las distintas opciones de sesion de usuario
 * de la aplicación.
 * <p>
 * Se encarga de gestionar los eventos de los botones de la sesión.
 * <p>
 * El panel es el encargado de gestionar el premium del usuario y de cerrar la
 * sesión.
 * 
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see Controlador
 * @see VentanaAppMusic
 * @see PanelMenu
 */
public class PanelSesion extends JPanel {

    private Controlador controlador;

    private JButton btnPremium;
    private JButton btnSalir;
    private JLabel lblUsuario;

    private VentanaAppMusic frame;

    /**
     * Constructor de la clase PanelSesion.
     * <p>
     * Se encarga de inicializar el panel de sesión, de crear los botones y de
     * añadir los listeners a los botones.
     * @param frame Ventana de la clase VentanaAppMusic
     * @param controlador Controlador de la aplicacion
     */
    public PanelSesion(VentanaAppMusic frame,Controlador controlador) {
        this.frame = frame;
        this.controlador = controlador;
        crearPanel();
        crearBotones();
        addBotonListeners();

    }

    /**
     * Crea el panel de la sesión.
     * <p>
     * Se encarga de establecer el layout del panel.
     */
    private void crearPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    }

    /**
     * Crea los botones de la sesión.
     * <p>
     * Se encarga de crear los botones de la sesión y de añadirlos al panel.
     */
    private void crearBotones() {
        lblUsuario = new JLabel();
        lblUsuario.setText("Usuario: " + controlador.getNombreUsuarioActual());
        add(lblUsuario);

        btnPremium = new JButton();
        btnPremium.setText(getPremiumString());
        add(btnPremium);

        btnSalir = new JButton();
        btnSalir.setText("Salir");
        add(btnSalir);
    }

    /**
     * Añade los listeners a los botones de la sesión.
     * <p>
     * Se encarga de añadir los listeners a los botones de la sesión.
     * <p>
     * El botón de premium se encarga de activar o desactivar la cuenta premium del
     * usuario.
     * <p>
     * El botón de salir se encarga de cerrar la sesión del usuario.
     */
    private void addBotonListeners() {
        btnPremium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBotonPremium();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accionBotonSalir();
            }
        });
    }

    // -------- Metodos auxiliares ------------

    
    /**
     * Acción del botón de premium.
     * <p>
     * Se encarga de activar o desactivar la cuenta premium del usuario.
     */
    private void accionBotonPremium() {
        if (controlador.esUsuarioActualPremium()) {
            controlador.desactivarPremium();
            frame.switchCardLayout("panelBuscar");
            btnPremium.setText(getPremiumString());
            JOptionPane.showMessageDialog(this, "Funcionalidad Premium desactivada", "Activar Premium",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            String activarPremium = controlador.activarPremium();
            btnPremium.setText(getPremiumString());
            JOptionPane.showMessageDialog(this, activarPremium, "Activar Premium", JOptionPane.INFORMATION_MESSAGE);
        }
        frame.setModoPremium();
    }

    /**
     * Acción del botón de salir.
     * <p>
     * Se encarga de cerrar la sesión del usuario.
     * <p>
     * Muestra un mensaje de información al usuario.
     * <p>
     * Cierra la sesión del usuario y muestra la ventana de login.
     */
    private void accionBotonSalir() {
        controlador.stopSong();
        controlador.salirUsuario();
        JOptionPane.showMessageDialog(this, "Sesion cerrada", "Salir", JOptionPane.INFORMATION_MESSAGE);
        VentanaLoginRegistro loginRegistro = new VentanaLoginRegistro();
        loginRegistro.setVisible(true);
        frame.setVisible(false);

    }

    /**
     * Devuelve el texto del botón de premium.
     * @return Texto del botón de premium.
     */
    private String getPremiumString() {
        return controlador.esUsuarioActualPremium() ? "Dar de baja Premium" : "Hazte Premium";
    }

}
