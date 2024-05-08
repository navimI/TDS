package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VentanaBuscarCanciones extends JPanel {
    private static final long serialVersionUID = 1L; //Añadido por el warning: The serializabel class VentanaBuscarCanciones does not declare a static final seralVersionUID field of type long
    private JTextField tituloField;
    private JTextField interpreteField;
    private JComboBox<String> estiloComboBox;
    private JCheckBox favoritasCheckbox;
    private JButton buscarButton;
    private JTable resultadosTable;
    private JButton reproducirButton;
    private JButton detenerButton;
    private JButton pausarButton;
    private JButton siguienteButton;
    private JButton anteriorButton;
    private JTable listaReproduccionTable;
    private JButton agregarAListaButton;

    public VentanaBuscarCanciones() {
        setLayout(new BorderLayout());

        JPanel filtroPanel = new JPanel(new FlowLayout());
        interpreteField = new JTextField(20);
        tituloField = new JTextField(20);
        estiloComboBox = new JComboBox<>();
        favoritasCheckbox = new JCheckBox("Favoritas");
        buscarButton = new JButton("Buscar");
        
        filtroPanel.add(new JLabel("Intérprete:"));
        filtroPanel.add(interpreteField);
        filtroPanel.add(new JLabel("Título:"));
        filtroPanel.add(tituloField);
        filtroPanel.add(new JLabel("Estilo:"));
        filtroPanel.add(estiloComboBox);
        filtroPanel.add(favoritasCheckbox);
        filtroPanel.add(buscarButton);

        add(filtroPanel, BorderLayout.NORTH);

        resultadosTable = new JTable();
        JScrollPane resultadosScrollPane = new JScrollPane(resultadosTable);
        add(resultadosScrollPane, BorderLayout.CENTER);

        JPanel reproduccionPanel = new JPanel(new FlowLayout());
        reproducirButton = new JButton("Reproducir");
        detenerButton = new JButton("Detener");
        pausarButton = new JButton("Pausar");
        siguienteButton = new JButton("Siguiente");
        anteriorButton = new JButton("Anterior");

        reproduccionPanel.add(reproducirButton);
        reproduccionPanel.add(detenerButton);
        reproduccionPanel.add(pausarButton);
        reproduccionPanel.add(siguienteButton);
        reproduccionPanel.add(anteriorButton);

        add(reproduccionPanel, BorderLayout.SOUTH);

        listaReproduccionTable = new JTable();
        JScrollPane listaReproduccionScrollPane = new JScrollPane(listaReproduccionTable);

        agregarAListaButton = new JButton("Añadir a Lista");
        JPanel listaReproduccionPanel = new JPanel(new BorderLayout());
        listaReproduccionPanel.add(listaReproduccionScrollPane, BorderLayout.CENTER);
        listaReproduccionPanel.add(agregarAListaButton, BorderLayout.SOUTH);

        add(listaReproduccionPanel, BorderLayout.EAST);
    }

    public void addBuscarActionListener(ActionListener listener) {
        buscarButton.addActionListener(listener);
    }

    public void addReproducirActionListener(ActionListener listener) {
        reproducirButton.addActionListener(listener);
    }

    public void addDetenerActionListener(ActionListener listener) {
        detenerButton.addActionListener(listener);
    }

    public void addPausarActionListener(ActionListener listener) {
        pausarButton.addActionListener(listener);
    }

    public void addSiguienteActionListener(ActionListener listener) {
        siguienteButton.addActionListener(listener);
    }

    public void addAnteriorActionListener(ActionListener listener) {
        anteriorButton.addActionListener(listener);
    }

    public void addAgregarAListaActionListener(ActionListener listener) {
        agregarAListaButton.addActionListener(listener);
    }
}
