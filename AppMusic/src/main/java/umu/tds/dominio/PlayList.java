package umu.tds.dominio;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Clase que representa una PlayList
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */

public class PlayList {
	//Properties	
	private int id;
	String nombre;
	List<Cancion> playList;
	
	//Constructor
	/**
	 * Constructor de la clase PlayList
	 * Constructor con la PlayList vacía
	 * @param nombre Nombre de la PlayList
	 */
	public PlayList(String nombre) {
		id = 0;
		this.nombre = nombre;
		this.playList = new LinkedList<Cancion>();
	}
	
	/**
	 * Constructor de la clase PlayList
	 * @param nombre Nombre de la PlayList
	 * @param canciones Lista de canciones de la PlayList
	 */
	public PlayList(String nombre, List<Cancion> canciones) {
		id = 0;
		this.nombre = nombre;
		this.playList = new LinkedList<Cancion>(canciones);
	}
	
	//Methods
	/**
	 * Devuelve el identificador de la PlayList
	 * @return Identificador de la PlayList
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador de la PlayList
	 * @param codigo Identificador de la PlayList
	 */
	
	public void setId(int codigo) {
		this.id = codigo;
	}
	
	/**
	 * Devuelve el nombre de la PlayList
	 * @return Nombre de la PlayList
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la PlayList
	 * @param nombre Nombre de la PlayList
	 */
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el número de canciones de la PlayList
	 * @return Número de canciones de la PlayList
	 */
	
	public int getNumCanciones() {
		return playList.size();
	}
	
	/**
	 * Devuelve una Lista con las canciones de la PlayList
	 * @return Lista con las canciones de la PlayList
	 */
	public List<Cancion> getPlayList(){
		return new LinkedList<Cancion>(playList); 
	}

	public Cancion getCancion(String nombre) {	
		return playList.stream()
				.filter(c -> c.getTitulo().equals(nombre))
				.findFirst()
				.orElse(null);
	}
	
	
	/**
	 * Añade una canción a la PlayList
	 * Las canciones no se pueden repetir en la PlayList
	 * Si la canción ya está en la PlayList, se pone en la última posición
	 * @param cancion Canción a añadir
	 */
	public void addCancion(Cancion cancion) {
		int i = hasCancion(cancion.getID());
		if (i>=0) {
			playList.remove(i);
			playList.add(cancion);
		} else {
			playList.add(cancion);
		}
	}

	
	/**
	 * Añade una lista de canciones a la PlayList
	 * @param canciones Lista de canciones a añadir
	 */
	public void addCanciones(List<Cancion> canciones) {
		canciones.forEach(c -> addCancion(c));
	}
	
	/**
	 * Elimina una canción de la PlayList
	 * @param cancion Canción a eliminar
	 */
	public void removeCancion(Cancion cancion) {
		int i = hasCancion(cancion.getID());
		if(i>=0)playList.remove(i);
	}
	
	/**
	 * Elimina una canción de la PlayList
	 * @param idCancion Identificador de la canción a eliminar
	 * @return true si la canción se ha eliminado, false en caso contrario
	 */
	public boolean removeCancion(int idCancion) {
		return playList.removeIf(c -> c.getID() == idCancion);
	}

	/**
	 * Comprueba si una canción está en la PlayList
	 * @param idCancion Identificador de la canción a comprobar
	 * @return Posición de la canción en la PlayList, -1 si no está
	 */
	
	public int hasCancion(int idCancion) {
		return IntStream.range(0, playList.size())
			.filter(i -> playList.get(i).getID() == idCancion)
			.findFirst()
			.orElse(-1);
	}

	/**
	 * Elimina todas las canciones de la PlayList
	 */

	public void removeAllCanciones() {
		playList.clear();
	}

	/**
	 * Comprueba si la PlayList está vacía
	 * @return true si la PlayList está vacía, false en caso contrario
	 */
	
	public boolean isEmpty() {
		return playList.size() == 0;
	}

	/**
	 * Elimina la primera canción de la PlayList
	 * Si la PlayList está vacía, no hace nada
	 */

	public void removeFirst(){

		if(playList.size() > 0) {
			playList.remove(0);
		}
	}

	/**
	 * Formatea la PlayList en formato String
	 * @return String con la información de la PlayList
	 */

	@Override
	public String toString() {
		return "PlayList [id=" + id + ", nombre=" + nombre + ", getNumCanciones()="
				+ getNumCanciones() + ", PlayList=" + playList  + "]"; 
	}

	/**
	 * Devuelve la siguiente canción de la PlayList
	 * @param cancion Canción actual
	 * @return Siguiente canción de la PlayList
	 */

	public Cancion getSiguienteCancion(Cancion cancion) {
		boolean found = false;
    	for (Cancion c : playList) {
			if (found) {
				return c;
			}
			if (c.getID() == cancion.getID()) {
				found = true;
			}
    	}
		return found ? playList.get(0) : null;
	}

	/**
	 * Devuelve la canción anterior de la PlayList
	 * @param cancion Canción actual
	 * @return Canción anterior de la PlayList
	 */
	public Cancion getAnteriorCancion(Cancion cancion) {
		Cancion preCancion = playList.get(playList.size() - 1);
		for (Cancion c : playList) {
			if (c.getID() == cancion.getID()) {
				return preCancion;
			}
			preCancion = c;
		}
		return null;
	}

	/**
	 * Elimina las canciones que no están en la lista de canciones
	 * @param idCanciones Lista de identificadores de las canciones a mantener
	 */

    public void removeNotInclued(List<Integer> idCanciones) {
		if(idCanciones != null && !idCanciones.isEmpty()){
			playList.removeIf(c -> !idCanciones.contains(c.getID()));
		}
			
    }

	
	
}