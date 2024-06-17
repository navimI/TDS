package umu.tds.dominio;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

		public Cancion getCancion(String titulo){
			return canciones.values().stream().filter(c -> c.getTitulo().equals(titulo)).findFirst().orElse(null);
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
		
		public List<String> listaEstilos(){
			return canciones.values().stream().map(c -> c.getEstilo()).distinct().collect(Collectors.toList());
		}

		public boolean existeCancion(Cancion cancion) {
			return canciones.values().stream().anyMatch(c -> c.cancionesIguales(cancion));
		}

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

		public List<Cancion> realizarBusqueda(String interprete, String titulo, String estilo) {
			List<Cancion> resultado = canciones.values().stream()
				.filter(c -> interprete.isEmpty() || c.containsInterprete(interprete))
				.filter(c -> titulo.isEmpty() || c.getTitulo().equalsIgnoreCase(titulo))
				.filter(c -> estilo.isEmpty() || c.getEstilo().equalsIgnoreCase(estilo))
				.collect(Collectors.toList());
			return resultado;
	    }



}
