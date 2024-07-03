package umu.tds.dao;

/**
 * Factoria abstracta que devuelve los adaptadores DAO concretos a utilizar.
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */

public abstract class FactoriaDAO {
	
	public static final String DAO_TDS = "umu.tds.dao.AdaptadorFactoriaTDS";

	private static FactoriaDAO unicaInstancia = null;
	
	/** 
	 * Crea un tipo de factoria DAO.
	 * La factoria es un singleton.
	 * @param tipo Tipo de la instancia que se quiere tener
	 * @return Factoria del tipo pasado por parametro
	 * @throws DAOException Excepcion lanzada si no se puede crear la factoria
	 */
	public static FactoriaDAO getInstancia(String tipo) throws DAOException{
		if (unicaInstancia == null)
			try { 
				unicaInstancia=(FactoriaDAO) Class.forName(tipo).getDeclaredConstructor().newInstance();
			} catch (Exception e) {	
				throw new DAOException(e.getMessage());
		} 
		return unicaInstancia;
	}
	
	/**
	 * Devuelve la única instancia de la clase FactoriaDAO.
	 * @return unicaInstancia que es un objeto de tipo FactoriaDAO.
	 * @throws DAOException Excepcion lanzada si no se puede crear la factoria
	 */

	public static FactoriaDAO getInstancia() throws DAOException{
		return getInstancia(FactoriaDAO.DAO_TDS);
	}

	/**
	 * Constructor de la clase FactoriaDAO.
	 */

	protected FactoriaDAO (){}
	
	/**
	 * Devuelve el adaptador DAO de Usuario.
	 * @return Objeto de tipo IAdaptadorUsuarioDAO.
	 */
	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();	
	/**
	 * Devuelve el adaptador DAO de Cancion.
	 * @return Objeto de tipo IAdaptadorCancionDAO.
	 */
	public abstract IAdaptadorCancionDAO getCancionDAO();
	/**
	 * Devuelve el adaptador DAO de PlayList.
	 * @return Objeto de tipo IAdaptadorPlayListDAO.
	 */
	public abstract IAdaptadorPlayListDAO getPlayListDAO();
}
