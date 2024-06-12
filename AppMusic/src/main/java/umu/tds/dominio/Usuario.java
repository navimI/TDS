package umu.tds.dominio;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.itextpdf.text.DocumentException;

import umu.tds.utils.GeneradorPDF;

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
	private static final int TOPCANCIONES = 10;


	private List<PlayList> playListUsuario;
	private PlayList recientes;
	private static final String RECIENTES = "Canciones Recientes";
	//TO-DO: Mirar si es interesante dejar esto asi o que se crea la lista de canciones y se almecene
	
	
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

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechaNacim() {
		return fechaNacim;
	}
	
	public LocalDate getDateNacimiento() {

		return LocalDate.parse(fechaNacim, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public boolean isPremiun() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public List<PlayList> getPlayListUsuario() {
		return new LinkedList<PlayList>(playListUsuario);
	}
	
	public boolean addUserPlaylists(List<PlayList> listaC) {
		return this.playListUsuario.addAll(listaC);
	}
	
	public boolean addPlayList(PlayList listaC) {
		return this.playListUsuario.add(listaC);
	}
	
	public PlayList getPlayListNamed(String playListName) {
		return playListUsuario.stream()
				.filter(pl -> pl.getNombre().equals(playListName))
				.findFirst()
				.orElse(null);
	}
	
	public Descuento obtenerDescuento() {
		return Descuento.descuetos().stream()
				.filter(d -> d.isApplicable(this))
				.reduce((d1,d2) -> d1.precioFinal() < d2.precioFinal() ? d1 : d2)
				.orElse(new NoDescuento());
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre  + ", email=" + email + ", user="
				+ user + ", fechaNacim=" + fechaNacim + ", password=" + password + ", premium=" + premium
				+ ", playListUsuario=" + playListUsuario + "]";
	}
	
	
	/*
	 * TODO implementar buscarCancion public List<Cancion> buscarCancion() { Canciones }
	 */

	public List<Cancion> buscarCancion(String nombreCancion) {
    		List<Cancion> cancionesEncontradas = new LinkedList<>();
    		for (PlayList lista : playListUsuario) {
    		    for (Cancion cancion : lista.getPlayList()) {
     		       if (cancion.getTitulo().equalsIgnoreCase(nombreCancion)) {
        		 	cancionesEncontradas.add(cancion);
        		    }
      		  }
   	 	}

    		return cancionesEncontradas;
		}


	/*
	 * TODO implementar topTen() public void topTen(){ if(!esPremium){ return; }
	 * 
	 * 
	 * }
	 */

	

    public boolean isPassword(String password) {
        return this.password.equals(password);
    }
    
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



	/*
	 * TODO implementar ver Cancion public void vervido() lanzar cancion actualizar
	 * ultimos canciones
	 */
	/*
	 * Para obtener el elemento en la posicion i es necesario recorrer los i-1 nodos
	 * precedente <- cosa de teoria poo Para que sea eficiente la implementacion de
	 * los ultimos canciones leidos utilizamos linkedlist que nos permite mantener en
	 * orden los canciones vistos del mas reciente al mas antiguo, el coste de las
	 * operaciones de insertar y eliminar de los extremos es de orden constante
	 * 
	 */

    
	 public boolean actualizarRecientes(Cancion cancion) {
		if (recientes.getPlayList().size() < ULTCANCIONES) {
			recientes.addCancion(cancion);
			return true;
		}
		else if (recientes.getPlayList().size() == ULTCANCIONES) {
			if (-1==recientes.hasCancion(cancion.getID())) {
				recientes.getPlayList().remove(ULTCANCIONES-1);
			}
			recientes.addCancion(cancion);
			return true;
		}
		return false;
	}
    


    public void removePlayListUsuarios(PlayList playlist) {
        if (playlist != null && playListUsuario.contains(playlist)) {
            playListUsuario.remove(playlist);
        }
    }
    
}