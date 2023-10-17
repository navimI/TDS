package umu.tds.AppMusic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private String nombre = "";
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal window = new VentanaPrincipal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaPrincipal() {
        initialize();
    }

    private void initialize() {
        nombre = "NOMBRE USUARIO";  // Reemplazar con el nombre de usuario

        frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLUE); // Cambia el fondo a azul
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JLabel nombreUsuario = new JLabel(nombre);
        nombreUsuario.setForeground(Color.WHITE); // Cambia el color del texto a blanco
        nombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
        frame.getContentPane().add(nombreUsuario, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setBackground(Color.cyan); // Cambia el fondo a cyan
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(4, 2, 10, 10));  // diseño de la disposición de los componentes 
        //4 filas, 2 columnas, espacio horizontal y vertical

        JButton btnExplorar = new JButton("Explorar App");
        btnExplorar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Acción a realizar al hacer clic en "Explorar"
            }
        });
        panel.add(btnExplorar);

        JButton btnNuevaLista = new JButton("Crear nueva lista");
        btnNuevaLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Acción a realizar al hacer clic en " rcear Nueva Lista"
            }
        });
        panel.add(btnNuevaLista);

        JButton btnReciente = new JButton("Más reciente escuchado");
        btnReciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Acción a realizar al hacer clic en "Más reciente escuchado"
            }
        });
        panel.add(btnReciente);

        JButton btnMisListas = new JButton("Mis listas de música");
        btnMisListas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Acción a realizar al hacer clic en "Mis listas de música"
            }
        });
        panel.add(btnMisListas);

        if (usuarioNoEsPremium()) {
            JButton btnMasReproducidas = new JButton("Canciones más reproducidas");
            btnMasReproducidas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // Acción a realizar al hacer clic en " Canciones más reproducidas"
                }
            });
            panel.add(btnMasReproducidas);
        }

        // para cambiar el tamaño de los botones:
        Dimension buttonSize = new Dimension(50, 50);
        btnExplorar.setPreferredSize(buttonSize);
        btnNuevaLista.setPreferredSize(buttonSize);
        btnReciente.setPreferredSize(buttonSize);
        btnMisListas.setPreferredSize(buttonSize);

        frame.setVisible(true);
    }

    // Simulación de comprobar si el usuario es premium
    private boolean usuarioNoEsPremium() {
        // Reemplaza con la lógica real para verificar si el usuario es premium
        return true;
    }
}
