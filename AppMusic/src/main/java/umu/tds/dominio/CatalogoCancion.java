package umu.tds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IAdaptadorCancionDAO;

public class CatalogoCancion {
	
		private HashMap<Integer, Cancion> canciones;
		private static CatalogoCancion unicaInstancia;
		
		private FactoriaDAO factoria;
		private IAdaptadorCancionDAO adaptadorCancion;


		public static CatalogoCancion getUnicaInstancia() {
			if (unicaInstancia == null) unicaInstancia = new CatalogoCancion();
			return unicaInstancia;
		}

		private CatalogoCancion (){
			
			try {
				factoria = FactoriaDAO.getInstancia();
				adaptadorCancion = factoria.getCancionDAO();
				canciones = new HashMap<Integer, Cancion>();
				this.cargarCatalogo();
				
			} catch (DAOException eDAO) {
				   eDAO.printStackTrace();
			}
		}

		public List<Cancion> getCanciones() throws DAOException{
			return new LinkedList<Cancion>(canciones.values());
		}
		
		public Cancion getCancion(int id) {
			return canciones.get(id);
		}
		
		public void addCancion(Cancion cancion) {
			canciones.put(cancion.getID(), cancion);
		}
		
		public void removeCancion(Cancion cancion) {
			canciones.remove(cancion.getID());
		}

		private void cargarCatalogo() throws DAOException{
			List<Cancion> listaContenido = adaptadorCancion.recuperarTodasCanciones();
			for (Cancion cancion: listaContenido) {
				canciones.put(cancion.getID(), cancion);
			}
		}

}
