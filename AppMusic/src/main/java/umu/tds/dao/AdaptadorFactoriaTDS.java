package umu.tds.dao;

/** 
 * Factoria concreta DAO para el Servidor de Persistencia de la asignatura TDS.
 * El Servidor de Persistencia es una base de datos orientada a objetos que permite
 * almacenar objetos Java de forma permanente. La factoría proporciona adaptadores
 * DAO concretos para cada clase de dominio que se desea persistir en el servidor.
 * <p>
 * Implementa la interfaz FactoriaDAO y se encarga de instanciar los adaptadores DAO
 * concretos para cada clase de dominio.
 * 
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see FactoriaDAO
 * @see AdaptadorUsuarioTDS
 * @see AdaptadorCancionTDS
 * @see AdaptadorPlayListTDS
 * @see AdaptadorCancionTDS
 * 
 * 
 */

public final class AdaptadorFactoriaTDS extends FactoriaDAO {

	/**
	 * Constructor de la clase AdaptadorFactoriaTDS.
	 */
	
	public AdaptadorFactoriaTDS() {	}
	
	/**
	 * Devuelve un objeto de tipo AdaptadorUsuarioTDS.
	 */
	@Override
	public AdaptadorUsuarioTDS getUsuarioDAO() {	
		return new AdaptadorUsuarioTDS(); 
	}

	/**
	 * Devuelve un objeto de tipo AdaptadorCancionTDS.
	 */
	@Override
	public AdaptadorCancionTDS getCancionDAO() {	
		return new AdaptadorCancionTDS(); 
	}

	/**
	 * Devuelve un objeto de tipo AdaptadorPlayListTDS.
	 */

	@Override
	public AdaptadorPlayListTDS getPlayListDAO() {
		return new AdaptadorPlayListTDS();
	}

}
