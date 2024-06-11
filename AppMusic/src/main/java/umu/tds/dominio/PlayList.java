package umu.tds.dominio;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class PlayList {
	//Properties	
	private int id;
	String nombre;
	List<Cancion> playList;
	
	//Constructor
	public PlayList(String nombre) {
		id = 0;
		this.nombre = nombre;
		this.playList = new LinkedList<Cancion>();
	}
	
	public PlayList(String nombre, List<Cancion> canciones) {
		id = 0;
		this.nombre = nombre;
		this.playList = new LinkedList<Cancion>(canciones);
	}
	
	//Methods
	
	public int getId() {
		return id;
	}
	
	public void setId(int codigo) {
		this.id = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getNumCanciones() {
		return playList.size();
	}
	
	public List<Cancion> getPlayList(){
		return new LinkedList<Cancion>(playList); 
	}
	
	
	//Las canciones que estan al final de la lista son las canciones añadidas recientemente
	//El orden de la playlist es de más antiguedad en la introducción de la lista a la más reciente.
	public void addCancion(Cancion cancion) {
		int i = hasCancion(cancion.getID());
		if (i>=0) {
			playList.remove(i);
			playList.add(cancion);
		} else {
			playList.add(cancion);
		}
	}
	
	// Usamos el stream que nos añade todas las canciones de la lista pasada por el argumento
	// haciendo uso de la función definida de añadir canción
	public void addCanciones(List<Cancion> canciones) {
		canciones.forEach(c -> addCancion(c));
	}
	
	public void removeCancion(Cancion cancion) {
		int i = hasCancion(cancion.getID());
		if(i>=0)playList.remove(i);
	}
	
	public boolean removeCancion(int idCancion) {
		return playList.removeIf(c -> c.getID() == idCancion);
	}
	
	public int hasCancion(int idCancion) {
		return IntStream.range(0, playList.size())
			.filter(i -> playList.get(i).getID() == idCancion)
			.findFirst()
			.orElse(-1);
	}

	public void removeAllCanciones() {
		playList.clear();
	}
	
	public boolean isEmpty() {
		return playList.size() == 0;
	}

	@Override
	public String toString() {
		return "PlayList [id=" + id + ", nombre=" + nombre + ", getNumCanciones()="
				+ getNumCanciones() + ", PlayList=" + playList  + "]"; 
	}

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

	public void ponerPrimera(Cancion cancion) {
		addCancion(cancion);
	}
	
}