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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*<<<<<<< HEAD
=======

>>>>>>> branch 'main' of https://github.com/navimI/TDS.git
*/
import pulsador.Luz;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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

        // Establecer la posición y tamaño del componente Luz
        luz.setBounds(50, 50, 100, 100); // Posición (x, y) y tamaño (ancho, alto)
        
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
		//JLabel lblBienvenido = new JLabel("Bienvenido, "+Controlador.getUnicaInstancia().getUsuarioActualField("user"));
		JLabel lblBienvenido = new JLabel("Bienvenido, [usuario]");
		panel.add(lblBienvenido);
		
		JButton btnPremium = new JButton("Premium");
		panel.add(btnPremium);
		
		JButton btnSalir = new JButton("Salir");
		panel.add(btnSalir);
		
		panelCentro.add(panelCardLayout, BorderLayout.CENTER);
		panelCardLayout.setLayout(new CardLayout(0, 0));
		
		
		
		
		//PANEL BUSCAR:
		JPanel panelBuscar = new JPanel();
		panelCardLayout.add(panelBuscar, "panelBuscar");
		
		// Crear una tabla vacía para mostrar los resultados
		tablaResultados = new JTable();
		// Configurar la tabla 
		tablaResultados.setPreferredScrollableViewportSize(new Dimension(400, 200));
		tablaResultados.setFillsViewportHeight(true);

		// Añadir la tabla a un JScrollPane para permitir el desplazamiento
		JScrollPane scrollPane1 = new JScrollPane(tablaResultados);
		panelBuscar.add(scrollPane1);
		//String[] columnas = {"Título", "Intérprete", "Estilo", "Favoritas"};
	    String[] columnas = {"ID", "Título", "Intérprete", "Estilo", "Favoritas", "Seleccionar"};


		//Componentes para el panel Buscar:
		JLabel lblInterprete = new JLabel("Intérprete:");
		panelBuscar.add(lblInterprete);
		//Configurar el tamaño mínimo y máximo del panelBuscar
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
		//Lista desplegable (combobox) para el estilo musical
		String[] estilosMusicales = {"Pop", "Rock", "Electrónica", "Hip-hop", "Otros"};
		JComboBox<String> comboBoxEstilo = new JComboBox<>(estilosMusicales);
		panelBuscar.add(comboBoxEstilo);

		JLabel lblFavoritas = new JLabel("Favoritas:");
		panelBuscar.add(lblFavoritas);

		//Casilla de marcado (checkbox) para favoritas
		JCheckBox checkBoxFavoritas = new JCheckBox();
		panelBuscar.add(checkBoxFavoritas);

		
		JButton btnRealizarBusqueda = new JButton("Buscar");
	    btnRealizarBusqueda.addActionListener(new ActionListener() {
	        // Dentro del ActionListener del botón btnRealizarBusqueda
	    	// Dentro del ActionListener del botón btnRealizarBusqueda
	    	public void actionPerformed(ActionEvent e) {
	    	    // Valores de los filtros
	    	    String interprete = textFieldInterprete.getText();
	    	    String titulo = textFieldTitulo.getText();
	    	    String estilo = (String) comboBoxEstilo.getSelectedItem();
	    	    boolean favoritas = checkBoxFavoritas.isSelected();
	    	    
	    	    // Llamamos al método realizarBusqueda a través del controlador
	    	    List<Cancion> resultados = controlador.realizarBusqueda(interprete, titulo, estilo, favoritas);
	    	    
	    	    // Ahora actualizamos el modelo de la tabla con los resultados obtenidos
	    	    DefaultTableModel modeloTabla = new DefaultTableModel();
	    	    // Añadir las columnas
	    	    modeloTabla.setColumnIdentifiers(columnas);
	    	    // Añadir las filas con los datos de las canciones encontradas
	    	    for (Cancion cancion : resultados) {
	    	        modeloTabla.addRow(new Object[]{cancion.getID(), cancion.getTitulo(), cancion.getListaInterpretes(), cancion.getEstilo(), controlador.esFavorita(cancion.getID()), false});	    	    }
	    	    // Asignar el nuevo modelo a la tabla
	    	    tablaResultados.setModel(modeloTabla);
	    	}


	    });
	    panelBuscar.add(btnRealizarBusqueda);
	 
		
		tablaResultados.setPreferredScrollableViewportSize(new Dimension(400, 200));
	    tablaResultados.setFillsViewportHeight(true);
	    JScrollPane scrollPane = new JScrollPane(tablaResultados);
	    panelBuscar.add(scrollPane);
	
	    //TODO cómo añadir la cancion que salga en el panel buscar a la lista temporal.
	    //pones la ID en una de los campos y luego paso la ID al catálogo
	    //con el panel buscar, donde set column, añadir otra columna que sea id, cuando se recuperen los datos, que aparezca el valor de id, en el for cancion resultados
	    //añadir otra columna que sea un seleccionable, que si esta activado, esa cancion la cargue a la playlist temporal, y si estaba seleccionada y la deselecciona, que la quite
	    //estas funciones aún no están en el Controlador. 
	    
	    //TODO - cambiar las llamadas al Controlador para que sean con Controlador.getUnicaInstancia()... 
	    //TODO - chequear los runeables de lo sbotones de reproduccion, son realmente necesarios????
	    

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
                controlador.obtenerPreviousSongRunnable().run();
            }
        });

        
        btnDetener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el objeto que puede invocar stopSong() y lo ejecuta
                controlador.obtenerStopSongRunnable().run();
            }
        });

        btnPausar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el objeto que puede invocar pauseSong() y lo ejecuta
                controlador.obtenerPauseSongRunnable().run();
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el objeto que puede invocar nextSong() y lo ejecuta
                controlador.obtenerNextSongRunnable().run();
            }
        });

        btnReproducir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el objeto que puede invocar playSong() y lo ejecuta
                controlador.obtenerPlaySongRunnable().run();
            }
        });



		//Añadir los botones al panelBuscar:
//		panelBuscar.add(btnReproducir);
//		panelBuscar.add(btnDetener);
//		panelBuscar.add(btnPausar);
//		panelBuscar.add(btnSiguiente);
//		panelBuscar.add(btnAnterior);

		
		
		JLabel lblPanelbuscar = new JLabel("PanelBuscar");
		panelBuscar.add(lblPanelbuscar);
		
		
		/*
		//GESTIÓN:
		//Para crear y editar playlists:		
		JPanel panelGestion = new JPanel();
		panelCardLayout.add(panelGestion, "panelGestion");
		panelGestion.setLayout(new BoxLayout(panelGestion, BoxLayout.Y_AXIS)); // Cambiar el layout a BoxLayout
		//panelGestion.setLayout(new BorderLayout());
		//Para introducir el título de la playlist:
	    textFieldTituloPlaylist = new JTextField();
	    textFieldTituloPlaylist.setPreferredSize(new Dimension(150, 20)); // Establece un tamaño más pequeño
	    textFieldTituloPlaylist.setText("Introduce el título de la playlist"); // Texto de sugerencia
	    panelGestion.add(textFieldTituloPlaylist, BorderLayout.NORTH);


	    // Botones para crear y eliminar playlists:
	    JButton btnCrearPlaylist = new JButton("Crear");
	    JButton btnEliminarPlaylist = new JButton("Eliminar");
	    JPanel panelBotonesPlaylist = new JPanel();
	    panelBotonesPlaylist.add(btnCrearPlaylist);
	    panelBotonesPlaylist.add(btnEliminarPlaylist);
	    panelGestion.add(panelBotonesPlaylist); // Agregar el panel de botones en la parte superior

	    //Para mostrar canciones seleccionadas, la tabla:
	    modeloTablaCanciones = new DefaultTableModel();
	    modeloTablaCanciones.setColumnIdentifiers(new String[]{"Título", "Intérprete", "Estilo", "Favoritas"});
	    JTable tablaCanciones = new JTable(modeloTablaCanciones);
	    JScrollPane scrollPaneTabla = new JScrollPane(tablaCanciones);
	    panelGestion.add(scrollPaneTabla, BorderLayout.CENTER);
	    //Para eliminar canciones de la lista, el bitón:
	    JButton btnEliminarCancion = new JButton("Eliminar de la Lista");
	    
	    //Lógica que llama a la clase PlayList:
	 //Llamada para crear o editar playlist
        btnCrearPlaylist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tituloPlaylist = textFieldTituloPlaylist.getText();
                // Verificar si el título de la playlist ya existe
                if (playlist != null && playlist.getNombre().equals(tituloPlaylist)) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Desea editar la playlist existente?",
                            "Editar Playlist", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        // Aquí se implementaría la lógica para editar la playlist
                        // Por ahora, simplemente mostramos un mensaje
                        JOptionPane.showMessageDialog(null, "Editando la playlist existente: " + playlist.getNombre());
                    }
                } else {
                    // Crear una nueva playlist
                    playlist = new PlayList(tituloPlaylist);
                    // Mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Playlist creada: " + tituloPlaylist);
                }
            }
        });

        // Llamada para eliminar playlist
        btnEliminarPlaylist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (playlist != null) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la playlist actual?",
                            "Eliminar Playlist", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        // Eliminar la playlist
                        playlist = null;
                        textFieldTituloPlaylist.setText("");
                        modeloTablaCanciones.setRowCount(0); // Limpiar la tabla de canciones
                        // Mostrar un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "Playlist eliminada.");
                    }
                } else {
                    // Mostrar un mensaje de error si no hay playlist para eliminar
                    JOptionPane.showMessageDialog(null, "No hay ninguna playlist para eliminar.");
                }
            }
        });

        // Llamada para eliminar canciones de la lista
        btnEliminarCancion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaCanciones.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Eliminar la canción seleccionada de la playlist
                    modeloTablaCanciones.removeRow(filaSeleccionada);
                } else {
                    // Mostrar un mensaje de error si no se ha seleccionado ninguna canción
                    JOptionPane.showMessageDialog(null, "Seleccione una canción para eliminar de la lista.");
                }
            }
        });
	    
	    
		
		JLabel lblPanelgestion = new JLabel("PanelGestion");
		panelGestion.add(lblPanelgestion);
		
		*/
		
		
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
		JButton btnCrearPlaylist = new JButton("Crear");
		JButton btnEliminarPlaylist = new JButton("Eliminar");
		JPanel panelBotonesPlaylist = new JPanel();
		panelBotonesPlaylist.add(btnCrearPlaylist);
		panelBotonesPlaylist.add(btnEliminarPlaylist);
		panelGestion.add(panelBotonesPlaylist); // Agregar el panel de botones en la parte superior

		// Para mostrar canciones seleccionadas, la tabla:
		DefaultTableModel modeloTablaCanciones = new DefaultTableModel();
		modeloTablaCanciones.setColumnIdentifiers(new String[]{"Título", "Intérprete", "Estilo", "Favoritas"});
		JTable tablaCanciones = new JTable(modeloTablaCanciones);
		JScrollPane scrollPaneTabla = new JScrollPane(tablaCanciones);
		panelGestion.add(scrollPaneTabla); // Agregar la tabla al panel

		// Para eliminar canciones de la lista, el botón:
		JButton btnEliminarCancion = new JButton("Eliminar de la Lista");
		panelGestion.add(btnEliminarCancion);

		// Lógica para manejar las acciones de los botones:
		// Lógica para manejar las acciones de los botones:
		btnCrearPlaylist.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String tituloPlaylist = textFieldTituloPlaylist.getText();
		        if (!tituloPlaylist.isEmpty()) {
		            boolean creado = controlador.guardarPlayListDesdeVentana(tituloPlaylist);
		            if (creado) {
		                // Playlist creada exitosamente
		                JOptionPane.showMessageDialog(null, "Playlist creada exitosamente.");
		            } else {
		                // No se pudo crear la playlist
		                JOptionPane.showMessageDialog(null, "Error al crear la playlist.");
		            }
		        } else {
		            // El campo del título de la playlist está vacío
		            JOptionPane.showMessageDialog(null, "Por favor, introduce un título para la playlist.");
		        }
		    }
		});

		btnEliminarPlaylist.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener la fila seleccionada
		        int filaSeleccionada = tablaCanciones.getSelectedRow();
		        if (filaSeleccionada != -1) {
		            // Obtener el título de la playlist seleccionada
		            String tituloPlaylist = (String) tablaCanciones.getValueAt(filaSeleccionada, 0);
		            
		            // Obtener la instancia actual de la lista de reproducción
		            PlayList listaSeleccionada = controlador.getPlayListActual();
		            
		            // Llamar al método removePlaylist del controlador con la instancia de PlayList
		            boolean eliminado = controlador.removePlaylist(listaSeleccionada);
		            if (eliminado) {
		                // Playlist eliminada exitosamente
		                JOptionPane.showMessageDialog(null, "Playlist eliminada exitosamente.");
		            } else {
		                // No se pudo eliminar la playlist
		                JOptionPane.showMessageDialog(null, "Error al eliminar la playlist.");
		            }
		        } else {
		            // No se seleccionó ninguna fila
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona una playlist.");
		        }
		    }
		});


		btnEliminarCancion.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int[] filasSeleccionadas = tablaCanciones.getSelectedRows();
		        if (filasSeleccionadas.length > 0) {
		            // Obtener las canciones seleccionadas directamente de la tabla
		            LinkedList<Cancion> cancionesSeleccionadas = new LinkedList<>();
		            for (int fila : filasSeleccionadas) {
		                String tituloCancion = (String) tablaCanciones.getValueAt(fila, 0);
		                String interpreteCancion = (String) tablaCanciones.getValueAt(fila, 1);
		                String estiloCancion = (String) tablaCanciones.getValueAt(fila, 2);
		                boolean favorita = (boolean) tablaCanciones.getValueAt(fila, 3);
		                // Crear la canción y agregarla a la lista
		                Cancion cancion = new Cancion(tituloCancion, interpreteCancion);
		                cancionesSeleccionadas.add(cancion);
		            }
		            // Llamar al método removeCancionesDePlaylist del controlador
		            boolean eliminado = controlador.removeCancionesDePlaylistDesdeMain(cancionesSeleccionadas);
		            if (eliminado) {
		                JOptionPane.showMessageDialog(null, "Canciones eliminadas exitosamente de la playlist.");
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar las canciones de la playlist.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona al menos una canción.");
		        }
		    }
		});

		JLabel lblPanelgestion = new JLabel("PanelGestion");
		panelGestion.add(lblPanelgestion);
		
		
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
		
		
		//PANEL PLAYLISTS
		JPanel panelPlaylists = new JPanel();
		panelCardLayout.add(panelPlaylists, "panelPlaylists");
		panelPlaylists.setLayout(new BorderLayout());
		
		
		// Crear el campo para introducir el título de la playlist
		JTextField textFieldTituloPlaylist1 = new JTextField(20);

		// Botón para crear la playlist
		JButton btnCrearPlaylist1 = new JButton("Crear Playlist");

		// Botón para eliminar la playlist
		JButton btnEliminarPlaylist1 = new JButton("Eliminar Playlist");

		// Botón para eliminar canciones de la lista
		JButton btnEliminarCancion1 = new JButton("Eliminar de la Lista");
		btnCrearPlaylist.setPreferredSize(new Dimension(12, 30));
		btnEliminarPlaylist.setPreferredSize(new Dimension(12, 30));
		btnEliminarCancion.setPreferredSize(new Dimension(12, 30));
		

		// Añadir acciones a los botones
		btnCrearPlaylist1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String tituloPlaylist = textFieldTituloPlaylist1.getText();
		        if (!tituloPlaylist.isEmpty()) {
		            boolean creado = controlador.guardarPlayListDesdeVentana(tituloPlaylist);
		            if (creado) {
		                JOptionPane.showMessageDialog(null, "La playlist se creó exitosamente.");
		            } else {
		                JOptionPane.showMessageDialog(null, "No se pudo crear la playlist.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Por favor, ingresa un título para la playlist.");
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

		PlayList listaDeReproduccion = controlador.getPlayListActual();
		// Agregar las canciones al modelo de la tablaaa
		List<Cancion> listaCanciones = listaDeReproduccion.getPlayList();
		for (Cancion cancion : listaCanciones) {
		    modeloTablaCanciones1.addRow(new Object[]{cancion.getTitulo(), cancion.getListaInterpretes(), cancion.getEstilo(), controlador.esFavorita(cancion.getID())});
		}



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
		
		
	
		

	}
}
