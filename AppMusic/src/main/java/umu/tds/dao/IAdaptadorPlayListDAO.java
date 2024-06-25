package umu.tds.dao;

import java.util.List;

import umu.tds.dominio.PlayList;

/**
 * Interfaz que define las operaciones que se pueden realizar sobre la clase PlayList en el servicio de persistencia.
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 
 */
public interface IAdaptadorPlayListDAO {
	/**
	 * Registra una PlayList en el servicio de persistencia.
	 * @param playList PlayList a registrar.
	 */
	public void registrarPlayList(PlayList playList);
	/**
	 * Borra una PlayList del servicio de persistencia.
	 * @param playList PlayList a borrar.
	 * @return true si se ha borrado correctamente, false en caso contrario.
	 */
	public boolean borrarPlayList(PlayList playList);
	/**
	 * Modifica una PlayList del servicio de persistencia.
	 * @param playList PlayList a modificar.
	 */
	public void modificarPlayList(PlayList playList);
	/**
	 * Recupera una PlayList del servicio de persistencia.
	 * @param id Identificador de la PlayList a recuperar.
	 * @return PlayList recuperada.
	 */
	public PlayList recuperarPlayList(int id);
	/**
	 * Recupera todas las PlayLists del servicio de persistencia.
	 * @return PlayLists recuperadas.
	 */
	public List<PlayList> recuperarTodosPlayList();
	
}
