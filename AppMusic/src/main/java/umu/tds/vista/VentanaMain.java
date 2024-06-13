package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import pulsador.IEncendidoListener;
import pulsador.Luz;
import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;


public class VentanaMain extends JFrame {

	private Controlador controlador;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaResultados;
	private PlayList playlist;
	//para las playlists:
	private JTextField textFieldTituloPlaylist;
	private DefaultTableModel modeloTablaCanciones;
	
	//para buscar:
	private JPanel panelBuscar;
	private JTextField textFieldInterprete;
    private JTextField textFieldTitulo;
    private JComboBox<String> comboBoxEstilo;
    private JCheckBox checkBoxFavoritas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain frame = new VentanaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	String[] columnas = {"Título", "Intérprete", "Estilo", "Favoritas"};
	public void realizarBusquedaDesdeControlador(String interprete, String titulo, String estilo, boolean favoritas, List<Cancion> resultados) {
        // Convertir los resultados a un arreglo bidimensional de objetos
        Object[][] datos = new Object[resultados.size()][4];
        for (int i = 0; i < resultados.size(); i++) {
            Cancion cancion = resultados.get(i);
            datos[i][0] = cancion.getTitulo();
            datos[i][1] = String.join(",", cancion.getListaInterpretes());
            datos[i][2] = cancion.getEstilo();
            datos[i][3] = controlador.esFavorita(cancion.getID());
        }

        // Actualizar la tabla con los nuevos datos
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tablaResultados.setModel(model);
    }
	
	/**
	 * Create the frame.
	 */
	//Trying git
	public VentanaMain() {
		
		playlist = new PlayList("NombreDeTuPlaylist");
		controlador = Controlador.getUnicaInstancia();
		
		setTitle("AppMusic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		// Crear una instancia de Luz
        Luz luz = new Luz();
        luz.setColorEncendido(Color.GREEN); // Establecer el color encendido
        luz.setColorApagado(Color.RED);     // Establecer el color apagado
        luz.setNombre("Luz Principal");     // Establecer el nombre de la luz

        // Posición y tamaño del componente Luz
        luz.setBounds(25, 250, 50, 50);
        
        luz.addEncendidoListener(new IEncendidoListener() {
        	@Override
            public void enteradoCambioEncendido(EventObject arg0) {
                if (luz.isEncendido()) {
                	//luz a verde:
                	luz.setColorEncendido(Color.GREEN);
                    luz.repaint();
                    
                    JFileChooser jfc = new JFileChooser(new File("./xml"));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Xml Song Files", "xml");
                    jfc.setFileFilter(filter);
    
                    int returnValue = jfc.showOpenDialog(contentPane);
    
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                    	
                        controlador.cargarCanciones(jfc.getSelectedFile().getAbsolutePath());
                        List<String> estilosMusicalesList = controlador.listarEstilos();
                		estilosMusicalesList.add("");
                		revalidate();
                		repaint();
                        JOptionPane.showMessageDialog(contentPane, 
                                "Canciones cargadas con éxito.\n", "", JOptionPane.INFORMATION_MESSAGE);
                        
                     //color de la luz a rojo después de mostrar el mensaje:
                        luz.setColorEncendido(Color.RED);
                        luz.repaint();
                    }
                   luz.setEncendido(false);
                   luz.repaint(); //repintada después de cambiar el estado
                }}
        });
        
        // Añadir la luz al contentPane
        contentPane.add(luz);
		
		
		JPanel panelBotonera = new JPanel();
		contentPane.add(panelBotonera, BorderLayout.WEST);
		GridBagLayout gbl_panelBotonera = new GridBagLayout();
		gbl_panelBotonera.columnWidths = new int[]{0, 0};
		gbl_panelBotonera.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelBotonera.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelBotonera.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBotonera.setLayout(gbl_panelBotonera);
		
		this.setMinimumSize(new Dimension(600, 500));
	    this.setMaximumSize(new Dimension(800, 700));
	    this.setSize(new Dimension(600, 500));
	    
		
		JPanel panelCardLayout = new JPanel();

		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelBuscar");
			}
		});
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/lupa.png")));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 0;
		panelBotonera.add(btnBuscar, gbc_btnBuscar);
		
		JButton btnGestionPlaylists = new JButton("Gestion Playlists");
		btnGestionPlaylists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelGestion");
				
			}
		});
		btnGestionPlaylists.setHorizontalAlignment(SwingConstants.LEFT);
		btnGestionPlaylists.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/mas.png")));
		btnGestionPlaylists.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnGestionPlaylists = new GridBagConstraints();
		gbc_btnGestionPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGestionPlaylists.insets = new Insets(0, 0, 5, 0);
		gbc_btnGestionPlaylists.gridx = 0;
		gbc_btnGestionPlaylists.gridy = 1;
		panelBotonera.add(btnGestionPlaylists, gbc_btnGestionPlaylists);
		
		JButton btnRecientes = new JButton("Recientes");
		btnRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Botón Recientes clicado"); //msg para depurar
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelRecientes");
				//System.out.println("Panel actual: " + card.toString());//msg para depurar
			}
		});
		btnRecientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecientes.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/reloj.png")));
		btnRecientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnRecientes = new GridBagConstraints();
		gbc_btnRecientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRecientes.insets = new Insets(0, 0, 5, 0);
		gbc_btnRecientes.gridx = 0;
		gbc_btnRecientes.gridy = 2;
		panelBotonera.add(btnRecientes, gbc_btnRecientes);
		
		JButton btnMisPlaylists = new JButton("Mis Playlists");
		btnMisPlaylists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelPlaylists");
			}
		});
		btnMisPlaylists.setHorizontalAlignment(SwingConstants.LEFT);
		btnMisPlaylists.setIcon(new ImageIcon(VentanaMain.class.getResource("/vista/imagenes/altavoz.png")));
		btnMisPlaylists.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnMisPlaylists = new GridBagConstraints();
		gbc_btnMisPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMisPlaylists.gridx = 0;
		gbc_btnMisPlaylists.gridy = 3;
		panelBotonera.add(btnMisPlaylists, gbc_btnMisPlaylists);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCentro.add(panel, BorderLayout.NORTH);
		
		// Cambiar comentario entre las 2 siguientes lineas dependiendo de si se va a ejecutar el todo el lanzador o solo la ventana main
		JLabel lblBienvenido = new JLabel("Bienvenido, "+ controlador.getUsuarioActualField("user"));
		//JLabel lblBienvenido = new JLabel("Bienvenido, [usuario]");
		panel.add(lblBienvenido);
		
		JButton btnPremium = new JButton("Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(controlador.esUsuarioActualPremium()){
					controlador.desactivarPremium();
					JOptionPane.showMessageDialog(contentPane, "Funcionalidad Premium desactivada", "Activar Premium", JOptionPane.INFORMATION_MESSAGE);
					}
				else{
					String activarPremium = controlador.activarPremium();
					JOptionPane.showMessageDialog(contentPane, activarPremium, "Activar Premium", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		panel.add(btnPremium);
		
		JButton btnSalir = new JButton("Salir");
		panel.add(btnSalir);
		
		panelCentro.add(panelCardLayout, BorderLayout.CENTER);
		panelCardLayout.setLayout(new CardLayout(0, 0));
		
		
		// PANEL BUSCAR:
		panelBuscar = new JPanel();
		panelCardLayout.add(panelBuscar, "panelBuscar");

		// Crear una tabla vacía para mostrar los resultados
		tablaResultados = new JTable() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public Class<?> getColumnClass(int column) {
		        if (column == 4) {
		            return Boolean.class; // La columna Favoritas será un checkbox
		        }
		        return super.getColumnClass(column);
		    }
		};
		// Configurar la tabla 
		tablaResultados.setPreferredScrollableViewportSize(new Dimension(400, 200));
		tablaResultados.setFillsViewportHeight(true);

		// Añadir la tabla a un JScrollPane para permitir el desplazamiento
		JScrollPane scrollPane1 = new JScrollPane(tablaResultados);
		panelBuscar.add(scrollPane1);
		String[] columnas = {"ID", "Título", "Intérprete", "Estilo", "Favoritas"};

		// Componentes para el panel Buscar:
		JLabel lblInterprete = new JLabel("Intérprete:");
		panelBuscar.add(lblInterprete);
		// Configurar el tamaño mínimo y máximo del panelBuscar
		panelBuscar.setMinimumSize(new Dimension(600, 400));
		panelBuscar.setMaximumSize(new Dimension(700, 500));

		JTextField textFieldInterprete = new JTextField();
		panelBuscar.add(textFieldInterprete);
		textFieldInterprete.setColumns(15);

		JLabel lblTitulo = new JLabel("Título:");
		panelBuscar.add(lblTitulo);

		JTextField textFieldTitulo = new JTextField();
		panelBuscar.add(textFieldTitulo);
		textFieldTitulo.setColumns(15);

		JLabel lblEstilo = new JLabel("Estilo:");
		panelBuscar.add(lblEstilo);

		List<String> estilosMusicalesList = controlador.listarEstilos();
		estilosMusicalesList.add("");
		// Lista desplegable (combobox) para el estilo musical
		DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>(estilosMusicalesList.toArray(new String[0]));
		JComboBox<String> comboBoxEstilo = new JComboBox<>(modeloComboBox);
		panelBuscar.add(comboBoxEstilo);

		JLabel lblFavoritas = new JLabel("Favoritas:");
		panelBuscar.add(lblFavoritas);

		// Casilla de marcado (checkbox) para favoritas
		JCheckBox checkBoxFavoritas = new JCheckBox();
		panelBuscar.add(checkBoxFavoritas);

		JButton btnRealizarBusqueda = new JButton("Buscar");
		btnRealizarBusqueda.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Valores de los filtros
		        String interprete = textFieldInterprete.getText();
		        String titulo = textFieldTitulo.getText();
		        String estilo = (String) comboBoxEstilo.getSelectedItem();
		        boolean favoritas = checkBoxFavoritas.isSelected();

		        // Llamamos al método realizarBusqueda a través del controlador
		        List<Cancion> resultados = controlador.realizarBusqueda(interprete, titulo, estilo, favoritas);

		        // Ahora actualizamos el modelo de la tabla con los resultados obtenidos
		        DefaultTableModel modeloTabla = new DefaultTableModel() {
		            /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
		            public boolean isCellEditable(int row, int column) {
		                return column == 4; // Solo la columna de Favoritas será editable
		            }
		        };
		        // Añadir las columnas
		        modeloTabla.setColumnIdentifiers(columnas);
		        // Añadir las filas con los datos de las canciones encontradas
		        for (Cancion cancion : resultados) {
		            modeloTabla.addRow(new Object[]{
		                cancion.getID(), 
		                cancion.getTitulo(), 
		                cancion.getListaInterpretes(), 
		                cancion.getEstilo(), 
		                controlador.esFavorita(cancion.getID())
		            });
		        }
		        // Asignar el nuevo modelo a la tabla
		        tablaResultados.setModel(modeloTabla);
		        
		        // Agregar un listener para manejar los cambios en la columna de Favoritas
		        tablaResultados.getModel().addTableModelListener(new TableModelListener() {
		            @Override
		            public void tableChanged(TableModelEvent e) {
		                if (e.getType() == TableModelEvent.UPDATE) {
		                    int row = e.getFirstRow();
		                    int column = e.getColumn();
		                    if (column == 4) { // Si la columna cambiada es "Favoritas"
		                        int idCancion = (int) tablaResultados.getValueAt(row, 0);
		                        boolean isFavorita = (boolean) tablaResultados.getValueAt(row, column);
		                        controlador.invertirFavoritosID(idCancion);
		                    }
		                }
		            }
		        });
		    }
		});
		panelBuscar.add(btnRealizarBusqueda);

		tablaResultados.setPreferredScrollableViewportSize(new Dimension(400, 200));
		tablaResultados.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(tablaResultados);
		panelBuscar.add(scrollPane);
	    
	  //PANEL PARA LOS BOTONES DE REPRODUCCIÓN:
	    JPanel panelBotonesReproduccion = new JPanel();

        //Los añadirlos al panel
        //botón anterior:
        JButton btnAnterior = new JButton();
        ImageIcon anterior = new ImageIcon(getClass().getResource("/vista/imagenes/anterior.jpg"));
        Image anteRedimensionada = anterior.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon anteRedimensionado = new ImageIcon(anteRedimensionada);
        panelBotonesReproduccion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnAnterior.setIcon(anteRedimensionado);
        btnAnterior.setBorderPainted(false);
        btnAnterior.setContentAreaFilled(false);
        btnAnterior.setFocusPainted(false);
        btnAnterior.setOpaque(false);
        panelBotonesReproduccion.add(btnAnterior);
        //botón stop:
        JButton btnDetener = new JButton();
        ImageIcon stop = new ImageIcon(getClass().getResource("/vista/imagenes/stop.jpg"));
        Image stopRedimensionada = stop.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon stopRedimensionado = new ImageIcon(stopRedimensionada);
        btnDetener.setIcon(stopRedimensionado);
        btnDetener.setBorderPainted(false);
        btnDetener.setContentAreaFilled(false);
        btnDetener.setFocusPainted(false);
        btnDetener.setOpaque(false);
        panelBotonesReproduccion.add(btnDetener);
        //botón pausar:
        JButton btnPausar = new JButton();
        ImageIcon pause = new ImageIcon(getClass().getResource("/vista/imagenes/pause.jpg"));
        Image pauseRedimensionada = pause.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon pauseRedimensionado = new ImageIcon(pauseRedimensionada);
        btnPausar.setIcon(pauseRedimensionado);
        btnPausar.setBorderPainted(false);
        btnPausar.setContentAreaFilled(false);
        btnPausar.setFocusPainted(false);
        btnPausar.setOpaque(false);
        panelBotonesReproduccion.add(btnPausar);
        //botón play:
        JButton btnReproducir = new JButton();
        ImageIcon play = new ImageIcon(getClass().getResource("/vista/imagenes/play.jpg"));
        Image playRedimensionada = play.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon playRedimensionado = new ImageIcon(playRedimensionada);
        btnReproducir.setIcon(playRedimensionado);
        btnReproducir.setBorderPainted(false);
        btnReproducir.setContentAreaFilled(false);
        btnReproducir.setFocusPainted(false);
        btnReproducir.setOpaque(false);
        panelBotonesReproduccion.add(btnReproducir);
        //botón siguiente:
		JButton btnSiguiente = new JButton();
		ImageIcon siguiente = new ImageIcon(getClass().getResource("/vista/imagenes/siguiente.jpg"));
		Image siguienteRedimensionada = siguiente.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon siguienteRedimensionado = new ImageIcon(siguienteRedimensionada);
		btnSiguiente.setIcon(siguienteRedimensionado);
		btnSiguiente.setBorderPainted(false);
		btnSiguiente.setContentAreaFilled(false);
		btnSiguiente.setFocusPainted(false);
		btnSiguiente.setOpaque(false);
        panelBotonesReproduccion.add(btnSiguiente);


     //Agregar el panel de botones a la parte inferior de la ventana 
        getContentPane().add(panelBotonesReproduccion, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        
        //Hacer visible la ventana
        setVisible(true);

        btnAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el objeto que puede invocar previousSong() y lo ejecuta
                controlador.previousSong();
            }
        });

        
        btnDetener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el objeto que puede invocar stopSong() y lo ejecuta
                controlador.stopSong();
            }
        });

        btnPausar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el objeto que puede invocar pauseSong() y lo ejecuta
                controlador.pauseSong();
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el objeto que puede invocar nextSong() y lo ejecuta
                controlador.nextSong();
            }
        });

        btnReproducir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el objeto que puede invocar playSong() y lo ejecuta
                controlador.playSong();
            }
        });

		
		JLabel lblPanelbuscar = new JLabel("PanelBuscar");
		panelBuscar.add(lblPanelbuscar);
		
		
		//GESTIÓN: 
		// Para crear y editar playlists:
		JPanel panelGestion = new JPanel();
		panelCardLayout.add(panelGestion, "panelGestion");
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.Y_AXIS)); // Cambiar el layout a BoxLayout

		// Para introducir el título de la playlist:
		JTextField textFieldTituloPlaylist = new JTextField();
		textFieldTituloPlaylist.setPreferredSize(new Dimension(150, 20)); // Establece un tamaño más pequeño
		textFieldTituloPlaylist.setText("Introduce el título de la playlist"); // Texto de sugerencia
		panelGestion.add(textFieldTituloPlaylist);

		// Botones para crear y eliminar playlists:
		JButton btnCrearPlaylist = new JButton("Crear/Guardar Playlist");
		btnCrearPlaylist.setMinimumSize(new Dimension(80, 30)); // Establecer tamaño mínimo
		JButton btnEliminarPlaylist = new JButton("Eliminar Playlist");
		btnEliminarPlaylist.setMinimumSize(new Dimension(80, 30)); // Establecer tamaño mínimo
		JPanel panelBotonesPlaylist = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Usar FlowLayout y alinear a la izquierda
		panelBotonesPlaylist.setPreferredSize(new Dimension(200, 50));
		panelGestion.setPreferredSize(new Dimension(300, 400));
		panelBotonesPlaylist.add(btnCrearPlaylist);
		panelBotonesPlaylist.add(btnEliminarPlaylist);
		panelGestion.add(panelBotonesPlaylist); // Agregar el panel de botones en la parte superior

		// Para mostrar canciones seleccionadas, la tabla: //TODO revisar los cheks
		DefaultTableModel modeloTablaCanciones = new DefaultTableModel() {
		    @Override
		    public Class<?> getColumnClass(int columnIndex) {
		        if (columnIndex == 4) {
		            return Boolean.class; // La columna "Check" maneja Boolean
		        }
		        return super.getColumnClass(columnIndex);
		    }
		};

		modeloTablaCanciones.setColumnIdentifiers(new String[]{"ID", "Título", "Intérprete", "Estilo", "Check"});
		JTable tablaCanciones = new JTable(modeloTablaCanciones);
		tablaCanciones.getColumnModel().getColumn(0).setPreferredWidth(50); // Ajustar el ancho de la columna ID
		tablaCanciones.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JCheckBox())); // Editor para la columna "Check"
		tablaCanciones.getColumnModel().getColumn(4).setCellRenderer(tablaCanciones.getDefaultRenderer(Boolean.class)); // Renderer para la columna "Check"
		JScrollPane scrollPaneTabla = new JScrollPane(tablaCanciones);
		panelGestion.add(scrollPaneTabla); // Agregar la tabla al panel

		// Para eliminar canciones de la lista, el botón:
		JButton btnMarcarTodo = new JButton("Marcar/Desmarcar todo");
		panelGestion.add(btnMarcarTodo);

		// AtomicBoolean para verificar si las canciones han sido cargadas
		AtomicBoolean cancionesCargadas = new AtomicBoolean(false);
		AtomicBoolean todasMarcadas = new AtomicBoolean(false); // Variable de estado para marcar/desmarcar todo

		// Lógica para manejar las acciones del botón "Crear/Guardar Playlist":
		btnCrearPlaylist.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String tituloPlaylist = textFieldTituloPlaylist.getText();
		        if (!tituloPlaylist.isEmpty()) {
		            if (!cancionesCargadas.get()) {
		                // Primera vez que se pulsa el botón, cargar canciones
		                PlayList playlist = controlador.existePlayList(tituloPlaylist);
		                List<Cancion> cancionesFavoritos = controlador.getPlayListFavoritos();

		                // Limpiar la tabla antes de cargar nuevas canciones
		                modeloTablaCanciones.setRowCount(0);

		                // Cargar canciones de la playlist especificada
		                if (playlist != null) {
		                    for (Cancion cancion : playlist.getPlayList()) {
		                        modeloTablaCanciones.addRow(new Object[]{
		                            cancion.getID(),
		                            cancion.getTitulo(),
		                            String.join(", ", cancion.getListaInterpretes()),
		                            cancion.getEstilo(),
		                            true
		                        });
		                    }
		                }

		                // Cargar canciones de favoritos
		                for (Cancion cancion : cancionesFavoritos) {
		                    modeloTablaCanciones.addRow(new Object[]{
		                        cancion.getID(),
		                        cancion.getTitulo(),
		                        String.join(", ", cancion.getListaInterpretes()),
		                        cancion.getEstilo(),
		                        false
		                    });
		                }

		                JOptionPane.showMessageDialog(null, "Entrando en modo edición de playLists");
		                textFieldTituloPlaylist.setEditable(false);
		                cancionesCargadas.set(true);
		            } else {
		                // Segunda vez que se pulsa el botón, guardar cambios
		                List<Integer> idCancionesSeleccionadas = new LinkedList<>();
		                for (int i = 0; i < modeloTablaCanciones.getRowCount(); i++) {
		                    boolean check = (boolean) modeloTablaCanciones.getValueAt(i, 4);
		                    if (check) {
		                        int idCancion = (int) modeloTablaCanciones.getValueAt(i, 0);
		                        idCancionesSeleccionadas.add(idCancion);
		                    }
		                }
		                boolean creado = controlador.guardarPlayListDesdeVentana(tituloPlaylist, idCancionesSeleccionadas);
		                if (creado) {
		                    JOptionPane.showMessageDialog(null, "Playlist creada/guardada exitosamente.");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Error al guardar la playlist.");
		                }
		                cancionesCargadas.set(false);
		                textFieldTituloPlaylist.setEditable(true);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Por favor, introduce un título para la playlist."); //TODO cambiar a etiqueta + campo de texto
		        }
		    }
		});
		
		// Lógica para manejar las acciones del botón "Marcar/Desmarcar todo":
		btnMarcarTodo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        boolean marcar = !todasMarcadas.get();
		        for (int i = 0; i < modeloTablaCanciones.getRowCount(); i++) {
		            modeloTablaCanciones.setValueAt(marcar, i, 4);
		        }
		        todasMarcadas.set(marcar);
		    }
		});
		
		// Para el botón eliminar:
		btnEliminarPlaylist.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String tituloPlaylist = textFieldTituloPlaylist.getText();
		        if (!tituloPlaylist.isEmpty()) {
		            boolean eliminado = controlador.removePlaylist(tituloPlaylist);
		            if (eliminado) {
		                JOptionPane.showMessageDialog(null, "Playlist eliminada exitosamente.");
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar la playlist.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Por favor, introduce un título para la playlist.");
		        }
		    }
		});

		//RECIENTES:
		JPanel panelRecientes = new JPanel();
		//panelCardLayout.add(panelRecientes, "panelRecientes");
		panelRecientes.setLayout(new BorderLayout());
		
		
		
	    //List<PlayList> ultimasCanciones = this.getPlayListUsuario();
	    LinkedList<Cancion> ultimasCanciones = (LinkedList<Cancion>) playlist.getPlayList();

	    
	    JScrollPane scrollPaneRecientes;

	    if (ultimasCanciones.isEmpty()) {
	        System.out.println("La lista de canciones recientes está vacía.");
	        // Crear una tabla vacía
	        DefaultTableModel modeloTablaRecientes = new DefaultTableModel();
	        modeloTablaRecientes.addColumn("Título");
	        modeloTablaRecientes.addColumn("Intérprete");
	        modeloTablaRecientes.addColumn("Estilo");
	        modeloTablaRecientes.addColumn("Favoritas");

	        JTable tablaRecientes = new JTable(modeloTablaRecientes);
	        scrollPaneRecientes = new JScrollPane(tablaRecientes);
	        panelRecientes.add(scrollPaneRecientes, BorderLayout.CENTER);
	    } else {
	        // Crear una tabla para mostrar las últimas canciones reproducidas
	        DefaultTableModel modeloTablaRecientes = new DefaultTableModel();
	        modeloTablaRecientes.addColumn("Título");
	        modeloTablaRecientes.addColumn("Intérprete");
	        modeloTablaRecientes.addColumn("Estilo");
	        modeloTablaRecientes.addColumn("Favoritas");

	        for (Cancion cancion : ultimasCanciones) {
	            modeloTablaRecientes.addRow(new Object[]{cancion.getTitulo(), cancion.getListaInterpretes(), cancion.getEstilo(), controlador.esFavorita(cancion.getID())});
	        }

	        JTable tablaRecientes = new JTable(modeloTablaRecientes);
	        scrollPaneRecientes = new JScrollPane(tablaRecientes);
	        panelRecientes.add(scrollPaneRecientes, BorderLayout.CENTER);
	    }

	    // Mover esta línea fuera del bloque if-else
	    panelCardLayout.add(panelRecientes, "panelRecientes");
	    CardLayout cardLayout = (CardLayout) panelCardLayout.getLayout();
	    cardLayout.show(panelCardLayout, "panelRecientes");

	    // Revalidar el panel para actualizar la interfaz de usuario
	    panelRecientes.revalidate();
	    
		
		JLabel lblpanelRecientes = new JLabel("PanelRecientes");
		panelRecientes.add(lblpanelRecientes);
		
		
		//PANEL PLAYLISTS //TODO
		// PANEL PLAYLISTS
		JPanel panelPlaylists = new JPanel(new BorderLayout());
		panelCardLayout.add(panelPlaylists, "panelPlaylists");

		// Obtener la lista de playlists asociadas al usuario
		List<PlayList> playlistsUsuario = controlador.getPlayListUsuario();

		// Crear un panel para contener las etiquetas de playlists
		JPanel contenedorPlaylists = new JPanel();
		contenedorPlaylists.setLayout(new GridLayout(playlistsUsuario.size(), 1));

		// Crear una lista de strings para almacenar los nombres de las playlists y agregar etiquetas al contenedor
		for (PlayList playlist : playlistsUsuario) {
		    String nombrePlaylist = playlist.getNombre();
		    JLabel labelPlaylist = new JLabel(nombrePlaylist); // Crear una etiqueta con el nombre de la playlist
		    contenedorPlaylists.add(labelPlaylist); // Agregar la etiqueta al contenedor
		}

		// Agregar el contenedor al panel principal
		panelPlaylists.add(new JScrollPane(contenedorPlaylists), BorderLayout.CENTER);

		// Etiqueta del panel
		JLabel lblPanelPlaylists = new JLabel("Playlists del Usuario", SwingConstants.CENTER);
		panelPlaylists.add(lblPanelPlaylists, BorderLayout.NORTH);

		

		
		/*
		// Crear el campo para introducir el título de la playlist
		JTextField textFieldTituloPlaylist1 = new JTextField(20);

		// Botón para crear la playlist
		JButton btnCrearPlaylist1 = new JButton("Crear Playlist");

		// Botón para eliminar la playlist
		JButton btnEliminarPlaylist1 = new JButton("Eliminar Playlist");

		// Botón para eliminar canciones de la lista //TODO POR QUÉ ESTÁ ESTA FUNCIONALIDAD AQUÍ??!!
		JButton btnEliminarCancion1 = new JButton("Eliminar de la Lista");
		//btnCrearPlaylist.setPreferredSize(new Dimension(12, 30));
		//btnEliminarPlaylist.setPreferredSize(new Dimension(12, 30));
		//btnEliminarCancion.setPreferredSize(new Dimension(12, 30));
		

		// Añadir acciones a los botones
				btnCrearPlaylist1.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        String tituloPlaylist = textFieldTituloPlaylist1.getText();
				        if (!tituloPlaylist.isEmpty() || tituloPlaylist!="Canciones Recientes") {
				            boolean creado = controlador.guardarPlayListDesdeVentana(tituloPlaylist);
				            if (creado) {
				                JOptionPane.showMessageDialog(null, "La playlist se creó exitosamente.");
				            } else {
				                JOptionPane.showMessageDialog(null, "No se pudo crear la playlist.");
				            }
				        } else {
				            JOptionPane.showMessageDialog(null, "Por favor, ingresa un título valido para la playlist.");
				        }
				    }
				});

		btnEliminarPlaylist1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener la playlist seleccionada en la interfaz (si hay alguna)
		        PlayList playlistSeleccionada = controlador.getPlayListActual();
		        if (playlistSeleccionada != null) {
		            boolean eliminado = controlador.removePlaylist(playlistSeleccionada);
		            if (eliminado) {
		                JOptionPane.showMessageDialog(null, "La playlist se eliminó exitosamente.");
		            } else {
		                JOptionPane.showMessageDialog(null, "No se pudo eliminar la playlist.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona una playlist.");
		        }
		    }
		});


		// Crear tabla para las canciones
		DefaultTableModel modeloTablaCanciones1 = new DefaultTableModel();
		modeloTablaCanciones1.addColumn("Título");
		modeloTablaCanciones1.addColumn("Intérprete");
		modeloTablaCanciones1.addColumn("Estilo");
		modeloTablaCanciones1.addColumn("Favoritas");
		
		controlador.isEmptyPlayListActual();
		// Agregar las canciones al modelo de la tablaaa
		if(!controlador.isEmptyPlayListActual()) {
		List<Cancion> listaCanciones = controlador.getCancionesPlayListActual();
		
		for (Cancion cancion : listaCanciones) {
		    modeloTablaCanciones1.addRow(new Object[]{cancion.getTitulo(), cancion.getListaInterpretes(), cancion.getEstilo(), controlador.esFavorita(cancion.getID())});
		}}



		// Crear la tabla con el modelo de datos
		JTable tablaCanciones1 = new JTable(modeloTablaCanciones1);

		// Agregar la tabla a un JScrollPane para permitir el desplazamiento si hay muchas canciones
		JScrollPane scrollPaneTablaCanciones = new JScrollPane(tablaCanciones1);

		// Agregar el JScrollPane al panel
		panelPlaylists.add(scrollPaneTablaCanciones, BorderLayout.CENTER);

		// Añadir acciones a los botones
		btnEliminarCancion1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        PlayList listaCanciones = controlador.getPlayListActual();
		        if (listaCanciones != null) {
		            List<Cancion> canciones = listaCanciones.getPlayList();
		            int[] filasSeleccionadas = tablaCanciones1.getSelectedRows();
		            if (filasSeleccionadas.length > 0) {
		                List<Cancion> cancionesSeleccionadas = new ArrayList<>();
		                for (int fila : filasSeleccionadas) {
		                    // Obtener la canción seleccionada en la tabla
		                    Cancion cancion = canciones.get(fila);
		                    cancionesSeleccionadas.add(cancion);
		                }
		                // Llamar al método removeCancionesDePlaylist del controlador
		                boolean eliminado = controlador.removeCancionesDePlaylistFavoritos(cancionesSeleccionadas);
		                if (eliminado) {
		                    JOptionPane.showMessageDialog(null, "Las canciones se eliminaron exitosamente de la playlist.");
		                } else {
		                    JOptionPane.showMessageDialog(null, "No se pudieron eliminar las canciones de la playlist.");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Por favor, selecciona al menos una canción.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "No hay una lista de canciones disponible.");
		        }
		    }
		});


		
		// Establecer el layout del panel
		panelPlaylists.setLayout(new BorderLayout());

		// Crear un panel para contener los botones en la parte sur
		JPanel panelBotonesMisPlayLists = new JPanel(new GridLayout(1, 3)); // GridLayout con una fila y tres columnas

		// Agregar los botones al panel de botones
		panelBotonesMisPlayLists.add(btnCrearPlaylist1);
		panelBotonesMisPlayLists.add(btnEliminarPlaylist1);
		panelBotonesMisPlayLists.add(btnEliminarCancion1);

		// Agregar el panel de botones al sur del panel principal
		panelPlaylists.add(panelBotonesMisPlayLists, BorderLayout.SOUTH);

;
		
		JLabel lblpanelPlaylists = new JLabel("PanelPlaylists");
		panelPlaylists.add(lblpanelPlaylists);
		*/
	}
}
