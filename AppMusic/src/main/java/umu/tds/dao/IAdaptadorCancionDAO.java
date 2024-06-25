package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.Cancion;

/**
 * Interfaz que define las operaciones que se pueden realizar sobre la clase Cancion en el servicio de persistencia.
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */

public interface IAdaptadorCancionDAO {
	/**
	 * Registra una cancion en el servicio de persistencia.
	 * @param cancion Cancion a registrar.
	 */
	public void registrarCancion(Cancion cancion);
	/**
	 * Borra una cancion del servicio de persistencia.
	 * @param cancion Cancion a borrar.
	 * @return true si se ha borrado correctamente, false en caso contrario.
	 */
	public boolean borrarCancion(Cancion cancion);
	/**
	 * Modifica una cancion del servicio de persistencia.
	 * @param cancion Cancion a modificar.
	 */
	public void modificarCancion(Cancion cancion);
	/**
	 * Recupera una cancion del servicio de persistencia.
	 * @param id Identificador de la cancion a recuperar.
	 * @return Cancion recuperada.
	 */
	public Cancion recuperarCancion(int id);
	/**
	 * Recupera todas las canciones del servicio de persistencia.
	 * @return Lista de canciones recuperadas.
	 */
	public List<Cancion> recuperarTodasCanciones();
	
}
