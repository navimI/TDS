package umu.tds.controlador;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import tds.CargadorCanciones.CancionesEvent;
import tds.CargadorCanciones.CancionesListener;
import tds.CargadorCanciones.CargadorCanciones;



import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IAdaptadorCancionDAO;
import umu.tds.dao.IAdaptadorPlayListDAO;
import umu.tds.dao.IAdaptadorUsuarioDAO;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.CatalogoCancion;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.Descuento;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;
import umu.tds.utils.CancionCargadorAdapter;
import umu.tds.utils.Player;

/**
 * <h1>Clase Controlador</h1>
 * Clase Controlador que actúa como intermediario entre el frontend y el backend de la aplicación.
 * Las funciones de esta clase son:
 * 
 * <p>
 * Implementa las funciones de la aplicación como el login, registro, búsqueda de canciones, gestion de playlist, etc.
 * <p>
 * Implementa la interfaz CancionesListener para escuchar los cambios en la lista de canciones.
 * <p>
 * Implementa los métodos para controlar el reproductor de música.
 * <p>
 * Implementa las llamadas a los catalogos y adaptadores de la aplicación.
 * 
 * 
 * @version 1.0
 * @since   2023-06-06
 * 
 */

public class Controlador implements CancionesListener{
	
	private static Controlador unicaInstancia;
	
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorPlayListDAO adaptadorPlayList;

	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCancion catalogoCanciones;
	private CargadorCanciones cargadorCanciones;
	
	private Player reproductorActual;
	
	private Usuario usuarioActual;
	
	private Cancion cancionActual;
	
	private  PlayList playListActual;
	
	private List<Cancion> playListFavoritos;
	
	private String usuarioTemporal;
	
	
	/**
	 * <h1>Constructor de la clase Controlador.</h1>
	 * Constructor de la clase Controlador que inicializa los atributos de la clase.
	 * Inicializa el componente cargador de canciones.
	 * Además, inicializa los catalogos de usuarios y canciones, y los adaptadores de usuario, canción y playlist.
	 * 
	 */

	private Controlador() {
		usuarioActual = null;
		cancionActual = null;
		playListActual = new PlayList("");
		playListFavoritos = new LinkedList<Cancion>();
		cargadorCanciones = new CargadorCanciones();
		cargadorCanciones.addListener(this);
		iniciarAdaptadores();
		inicializarCatalogos();
		iniciarReproductor();
	}

	/**
	 * <h1>Obtener la instancia única del controlador.</h1>
	 * Método que devuelve la instancia única del controlador.
	 * Si no existe, crea una nueva instancia.
	 * 
	 * @return Instancia única del controlador.
	 */

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}

	/**
	 * <h1>Inicializa servicio de persistencia.</h1>
	 * Método que inicializa los adaptadores de persistencia de usuario, canción y playlist.
	 */

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

	/**
	 * <h1>Inicializa catálogos.</h1>
	 * Método que inicializa los catálogos de usuarios y canciones.
	 */
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCancion.getUnicaInstancia();
	}
	
	/**
	 * <h1>Inicializa el reproductor.</h1>
	 * Método que inicializa el reproductor de música.
	 */

	private void iniciarReproductor() {
		reproductorActual = new Player();
	}

	/**
	 * <h1>Obtener el usuario actual.</h1>
	 * @return Usuario actual.
	 */


	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	/**
	 * <h1>Obtener la playlist actual.</h1>
	 * @return Playlist actual.
	 */

	public  PlayList getPlayListActual() {
		return playListActual;
    }
	
	public List<Cancion> getPlayListFavoritos(){
		return new LinkedList<Cancion>(playListFavoritos);
	}
	
	
	public List<Cancion> getCancionesPlayListActual(){
		return playListActual.getPlayList();
	}
	
	public boolean isEmptyPlayListActual() {
		
		return playListActual.isEmpty();
	}

	/**
	 * <h1>Obtiene un valor del usuario actual.</h1>
	 * Método que obtiene un valor del usuario actual.
	 * @param field Campo del usuario.
	 * @return Valor del campo del usuario.
	 */
	
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

	/**
	 * <h1>Informa si el usuario esta registrado en el sistema.</h1>
	 * @param user Nombre de usuario.
	 * @return True si el usuario esta registrado, False en caso contrario.
	 */

	public boolean esUsuarioRegistrado(String user) {
		return catalogoUsuarios.getUsuario(user) != null;
	}

	/**
	 * <h1>Establece el usuario actual.</h1>
	 * @param user Nombre de usuario.
	 */
	
	public void setUsuarioActual(String user) {
		usuarioActual = catalogoUsuarios.getUsuario(user);
	}
	
	/**
	 * <h1>Obtiene si el usuario actual es premium.</h1>
	 * @return True si el usuario es premium, False en caso contrario.
	 */
	public boolean esUsuarioActualPremium() {
		return usuarioActual.isPremiun();
	}


	/**
	 * <h1>Devuelve el usuario temporal cargado en la aplicacion.</h1>
	 * @param user Nombre del usuario.
	 */
	public void setUsuarioTemporal(String user) {
		usuarioTemporal = user;
	}

	/**
	 * <h1>Obtiene el usuario temporal cargado en la aplicacion.</h1>
	 * @return Nombre del usuario temporal.
	 */

	public String getUsuarioTemporal() {
		return usuarioTemporal;
	}
	
	/**
	 * <h1>Loguea al usuario en la aplicación.</h1>
	 * @param user Nombre de usuario.
	 * @param password Contraseña del usuario.
	 * @return True si el usuario se ha logueado correctamente, False en caso contrario.
	 */

	public boolean loginUsuario(String user, String password) {
		if (catalogoUsuarios.login(user, password)){
			setUsuarioActual(user);
			return true;
		} else return false;
	}
	
	/**
	 * <h1>Registra un nuevo usuario en la la base.</h1>
	 * @param nombre Nombre del usuario.
	 * @param email Email del usuario.
	 * @param user Nombre de usuario.
	 * @param password Contraseña del usuario.
	 * @param fechaNacim Fecha de nacimiento del usuario.
	 * @return True si el usuario se ha registrado correctamente, False en caso contrario.
	 */

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

	/**
	 * <h1>Borra un usuario de la base de datos.</h1>
	 * @param usuario Usuario a borrar.
	 * @return True si el usuario se ha borrado correctamente, False en caso contrario.
	 */

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getUser()))
			return false;

		adaptadorUsuario.borrarUsuario(usuario);

		catalogoUsuarios.removeUsuario(usuario);
		return true;
	}

	/**
	 * <h1>Obtener top 10 canciones.</h1>
	 * @return Lista de las 10 canciones más reproducidas en orde descendente.
	 */

	public List<Cancion> getTopCanciones() {
		return catalogoCanciones.topCanciones();
	}

	/**
	 * <h1>Carga las canciones de un fichero en la aplicación.</h1>
	 * Para cargar las canciones de un fichero en la aplicación, se llama al método cargarCanciones del componente cargador de canciones.
	 * @param fichero Ruta del fichero de canciones.
	 */

	public void cargarCanciones(String fichero){
		
		
		cargadorCanciones.setArchivoCanciones(fichero);
		
	}
	
	/**
	 * <h1>Establece la canción actual.</h1>
	 * Establece la canción actual en la aplicación.
	 * Este método es necesario para el reproductor de música.
	 * @param cancion Canción actual.
	 */

	private void setCancion(Cancion cancion) {
		cancion.addReproduccion();
		adaptadorCancion.modificarCancion(cancion);
		usuarioActual.actualizarRecientes(cancion);
		cancionActual = cancion;
	}

	/**
	 * <h1>Establece la playlist actual.</h1>
	 * @param playList Playlist actual.
	 */
	
	private void setPlayList(PlayList playList) {
		playListActual = playList;
	}

	/**
	 * <h1> Reproduce la cancion actual.</h1>
	 * @return True si la canción se ha reproducido correctamente, False en caso contrario.
	 */
	
	public boolean playSong() {
		if(cancionActual != null) {
			reproductorActual.play("play",cancionActual);
			return true;
		}
		return false;
	}

	
	/**
	 * <h1> Detiene la cancion actual.</h1>
	 */
	public boolean stopSong() {
		if(cancionActual != null) {
			reproductorActual.play("stop",cancionActual);
			return true;
		}
		return false;
	}
	
	/**
	 * <h1> Reproduce la siguiente cancion.</h1>
	 * @return True si la canción se ha reproducido correctamente, False en caso contrario.
	 */
	
	public boolean nextSong() {
		if(playListActual != null || !playListActual.isEmpty()) {
			setCancion(playListActual.getSiguienteCancion(cancionActual));
		}
		
		if(cancionActual != null) {
			reproductorActual.play("play",cancionActual);
			return true;
		}
		return false;
	}
	
	/**
	 * <h1> Reproduce la cancion anterior.</h1>
	 * @return True si la canción se ha reproducido correctamente, False en caso contrario.
	 */
	
	public boolean previousSong() {
		if(playListActual != null || playListActual.isEmpty()) {
			setCancion(playListActual.getAnteriorCancion(cancionActual));
		}
		if(cancionActual != null) {
			reproductorActual.play("play",cancionActual);
			return true;
		}
		return false;
	}
	
	
	/**
	 * <h1> Pausa la cancion actual.</h1>
	 * @return True si la canción se ha pausado correctamente, False en caso contrario. 
	*/
	
	public boolean pauseSong() {
		if(cancionActual != null) {
			reproductorActual.play("pause",cancionActual);
			return true;
		}
		return false;
	}
	
	/**
	 * <h1> Añade una cancion a la playlist de favoritos.</h1>
	 * @param cancion Canción a añadir.
	 * @return True si la canción se ha añadido correctamente, False en caso contrario.
	 */

	
	private boolean addCancionPlayListFavoritos(Cancion cancion) {
		if(cancion != null) {
			int i = posicionFavorita(cancion.getID());
			if (i>=0) {
				playListFavoritos.remove(i);
				playListFavoritos.add(cancion);
			} else {
				playListFavoritos.add(cancion);
			}
			return true;
		}
		return false;
	}

	/**
	 * <h1>Elimina canciones de la playlist de favoritos.</h1>
	 * @param canciones Lista de canciones a eliminar.
	 * @return True si las canciones se han eliminado correctamente, False en caso contrario.
	 */

	public boolean removeCancionesDePlaylistFavoritos(List<Cancion> canciones) {
	    boolean eliminado = false;
	    for (Cancion cancion : canciones) {
	        if (removeCancionPlayListFavoritos(cancion)) {
	            eliminado = true;
	        }
	    }
	    return eliminado;
	}

	/**
	 * <h1>Elimina canciones de la playlist de favoritos.</h1>
	 * @param cancion Nombre de la playlist.
	 * @return True si la playlist se ha eliminado correctamente, False en caso contrario.
	 */

	public boolean removeCancionPlayListFavoritos(Cancion cancion) {
        if(cancion != null) {
        	int i = posicionFavorita(cancion.getID());
        	if(i>=0) playListFavoritos.remove(i);
            return true;
        }
        return false;
    }

	/**
	 * <h1> Eliminar playlist del usuario actual.</h1>
	 * Elimina la playlist del modelo de persistencia.
	 * Elimina la playlist del usuario actual.
	 * Guarda los cambios en el adaptador de usuario.
	 * @param playlistSeleccionada Playlist a eliminar.
	 * @return True si la playlist se ha eliminado correctamente, False en caso contrario.
	 */
	
	public boolean removePlaylist(String playlistSeleccionada) {

		PlayList selectedPlaylist = usuarioActual.getPlayListNamed(playlistSeleccionada);
	    
	    if (selectedPlaylist != null) {

	        adaptadorPlayList.borrarPlayList(selectedPlaylist); // eliminar la playlist del adaptador

	        usuarioActual.removePlayListUsuarios(selectedPlaylist); // eliminar la playlist del usuario actual
	        adaptadorUsuario.modificarUsuario(usuarioActual); // guardar los cambios en el adaptador de usuario
			catalogoUsuarios.updateUsuario(usuarioActual); // actualizar el usuario en el catálogo de usuarios
	        return true;
	    } else {
	        System.out.println("No se encontró la PlayList");
	        return false;
	    }
	}

	/**
	 * <h1> Guardar playlist de favoritos.</h1>
	 * Guarda la playlist de favoritos en el modelo de persistencia.
	 * Guarda la playlist de favoritos en el usuario actual.
	 * Guarda los cambios en el adaptador de usuario.
	 * @param nombrePlaylist Nombre de la playlist.
	 * @return True si la playlist se ha guardado correctamente, False en caso contrario.
	 */
	
	
	public boolean guardarPlayListDesdeVentana(String nombrePlaylist,List<Integer> idCanciones) {
		if (playListFavoritos.isEmpty())
			return false;
		PlayList selectedPlaylist = usuarioActual.getPlayListNamed(nombrePlaylist);
		if (selectedPlaylist == null) {
			selectedPlaylist = new PlayList(nombrePlaylist, playListFavoritos);
			usuarioActual.addPlayList(selectedPlaylist);
			adaptadorPlayList.registrarPlayList(selectedPlaylist);

		}
		else {
			List<Cancion> songList = new LinkedList<Cancion>();
			songList.addAll(playListFavoritos);
			songList.addAll(selectedPlaylist.getPlayList());
			selectedPlaylist.removeAllCanciones();
			songList = songList.stream()
				.filter(c -> idCanciones.contains(c.getID()))
				.collect(Collectors.toList());

			selectedPlaylist.addCanciones(songList);
			adaptadorPlayList.modificarPlayList(selectedPlaylist);
			
		}

		
		adaptadorUsuario.modificarUsuario(usuarioActual);
		playListFavoritos.clear();
		return true;
	}
	
	public PlayList existePlayList(String nombrePlaylist) {
		PlayList pL = usuarioActual.getPlayListNamed(nombrePlaylist);
		if (pL != null) {
			return pL;
		}
		return null;
	}
	
	
	
	public List<String> listarEstilos(){
		return catalogoCanciones.listaEstilos();
	}

	/**
	 * <h1> Buscar canciones en el catálogo.</h1>
	 * Busca canciones en el catálogo de canciones usando los diferentes filtros que se pasen por parametro.
	 * @param interprete Nombre del interprete.
	 * @param titulo Titulo de la canción.
	 * @param estilo Estilo de la canción.
	 * @param favoritas True si se buscan canciones favoritas, False en caso contrario.
	 * @return Lista de canciones que cumplen los filtros.
	 */ 

	public List<Cancion> realizarBusqueda(String interprete, String titulo, String estilo, boolean favoritas) {
        
		List<Cancion> aux;
		
		//llamando al método realizarBusqueda del CatalogoCancion, para respetar el patrón experto
		
		
		aux = catalogoCanciones.realizarBusqueda(interprete, titulo, estilo);
		
		if (favoritas) {
			return aux.stream()
				.filter(cancion -> playListFavoritos.contains(cancion))
				.collect(Collectors.toList());
		}
		else return aux;
		
    }

	/**
	 * <h1>Obtener las canciones de la playlist de favoritos.</h1>
	 * @param idCancion Id de la canción.
	 * @throws IllegalArgumentException Si la canción no existe.
	 */

    public void agregarCancionAPlayListFavoritosPorID(int idCancion) {
        Cancion cancion = catalogoCanciones.getCancion(idCancion);
        if (cancion != null) {
            addCancionPlayListFavoritos(cancion);
        }else 
        	throw new IllegalArgumentException("La canción no existe");
    }

	/**
	 * <h1> Quitar canción de la playlist de favoritos con id.</h1>
	 * @param idCancion Id de la canción a quitar.
	 */

    public void quitarCancionDePlayListFavoritosPorID(int idCancion) {
        playListActual.removeCancion(posicionFavorita(idCancion));
    }
    
    public void invertirFavoritosID(int idCancion) {
    	if(esFavorita(idCancion)) quitarCancionDePlayListFavoritosPorID(idCancion);
    	else agregarCancionAPlayListFavoritosPorID(idCancion);
    }
    
    public boolean generarPDF(String rutaFichero) {
    	return usuarioActual.generarPDF(rutaFichero);
    }
    
    //TODO: Add persistencia
    public String activarPremium() {
    	usuarioActual.setPremium(true);
    	Descuento desc = usuarioActual.obtenerDescuento();
    	catalogoUsuarios.updateUsuario(usuarioActual);
    	adaptadorUsuario.modificarUsuario(usuarioActual);
    	return "El usuario "+usuarioActual.getUser()+" ha activado la funcionalidad Premium y ha sido aplicado "+desc.asString()+"\n"
    			+"El precio de la funcionalidad es de "+desc.precioFinal()+" €";
    	
    	
    }
    
    //TODO: Add persistencia
    public void desactivarPremium() {
    	usuarioActual.setPremium(false);
    	catalogoUsuarios.updateUsuario(usuarioActual);
    	adaptadorUsuario.modificarUsuario(usuarioActual);
    }

   //------------------ metodos auxiliares -----------------------------
   
   /**
	* <h1>Obtener la posición de una canción en la playlist de favoritos.</h1>
	* @param idCancion Id de la canción.
	* @return Posición de la canción en la playlist de favoritos.
    */
    public int posicionFavorita(int idCancion) {
		return IntStream.range(0, playListFavoritos.size())
			.filter(i -> playListFavoritos.get(i).getID() == idCancion)
			.findFirst()
			.orElse(-1);
	}
    
	/**
	 * <h1>Comprueba si una canción es favorita.</h1>
	 * @param idCancion Id de la canción.
	 * @return True si la canción es favorita, False en caso contrario.
	 */
    public boolean esFavorita(int idCancion) {
    	return posicionFavorita(idCancion) >= 0 ? true : false;
    }
	
	/**
	 * <h1>Registra una cancion de tipo CargadorCanciones en la base de datos.</h1>
	 * Utiliza un adaptador de canción para registrar la canción en la base de datos.
	 * Si la cancion no esta registrada en el catálogo de canciones, el método realiza las siguientes acciones:
	 * <p>
	 * Descarga la canción.
	 * <p>
	 * Registra la canción en el adaptador de canciones.
	 * <p>
	 * Añade la canción al catálogo de canciones.
	 * @param cancion Cancion a registrar.
	 */
	
	private void registrarCancionesCargador(tds.CargadorCanciones.Cancion cancion) {
		
		
		
		CancionCargadorAdapter cancionAdapter = new CancionCargadorAdapter(cancion);
		
		if(!catalogoCanciones.existeCancion(cancionAdapter)) {
			
			cancionAdapter.descargarCancion();
			
			adaptadorCancion.registrarCancion(cancionAdapter);
			
			catalogoCanciones.addCancion(cancionAdapter);
			

		}
		
	}

	//------------------ eventos del cargador de canciones -----------------------------

	/**
	 * <h1>Registra un listener en el cargador de canciones.</h1>
	 * Registra un listener en el cargador de canciones para escuchar los cambios en la lista de canciones.
	 * @param e Evento del CargadorCanciones.
	 */
	@Override
	public void cambioNotificado(CancionesEvent e) {
		tds.CargadorCanciones.Canciones canciones = e.getCancionesNuevas();
		for (tds.CargadorCanciones.Cancion cancion : canciones.getCancion()) {
			registrarCancionesCargador(cancion);
		}
		
		
	}

	
}
