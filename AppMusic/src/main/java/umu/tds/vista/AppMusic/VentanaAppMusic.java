package umu.tds.vista.AppMusic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import umu.tds.controlador.Controlador;

public class VentanaAppMusic extends JFrame{
    
    private Controlador controlador;

    PanelReproduccion panelReproductor;
    PanelSesion panelSesion;
    PanelMenu panelMenu;
    PanelCentral panelCentral;
                


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

    public VentanaAppMusic() {
        initialize();
    }

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

    public void setModoPremium(){
            boolean b = controlador.esUsuarioActualPremium();
            panelMenu.setModoPremium(b);
    }

    public void ajustarBuscarCanciones(){
        panelCentral.setPanelBuscar();
    }

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
