package umu.tds.dominio;


import java.util.LinkedList;
import java.util.List;

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
	
	public void addCanciones(Cancion cancion) {
		playList.add(cancion);
	}
	
	public void addCanciones(List<Cancion> canciones) {
		playList.addAll(canciones);
	}
	
	public void removeCancion(Cancion cancion) {
		playList.remove(cancion);
	 
	}

	public void removeAllCanciones() {
		playList.clear();
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

	
	
}