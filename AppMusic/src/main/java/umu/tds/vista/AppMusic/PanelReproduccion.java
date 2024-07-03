package umu.tds.vista.AppMusic;

import javax.swing.JPanel;

import umu.tds.controlador.Controlador;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel que contiene los botones de reproducción de la aplicación.
 * <p>
 * Se encarga de gestionar los eventos de los botones de reproducción y de cambiar el panel
 * principal de la aplicación.
 */
public class PanelReproduccion extends JPanel{

	private Controlador controlador;

    private JButton btnPlay;
    private JButton btnPausar;
    private JButton btnSiguiente;
    private JButton btnAnterior;
    private JButton btnStop;

    /**
     * Constructor de la clase PanelReproduccion.
     * <p>
     * Se encarga de inicializar el panel de reproducción y de crear los botones de reproducción.
     * Ademas, añade los listeners a los botones.
     * @param controlador Controlador de la aplicación.
     */
    public PanelReproduccion( Controlador controlador) {

        this.controlador = controlador;
        crearPanel();
        crearBotones();
        addBotonListeners();
    }

    /**
     * Crea el panel de reproducción.
     * <p>
     * Se encarga de crear el panel y establecer su layout.
     */
    private void crearPanel() {
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
    }

    /**
     * Crea los botones de reproducción.
     */
    private void crearBotones() {

        btnAnterior = new JButton();
        modificaBoton(btnAnterior, "anterior");
        add(btnAnterior);

        btnStop = new JButton();
        modificaBoton(btnStop, "stop");
        add(btnStop);

        btnPlay = new JButton();
        modificaBoton(btnPlay, "play");
        add(btnPlay);

        btnPausar = new JButton();
        modificaBoton(btnPausar, "pause");
        add(btnPausar);

        btnSiguiente = new JButton();
        modificaBoton(btnSiguiente, "siguiente");
        add(btnSiguiente);


    }

    /**
     * Añade los listeners a los botones de reproducción.
     * <p>
     * Se encarga de añadir los listeners a los botones de reproducción.
     * Cada botón tiene un listener que se encarga de gestionar la acción que se realiza al pulsar el botón.
     */
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

    /**
     * Crea un icono a partir de una ruta.
     * @param ruta Ruta del icono.
     * @return Icono creado.
     */
    private ImageIcon crearIcono(String ruta) {
        ImageIcon icono = new ImageIcon(getClass().getResource("/vista/imagenes/" + ruta + ".jpg"));
        Image imagenRedimensionada = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
        return iconoRedimensionado;
    }

    /**
     * Modifica un boton con los iconos y bordes correspondientes.
     * @param boton Boton a modificar.
     * @param ruta Ruta del icono que se añadirá al botón.
     */
    private void modificaBoton(JButton boton, String ruta) {
        ImageIcon icono = crearIcono(ruta);
        boton.setIcon(icono);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
    }

}
