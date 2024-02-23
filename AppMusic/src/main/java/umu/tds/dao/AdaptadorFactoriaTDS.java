package umu.tds.dao;

/** 
 * Factoria concreta DAO para el Servidor de Persistencia de la asignatura TDS.
 * 
 */

public final class AdaptadorFactoriaTDS extends FactoriaDAO {
	
	public AdaptadorFactoriaTDS() {	}
	
	@Override
	public AdaptadorUsuarioTDS getUsuarioDAO() {	
		return new AdaptadorUsuarioTDS(); 
	}
	@Override
	public AdaptadorCancionTDS getCancionDAO() {	
		return new AdaptadorCancionTDS(); 
	}

	@Override
	public AdaptadorPlayListTDS getPlayListDAO() {
		return new AdaptadorPlayListTDS();
	}

}
