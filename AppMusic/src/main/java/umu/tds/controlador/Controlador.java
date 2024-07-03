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
 * Clase Controlador
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

 * @author Ivan Garcia Alcaraz
 * @see umu.tds.vista.Login para usos de controlador
 * @see umu.tds.vista.AppMusic para usos de controlador
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
	 * Constructor de la clase Controlador.
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
	 * Obtener la instancia única del controlador.
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
	 * Inicializa los servicios de persistencia.
	 * Método que inicializa los adaptadores de persistencia de usuario, canción y playlist.
	 * @see umu.tds.dao.AdaptadorCancionTDS
	 * @see umu.tds.dao.AdaptadorPlayListTDS
	 * @see umu.tds.dao.AdaptadorUsuarioTDS
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
	 * Usuario sale de la aplicacion.
	 * Tras salir de la aplicacion se salen de los atributos usuarioActual, cancionActual y playListActual.
	 */
	
	public void salirUsuario() {
		usuarioActual = null;
		cancionActual = null;
		playListActual = new PlayList("");
		playListFavoritos = new LinkedList<Cancion>();
	}

	/**
	 * Inicializa catálogos.
	 * Método que inicializa los catálogos de usuarios y canciones.
	 * @see umu.tds.dominio.CatalogoUsuarios
	 * @see umu.tds.dominio.CatalogoCancion
	 */
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCancion.getUnicaInstancia();
	}
	
	/**
	 * Inicializa el reproductor.
	 * Método que inicializa el reproductor de música.
	 * @see umu.tds.utils.Player
	 */

	private void iniciarReproductor() {
		reproductorActual = new Player();
	}


	/**
	 * Obtener el nombre del usuario actual.
	 * @return Nombre del usuario actual.
	 */
	
	public String getNombreUsuarioActual() {
		return usuarioActual.getUser();
	}
	
	
	
	/**
	 * Obtener una lista de canciones favoritas del usuario.
	 * La playlist de favoritos es una lista de canciones que el usuario ha marcado como favoritas.
	 * El uso de esta lista es para añadir nuevas canciones a una playlist o crear una nueva playlist.
	 * @return Lista de canciones de la playlist de favoritos.
	 */

	public List<Cancion> getPlayListFavoritos(){
		return new LinkedList<Cancion>(playListFavoritos);
	}
	
	/**
	 * Obtener una lista de PlayList del usuario actual.
	 *  Las playlist que se obtienen son las playlist validas para mostrar del usuario actual.
	 * @return Lista de playlist vaalidas del usuario actual.
	 */

	public List<String> stringPlayListUsuario(){
		return usuarioActual.getUsablePlayList().stream()
			.map(p -> p.getNombre())
			.collect(Collectors.toList());
	}
		
	/**
	 * Obtiene las canciones de la playlist actual.
	 * @return Lista de canciones de la playlist actual.
	 */
	
	public List<Cancion> getCancionesPlayListActual(){
		return playListActual.getPlayList();
	}

	/**
	 * Informa si el usuario esta registrado en el sistema.
	 * @param user Nombre de usuario.
	 * @return True si el usuario esta registrado, False en caso contrario.
	 */

	public boolean esUsuarioRegistrado(String user) {
		return catalogoUsuarios.getUsuario(user) != null;
	}

	/**
	 * Establece el usuario actual.
	 * @param user Nombre de usuario.
	 */
	
	public void setUsuarioActual(String user) {
		usuarioActual = catalogoUsuarios.getUsuario(user);
	}
	
	/**
	 * Obtiene si el usuario actual es premium.
	 * @return True si el usuario es premium, False en caso contrario.
	 */
	public boolean esUsuarioActualPremium() {
		return usuarioActual.isPremiun();
	}


	/**
	 * Devuelve el usuario temporal cargado en la aplicacion.
	 * @param user Nombre del usuario.
	 */
	public void setUsuarioTemporal(String user) {
		usuarioTemporal = user;
	}

	/**
	 * Obtiene el usuario temporal cargado en la aplicacion.
	 * @return Nombre del usuario temporal.
	 */

	public String getUsuarioTemporal() {
		return usuarioTemporal;
	}
	
	/**
	 * Loguea al usuario en la aplicación.
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
	 * Registra un nuevo usuario en el servicio de persistencia y en el catalogo.
	 * @param nombre Nombre del usuario.
	 * @param email Email del usuario.
	 * @param user Nombre de usuario.
	 * @param password Contraseña del usuario.
	 * @param fechaNacim Fecha de nacimiento del usuario.
	 * @return True si el usuario se ha registrado correctamente, False en caso contrario.
	 */

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
	 * Borra un usuario de la base de datos.
	 * Borra un usuario de la base de datos y del catálogo de usuarios.
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
	 * Obtener top 10 canciones.
	 * Obtiene las 10 canciones más reproducidas en la aplicación.
	 * @return Lista de las 10 canciones más reproducidas en orde descendente.
	 */

	public List<Cancion> getTopCanciones() {
		return catalogoCanciones.topCanciones();
	}

	/**
	 * Establece la playlist actual con las 10 canciones más reproducidas.
	 */

	public void setTopCancionesPlayListActual() {
		playListActual = new PlayList("Top 10",getTopCanciones());
		
	}

	

	/**
	 * Carga las canciones de un fichero en la aplicación.
	 * Para cargar las canciones de un fichero en la aplicación, se llama al método cargarCanciones del componente cargador de canciones.
	 * @param fichero Ruta del fichero de canciones.
	 * @see tds.CargadorCanciones.CargadorCanciones
	 */

	public void cargarCanciones(String fichero){
		
		
		cargadorCanciones.setArchivoCanciones(fichero);
		
	}
	
	/**
	 * Establece la canción actual.
	 * Establece la canción actual en la aplicación.
	 * Este método es necesario para el reproductor de música.
	 * @param cancion Canción actual.
	 */

	private void setCancion(Cancion cancion) {
		if(cancion!=null) {
		cancion.addReproduccion();
		adaptadorCancion.modificarCancion(cancion);
		usuarioActual.actualizarRecientes(cancion);
		cancionActual = cancion;
	}}

	/**
	 * Establece la canción actual.
	 * Esto es necesario para el reproductor de música.
	 * @param nombreCancion Nombre de la canción.
	 */

	public void setCancion(String nombreCancion) {
		Cancion cancion = playListActual.getCancion(nombreCancion);
		if (cancion != null) {
			setCancion(cancion);
		}
	}

	/**
	 * Establece la canción actual sin playList.
	 * Esta funcion es usada para las canciones de la ventana de busqueda.
	 * @param nombreCancion nombre de la cancion.
	 */
	
	public void setCancionSinPlayList(String nombreCancion) {
		Cancion cancion = catalogoCanciones.getCancion(nombreCancion);
		setCancion(cancion);
	}
	
	/**
	 * Establece la cancion actual en la primera posicion de la playlist.
	 * Si la playlist esta vacia no hace nada.
	 */
	public void setFirstCancion() {
		if(!playListActual.isEmpty()) setCancion(playListActual.getPlayList().get(0));
	}

	/**
	 * Establece la cancion actual en la ultima posicion de la playlist.
	 * Si la playlist esta vacia no hace nada.
	 */

	public void setLastCancion(){
		if(!playListActual.isEmpty())setCancion(playListActual.getPlayList().get(playListActual.getPlayList().size()-1));
	}
	
	/**
	 * Establece la playlist actual.
	 * Tambien establece la cancion actual en la primera posicion de la playlist.
	 * @param nombrePlayList Nombre de la PlayList.
	 */

	public void setPlayList(String nombrePlayList) {
		PlayList playList = usuarioActual.getPlayListNamed(nombrePlayList);
		if (playList != null) {
			playListActual = playList;
			setFirstCancion();
		}
	}

	/**
	 * Establece la playlist actual a la playlist de canciones recientes.
	 * La playlist de canciones recientes es una lista de canciones que el usuario ha escuchado recientemente.
	 */

	public void setCancionesRecientes(){
		playListActual = usuarioActual.getRecientes();
	}

	/**
	 * Reproduce la cancion actual.
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
	 * Detiene la cancion actual.
	 * @return True si la canción se ha parado correctamente, False en caso contrario.
	 */
	public boolean stopSong() {
		if(cancionActual != null) {
			reproductorActual.play("stop",cancionActual);
			return true;
		}
		return false;
	}
	
	/**
	 * Reproduce la siguiente cancion.
	 * @return True si la canción se ha reproducido correctamente, False en caso contrario.
	 */
	
	public boolean nextSong() {
		if(playListActual != null && !playListActual.isEmpty()) {
			stopSong();
			setCancion(playListActual.getSiguienteCancion(cancionActual));
		}
		
		if(cancionActual != null) {
			stopSong();
			reproductorActual.play("play",cancionActual);
			return true;
		}
		return false;
	}
	
	/**
	 * Reproduce la cancion anterior.
	 * @return True si la canción se ha reproducido correctamente, False en caso contrario.
	 */
	
	public boolean previousSong() {
		if(playListActual != null && !playListActual.isEmpty()) {
			stopSong();
			setCancion(playListActual.getAnteriorCancion(cancionActual));
		}
		if(cancionActual != null) {
			stopSong();
			reproductorActual.play("play",cancionActual);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Pausa la cancion actual.
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
	 * Añade una cancion a la playlist de favoritos.
	 * 
	 * @param cancion Canción a añadir.
	 * @return True si la canción se ha añadido correctamente, False en caso contrario.
	 */

	
	private boolean addCancionPlayListFavoritos(Cancion cancion) {
		if(cancion != null) {
			int i = posicionFavorita(cancion.getID());

			if (i == -1) playListFavoritos.add(cancion);
			
			return true;
		}
		return false;
	}

	/**
	 * Eliminar playlist del usuario actual.
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
	        usuarioActual.removePlayList(selectedPlaylist); // eliminar la playlist del usuario actual
	        adaptadorUsuario.modificarUsuario(usuarioActual); // guardar los cambios en el adaptador de usuario
			catalogoUsuarios.updateUsuario(usuarioActual); // actualizar el usuario en el catálogo de usuarios
			
	        return true;
	    } else {
	        return false;
	    }
	}

	/**
	 * Guardar playlist.
	 * Si el usuario no tiene la playlist, se crea una nueva playlist con las canciones seleccionadas.
	 * Si el usuario tiene la playlist, deja la playlist con las canciones seleccionadas en la ventana gestion.
	 * Guarda la playlist en el modelo de persistencia.
	 * Guarda la playlist en el usuario actual.
	 * Guarda los cambios en el servicio de persistencia del usuario.
	 * @param nombrePlaylist Nombre de la playlist.
	 * @param idCanciones Lista de los ids de las canciones a establecer en la PlayList
	 */
	
	
	public void guardarPlayList(String nombrePlaylist,List<Integer> idCanciones) {
		PlayList selectedPlaylist = usuarioActual.getPlayListNamed(nombrePlaylist);
		//Crear nueva playlist con las canciones seleccionadas y la añade al usuario y al adaptador de playlist
		if (selectedPlaylist == null && playListFavoritos.size() > 0 ) {
			List<Cancion> songList = new LinkedList<Cancion>();
			playListFavoritos.stream()
				.filter(c -> idCanciones.contains(c.getID()))
				.forEach(c -> songList.add(c));
			selectedPlaylist = new PlayList(nombrePlaylist, songList);
			usuarioActual.addPlayList(selectedPlaylist);
			adaptadorPlayList.registrarPlayList(selectedPlaylist);

		}
		//Modificar la playlist seleccionada con las canciones seleccionadas
		else if(selectedPlaylist != null && playListFavoritos.size() > 0){
			List<Cancion> songList = new LinkedList<Cancion>();
			songList.addAll(playListFavoritos);
			songList.addAll(selectedPlaylist.getPlayList());
			selectedPlaylist.removeAllCanciones();
			songList = songList.stream()
				.filter(c -> idCanciones.contains(c.getID()))
				.collect(Collectors.toList());

			selectedPlaylist.addCanciones(songList);
			adaptadorPlayList.modificarPlayList(selectedPlaylist);
		// Si el usuario tiene la playlist y el idCanciones no esta vacio se quitara las canciones que no esten en idCanciones
		}else if(selectedPlaylist != null){
			selectedPlaylist.removeNotInclued(idCanciones);
			adaptadorPlayList.modificarPlayList(selectedPlaylist);
		}
		// Modifica el usuario actual en el servicio de persistencia
		adaptadorUsuario.modificarUsuario(usuarioActual);
		catalogoUsuarios.updateUsuario(usuarioActual);
		playListFavoritos.clear();
		
	}

	/**
	 * Devuelve una lista de canciones si existe la playlist.
	 * @param nombrePlayList Nombre de la playlist.
	 * @return Lista de canciones de la playlist si existe, null en caso contrario.
	 */
	
	public List<Cancion> existeListPlayList(String nombrePlayList){
		PlayList pL = usuarioActual.getPlayListNamed(nombrePlayList);
		if (pL != null) {
			return pL.getPlayList();
		}
		return null;
	}
	
	/**
	 * Devuelve una lista de estilos de canciones que hay en el catalogo de canciones.
	 * @return Lista de estilos de canciones.
	 */
	
	public List<String> listarEstilos(){
		return catalogoCanciones.listaEstilos();
	}

	/**
	 * Buscar canciones en el catálogo.
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
	 * Agrega la cancion a la lista de favoritos.
	 * @param idCancion Id de la canción.
	 */

    public void addCancionFavoritos(int idCancion) {
        Cancion cancion = catalogoCanciones.getCancion(idCancion);
        if (cancion != null) {
            addCancionPlayListFavoritos(cancion);
        }
    }

	/**
	 * Quitar canción de la playlist de favoritos.
	 * @param idCancion Id de la canción a quitar.
	 */

    public void removeCancionFavoritos(int idCancion) {
        playListFavoritos.remove(posicionFavorita(idCancion));
    }
    
	/**
	 * Cambia el estado de la cancion en la playlist de favoritos.
	 * Si la cancion esta en la playlist la elimina, si no esta la añade.
	 * @param idCancion Id de la canción.
	 */
    public void invertirFavoritosID(int idCancion) {
    	if(esFavorita(idCancion)) removeCancionFavoritos(idCancion);
    	else addCancionFavoritos(idCancion);
    }
    
	/**
	 * Genera un fichero PDF en la direccion pasada por parametro.
	 * El fichero PDF se genera con la informacion del usuario actual.
	 * @param rutaFichero Ruta del fichero PDF.
	 * @return True si el fichero se ha generado correctamente, False en caso contrario.
	 */
    public boolean generarPDF(String rutaFichero) {
    	return usuarioActual.generarPDF(rutaFichero);
    }
    
    /**
	 * Activa la funcionalidad Premium del usuario actual.
	 * Aplica el descuento correspondiente al usuario actual.
	 * Actualiza el usuario en el catálogo de usuarios.
	 * @return Mensaje de activación de la funcionalidad Premium con el descuento aplicado.
	 */
    public String activarPremium() {
    	usuarioActual.setPremium(true);
    	Descuento desc = usuarioActual.obtenerDescuento();
    	catalogoUsuarios.updateUsuario(usuarioActual);
    	adaptadorUsuario.modificarUsuario(usuarioActual);
    	return "El usuario "+usuarioActual.getUser()+" ha activado la funcionalidad Premium y ha sido aplicado "+desc.asString()+"\n"
    			+"El precio de la funcionalidad es de "+desc.precioFinal()+" €";
    	
    	
    }
    
    /**
	 * Desactiva la funcionalidad Premium del usuario actual.
	 * Actualiza el usuario en el catálogo de usuarios.
	 */
    public void desactivarPremium() {
    	usuarioActual.setPremium(false);
    	cancionActual = null;
    	playListActual = null;
    	catalogoUsuarios.updateUsuario(usuarioActual);
    	adaptadorUsuario.modificarUsuario(usuarioActual);
    }

   //------------------ metodos auxiliares -----------------------------
   
   /**
	 * Obtener la posición de una canción en la playlist de favoritos.
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
	 * Comprueba si una canción es favorita.
	 * @param idCancion Id de la canción.
	 * @return True si la canción es favorita, False en caso contrario.
	 */
    public boolean esFavorita(int idCancion) {
    	return posicionFavorita(idCancion) >= 0 ? true : false;
    }
	
	/**
	 * Registra una cancion de tipo CargadorCanciones en la base de datos.
	 * Utiliza un adaptador de canción para registrar la canción en la base de datos.
	 * Si la cancion no esta registrada en el catálogo de canciones, el método realiza las siguientes acciones:
	 * <p>
	 * Descarga la canción.
	 * <p>
	 * Registra la canción en el adaptador de canciones.
	 * <p>
	 * Añade la canción al catálogo de canciones.
	 * @param cancion Cancion a registrar.
	 * @see umu.tds.utils.CancionCargadorAdapter
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
	 * Registra un listener en el cargador de canciones.
	 * Registra un listener en el cargador de canciones para escuchar los cambios en la lista de canciones.
	 * @param e Evento del CargadorCanciones.
	 * @see tds.CargadorCanciones.CancionesListener
	 */
	@Override
	public void cambioNotificado(CancionesEvent e) {
		tds.CargadorCanciones.Canciones canciones = e.getCancionesNuevas();
		for (tds.CargadorCanciones.Cancion cancion : canciones.getCancion()) {
			registrarCancionesCargador(cancion);
		}
		
		
	}

	
}
