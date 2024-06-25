package umu.tds.dominio;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Clase que representa una cancion
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */

public class Cancion {
	//ATRIBUTOS

	private int ID; 		
	private String titulo;
	private int numReproducciones;
	private String estilo;
	private List<String> listaInterpretes;
	// URL es un atributo calculado por el get
	
	//CONSTRUCTORES
	/**
	 * Constructor de la clase Cancion
	 */
	public Cancion() {
		this.numReproducciones = 0;
	}

	/**
	 * Constructor de la clase Cancion
	 * @param titulo Titulo de la cancion
	 * @param estilo Estilo de la cancion
	 * @param interpretes Lista de interpretes de la cancion
	 */
	
	public Cancion(String titulo, String estilo, String ... interpretes) {
		this.titulo = titulo;
		this.estilo = estilo;
		this.listaInterpretes = new LinkedList<String>(Arrays.asList(interpretes));
		this.numReproducciones = 0;
	}

	/**
	 * Constructor de la clase Cancion
	 * @param titulo Titulo de la cancion
	 * @param estilo Estilo de la cancion
	 * @param interpretes Lista de interpretes de la cancion
	 */
	
	public Cancion(String titulo, String estilo, List<String>interpretes) {
		this.titulo = titulo;
		this.estilo = estilo;
		this.listaInterpretes = new LinkedList<String>(interpretes);
		this.numReproducciones = 0;

	}

	/**
	 * Constructor de la clase Cancion
	 * @param titulo Titulo de la cancion
	 * @param estilo Estilo de la cancion
	 */
	
	public Cancion(String titulo, String estilo) {
		this.titulo = titulo;
		this.estilo = estilo;
		this.listaInterpretes = new LinkedList<String>();
		this.listaInterpretes.add("Anonimo");
		this.numReproducciones = 0;

	}

	//MÉTODOS

	/**
	 * Devuelve el titulo de la cancion
	 * @return Titulo de la cancion
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Indica si la cancion contiene un interprete
	 * @param interprete Interprete a buscar
	 * @return true si la cancion contiene el interprete, false en caso contrario
	 */
	 
	public boolean containsInterprete(String interprete) {
		return listaInterpretes.contains(interprete);
	}

	/**
	 * Devuelve la ruta del fichero de la cancion
	 * @return Ruta del fichero de la cancion
	 */
	public String getRutaFichero() {
		
		String url = estilo + "/";
		url += listaInterpretes.stream().collect(Collectors.joining("&"));
		url += "-" + titulo + ".mp3";

		return url;
	}

	/**
	 * Devuelve el numero de reproducciones de la cancion
	 * @return Numero de reproducciones de la cancion
	 */

	public int getNumReproducciones() {
		return numReproducciones;
	}

	/**
	 * Establece el numero de reproducciones de la cancion
	 * @param n Numero de reproducciones de la cancion
	 */
	
	public void setNumReproducciones(int n) {
		this.numReproducciones = n;
	}
	/**
	 * Añade una reproduccion a la cancion
	 */
	public void addReproduccion() {
		numReproducciones ++;
	}

	/**
	 * Devuelve la lista de interpretes de la cancion
	 * @return Lista de interpretes de la cancion
	 */

	public List<String> getListaInterpretes() {
		List<String> listaInterpretes = new LinkedList<String>();
		this.listaInterpretes.forEach(interprete -> listaInterpretes.add(interprete));
		return listaInterpretes;
	}

	/**
	 * Devuelve el ID de la cancion
	 * @return ID de la cancion
	 */
	
	public int getID(){
		return ID;
	}

	/**
	 * Establece el ID de la cancion
	 * @param ID ID de la cancion
	 */
	
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Comprueba si dos canciones son iguales
	 * @param obj Cancion a comparar
	 * @return true si las canciones son iguales, false en caso contrario
	 */

	public boolean cancionesIguales(Object obj) {
		
		Cancion other = (Cancion) obj;
		return Objects.equals(getTitulo(), other.getTitulo()) &&
			Objects.equals(getEstilo(), other.getEstilo()) &&
			Objects.equals(getListaInterpretes(), other.getListaInterpretes());
	}
	
	/**
	 * Devuelve el estilo de la cancion
	 * @return Estilo de la cancion
	 */

	public String getEstilo() {
		return estilo;
	}

	/**
	 * Devuelve los interpretes de la cancion en formato String
	 * @return Interpretes de la cancion en formato String
	 */

	public String getStringInterpretes(){
		String interpretes = "";
		interpretes+=listaInterpretes.stream().collect(Collectors.joining(", "));
		return interpretes;
	}
	// Los unicos metodos que permiten la modificacion de valores son los que se encargan de la reproduccion de la cancion
	// y el setID, que es necesario para la persistencia de la cancion en la base de datos
	// El resto de metodos son de consulta y no permiten la modificacion de los valores de los atributos
	// por eso los definimos al principio como final
	// para asegurar que el atributo listaInterpretes no pueda ser modificado desde fuera de la clase
	// se devuelve una copia defensiva en el metodo getListaInterpretes()
	
	//METODOS SOBREESCRITOS

	/**
	 * Formatea la cancion en formato String
	 * @return String con la informacion de la cancion
	 */

	@Override
	public String toString() {
		return "Cancion [ID=" + ID + ", url=" + getRutaFichero() + ", titulo=" + getTitulo() + ", numReproducciones=" + getNumReproducciones()
				+ ", listaInterpretes=" + getStringInterpretes() + "]";
	}
	
	
}

