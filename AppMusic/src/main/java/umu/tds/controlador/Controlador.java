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
	



	private Controlador() {
		usuarioActual = null;
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

	public boolean esUsuarioRegistrado(String user) {
		return catalogoUsuarios.getUsuario(user) != null;
	}

	public boolean loginUsuario(String user, String password) {
		return catalogoUsuarios.login(user, password);
	}

	//TODO: mejora decir que error a la hora de registrar se tiene
	public boolean registrarUsuario(String nombre, String email, String user, String password,
			String fechaNacim) {

		if (esUsuarioRegistrado(user))
			return false;
		Usuario usuario = new Usuario(nombre, email, user, password, fechaNacim);

		adaptadorUsuario.registrarUsuario(usuario);

		catalogoUsuarios.addUsuario(usuario);
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
		cancionActual = cancion;
	}
	
	private void playSong() {
		reproductorActual.play("play",cancionActual);
	}
	
	private void stopSong() {
		reproductorActual.play("stop",cancionActual);
	}
	
	private void nextSong() {
		//TODO: implement how to get next song
	}
	
	private void previousSong() {
		//TODO: implement how to get previous song
	}
	
	private void pauseSong() {
		reproductorActual.play("stop",cancionActual);
	}
	
}
