package umu.tds.dominio;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.dao.DAOException;
import umu.tds.dao.FactoriaDAO;
import umu.tds.dao.IAdaptadorCancionDAO;

/**
 * Catalogo de canciones de la aplicacion
 * Una clase singleton que guarda las canciones de la aplicacion de manera local
 * y se encarga de gestionar las operaciones de las canciones
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 * @see Cancion
 * @see FactoriaDAO
 * @see IAdaptadorCancionDAO
 */

public class CatalogoCancion {
	
		private HashMap<Integer, Cancion> canciones;
		private static CatalogoCancion unicaInstancia;
		
		private FactoriaDAO factoria;
		private IAdaptadorCancionDAO adaptadorCancion;

		/**
		 * Devuelve la unica instancia de la clase CatalogoCancion
		 * Si no existe la crea
		 * @return unicaInstancia la unica instancia de la clase CatalogoCancion
		 */

		public static CatalogoCancion getUnicaInstancia() {
			if (unicaInstancia == null) unicaInstancia = new CatalogoCancion();
			return unicaInstancia;
		}

		/**
		 * Constructor de la clase CatalogoCancion
		 * Crea un catalogo de canciones vacio y carga las canciones de la base de datos
		 */
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

		/**
		 * Devuelve una lista con todas las canciones del catalogo
		 * @return Lista de canciones
		 * @throws DAOException Excepcion lanzada si no se pueden recuperar las canciones
		 */
		public List<Cancion> getCanciones() throws DAOException{
			return new LinkedList<Cancion>(canciones.values());
		}
		
		/**
		 * Devuelve una cancion del catalogo
		 * @param id Identificador de la cancion
		 * @return Objeto de tipo Cancion
		 */
		public Cancion getCancion(int id) {
			return canciones.get(id);
		}

		/**
		 * Devuelve una cancion del catalogo
		 * @param titulo Titulo de la cancion
		 * @return Objeto de tipo Cancion
		 */
		public Cancion getCancion(String titulo){
			return canciones.values().stream().filter(c -> c.getTitulo().equals(titulo)).findFirst().orElse(null);
		}
		
		/**
		 * Añade una cancion al catalogo
		 * @param cancion Objeto de tipo Cancion
		 */
		public void addCancion(Cancion cancion) {
			
				canciones.put(cancion.getID(), cancion);
		}

		/**
		 * Elimina una cancion del catalogo
		 * @param cancion Objeto de tipo Cancion
		 */
		public void removeCancion(Cancion cancion) {
			canciones.remove(cancion.getID());
		}

		/**
		 * Carga las canciones de la base de datos al catalogo
		 */
		private void cargarCatalogo() throws DAOException{
			List<Cancion> listaContenido = adaptadorCancion.recuperarTodasCanciones();
			for (Cancion cancion: listaContenido) {
				canciones.put(cancion.getID(), cancion);
			}
		}
		
		/**
		 * Devuelve una lista con todos los estilos de las canciones del catalogo
		 * @return Lista de estilos
		 */
		public List<String> listaEstilos(){
			return canciones.values().stream().map(c -> c.getEstilo()).distinct().collect(Collectors.toList());
		}

		/**
		 * Comprueba si una cancion esta en el catalogo
		 * @param cancion Objeto de tipo Cancion
		 * @return true si la cancion esta en el catalogo, false en caso contrario
		 */

		public boolean existeCancion(Cancion cancion) {
			return canciones.values().stream().anyMatch(c -> c.cancionesIguales(cancion));
		}

		/**
		 * Devuelve una lista con las canciones mas escuchadas del catalogo
		 * @return Lista de canciones
		 */
		public List<Cancion> topCanciones(){
			List<Cancion> topCanciones = new LinkedList<Cancion>();
			for (Cancion cancion: canciones.values()) {
				if (topCanciones.size() < 10) {
					topCanciones.add(cancion);
				} else {
					for (Cancion cancionTop: topCanciones) {
						if (cancion.getNumReproducciones() > cancionTop.getNumReproducciones()) {
							topCanciones.remove(cancionTop);
							topCanciones.add(cancion);
							break;
						}
					}
				}
			}
			topCanciones.sort(Comparator.comparingInt(Cancion::getNumReproducciones).reversed());
			return topCanciones;
		}

		/**
		 * Realiza una busqueda de canciones en el catalogo
		 * Los parametros de busqueda son opcionales
		 * @param interprete Interprete de la cancion
		 * @param titulo Titulo de la cancion
		 * @param estilo Estilo de la cancion
		 * @return Lista de canciones
		 */
		public List<Cancion> realizarBusqueda(String interprete, String titulo, String estilo) {
			List<Cancion> resultado = canciones.values().stream()
				.filter(c -> interprete.isEmpty() || c.containsInterprete(interprete))
				.filter(c -> titulo.isEmpty() || c.getTitulo().equalsIgnoreCase(titulo))
				.filter(c -> estilo.isEmpty() || c.getEstilo().equalsIgnoreCase(estilo))
				.collect(Collectors.toList());
			return resultado;
	    }



}
