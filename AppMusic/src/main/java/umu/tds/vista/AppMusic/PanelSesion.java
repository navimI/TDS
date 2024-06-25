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

public class PanelSesion extends JPanel {

    private Controlador controlador;

    private JButton btnPremium;
    private JButton btnSalir;
    private JLabel lblUsuario;

    private VentanaAppMusic frame;

    public PanelSesion(VentanaAppMusic frame,Controlador controlador) {
        this.frame = frame;
        this.controlador = controlador;
        crearPanel();
        crearBotones();
        addBotonListeners();

    }

    private void crearPanel() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    }

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

    
    private void accionBotonPremium() {
        if (controlador.esUsuarioActualPremium()) {
            controlador.desactivarPremium();
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

    private void accionBotonSalir() {
        controlador.stopSong();
        controlador.salirUsuario();
        JOptionPane.showMessageDialog(this, "Sesion cerrada", "Salir", JOptionPane.INFORMATION_MESSAGE);
        VentanaLoginRegistro loginRegistro = new VentanaLoginRegistro();
        loginRegistro.setVisible(true);
        frame.setVisible(false);

    }

    private String getPremiumString() {
        return controlador.esUsuarioActualPremium() ? "Dar de baja Premium" : "Hazte Premium";
    }

}
