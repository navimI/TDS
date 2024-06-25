package umu.tds.dao;

/**
 * Excepción DAO.
 * <p>
 * Excepción que se lanza cuando se produce un error en la capa DAO.
 * 
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */

@SuppressWarnings("serial")
public class DAOException extends Exception {

	/**
	 * Constructor de la excepción DAO.
	 * @param mensaje Mensaje que se mostrará en la excepción.
	 */
	public DAOException(final String mensaje) {
		super(mensaje);
	}

}
