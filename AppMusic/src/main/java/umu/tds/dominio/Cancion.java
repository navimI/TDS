package umu.tds.dominio;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Cancion {
	//ATRIBUTOS

	private int ID; 		
	private final String titulo;
	private int numReproducciones;
	private final String estilo;
	private final List<String> listaInterpretes;
	private Boolean favorita;
	// URL es un atributo calculado por el get
	
	//CONSTRUCTORES
	public Cancion(String titulo, String estilo, String ... interpretes) {
		this.titulo = titulo;
		this.estilo = estilo;
		this.listaInterpretes = new LinkedList<String>(Arrays.asList(interpretes));
		this.numReproducciones = 0;
		this.favorita = false;
	}
	
	public Cancion(String titulo, String estilo, List<String>interpretes) {
		this.titulo = titulo;
		this.estilo = estilo;
		this.listaInterpretes = new LinkedList<String>(interpretes);
		this.numReproducciones = 0;
		this.favorita = false;
	}
	
	public Cancion(String titulo, String estilo) {
		this.titulo = titulo;
		this.estilo = estilo;
		this.listaInterpretes = new LinkedList<String>();
		this.listaInterpretes.add("Anonimo");
		this.numReproducciones = 0;
		this.favorita = false;
	}

	//MÉTODOS

	public String getTitulo() {
		return titulo;
	}
	
	public Boolean esFavorita() {
		return favorita;
	}
	
	public void hacerFavorita() {
		this.favorita = true;
	}

	public void quitarFavorita() {
		this.favorita = false;
	}
	
	//No existe un método setTitulo() porque no tiene sentido poder editar el titulo una vez que la cancion ya esta creada

	public String getRutaFichero() {
		/* String url = estilo + "/";
		url += listaInterpretes.get(0);
		for (int i = 1; i < listaInterpretes.size(); i++) {
			url += "&";
			url += listaInterpretes.get(i);
		}
		url += "-" + titulo;
		url += ".mp3"; */

		String url = estilo + "/";
		url += listaInterpretes.stream().collect(Collectors.joining("&"));
		url += "-" + titulo + ".mp3";

		return url;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}
	
	public void setNumReproducciones(int n) {
		this.numReproducciones = n;
	}

	public List<String> getListaInterpretes() {
		return new LinkedList<String>(listaInterpretes);  //copia defensiva para evitar aliasing
	}
	
	public int getID(){ //PROPIEDAD CALCULADA
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	

	public String getEstilo() {
		return estilo;
	}
	// Los unicos metodos que permiten la modificacion de valores son los que se encargan de la reproduccion de la cancion
	// y el setID, que es necesario para la persistencia de la cancion en la base de datos
	// El resto de metodos son de consulta y no permiten la modificacion de los valores de los atributos
	// por eso los definimos al principio como final
	// para asegurar que el atributo listaInterpretes no pueda ser modificado desde fuera de la clase
	// se devuelve una copia defensiva en el metodo getListaInterpretes()

	

	

	
	public void addReproduccion() {   //para añadir  una reproducción al vídeo
		this.numReproducciones = numReproducciones ++;
	}

	@Override
	public String toString() {
		return "Cancion [ID=" + ID + ", url=" + getRutaFichero() + ", titulo=" + titulo + ", numReproducciones=" + numReproducciones
				+ ", listaInterpretes=" + listaInterpretes + "]";
	}
	
	
	
	/*TODO implementar reproducir Cancion
	 * public void reproducirCancion(){
	 * 	addReproduccion();
	 * 	play();*/
	
}

