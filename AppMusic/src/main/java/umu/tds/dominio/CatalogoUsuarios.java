package umu.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IAdaptadorUsuarioDAO;

/**
 * Catalogo de usuarios de la aplicacion
 * Una clase singleton que guarda los usuarios de la aplicacion de manera local
 * y se encarga de gestionar las operaciones de los usuarios
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see Usuario
 * @see FactoriaDAO
 * @see IAdaptadorUsuarioDAO
 */

public class CatalogoUsuarios {
	private static CatalogoUsuarios unicaInstancia;
	private FactoriaDAO factoria;
	private IAdaptadorUsuarioDAO adaptadorUsuario;

	private HashMap<Integer, Usuario> userID;

	/**
	 * Devuelve la unica instancia de la clase CatalogoUsuarios
	 * Si no existe la crea
	 * @return unicaInstancia la unica instancia de la clase CatalogoUsuarios
	 */

	public static CatalogoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}

	/**
	 * Constructor de la clase CatalogoUsuarios
	 * Crea un catalogo de usuarios vacio y carga los usuarios de la base de datos
	 */

	private CatalogoUsuarios (){
		
		try {
			factoria = FactoriaDAO.getInstancia();
			adaptadorUsuario = factoria.getUsuarioDAO();
			userID = new HashMap<Integer, Usuario>();
			this.cargarCatalogo();
			
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}

	/**
	 * Devuelve una lista con todos los usuarios del catalogo
	 * @return Lista de usuarios
	 * @throws DAOException
	 */

	
	public List<Usuario> getUsuarios() throws DAOException {
		return new LinkedList<Usuario>(userID.values());
	}

	/**
	 * Devuelve un usuario del catalogo
	 * @param id Identificador del usuario
	 * @return Objeto Usuario
	 */
	public Usuario getUsuario(int id) {
		return userID.get(id);
	}

	/**
	 * Devuelve un usuario del catalogo
	 * Si no existe devuelve null
	 * @param user Nombre de usuario
	 * @return Objeto Usuario
	 */
	public Usuario getUsuario(String user) {
		Usuario foundUsuario = userID.values().stream()
			.filter(usuario -> usuario.getUser().equals(user))
			.findFirst()
			.orElse(null);
		return foundUsuario;

	}

	/**
	 * Comprueba si un usuario esta registrado en el catalogo
	 * @param user Nombre de usuario
	 * @return true si el usuario esta registrado, false en caso contrario
	 */
	public boolean login(String user, String password) {
		Usuario usuario = getUsuario(user);
		if (usuario != null) {
			return usuario.isPassword(password);
		}
		return false;
	}
		
	/**
	 * Añade un usuario al catalogo
	 * @param usuario Objeto Usuario
	 */
	public void addUsuario(Usuario usuario) {
		userID.put(usuario.getId(), usuario);
	}
	
	/**
	 * Elimina un usuario del catalogo
	 * @param usuario Objeto Usuario
	 */
	public void removeUsuario(Usuario usuario) {
		userID.remove(usuario.getId());
	}

	/**
	 * Actualiza un usuario del catalogo
	 * @param usuario Objeto Usuario
	 */
	public void updateUsuario(Usuario usuario) {
		addUsuario(usuario);
	}
	/**
	 * Carga los usuarios de la base de datos en el catalogo
	 * @throws DAOException
	 */
	private void cargarCatalogo() throws DAOException{
		List<Usuario> listaAsistentes = adaptadorUsuario.recuperarTodosUsuarios();
		for (Usuario usuario : listaAsistentes) {
			userID.put(usuario.getId(), usuario);
		}
	}
}
