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
	
	public void removeCanciones(Cancion cancion) {
		playList.remove(cancion);
	 
	}

	@Override
	public String toString() {
		return "PlayList [id=" + id + ", nombre=" + nombre + ", getNumCanciones()="
				+ getNumCanciones() + ", PlayList=" + playList  + "]"; 
	}

	public Cancion getSiguienteCancion(Cancion cancion) {
		return null;

	}

	public Cancion getAnteriorCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*TODO implementar reproducir lista
	 * public void reproducirLista(){
	 * listaCanciones.stream().forEach(l -> cancion.reproducirCancion())*/
	
}