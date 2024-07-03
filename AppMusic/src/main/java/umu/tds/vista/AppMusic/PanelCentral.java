package umu.tds.vista.AppMusic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;

/**
 * Panel central de la aplicación.
 * En el panel central se encuentran los paneles de búsqueda, gestión de
 * canciones, canciones recientes, mis playlists y top canciones.
 * 
 * 
 * @version 1.0
 * @author Ivan Garcia Alcarz
 * @see Controlador
 * @see Cancion
 */

public class PanelCentral extends JPanel {
    private Controlador controlador;

    private JPanel panelBuscar;

    private JButton btnBuscar;

    private JTextField txtInterprete;
    private JTextField txtTitulo;
    private JCheckBox checkBoxFavoritos;

    private JComboBox<String> comboBoxEstilo;

    private JTable tablaBuscar;
    private DefaultTableModel modeloTablaBuscar;

    private JPanel panelGestion;

    private JTextField txtPlayList;
    private JTable tablaGestion;
    private DefaultTableModel modeloTablaGestion;
    private boolean creando = false;

    private JButton btnCancelar;
    private JButton btnCrear;
    private JButton btnMarcarDesmarcar;
    private JButton btnEliminar;

    private JPanel panelMisPL;
    private JTable tablaPlayList;
    private JTable tablaCancionesMisPL;
    private JTable tablaCancionesRecientes;

    private JPanel panelRecientes;
    private JPanel panelTopCanciones;

    private JTable tablaCancionesTop;

    /**
     * Constructor de la clase.
     * La clase crea el panel inicial e inicia los paneles de búsqueda, gestión de
     * canciones, canciones recientes, mis playlists y top canciones.
     * @param controlador Controlador de la aplicación.
     */

    public PanelCentral(Controlador controlador) {
        this.controlador = controlador;
        crearPanelInicial();
        iniciarPaneles();
    }

    /**
     * Método que crea el panel inicial.
     * El panel inicial se compone de un CardLayout.
     */

    private void crearPanelInicial() {
        setLayout(new CardLayout(0, 0));
    }

    /**
     * Método que inicia los paneles de búsqueda, gestión de canciones, canciones
     * recientes, mis playlists y top canciones.
     */
    private void iniciarPaneles() {
        crearPanelBuscar();
        crearPanelGestion();
        crearPanelRecientes();
        crearPanelMisPL();
        crearPanelTopCanciones();
    }

    /**
     * Método que crea el panel de búsqueda.
     * El panel de búsqueda se compone de un panel de filtros y una tabla de
     * canciones.
     * <p>
     * El panel de filtros esta compuesto de los diferentes campos de búsqueda
     * (intérprete, título, estilo y favoritos) y un botón de búsqueda.
     * <p>
     * La tabla de canciones muestra las canciones que cumplen los filtros de
     * búsqueda.
     * <p>
     * Si se hace doble clic en una canción de la tabla, se reproduce la canción.
     * <p>
     * Si se hace clic en la columna de favoritos, se marcará o desmarcará la
     * canción como favorita.
     * 
     * @see umu.tds.controlador.Controlador#realizarBusqueda(String, String, String, boolean)
     */
    private void crearPanelBuscar() {
        panelBuscar = new JPanel();
        add(panelBuscar, "panelBuscar");
        panelBuscar.setLayout(new BorderLayout());

        JPanel filtros = addPanelFiltros();
        panelBuscar.add(filtros, BorderLayout.NORTH);

        tablaBuscar = new JTable();
        JPanel panelTablaBusqueda = crearTablas(tablaBuscar, "Favoritas");
        panelBuscar.add(panelTablaBusqueda, BorderLayout.CENTER);

        listenerBtnBuscar();
        listenerTablaBuscar();

        //reproducirCancionTabla(tablaBuscar);

        

    }

    /**
     * Método que crea el panel de gestión de canciones.
     * El panel de gestión de canciones se compone de una botonera y una tabla de
     * canciones.
     * <p>
     * La botonera contiene un campo de texto para el nombre de la playlist, un
     * botón de marcar/desmarcar todas las canciones, un botón de crear/cargar una
     * playlist, un botón de eliminar una playlist y un botón de cancelar operación.
     * <p>
     * La tabla de canciones muestra las canciones que se pueden añadir a la
     * playlist.
     */
    private void crearPanelGestion() {
        panelGestion = new JPanel();
        add(panelGestion, "panelGestion");
        panelGestion.setLayout(new BorderLayout());

        JPanel botoneraGestion = addPanelBotoneraGestion();
        panelGestion.add(botoneraGestion, BorderLayout.NORTH);

        tablaGestion = new JTable();
        JPanel panelTablaGestion = crearTablas(tablaGestion, "Canciones");
        panelGestion.add(panelTablaGestion, BorderLayout.CENTER);

        listenersPanelGestion();
    }

    /**
     * Método que crea el panel de canciones recientes.
     * El panel de canciones recientes se compone de una tabla de canciones.
     * <p>
     * La tabla de canciones muestra las canciones recientes.
     * <p>
     * Si se hace doble clic en una canción de la tabla, se reproduce la canción.
     * <p>
     * Las canciones recientes se cargan en una playlist para poder usar los botones de siguiente y anterior.
     */
    private void crearPanelRecientes() {
        panelRecientes = new JPanel();
        add(panelRecientes, "panelRecientes");
        panelRecientes.setLayout(new BorderLayout());

        controlador.setCancionesRecientes();
        controlador.setLastCancion();

        tablaCancionesRecientes = new JTable();
        JPanel panelCancion = crearPanelCancion(tablaCancionesRecientes);
        panelRecientes.add(panelCancion, BorderLayout.CENTER);
    }

    /**
     * Método que crea el panel de mis playlists.
     * El panel de mis playlists se compone de un panel de playlists y un panel de
     * canciones.
     * <p>
     * El panel de playlists muestra las playlists del usuario.
     * <p>
     * Una vez seleccionada una playlist, se muestra el panel de canciones de la
     * playlist.
     * <p>
     * Las canciones de la playlist se pueden reproducir.
     * <p>
     * Para volver al panel de playlists, se puede hacer clic en el botón de volver.
     */
    private void crearPanelMisPL() {
        panelMisPL = new JPanel();
        add(panelMisPL, "panelMisPL");
        panelMisPL.setLayout(new CardLayout(0, 0));

        tablaPlayList = new JTable();
        JPanel panelPL = crearPanelPL();
        panelMisPL.add(panelPL, "panelPL");
        CardLayout cardLayout = (CardLayout) panelMisPL.getLayout();
        cardLayout.show(panelMisPL, "panelPL");

        tablaCancionesMisPL = new JTable();
        JPanel panelCancion = crearPanelCancion(tablaCancionesMisPL);
        addBotonVolver(panelCancion);

        panelMisPL.add(panelCancion, "panelCancion");

    }

    /**
     * Método que crea el panel de top canciones.
     * El panel de top canciones se compone de una tabla de canciones.
     * <p>
     * La tabla de canciones muestra las top canciones.
     * <p>
     * Si se hace doble clic en una canción de la tabla, se reproduce la canción.
     */
    private void crearPanelTopCanciones() {
        panelTopCanciones = new JPanel();
        add(panelTopCanciones, "panelTopCanciones");
        panelTopCanciones.setLayout(new BorderLayout());

        // controlador.setTopCancionesPlayListActual();
        // controlador.setLastCancion();

        tablaCancionesTop = new JTable();
        JPanel panelCancion = crearPanelCancion(tablaCancionesTop);

        Border titulo = BorderFactory.createTitledBorder("Top Canciones");
        panelCancion.setBorder(titulo);

        panelTopCanciones.add(panelCancion, BorderLayout.CENTER);

        botonGenerarPDF();

    }

    /**
     * Método que refresca el panel de búsqueda.
     * El panel de búsqueda se limpia para poder realizar una nueva búsqueda.
     * <p>
     * Se vacían los campos de búsqueda y se limpia la tabla de canciones.
     */
    public void setPanelBuscar() {
        refrescarEstilo();
        vaciarCamposBuscar();
    }

    /**
     * Método que refresca el panel de gestión de canciones.
     * El panel de gestión de canciones se limpia para poder realizar una nueva
     * gestión.
     */
    public void setPanelGestion() {
        vaciarCamposGestionar();
    }

    /**
     * Método que refresca el panel de canciones recientes.
     * Refresca el panel de canciones recientes para mostrar las canciones recientes.
     */
    public void setPanelRecientes() {
        controlador.setCancionesRecientes();
        controlador.setLastCancion();
        refrescarTablaCanciones(tablaCancionesRecientes);
    }

    /**
     * Método que refresca el panel de mis playlists.
     * Refresca el panel de mis playlists para mostrar las playlists del usuario.
     * <p>
     * Se muestra el panel de playlists.
     */
    public void setPanelMisPL() {
        tablaPlayList.setModel(crearModeloPlayList());
        CardLayout cardLayout = (CardLayout) panelMisPL.getLayout();
        cardLayout.show(panelMisPL, "panelPL");
    }

    /**
     * Método que refresca el panel de top canciones.
     * Refresca el panel de top canciones para mostrar las top canciones.
     */
    public void setPanelTopCanciones() {
        controlador.setTopCancionesPlayListActual();
        controlador.setLastCancion();
        refrescarTablaCanciones(tablaCancionesTop);
    }

    // --------- Metodos auxiliares ---------------

    /**
     * Método que crea una tabla de canciones.
     * Crea una tabla de canciones con las columnas de ID, Título, Intérprete,
     * Estilo y una columna adicional.
     * <p>
     * La columna adicional puede ser de favoritas o de canciones.
     * <p>
     * La tabla de canciones se añade a un panel.
     * @param tabla Tabla de canciones.
     * @param checkColumna Columna de la tabla.
     * @return Panel de la tabla de canciones.
     */
    private JPanel crearTablas(JTable tabla, String checkColumna) {
        JPanel panelTabla = new JPanel();
        @SuppressWarnings("rawtypes")
        final Class[] tiposColumnas = new Class[] { Integer.class, String.class, String.class, String.class,
                Boolean.class };
        String[] columnas = { "ID", "Título", "Intérprete", "Estilo", checkColumna };

        DefaultTableModel modeloTablaBuscar = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna de Favoritas será editable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return tiposColumnas[columnIndex];
            }

        };
        tabla.setModel(modeloTablaBuscar);
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);

        return panelTabla;
    }

    /**
     * Método que crea una tabla de canciones no modificable.
     * @param Data Datos de la tabla.
     * @param Columnas Columnas de la tabla.
     * @return Modelo de la tabla de canciones.
     */
    private DefaultTableModel crearTablaNoMod(Object[][] Data, Object[] Columnas) {
        return new DefaultTableModel(Data, Columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // This makes all cells non-editable
                return false;
            }
        };
    }

    /**
     * Método que reproduce una canción de la tabla.
     * Si se hace doble clic en una canción de la tabla, se reproduce la canción.
     * @param tabla Tabla de canciones.
     */
    private void reproducirCancionTabla(JTable tabla){
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow(); // Obtener la fila seleccionada
                    String cancionSeleccionada = (String) target.getValueAt(row, 1); // Obtener el nombre de la playlist

                    // seleccionada
                    controlador.setCancion(cancionSeleccionada);
                    controlador.playSong();

                }
            }
        });
    }

    /**
     * Método que crea un panel de canciones.
     * @param tabla Tabla de canciones.
     * @return Panel de canciones.
     */
    private JPanel crearPanelCancion(JTable tabla) {
        JPanel panelCancion = new JPanel();
        panelCancion.setLayout(new BorderLayout());

        refrescarTablaCanciones(tabla);
        reproducirCancionTabla(tabla);

        // Añadir la tabla a un JScrollPane y luego al panel correspondiente
        JScrollPane scrollPane = new JScrollPane(tabla);
        panelCancion.add(scrollPane, BorderLayout.CENTER);

        return panelCancion;
    }

    // Metodos Auxiliares para el panelBuscar

    /**
     * Método que añade un panel de filtros.
     * El panel de filtros se compone de los diferentes campos de búsqueda (intérprete, título, estilo y favoritos) y un botón de búsqueda.
     * @return Panel de filtros.
     */
    private JPanel addPanelFiltros() {
        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuración inicial común de GridBagConstraints
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(20, 20, 20, 20); // Margen para cada componente

        // Primer JLabel y JTextField
        JLabel lblInterprete = new JLabel("Interprete :");
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        panelFiltros.add(lblInterprete, gbc);

        txtInterprete = new JTextField(10);
        gbc.gridx = 1; // Columna 1
        panelFiltros.add(txtInterprete, gbc);

        // Segundo JLabel y JTextField
        JLabel lblTitulo = new JLabel("Titulo :");
        gbc.gridx = 2; // Columna 2
        panelFiltros.add(lblTitulo, gbc);

        txtTitulo = new JTextField(10);
        gbc.gridx = 3; // Columna 3
        panelFiltros.add(txtTitulo, gbc);

        // Tercer JLabel y JTextField
        JLabel lblEstilo = new JLabel("Estilo:");
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 1; // Fila 1
        panelFiltros.add(lblEstilo, gbc);

        comboBoxEstilo = new JComboBox<>();
        refrescarEstilo();
        gbc.gridx = 1; // Columna 1
        panelFiltros.add(comboBoxEstilo, gbc);

        // Cuarto JLabel y JCheckBox
        JLabel lblFavoritos = new JLabel("Favoritos:");
        gbc.gridx = 2; // Columna 2
        panelFiltros.add(lblFavoritos, gbc);

        checkBoxFavoritos = new JCheckBox();
        gbc.gridx = 3; // Columna 3
        panelFiltros.add(checkBoxFavoritos, gbc);

        btnBuscar = new JButton("Buscar");
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 2; // Fila 2
        gbc.gridwidth = 4; // Ocupa 4 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrado
        panelFiltros.add(btnBuscar, gbc);

        return panelFiltros;
    }

    /**
     * Método que rellena la tabla de búsqueda.
     * Rellena la tabla de búsqueda con las canciones que cumplen los filtros de búsqueda.
     * @param canciones Lista de canciones.
     */
    private void rellenarTablaBuscar(List<Cancion> canciones) {
        modeloTablaBuscar = (DefaultTableModel) tablaBuscar.getModel();
        modeloTablaBuscar.setRowCount(0);

        canciones.forEach(c -> {
            Object[] fila = new Object[] { c.getID(), c.getTitulo(), c.getListaInterpretes(), c.getEstilo(),
                    controlador.esFavorita(c.getID()) };
            modeloTablaBuscar.addRow(fila);
        });

    }

    /**
     * Método que añade un listener a la tabla de búsqueda.
     * Si se hace doble clic en una canción de la tabla, se reproduce la canción.
     * Si se hace clic en la columna de favoritos, se marcará o desmarcará la canción como favorita.
     * 
     */
    private void listenerTablaBuscar() {
        tablaBuscar.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    if (column == 4) { // Si la columna cambiada es "Favoritas"
                        int idCancion = (int) tablaBuscar.getValueAt(row, 0);
                        controlador.invertirFavoritosID(idCancion);
                    }
                }
            }
        });
        tablaBuscar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow(); // Obtener la fila seleccionada
                    String cancionSeleccionada = (String) target.getValueAt(row, 1); // Obtener el nombre de la playlist
                    

                    // seleccionada
                    controlador.setCancionSinPlayList(cancionSeleccionada);
                    controlador.playSong();

                }
            }
        });
    }

    /**
     * Método que añade un listener al botón de búsqueda.
     * Si se hace clic en el botón de búsqueda, se realizará una búsqueda con los filtros seleccionados.
     */
    private void listenerBtnBuscar() {
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String interprete = txtInterprete.getText();
                String titulo = txtTitulo.getText();
                String estilo = (String) comboBoxEstilo.getSelectedItem();
                boolean favoritos = checkBoxFavoritos.isSelected();

                List<Cancion> canciones = controlador.realizarBusqueda(interprete, titulo, estilo, favoritos);

                rellenarTablaBuscar(canciones);

                tablaBuscar.setModel(modeloTablaBuscar);
                
            }
        });
    }

    /**
     * Método que refresca el estilo de la lista desplegable.
     * Refresca el estilo de la lista desplegable con los estilos de las canciones.
     */
    private void refrescarEstilo() {
        DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>(
                controlador.listarEstilos().toArray(new String[0]));
        comboBoxEstilo.setModel(modeloComboBox);
        comboBoxEstilo.insertItemAt("", 0);
        comboBoxEstilo.setSelectedIndex(0);
    }

    /**
     * Método que vacía los campos de búsqueda.
     * Vacía los campos de búsqueda y limpia la tabla de canciones.
     */
    private void vaciarCamposBuscar() {
        txtInterprete.setText("");
        txtTitulo.setText("");
        comboBoxEstilo.setSelectedIndex(0);
        checkBoxFavoritos.setSelected(false);
        ((DefaultTableModel) tablaBuscar.getModel()).setRowCount(0);
    }

    // Metodos Auxiliares para el panelGestion

    /**
     * Método que añade un panel de botonera de gestión.
     * El panel de botonera de gestión se compone de un campo de texto para el nombre de la playlist,
     *  un botón de marcar/desmarcar todas las canciones, un botón de crear/cargar una playlist, un botón de eliminar una playlist 
     * y un botón de cancelar operación.
     * <p>
     * Si se hace clic en el botón de marcar/desmarcar todas las canciones, se marcarán o desmarcarán todas las canciones.
     * <p>
     * 
     * @return Panel de botonera de gestión.
     */
    private JPanel addPanelBotoneraGestion() {

        JPanel panelBotonera = new JPanel();
        panelBotonera.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // JLabel PlayList
        JLabel lblPlayList = new JLabel("Nombre de la PlayList:");
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        panelBotonera.add(lblPlayList, gbc);

        // JTextField nombre PlayList
        txtPlayList = new JTextField(10);
        gbc.gridx = 1; // Columna 1
        panelBotonera.add(txtPlayList, gbc);

        // Botón vacío para ajustar la posición del último botón
        btnCancelar = new JButton("Cancelar Operación");
        gbc.gridx = 2; // Columna 2
        btnCancelar.setVisible(false);
        panelBotonera.add(btnCancelar, gbc);

        // Segunda fila de botones
        gbc.gridy = 1; // Fila 1

        // Botón "Marcar/Desmarcar todos"
        btnMarcarDesmarcar = new JButton("Marcar/Desmarcar todos");
        gbc.gridx = 0; // Columna 1
        btnMarcarDesmarcar.setVisible(false);
        panelBotonera.add(btnMarcarDesmarcar, gbc);

        // Botón "Crear/Cargar"
        btnCrear = new JButton("Crear/Cargar");
        gbc.gridx = 1; // Columna 2
        panelBotonera.add(btnCrear, gbc);

        // Botón "Eliminar"
        btnEliminar = new JButton("Eliminar");
        gbc.gridx = 2; // Columna 0
        btnEliminar.setVisible(false);
        panelBotonera.add(btnEliminar, gbc);

        return panelBotonera;
    }

    /**
     * Método que cambia la visibilidad de los botones de la botonera de gestión.
     */
    private void switchVisibilidadBotones() {
        btnCancelar.setVisible(!btnCancelar.isVisible());
        btnMarcarDesmarcar.setVisible(!btnMarcarDesmarcar.isVisible());
        btnEliminar.setVisible(!btnEliminar.isVisible());
    }

    /**
     * Método que apaga la visibilidad de los botones de la botonera de gestión.
     */
    private void offVisibilidadBotones() {
        btnCancelar.setVisible(false);
        btnMarcarDesmarcar.setVisible(false);
        btnEliminar.setVisible(false);
    }

    /**
     * Método que rellena la tabla de gestión.
     * Rellena la tabla de gestión con las canciones que se pueden añadir a la playlist.
     * @param canciones Lista de canciones.
     * @param cancionesPlayList Lista de canciones de la playlist.
     */
    private void rellenarTablaGestion(List<Cancion> canciones, List<Cancion> cancionesPlayList) {
        modeloTablaGestion = (DefaultTableModel) tablaGestion.getModel();
        modeloTablaGestion.setRowCount(0);

        if (canciones != null) {
            canciones.forEach(c -> {
                Object[] fila = new Object[] { c.getID(), c.getTitulo(), c.getListaInterpretes(), c.getEstilo(),
                        true };
                modeloTablaGestion.addRow(fila);
            });
        }

        if (canciones == null) {
            cancionesPlayList.forEach(c -> {
                Object[] fila = new Object[] { c.getID(), c.getTitulo(), c.getListaInterpretes(), c.getEstilo(),
                        false };
                modeloTablaGestion.addRow(fila);
            });
        } else {
            cancionesPlayList.stream().filter(c -> !canciones.stream()
                    .anyMatch(cancion -> cancion.getID() == c.getID()))
                    .forEach(c -> {
                        Object[] fila = new Object[] { c.getID(), c.getTitulo(), c.getListaInterpretes(), c.getEstilo(),
                                false };
                        modeloTablaGestion.addRow(fila);
                    });
        }
    }

    /**
     * Método que añade listeners al panel de gestión.
     * Si se hace clic en el botón de crear/cargar, se creará o cargará una playlist.
     * <p>
     * Al crear/cargar una playlist valida se entrará en modo edición de la playlist.
     * <p>
     * El modo edición de la playlist activa los botones de marcar/desmarcar todas las canciones, eliminar y cancelar operación.
     * <p>
     * Si se estaba en modo edición de la playlist y se hace clic en el botón de guardar, se guardará la playlist.
     */
    private void listenersPanelGestion() {

        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombrePlayList = txtPlayList.getText();
                if (!nombrePlayList.isEmpty() && !nombrePlayList.equals("Canciones Recientes") && !nombrePlayList.equals("Top 10")) {
                    if (!creando) {

                        List<Cancion> cancionesFavoritas = controlador.getPlayListFavoritos();

                        btnCrear.setText("Guardar");

                        txtPlayList.setEditable(false);
                        switchVisibilidadBotones();

                        // Cargar las canciones de la PlayList
                        List<Cancion> canciones = controlador.existeListPlayList(nombrePlayList);
                        rellenarTablaGestion(canciones, cancionesFavoritas);

                        // Modo edicion de la PlayList

                        JOptionPane.showMessageDialog(null, "Entrando en modo edición de playLists");
                        creando = true;

                    } else {
                        // Guardar la PlayList
                        List<Integer> canciones = new LinkedList<>();
                        // Recorrer la tabla y añadir las canciones marcadas
                        for (int i = 0; i < modeloTablaGestion.getRowCount(); i++) {
                            if ((boolean) modeloTablaGestion.getValueAt(i, 4)) {
                                canciones.add((int) modeloTablaGestion.getValueAt(i, 0));
                            }
                        }

                        if (canciones.size() > 0) {
                            controlador.guardarPlayList(nombrePlayList, canciones);
                            JOptionPane.showMessageDialog(null, "PlayList guardada correctamente");
                            vaciarCamposGestionar();

                        } else {
                            JOptionPane.showMessageDialog(null, "No hay canciones seleccionadas", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de PlayList no válido", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vaciarCamposGestionar();
                JOptionPane.showMessageDialog(null, "Cancelada la operacion de creacion de PlayList");

            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombrePlayList = txtPlayList.getText();

                boolean existe = controlador.removePlaylist(nombrePlayList);
                if (existe)
                    JOptionPane.showMessageDialog(null, "PlayList eliminada correctamente");
                else
                    JOptionPane.showMessageDialog(null, "PlayList no existe en usuario actual", "Error",
                            JOptionPane.ERROR_MESSAGE);
                vaciarCamposGestionar();

            }
        });

        btnMarcarDesmarcar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (modeloTablaGestion.getRowCount() > 0) {
                    modeloTablaGestion.setValueAt(!((boolean) modeloTablaGestion.getValueAt(0, 4)), 0, 4);
                    for (int i = 1; i < modeloTablaGestion.getRowCount(); i++) {
                        modeloTablaGestion.setValueAt(((boolean) modeloTablaGestion.getValueAt(0, 4)), i, 4);
                    }
                }
                
            }
        });
    }

    /**
     * Método que vacía los campos de gestión.
     */
    private void vaciarCamposGestionar() {
        btnCrear.setText("Crear/Cargar");
        txtPlayList.setText("");
        txtPlayList.setEditable(true);
        creando = false;
        offVisibilidadBotones();
        ((DefaultTableModel) tablaGestion.getModel()).setRowCount(0);
    }

    // Metodos Auxiliares para el panelMisPL

    /**
     * Método que crea un modelo de tabla de playlists.
     * @return Modelo de tabla de playlists.
     */
    private DefaultTableModel crearModeloPlayList() {
        // Obtener la lista de playlists del usuario
        List<String> playlists = controlador.stringPlayListUsuario();
        

        // Convertir la List a un modelo de tabla
        String[] columnNames = { "Playlist" };
        Object[][] data = new Object[playlists.size()][1];
        for (int i = 0; i < playlists.size(); i++) {
            data[i][0] = playlists.get(i);
        }
        DefaultTableModel model = crearTablaNoMod(data, columnNames);
        return model;
    }

    /**
     * Método que crea un panel de playlists.
     * El panel de playlists se compone de una tabla de playlists.
     * @return Panel de playlists.
     */
    private JPanel crearPanelPL() {
        JPanel panelPL = new JPanel();
        
        panelPL.setLayout(new BorderLayout());

        tablaPlayList.setModel(crearModeloPlayList());

        // Añadir MouseListener para detectar doble clic
        tablaPlayList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow(); // Obtener la fila seleccionada
                    String selectedPlaylist = (String) target.getValueAt(row, 0); // Obtener el nombre de la playlist

                    // seleccionada
                    controlador.setPlayList(selectedPlaylist); // Cambiar la playlist actual a la seleccionada

                    // Cambiar al panel "panelCancion"
                    refrescarTablaCanciones(tablaCancionesMisPL);
                    CardLayout cl = (CardLayout) (panelMisPL.getLayout());
                    cl.show(panelMisPL, "panelCancion"); 
                }
            }
        });

        // Añadir la tabla a un JScrollPane y luego al panel correspondiente

        JScrollPane scrollPane = new JScrollPane(tablaPlayList);
        panelPL.add(scrollPane, BorderLayout.CENTER);

        return panelPL;
    }

    /**
     * Método que refresca la tabla de canciones.
     * Refresca la tabla de canciones con las canciones de la playlist seleccionada.
     * @param tabla Tabla de canciones.
     */
    private void refrescarTablaCanciones(JTable tabla) {
        List<Cancion> canciones = controlador.getCancionesPlayListActual();
        String[] columnNames = { "ID", "Título", "Intérprete", "Estilo" };
        Object[][] data;
        if (canciones != null && canciones.size() > 0) {
            data = new Object[canciones.size()][4];
            for (int i = 0; i < canciones.size(); i++) {
                data[i][0] = canciones.get(i).getID();
                data[i][1] = canciones.get(i).getTitulo();
                data[i][2] = canciones.get(i).getListaInterpretes();
                data[i][3] = canciones.get(i).getEstilo();
            }
        } else
            data = null;

        DefaultTableModel model = crearTablaNoMod(data, columnNames);
        tabla.setModel(model);
    }

    /**
     * Método que añade un botón de volver al panel de canciones.
     * @param panelCancion Panel de canciones.
     */
    private void addBotonVolver(JPanel panelCancion) {
        // Create a button to return to the panelPL
        JButton btnReturn = new JButton("Volver");
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to switch back to panelPL
                CardLayout cardLayout = (CardLayout) panelMisPL.getLayout();
                cardLayout.show(panelMisPL, "panelPL");
            }
        });
        panelCancion.add(btnReturn, BorderLayout.SOUTH);
    }

    // -------- metodos auxiliares para el panelTopCanciones

    /**
     * Método que añade un botón de generar PDF.
     * Si se hace clic en el botón de generar PDF, se generará un PDF con las top canciones.
     * <p>
     * Se abrirá un JFileChooser para seleccionar la ruta donde guardar el PDF.
     */
    private void botonGenerarPDF() {

        JButton btnGenerarPDF = new JButton("Generar PDF");
        panelTopCanciones.add(btnGenerarPDF, BorderLayout.SOUTH);
        btnGenerarPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar PDF");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Select directory
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File directoryToSave = fileChooser.getSelectedFile();
                String directoryPath = directoryToSave.getAbsolutePath();
                boolean success = controlador.generarPDF(directoryPath);
                    
                    if (success) {
                        JOptionPane.showMessageDialog(null, "El PDF se ha generado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido generar el PDF.");
                    }
                }
            }
        });
    }
    

   

}
