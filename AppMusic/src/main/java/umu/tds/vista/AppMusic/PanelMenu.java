package umu.tds.vista.AppMusic;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import pulsador.IEncendidoListener;
import pulsador.Luz;
import umu.tds.controlador.Controlador;


public class PanelMenu extends JPanel {

    private Controlador controlador;

    Luz luz;

    private JButton btnBuscar;
    private JButton btnGestion;
    private JButton btnRecientes;
    private JButton btnMisPL;
    private JButton btnTopCanciones;
    Dimension buttonSize;

    private VentanaAppMusic frame;

    private GridBagConstraints gbc;

    public PanelMenu(VentanaAppMusic frame, Controlador controlador) {
        this.frame = frame;
        this.controlador = controlador;
        crearPanel();
        crearBotones();
        addBotonListeners();
    }

    private void crearPanel() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        buttonSize = new Dimension(200, 30);
        gbc.weightx = 1.0; // Give each component extra horizontal space.
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 0, 0);
    }

    private void crearBotones() {

        btnBuscar = new JButton("Buscar Canciones");
        generarBotones(0, "buscar", btnBuscar);
        add(btnBuscar, gbc);

        btnGestion = new JButton("Gestionar PlayList");
        generarBotones(1, "gestion", btnGestion);
        add(btnGestion, gbc);

        btnRecientes = new JButton("Canciones Recientes");
        generarBotones(2, "recientes", btnRecientes);
        add(btnRecientes, gbc);

        btnMisPL = new JButton("Mis PlayList");
        generarBotones(3, "misPL", btnMisPL);
        add(btnMisPL, gbc);

        btnTopCanciones = new JButton("Panel Premium");
        generarBotones(4, "topCanciones", btnTopCanciones);
        setModoPremium(controlador.esUsuarioActualPremium());
        add(btnTopCanciones, gbc);

        luz = new Luz();
        generarBotonLuz();
        add(luz, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;

    }

    private void addBotonListeners() {

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarCardLayout("panelBuscar");
            }
        });
        btnGestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarCardLayout("panelGestion");
            }
        });
        btnRecientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarCardLayout("panelRecientes");
            }
        });
        btnMisPL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarCardLayout("panelMisPL");
            }
        });
        btnTopCanciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarCardLayout("panelTopCanciones");
            }
        });

        luz.addEncendidoListener(new IEncendidoListener() {
            @Override
            public void enteradoCambioEncendido(EventObject arg0) {
                if (luz.isEncendido()) {
                    luz.setColorEncendido(Color.GREEN);
                    luzCargaCanciones();
                    frame.ajustarBuscarCanciones();
                }
                luz.setColorEncendido(Color.RED);
                
            }

        });

    }

    // -------- Metodos auxiliares

    private void generarBotones(int posicion, String nombreIcono, JButton boton) {
        gbc.gridx = 0;
        gbc.gridy = posicion;
        boton.setPreferredSize(buttonSize);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setIcon(new ImageIcon(getClass().getResource("/vista/imagenes/" + nombreIcono + ".png")));
        boton.setFont(new Font("Tahoma", Font.BOLD, 14));
    }

    private void generarBotonLuz() {
        luz.setColorEncendido(Color.GREEN);
        luz.setColorApagado(Color.RED);
        luz.setNombre("Luz Principal");
        gbc.gridx = 0;
        gbc.gridy = 5;
    }

    public void setModoPremium(Boolean b) {
        btnTopCanciones.setVisible(b);
    }

    private void cambiarCardLayout(String cardLy) {
        frame.switchCardLayout(cardLy);
    }

    private void luzCargaCanciones() {
        JFileChooser jfc = new JFileChooser(new String("./xml"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Xml Song Files", "xml");
        jfc.setFileFilter(filter);

        int returnValue = jfc.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            controlador.cargarCanciones(jfc.getSelectedFile().getAbsolutePath());

            JOptionPane.showMessageDialog(this,
                    "Canciones cargadas con éxito.\n", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
