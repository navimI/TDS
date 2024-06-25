package umu.tds.dominio;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.DocumentException;

import umu.tds.utils.GeneradorPDF;

/**
 * Clase que representa un Usuario
 * @version 1.0
 * @author Ivan Garcia Alcaraz
 */

public class Usuario {

	private int id;
	private final String nombre;

	private String email;
	private String user;
	private final String fechaNacim;
	private String password;
	private boolean premium;

	static final double PRECIO_PREMIUM = 15;
	private static final int ULTCANCIONES = 5;


	private List<PlayList> playListUsuario;
	private static final String RECIENTES = "Canciones Recientes";
	
	/**
	 * Constructor de la clase Usuario
	 * @param nombre Nombre del Usuario
	 * @param email Email del Usuario
	 * @param user Nombre de usuario del Usuario
	 * @param password Contraseña del Usuario
	 * @param fechaNacim Fecha de nacimiento del Usuario
	 */
	
	public Usuario(String nombre, String email, String user, String password, String fechaNacim) {
		this.id = 0;
		this.nombre = nombre;

		this.fechaNacim = fechaNacim;
		this.email = email;
		this.user = user;
		this.password = password;
		this.premium = false;
		this.playListUsuario = new LinkedList<PlayList>();
		PlayList recientes = new PlayList(RECIENTES);
		this.playListUsuario.add(recientes); 
	}

	/**
	 * Constructor de la clase Usuario
	 * @param id Identificador del Usuario
	 * @param nombre Nombre del Usuario
	 * @param email Email del Usuario
	 * @param user Nombre de usuario del Usuario
	 * @param password Contraseña del Usuario
	 * @param fechaNacim Fecha de nacimiento del Usuario
	 * @param premium Indica si el Usuario es premium
	 * @param playListUsuario Lista de PlayList del Usuario
	 */

	public Usuario(int id, String nombre, String email, String user, String password, String fechaNacim, boolean premium, List<PlayList> playListUsuario) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.user = user;
		this.password = password;
		this.fechaNacim = fechaNacim;
		this.premium = premium;
		this.playListUsuario = playListUsuario;
	}

	/**
	 * Devuelve el identificador del Usuario
	 * @return Identificador del Usuario
	 */
	
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del Usuario
	 * @param id Identificador del Usuario
	 */

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Devuelve el nombre del Usuario
	 * @return Nombre del Usuario
	 */

	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Devuelve el email del Usuario
	 * @return Email del Usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el email del Usuario
	 * @param email Email del Usuario
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve el nombre de usuario del Usuario
	 * @return Nombre de usuario del Usuario
	 */

	public String getUser() {
		return user;
	}

	/**
	 * Establece el nombre de usuario del Usuario
	 * @param user Nombre de usuario del Usuario
	 */

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Devuelve la contraseña del Usuario
	 * @return Contraseña del Usuario
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * Establece la contraseña del Usuario
	 * @param password Contraseña del Usuario
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Devuelve la fecha de nacimiento del Usuario
	 * @return Fecha de nacimiento del Usuario
	 */

	public String getFechaNacim() {
		return fechaNacim;
	}

	/**
	 * Devuelve la fecha de nacimiento del Usuario
	 * @return Fecha de nacimiento del Usuario
	 */
	
	public LocalDate getDateNacimiento() {

		return LocalDate.parse(fechaNacim, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * Devuelve si el Usuario es premium
	 * @return Indica si el Usuario es premium
	 */

	public boolean isPremiun() {
		return premium;
	}

	/**
	 * Establece si el Usuario es premium
	 * @param premium Indica si el Usuario es premium
	 */

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	/**
	 * Devuelve las PlayList del Usuario
	 * @return Lista de PlayList del Usuario
	 */

	public List<PlayList> getPlayListUsuario() {
		return new LinkedList<PlayList>(playListUsuario);
	}

	/**
	 * Devuelve las PlayList del Usuario que no sean la de canciones recientes
	 * @return Lista de PlayList del Usuario que no sean la de canciones recientes
	 */
	
	public List<PlayList> getUsablePlayList(){
		return new LinkedList<PlayList>(playListUsuario.stream().filter(pl -> !pl.getNombre().equals(RECIENTES)).collect(Collectors.toList()));
	}

	/**
	 * Añade una PlayList al Usuario
	 * @param listaC PlayList a añadir
	 * @return Indica si se ha añadido la PlayList
	 */
	
	public boolean addPlayList(PlayList listaC) {
		return this.playListUsuario.add(listaC);
	}

	/**
	 * Devuelve una PlayList del Usuario
	 * @param playListName Nombre de la PlayList a devolver
	 * @return Devuelve la PlayList con el nombre indicado, si no existe devuelve null
	 */
	
	public PlayList getPlayListNamed(String playListName) {
		return playListUsuario.stream()
				.filter(pl -> pl.getNombre().equals(playListName))
				.findFirst()
				.orElse(null);
	}

	/**
	 * Obtiene el descuento aplicable al Usuario
	 * @return Descuento aplicable al Usuario
	 */
	
	public Descuento obtenerDescuento() {
		return Descuento.descuetos().stream()
				.filter(d -> d.isApplicable(this))
				.reduce((d1,d2) -> d1.precioFinal() < d2.precioFinal() ? d1 : d2)
				.orElse(new NoDescuento());
	}

	/**
	 * Formatea el usuario en formato String
	 * @return String con la informacion del usuario
	 */

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre  + ", email=" + email + ", user="
				+ user + ", fechaNacim=" + fechaNacim + ", password=" + password + ", premium=" + premium
				+ ", playListUsuario=" + playListUsuario + "]";
	}

	/**
	 * Indica si la contraseña es correcta
	 * @param password Contraseña a comprobar
	 * @return Indica si la contraseña es correcta
	 */
	

    public boolean isPassword(String password) {
        return this.password.equals(password);
    }

	/**
	 * Genera un fichero PDF con la informacion del Usuario
	 * @param rutaFichero Ruta del fichero a generar
	 * @return true si se ha generado el fichero, false en caso contrario
	 */
    
    public boolean generarPDF(String rutaFichero) {
    	GeneradorPDF generador = new GeneradorPDF();
    	try {
			generador.generarFichero(rutaFichero, getNombre(), getPlayListUsuario());
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
    }

	/**
	 * Devuelve la PlayList de canciones recientes
	 * @return PlayList de canciones recientes
	 */

	public PlayList getRecientes() {
		return playListUsuario.stream()
				.filter(pl -> pl.getNombre().equals(RECIENTES))
				.findFirst()
				.orElse(null);
	}

	/**
	 * Actualiza la PlayList de canciones recientes
	 * @param cancion Cancion a añadir
	 * @return true si se ha actualizado la PlayList, false en caso contrario
	 */

    
	 public boolean actualizarRecientes(Cancion cancion) {
		if (getRecientes().getNumCanciones() < ULTCANCIONES) {
			getRecientes().addCancion(cancion);
			

			return true;
		}
		else if (getRecientes().getNumCanciones() == ULTCANCIONES) {
			int i = getRecientes().hasCancion(cancion.getID());
			if(i >= 0) {
				getRecientes().addCancion(cancion);
			}else {
				getRecientes().removeFirst();
				getRecientes().addCancion(cancion);
			}
			
			return true;
		}
		return false;
	}
    
	/**
	 * Elimina una PlayList del Usuario
	 * @param playlist PlayList a eliminar
	 */
    public void removePlayList(PlayList playlist) {
        if (playlist != null && playListUsuario.contains(playlist)) {
            playListUsuario.remove(playlist);
        }
    }
    
}