package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.eclipse.persistence.internal.oxm.schema.model.List;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.Box;
/*<<<<<<< HEAD
=======

>>>>>>> branch 'main' of https://github.com/navimI/TDS.git
*/
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Dimension;


public class VentanaMain extends JFrame {

	/**
	 * 
	 */
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

		/*JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBuscarCanciones ventanaBuscar = new VentanaBuscarCanciones();
				Point botonLocation = getLocationOnScreen();
				ventanaBuscar.setLocation(botonLocation);
				ventanaBuscar.setVisible(true);
				//dispose();
				//Cuando el user hace click en el botón "Buscar":
				//Creamos la ventana VEntanaBuscarCanciones
				//contentPane.setVisible(false);
			}
		});*/
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
		
		JLabel lblBienvenido = new JLabel("Bienvenido, ");
		panel.add(lblBienvenido);
		
		JButton btnPremium = new JButton("Premium");
		panel.add(btnPremium);
		
		JButton btnSalir = new JButton("Salir");
		panel.add(btnSalir);
		
		panelCentro.add(panelCardLayout, BorderLayout.CENTER);
		panelCardLayout.setLayout(new CardLayout(0, 0));
		
		
		JPanel panelBuscar = new JPanel();
		panelCardLayout.add(panelBuscar, "panelBuscar");
		
		
		///////////
		

	    // Crear una tabla para mostrar los resultados (mover esta sección antes de su uso)
	    String[] columnas = {"Título", "Intérprete", "Estilo", "Favoritas"};
	    Object[][] datos = {{"Canción 1", "Intérprete 1", "Pop", false},
	                        {"Canción 2", "Intérprete 2", "Rock", true},
	                        {"Canción 3", "Intérprete 3", "Jazz", false}};
	    tablaResultados = new JTable(datos, columnas);

	    // Configurar la tabla 
	    tablaResultados.setPreferredScrollableViewportSize(new Dimension(400, 200));
	    tablaResultados.setFillsViewportHeight(true);

	    // Añadir la tabla a un JScrollPane para permitir el desplazamiento
	    JScrollPane scrollPane1 = new JScrollPane(tablaResultados);
	    panelBuscar.add(scrollPane1);

	    // Añadir acción de doble clic a la tabla para reproducir la canción seleccionada
	    tablaResultados.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                // Obtener la fila seleccionada
	                int filaSeleccionada = tablaResultados.getSelectedRow();
	                // TODO Lógica para reproducir la canción de la fila seleccionada
	                System.out.println("Reproduciendo canción de la fila: " + filaSeleccionada);
	            }
	        }
	    });
	    ///////////////
		
		

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

		//Botón para realizar la búsqueda
		JButton btnRealizarBusqueda = new JButton("Buscar");
		btnRealizarBusqueda.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        //Obtener los valores de los campos y realizar la búsqueda
		        String interprete = textFieldInterprete.getText();
		        String titulo = textFieldTitulo.getText();
		        String estilo = (String) comboBoxEstilo.getSelectedItem();
		        boolean favoritas = checkBoxFavoritas.isSelected();

		        //Realizar la acción correspondiente con los valores de búsqueda
		        System.out.println("Realizando búsqueda con los siguientes filtros:");
		        System.out.println("Intérprete: " + interprete);
		        System.out.println("Título: " + titulo);
		        System.out.println("Estilo: " + estilo);
		        System.out.println("Favoritas: " + favoritas);
		    }
		});
		panelBuscar.add(btnRealizarBusqueda);
		
		tablaResultados.setPreferredScrollableViewportSize(new Dimension(400, 200));
	    tablaResultados.setFillsViewportHeight(true);
	    JScrollPane scrollPane = new JScrollPane(tablaResultados);
	    panelBuscar.add(scrollPane);
	    
	    /*
	 // Crear una tabla para mostrar los resultados
	    String[] columnas = {"Título", "Intérprete", "Estilo", "Favoritas"};
	    Object[][] datos = {{"Canción 1", "Intérprete 1", "Pop", false},
	                        {"Canción 2", "Intérprete 2", "Rock", true},
	                        {"Canción 3", "Intérprete 3", "Jazz", false}};
	 // Crear una tabla para mostrar los resultados
	    tablaResultados = new JTable(datos, columnas);

	    // Configurar la tabla 
	    tablaResultados.setPreferredScrollableViewportSize(new Dimension(400, 200));
	    tablaResultados.setFillsViewportHeight(true);

	    // Añadir la tabla a un JScrollPane para permitir el desplazamiento
	    JScrollPane scrollPane1 = new JScrollPane(tablaResultados);
	    panelBuscar.add(scrollPane1);

	    // Añadir acción de doble clic a la tabla para reproducir la canción seleccionada
	    tablaResultados.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                // Obtener la fila seleccionada
	                int filaSeleccionada = tablaResultados.getSelectedRow();
	                // TODO Lógica para reproducir la canción de la fila seleccionada
	                System.out.println("Reproduciendo canción de la fila: " + filaSeleccionada);
	            }
	        }
	    });
	    
	 // Añadir acción de doble clic a la tabla para reproducir la canción seleccionada
	    tablaResultados.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                // Obtener la fila seleccionada
	                int filaSeleccionada = tablaResultados.getSelectedRow();
	                // TODO Lógica para reproducir la canción de la fila seleccionada
	                System.out.println("Reproduciendo canción de la fila: " + filaSeleccionada);
	            }
	        }
	    }); 
	
	    */
	    /*
		//Botón para añadir a la lista
	    JButton btnAnadirLista = new JButton("Añadir a Lista");
		btnAnadirLista.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener las filas seleccionadas
		        int[] filasSeleccionadas = tablaResultados.getSelectedRows();

		        if (filasSeleccionadas.length > 0) {
		            // Obtener las playlists disponibles
		            String[] playlistsDisponibles = getPlaylists();

		            // Crear el JComboBox con las playlists disponibles
		            JComboBox<String> comboBoxPlaylists = new JComboBox<>(playlistsDisponibles);
		            comboBoxPlaylists.setEditable(true);

		            // Crear el panel de la ventana de diálogo
		            JPanel panelDialogo = new JPanel();
		            panelDialogo.add(new JLabel("Selecciona o introduce el nombre de la playlist:"));
		            panelDialogo.add(comboBoxPlaylists);

		            // Mostrar la ventana de diálogo
		            int opcion = JOptionPane.showConfirmDialog(null, panelDialogo, "Añadir a Lista",
		                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		            if (opcion == JOptionPane.OK_OPTION) {
		                // Obtener el nombre de la playlist seleccionada o introducida
		                String nombrePlaylist = (String) comboBoxPlaylists.getSelectedItem();

		                // Lógica para añadir las canciones a la playlist seleccionada
		                for (int fila : filasSeleccionadas) {
		                    String titulo = (String) tablaResultados.getValueAt(fila, 0);
		                    String interprete = (String) tablaResultados.getValueAt(fila, 1);
		                    String estilo = (String) tablaResultados.getValueAt(fila, 2);
		                    boolean favoritas = (boolean) tablaResultados.getValueAt(fila, 3);

		                    // Lógica para añadir la canción a la playlist (nombrePlaylist)
		                    // Puedes imprimir estos valores como ejemplo
		                    System.out.println("Añadiendo a la playlist: " + nombrePlaylist);
		                    System.out.println("Canción: " + titulo);
		                    System.out.println("Intérprete: " + interprete);
		                    System.out.println("Estilo: " + estilo);
		                    System.out.println("Favoritas: " + favoritas);

		                    // Añadir la canción a la playlist
		                    playlist.addCanciones(new Cancion(titulo, estilo));
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Selecciona al menos una canción para añadir a la lista.",
		                    "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }

		    //TODO
			private String[] getPlaylists() {
				// TODO lógica para obtener las playlists disponibles. Habría que cargarlas desde algún almacenamiento
			    // Por ahora, simplemente devolveré un array de ejemplo
			    return new String[]{"Playlist1", "Playlist2", "Playlist3"};
			}
	
			
		});
		panelBuscar.add(btnAnadirLista);
		
		*/

	  //PANEL PARA LOS BOTONES DE REPRODUCCIÓN:
        //TODO: hacer que se comporten como un bloque fijo 	    
	    JPanel panelBotonesReproduccion = new JPanel();
        panelBotonesReproduccion.setLayout(new BoxLayout(panelBotonesReproduccion, BoxLayout.X_AXIS));

        //Los añadirlos al panel
        //botón anterior:
        JButton btnAnterior = new JButton();
        ImageIcon anterior = new ImageIcon(getClass().getResource("/vista/imagenes/anterior.jpg"));
        Image anteRedimensionada = anterior.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon anteRedimensionado = new ImageIcon(anteRedimensionada);
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
        add(panelBotonesReproduccion, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        
        //Hacer visible la ventana
        setVisible(true);
    

            
            //TODO: añadir ActionListener 
        btnAnterior.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Lógica para el botón Anterior
                }
            });
		
		btnDetener.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // TODO Lógica para detener la reproducción
		    }
		});

		btnPausar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // TODO Lógica para pausar la reproducción
		    }
		});

		btnSiguiente.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // TODO Lógica para reproducir la siguiente canción
		    }
		});

		btnAnterior.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // TODO Lógica para reproducir la canción anterior
		    }
		});

		//Añadir los botones al panelBuscar:
		panelBuscar.add(btnReproducir);
		panelBuscar.add(btnDetener);
		panelBuscar.add(btnPausar);
		panelBuscar.add(btnSiguiente);
		panelBuscar.add(btnAnterior);

		
		
		JLabel lblPanelbuscar = new JLabel("PanelBuscar");
		panelBuscar.add(lblPanelbuscar);
		
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
		
		//RECIENTES: 
		
		//TODO
		
		JPanel panelRecientes = new JPanel();
		panelCardLayout.add(panelRecientes, "panelRecientes");
		panelRecientes.setLayout(new BorderLayout());
		
		/*
		// Crear una tabla para mostrar las últimas canciones reproducidas
		DefaultTableModel modeloTablaRecientes = new DefaultTableModel();
		modeloTablaRecientes.addColumn("Título");
		modeloTablaRecientes.addColumn("Intérprete");
		modeloTablaRecientes.addColumn("Estilo");
		modeloTablaRecientes.addColumn("Favoritas");
		JTable tablaRecientes = new JTable(modeloTablaRecientes);
		
		// Agregar la tabla a un JScrollPane para permitir el desplazamiento
		JScrollPane scrollPaneRecientes = new JScrollPane(tablaRecientes);
		Usuario.actualizarRecientes.add(scrollPaneRecientes, BorderLayout.CENTER);
		
		List<Cancion> ultimasCanciones = this.usuario.getPlayListUsuario();
		this.usuario.actualizarRecientes(ultimasCanciones);
		
		*/
		
		
		
		
		
		JLabel lblpanelRecientes = new JLabel("PanelRecientes");
		panelRecientes.add(lblpanelRecientes);
		
		
		//PANEL PLAYLISTS
		JPanel panelPlaylists = new JPanel();
		panelCardLayout.add(panelPlaylists, "panelPlaylists");
		panelPlaylists.setLayout(new BorderLayout());
		
		JLabel lblpanelPlaylists = new JLabel("PanelPlaylists");
		panelPlaylists.add(lblpanelPlaylists);
		
		//obtener las playlists disponibles
		//java.util.LinkedList<Cancion> playlistsDisponibles = (LinkedList<Cancion>) playlist.getPlayList();
		//para obtener la lista de canciones desde la instancia de PlayList
        PlayList playlist = new PlayList("NombreDeLaPlaylist");
        LinkedList<Cancion> listaCanciones = (LinkedList<Cancion>) playlist.getPlayList();
        
        
        //crear tabla:
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Título");
        modeloTabla.addColumn("Intérprete");
        modeloTabla.addColumn("Estilo");
        modeloTabla.addColumn("Favoritas");
        //añadir las canciones a la tabla:
        for (Cancion cancion : listaCanciones) {
            modeloTabla.addRow(new Object[]{cancion.getTitulo(), cancion.getListaInterpretes(), cancion.getEstilo(), cancion.esFavorita()});
        }
     // Crear la tabla con el modelo de datos
        JTable tablaCanciones1 = new JTable(modeloTabla);

        // Agregar la tabla a un JScrollPane para permitir el desplazamiento si hay muchas canciones
        JScrollPane scrollPaneTablaCanciones = new JScrollPane(tablaCanciones1);

        // Agregar el JScrollPane al panel
        panelPlaylists.add(scrollPaneTablaCanciones, BorderLayout.CENTER);
        
        
		
		

	}
}
