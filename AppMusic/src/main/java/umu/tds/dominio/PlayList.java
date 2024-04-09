package umu.tds.dominio;


import java.util.LinkedList;
import java.util.List;

public class PlayList {
	//Properties	
	private int id;
	String nombre;
	List<Cancion> PlayList;
	
	//Constructor
	public PlayList(String nombre) {
		id = 0;
		this.nombre = nombre;
		this.PlayList = new LinkedList<Cancion>();
	}
	
	public PlayList(String nombre, List<Cancion> canciones) {
		id = 0;
		this.nombre = nombre;
		this.PlayList = new LinkedList<Cancion>(canciones);
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
		return PlayList.size();
	}
	
	public List<Cancion> getPlayList(){
		return new LinkedList<Cancion>(PlayList); 
	}
	
	public void addCanciones(Cancion cancion) {
		PlayList.add(cancion);
	}
	
	public void addCanciones(List<Cancion> canciones) {
		PlayList.addAll(canciones);
	}
	
	public void removeCanciones(Cancion cancion) {
		PlayList.remove(cancion);
	
	}

	@Override
	public String toString() {
		return "PlayList [id=" + id + ", nombre=" + nombre + ", getNumCanciones()="
				+ getNumCanciones() + ", PlayList=" + PlayList  + "]";
	}
	
	/*TODO implementar reproducir lista
	 * public void reproducirLista(){
	 * listaCanciones.stream().forEach(l -> cancion.reproducirCancion())*/
	
}