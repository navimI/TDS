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

		public boolean existeCancion(Cancion cancion) {
			return canciones.values().stream().anyMatch(c -> c.cancionesIguales(cancion));
		}

		public List<Cancion> realizarBusqueda(String interprete, String titulo, String estilo) {
	        List<Cancion> cancionesEncontradas = new LinkedList<>();
	        try {
	            List<Cancion> todasLasCanciones = getCanciones();

	            for (Cancion cancion : todasLasCanciones) {
	                if ((titulo.isEmpty() || cancion.getTitulo().equalsIgnoreCase(titulo)) &&
	                    (estilo.isEmpty() || cancion.getEstilo().equalsIgnoreCase(estilo)) 
	                    ) {
	                    // Verificar si algún intérprete de la canción coincide con algún intérprete de la lista
	                    boolean coincidenciaInterprete = false;
	                    for (String interpreteCancion : cancion.getListaInterpretes()) {
	                        if (interpreteCancion.equalsIgnoreCase(interprete)) {
	                            coincidenciaInterprete = true;
	                            break;
	                        }
	                    }
	                    // Si hay coincidencia con el intérprete, agregar la canción a la lista de canciones encontradas
	                    if (coincidenciaInterprete) {
	                        cancionesEncontradas.add(cancion);
	                    }
	                }
	            }
	        } catch (DAOException e) {
	            // Manejar la excepción, por ejemplo, imprimir un mensaje de error
	            e.printStackTrace();
	        }
	        return cancionesEncontradas;
	    }



}
