package umu.tds.vista.AppMusic;

import javax.swing.JPanel;

import umu.tds.controlador.Controlador;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelReproduccion extends JPanel{

	private Controlador controlador;

    private JButton btnPlay;
    private JButton btnPausar;
    private JButton btnSiguiente;
    private JButton btnAnterior;
    private JButton btnStop;


    public PanelReproduccion( Controlador controlador) {

        this.controlador = controlador;
        crearPanel();
        crearBotones();
        addBotonListeners();

    }

    private void crearPanel() {
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
    }

    private void crearBotones() {

        btnAnterior = new JButton();
        crearBoton(btnAnterior, "anterior");
        add(btnAnterior);

        btnStop = new JButton();
        crearBoton(btnStop, "stop");
        add(btnStop);

        btnPlay = new JButton();
        crearBoton(btnPlay, "play");
        add(btnPlay);

        btnPausar = new JButton();
        crearBoton(btnPausar, "pause");
        add(btnPausar);

        btnSiguiente = new JButton();
        crearBoton(btnSiguiente, "siguiente");
        add(btnSiguiente);


    }

    private void addBotonListeners(){

            btnPlay.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controlador.playSong();
                }
            });
            btnPausar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controlador.pauseSong();
                }
            });
            btnSiguiente.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controlador.nextSong();
                }
            });
            btnAnterior.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controlador.previousSong();
                }
            });
            btnStop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    controlador.stopSong();
                }
            });

    }

    // ------------- Metodos auxiliares -------------

    private ImageIcon crearIcono(String ruta) {
        ImageIcon icono = new ImageIcon(getClass().getResource("/vista/imagenes/" + ruta + ".jpg"));
        Image imagenRedimensionada = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
        return iconoRedimensionado;
    }

    private void crearBoton(JButton boton, String ruta) {
        ImageIcon icono = crearIcono(ruta);
        boton.setIcon(icono);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
    }

}
