package umu.tds.controlador;

import umu.tds.dao.IAdaptadorUsuarioDAO;
import umu.tds.dao.IAdaptadorCancionDAO;
import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IAdaptadorPlayListDAO;
import umu.tds.dominio.Usuario;
import umu.tds.utils.Player;
import umu.tds.dominio.CatalogoUsuarios;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.CatalogoCancion;


public class Controlador {
	private static Controlador unicaInstancia;
	
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorPlayListDAO adaptadorPlayList;

	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCancion catalogoCanciones;
	
	private Player reproductorActual;
	
	private Usuario usuarioActual;
	
	private Cancion cancionActual;
	
	private PlayList playListActual;
	
	private PlayList playListTemporal;
	
	private String usuarioTemporal;
	
	

	private Controlador() {
		usuarioActual = null;
		cancionActual = null;
		playListActual = null;
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
			playListTemporal.addCanciones(cancion);
			return true;
		}
		return false;
	}

	private boolean removeCancionPlayList(Cancion cancion) {
		if(cancion != null) {
			playListTemporal.removeCancion(cancion);
			return true;
		}
		return false;
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
	
}
