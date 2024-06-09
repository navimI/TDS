package umu.tds.dominio;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Usuario {

	private int id;
	private final String nombre;

	private String email;
	private String user;
	private final String fechaNacim;
	private String password;
	private boolean premium;

	private static final int ULTCANCIONES = 5;
	private static final int TOPCANCIONES = 10;

	private List<PlayList> playListUsuario;
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
		PlayList aux = new PlayList(RECIENTES);
		this.playListUsuario.add(aux);
		
		 
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

	public boolean isPremiun() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public List<PlayList> getPlayListUsuario() {
		return new LinkedList<PlayList>(playListUsuario);
	}
	
	public boolean addPlayListUsuarios(List<PlayList> listaC) {
		return this.playListUsuario.addAll(listaC);
	}
	
	public boolean addPlayListUsuarios(PlayList listaC) {
		return this.playListUsuario.add(listaC);
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

	public void topTen() {
    	if (!premium) {
      		  //El usuario no es premium, se muestra:
			  //Hay que cambiar esto por un mensaje en la interfaz
        System.out.println("Debes ser usuario premium para acceder al topTen.");
        return;
    	}

    		//Todas las canciones:
    		List<Cancion> todasLasCanciones = new LinkedList<>();
    		for (PlayList lista : playListUsuario) {
      		  todasLasCanciones.addAll(lista.getPlayList());
   		 }

   	 //Ordena las canciones:
 		 todasLasCanciones.sort(Comparator.comparingInt(Cancion::getNumReproducciones).reversed());

  	  //Muestra topTen
    		int limite = Math.min(TOPCANCIONES, todasLasCanciones.size());
    		System.out.println("Top Ten:");

   		 for (int i = 0; i < limite; i++) {
     		   Cancion cancion = todasLasCanciones.get(i);
      		  System.out.println((i + 1) + ". " + cancion.getTitulo() + " - " + cancion.getListaInterpretes());
   		 }
		}

    public boolean isPassword(String password) {
        return this.password.equals(password);
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

	/*
	 * TODO implementar actualiza ultimos canciones
	 * 
	 * private void actualizaultimosCanciones(){ quita del final el cancion mas antiguo
	 * inserta al principio el mas receciente
	 * 
	 * 
	 */
    
    // public boolean actualizarRecientes(Cancion cancion) {
    //     z
    // }


    public void removePlayListUsuarios(PlayList playlist) {
        if (playlist != null && playListUsuario.contains(playlist)) {
            playListUsuario.remove(playlist);
        }
    }
    
}