package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.Usuario;

/**
 * Interfaz que define las operaciones que se pueden realizar sobre la clase Usuario en el servicio de persistencia.
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */

public interface IAdaptadorUsuarioDAO {

	/**
	 * Registra un usuario en el servicio de persistencia.
	 * @param usuario Usuario a registrar.
	 */
	
	public void registrarUsuario(Usuario usuario);
	/**
	 * Borra un usuario del servicio de persistencia.
	 * @param usuario Usuario a borrar.
	 * @return true si se ha borrado correctamente, false en caso contrario.
	 */
	public boolean borrarUsuario(Usuario usuario);
	/**
	 * Modifica un usuario del servicio de persistencia.
	 * @param usuario Usuario a modificar.
	 */
	public void modificarUsuario(Usuario usuario);
	/**
	 * Recupera un usuario del servicio de persistencia.
	 * @param id Identificador del usuario a recuperar.
	 * @return Usuario recuperado.
	 */
	public Usuario recuperarUsuario(int id);
	/**
	 * Recupera todos los usuarios del servicio de persistencia.
	 * @return Usuarios recuperados.
	 */
	public List<Usuario> recuperarTodosUsuarios();
	
}
