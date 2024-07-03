package umu.tds.vista.AppMusic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import umu.tds.controlador.Controlador;

/**
 * Ventana principal de la aplicación AppMusic.
 * <p>
 * Se encarga de gestionar la ventana principal de la aplicación, de inicializar
 * los paneles de la aplicación y de gestionar el cambio de paneles.
 */
public class VentanaAppMusic extends JFrame{
    
    private Controlador controlador;

    PanelReproduccion panelReproductor;
    PanelSesion panelSesion;
    PanelMenu panelMenu;
    PanelCentral panelCentral;
                

    /**
     * Lanza la aplicación.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaAppMusic window = new VentanaAppMusic();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor de la clase VentanaAppMusic.
     */
    public VentanaAppMusic() {
        initialize();
    }

    /**
     * Inicializa la ventana de la aplicación.
     * <p>
     * Se encarga de inicializar la ventana de la aplicación, de añadir los
     * paneles de la aplicación y de añadir los listeners a los botones.
     */
    private void initialize() {
        setTitle("AppMusic");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        controlador = Controlador.getUnicaInstancia();

        //controlador.setFirstCancion();
        //controlador.setFirstCU();
        panelReproductor = new PanelReproduccion(controlador);
        add(panelReproductor, BorderLayout.SOUTH);
        
        panelSesion = new PanelSesion(this, controlador);
        add(panelSesion, BorderLayout.NORTH);

        panelMenu = new PanelMenu(this, controlador);
        add(panelMenu, BorderLayout.WEST);

        panelCentral = new PanelCentral(controlador);
        add(panelCentral, BorderLayout.CENTER);
    
    }


    //----------auxiliar methods----------------

    /**
     * Establece el modo premium en el panel de menú.
     */
    public void setModoPremium(){
            boolean b = controlador.esUsuarioActualPremium();
            panelMenu.setModoPremium(b);
    }

    /**
     * Ajusta el panel de búsqueda de canciones.
     */
    public void ajustarBuscarCanciones(){
        panelCentral.setPanelBuscar();
    }

    /**
     * Cambia el panel central de la aplicación.
     * @param cardLy Nombre del panel al que se quiere cambiar.
     */
    public void switchCardLayout(String cardLy) {
        switch (cardLy) {
            case "panelBuscar":
                panelCentral.setPanelBuscar();
                break;
    
            case "panelGestion":
                panelCentral.setPanelGestion();
                break;
    
            case "panelRecientes":
                panelCentral.setPanelRecientes();
                break;
    
            case "panelMisPL":
                panelCentral.setPanelMisPL();
                break;
    
            case "panelTopCanciones":
                panelCentral.setPanelTopCanciones();
                break;
        }
    
        CardLayout card = (CardLayout) panelCentral.getLayout();
        card.show(panelCentral, cardLy);
    }

    

}
