package umu.tds.controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tds.CargadorCanciones.CancionesListener;

//import org.eclipse.persistence.internal.oxm.schema.model.List;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IAdaptadorCancionDAO;
import umu.tds.dao.IAdaptadorPlayListDAO;
import umu.tds.dao.IAdaptadorUsuarioDAO;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.CatalogoCancion;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.TipoDescuentos;
import umu.tds.dominio.Usuario;
import umu.tds.utils.Player;


public class Controlador implements CancionesListener{
	private static Controlador unicaInstancia;
	
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorPlayListDAO adaptadorPlayList;

	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCancion catalogoCanciones;
	
	private Player reproductorActual;
	
	private Usuario usuarioActual;
	
	private Cancion cancionActual;
	
	private  PlayList playListActual;
	
	private PlayList playListTemporal;
	
	private String usuarioTemporal;
	
	private TipoDescuentos descuentoAplicado;
	

	private Controlador() {
		usuarioActual = null;
		cancionActual = null;
		playListActual = null;
		descuentoAplicado = TipoDescuentos.NINGUNO;
		playListTemporal = new PlayList("temp");
		iniciarAdaptadores();
		inicializarCatalogos();
		iniciarReproductor();
	}

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	//TODO get para las playlists
	//crear canciones de prueba con el formato y jugar con eso, con URL a la carpeta de recursos de canciones
	
	public  PlayList getPlayListActual() {
        return playListActual;
    }
	
	public String getUsuarioActualField(String field) {
		String value = null;
		switch (field) {
			case "nombre":
				value = usuarioActual.getNombre();
				break;
			case "email":
				value = usuarioActual.getEmail();
				break;
			case "user":
				value = usuarioActual.getUser();
				break;
			case "fechaNacim":
				value = usuarioActual.getFechaNacim();
				break;
			default:
				value = null;
				break;
		}
		return value;
	}

	public boolean esUsuarioRegistrado(String user) {
		return catalogoUsuarios.getUsuario(user) != null;
	}
	
	public void setUsuarioActual(String user) {
		usuarioActual = catalogoUsuarios.getUsuario(user);
	}
	
	public void setUsuarioTemporal(String user) {
		usuarioTemporal = user;
	}

	public String getUsuarioTemporal() {
		return usuarioTemporal;
	}
	
	public boolean loginUsuario(String user, String password) {
		if (catalogoUsuarios.login(user, password)){
			setUsuarioActual(user);
			return true;
		} else return false;
	}
	
	

	//TODO: mejora decir que error a la hora de registrar se tiene
	public boolean registrarUsuario(String nombre, String email, String user, String password,
			String fechaNacim) {

		if (esUsuarioRegistrado(user))
			return false;
		Usuario usuario = new Usuario(nombre, email, user, password, fechaNacim);

		adaptadorUsuario.registrarUsuario(usuario);

		catalogoUsuarios.addUsuario(usuario);
		
		usuarioActual = usuario;
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getUser()))
			return false;

		adaptadorUsuario.borrarUsuario(usuario);

		catalogoUsuarios.removeUsuario(usuario);
		return true;
	}
	
	private void iniciarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		}catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion = factoria.getCancionDAO();
		adaptadorPlayList = factoria.getPlayListDAO();
		
	}
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCancion.getUnicaInstancia();
	}
	
	private void iniciarReproductor() {
		reproductorActual = new Player();
	}
	
	private void setCancion(Cancion cancion) {
		cancion.addReproduccion();
		adaptadorCancion.modificarCancion(cancion);
		cancionActual = cancion;
	}
	
	private void setPlayList(PlayList playList) {
		playListActual = playList;
	}
	
	private boolean playSong() {
		if(cancionActual != null) {
			reproductorActual.play("play",cancionActual);
			return true;
		}
		return false;
	}
	//runneable play:
	public Runnable obtenerPlaySongRunnable() {
	    return new Runnable() {
	        public void run() {
	            playSong();
	        }
	    };
	}
	private boolean stopSong() {
		if(cancionActual != null) {
			reproductorActual.play("stop",cancionActual);
			return true;
		}
		return false;
	}
	
	//runneable stop:
	public Runnable obtenerStopSongRunnable() {
	    return new Runnable() {
	        public void run() {
	            stopSong();
	        }
	    };
	}
	
	private boolean nextSong() {
		if(playListActual != null || playListActual.isEmpty()) {
			cancionActual = playListActual.getSiguienteCancion(cancionActual);
		}
		
		if(cancionActual != null) {
			reproductorActual.play("next",cancionActual);
			return true;
		}
		return false;
	}
	//runneable siguiente canción:
	public Runnable obtenerNextSongRunnable() {
	    return new Runnable() {
	        public void run() {
	            nextSong();
	        }
	    };
	}
	
	private boolean previousSong() {
		if(playListActual != null || playListActual.isEmpty()) {
			cancionActual = playListActual.getAnteriorCancion(cancionActual);
		}
		if(cancionActual != null) {
			reproductorActual.play("previous",cancionActual);
			return true;
		}
		return false;
	}
	
	//runneable canción anterior:
	public Runnable obtenerPreviousSongRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                previousSong();
            }
        };
    }
	
	
	private boolean pauseSong() {
		if(cancionActual != null) {
			reproductorActual.play("pause",cancionActual);
			return true;
		}
		return false;
	}
	//runneable pause :
	public Runnable obtenerPauseSongRunnable() {
	    return new Runnable() {
	        public void run() {
	            pauseSong();
	        }
	    };
	}
	
	private boolean addCancionPlayList(Cancion cancion) {
		if(cancion != null) {
			playListTemporal.addCancion(cancion);
			return true;
		}
		return false;
	}

	public boolean removeCancionesDePlaylist(List<Cancion> canciones) {
	    boolean eliminado = false;
	    for (Cancion cancion : canciones) {
	        if (removeCancionPlayList(cancion)) {
	            eliminado = true;
	        }
	    }
	    return eliminado;
	}

	public boolean removeCancionPlayList(Cancion cancion) {
        if(cancion != null) {
            playListTemporal.removeCancion(cancion);
            return true;
        }
        return false;
    }
	public boolean removeCancionesDePlaylistDesdeMain(LinkedList<Cancion> cancionesSeleccionadas) {
		return removeCancionesDePlaylist(cancionesSeleccionadas);
	}

	
	public boolean removePlaylist(PlayList playlistSeleccionada) {
	    String nombrePlaylist = playlistSeleccionada.getNombre(); // obtener el nombre de la playlist seleccionada
	    PlayList playlist = adaptadorPlayList.buscarPlayListPorNombre(nombrePlaylist); // buscar la playlist por su nombre
	    if (playlist != null) {
	        adaptadorPlayList.borrarPlayList(playlist); // eliminar la playlist del adaptador
	        usuarioActual.removePlayListUsuarios(playlist); // eliminar la playlist del usuario actual
	        adaptadorUsuario.modificarUsuario(usuarioActual); // guardar los cambios en el adaptador de usuario
	        return true;
	    } else {
	        System.out.println("No se encontró la PlayList");
	        return false;
	    }
	}

	
	
	public boolean guardarPlayListDesdeVentana(String nombrePlaylist) {
        return guardarPlayList(nombrePlaylist);
    }
	private boolean guardarPlayList(String nombre) {
		if (playListTemporal.isEmpty())
			return false;
		PlayList aux = new PlayList(nombre, playListTemporal.getPlayList());
		adaptadorPlayList.registrarPlayList(aux);
		usuarioActual.addPlayListUsuarios(aux);
		adaptadorUsuario.modificarUsuario(usuarioActual);
		playListTemporal.removeAllCanciones();
		return true;
	}
/*
	public static List<Cancion> realizarBusqueda(String interprete, String titulo, String estilo, boolean favoritas) {
	    List<Cancion> resultados = new ArrayList<>();

	    try {
	        // Obtener todas las canciones del catálogo
	        List<Cancion> canciones = CatalogoCancion.getUnicaInstancia().getCanciones();
	        // OJO PATRON EXPERTO - 

	        // Iterar sobre todas las canciones para realizar la búsqueda
	        for (Cancion cancion : canciones) {
	            // Comprobar si la canción coincide con los criterios de búsqueda
	            if ((interprete.isEmpty() || cancion.getListaInterpretes().contains(interprete)) &&
	                (titulo.isEmpty() || cancion.getTitulo().equalsIgnoreCase(titulo)) &&
	                (estilo.isEmpty() || cancion.getEstilo().equalsIgnoreCase(estilo)) &&
	                (!favoritas || cancion.esFavorita())) {
	                // Agregar la canción a los resultados si coincide con los criterios de búsqueda
	                resultados.add(cancion);
	            }
	        }

	        // Realizar acciones con los resultados (mostrar en la interfaz, etc.)
	        // Por ahora, solo imprimimos los resultados por consola
	        if (resultados.isEmpty()) {
	            System.out.println("No se encontraron canciones que coincidan con los criterios de búsqueda.");
	        } else {
	            System.out.println("Se encontraron las siguientes canciones:");
	            for (Cancion cancion : resultados) {
	                System.out.println(cancion);
	            }
	        }
	    } catch (DAOException e) {
	        // Manejar la excepción específica DAOException
	        System.err.println("Error al obtener la lista de canciones del catálogo: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception ex) {
	        // Manejar cualquier otra excepción no controlada
	        System.err.println("Error no controlado: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	    return resultados;
	}
*/
	public List<Cancion> realizarBusqueda(String interprete, String titulo, String estilo, boolean favoritas) {
        //llamando al método realizarBusqueda del CatalogoCancion, para respetar el patrón experto
        return CatalogoCancion.getUnicaInstancia().realizarBusqueda(interprete, titulo, estilo, favoritas);
		
		/*prueba:
			//objetos de prueba Cancion
	        Cancion cancion1 = new Cancion("Cancion 1", "Rock", "Interprete 1", "Interprete 2");
	        Cancion cancion2 = new Cancion("Cancion 2", "Pop", "Interprete 3");
	        Cancion cancion3 = new Cancion("Cancion 3", "Electrónica", Arrays.asList("Interprete 4", "Interprete 5"));
	        List<Cancion> canciones = new ArrayList<>();
	        canciones.add(cancion1);
	        canciones.add(cancion2);
	        canciones.add(cancion3);
	        return canciones;
	        */
    }


	// Método para crear o editar una playlist
    public void crearOEditarPlaylist(String tituloPlaylist) {
        if (tituloPlaylist == null || tituloPlaylist.isEmpty()) {
            // Mostrar un mensaje de error si no se proporciona un título de playlist válido
            System.out.println("Error: título de playlist inválido");
            return;
        }

        if (playListActual != null && playListActual.getNombre().equals(tituloPlaylist)) {
            // La playlist actual ya tiene el mismo título, por lo que se puede editar
            // Aquí implementar la lógica para editar la playlist existente
            System.out.println("Editando la playlist existente: " + playListActual.getNombre());
        } else {
            // Crear una nueva playlist
            playListActual = new PlayList(tituloPlaylist);
            // Mostrar un mensaje de éxito
            System.out.println("Playlist creada: " + tituloPlaylist);
        }
    }

    public void agregarCancionAPlayListTemporalPorID(int idCancion) {
        Cancion cancion = CatalogoCancion.getUnicaInstancia().getCancion(idCancion);
        if (cancion != null) {
            playListTemporal.addCancion(cancion);
        }
    }

    public void quitarCancionDePlayListTemporalPorID(int idCancion) {
        Cancion cancion = CatalogoCancion.getUnicaInstancia().getCancion(idCancion);
        if (cancion != null) {
            playListTemporal.removeCancion(cancion);
        }
    }

   
	
}
